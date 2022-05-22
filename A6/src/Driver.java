import BaseLogic.IllegalRobotStateException;
import BaseLogic.Logger;
import BaseLogic.LoggingStrategy;
import Calculation.*;
import Programs.Program;
import Robot.*;
import Actions.*;


public class Driver {
    public static void main(String args[]) throws IllegalRobotStateException {

        // creating two Robot instance
        Robot robot1 = new WallE();
        Robot robot2 = new WallE();

        // creating instances of basic actions

        BasicAction moveBasic = MoveForwardAction.getInstance(100);     // moves 100 forward

        /** Note that we can turn with any angle we want.
         *  I decided to create this BasicAction instead of just
         *  a 90 degrees BasicAction given that it has more functionality.
         */
        BasicAction turn180Basic = TurnAction.getInstance(180);         // turns 180 degrees
        BasicAction turn90Basic = TurnAction.getInstance(90);           // turn 90 degrees
        BasicAction compactBasic = CompactObjectAction.getInstance();
        BasicAction emptyBasic = EmptyCompactorAction.getInstance();
        BasicAction grabBasic = GrabObjectAction.getInstance();
        BasicAction realiseBasic = ReleaseObjectAction.getInstance();

        // creating complex actions

        ComplexAction moveBack = new ComplexAction(turn180Basic, moveBasic);
        ComplexAction compactANDempty = new ComplexAction(realiseBasic, grabBasic, compactBasic, emptyBasic);
        ComplexAction circles = new ComplexAction(moveBasic, moveBack, moveBack, moveBack);
        ComplexAction makeSquare = new ComplexAction(moveBasic, turn90Basic, moveBasic, turn90Basic, moveBasic, turn90Basic, moveBasic);

        // make an action critical

        CriticalActionDecorator circlesCritical = new CriticalActionDecorator(circles);
        CriticalActionDecorator makeSquareCritical = new CriticalActionDecorator(makeSquare);

        // create visitors

        DistanceTraveledVisitor distanceCalculator = new DistanceTraveledVisitor();
        DegreesTurnedVisitor turnCalculator = new DegreesTurnedVisitor();

        // creating new LoggingStrategy

        /**
         * Like this you can define new LoggingStrategys.
         * Here the LoggingStrategy prints every log made on the console. */
        class PrintingLogStrategy implements LoggingStrategy {

            @Override
            public void log(Action pAction, Robot pBot) {
                System.out.println("-> Logged: "+ pAction.getClass().getName() + " action performed, battery level is " + pBot.getBatteryCharge());
            }
        }

        Logger.setLoggingStrategy(new PrintingLogStrategy());

        /**
         * Like this you can write a new visitors.
         * Here the visitor  to count the number of objects compacted in a program. */
        class NumCompactedVisitor implements CalculationVisitor {

            // we only compact object in the CompactObjectAction and only one object gets compacted
            @Override
            public double visit(CompactObjectAction pCompactAction) {
                return 1;
            }

            @Override
            public double visit(EmptyCompactorAction pEmptyAction) {
                return 0;
            }

            @Override
            public double visit(GrabObjectAction pGrabAction) {
                return 0;
            }

            @Override
            public double visit(MoveForwardAction pMoveAction) {
                return 0;
            }

            @Override
            public double visit(ReleaseObjectAction pReleaseAction) {
                return 0;
            }

            @Override
            public double visit(TurnAction pTurnAction) {
                return 0;
            }
        }

        NumCompactedVisitor numCompactCalculator = new NumCompactedVisitor();

        // make programs

        Program move_back_Program = new Program(moveBack);
        Program square_cricle_Program = new Program(makeSquareCritical, circlesCritical);
        Program compact_and_empty_Program = new Program(compactANDempty);

        // execute a single action

        /**
         * Note that you can execute a single Action too.
         */
        System.out.println("\nExecuting moveBack individually on Robot1.");
        moveBack.executeANDlog(robot1);

        System.out.println("\nExecuting makeSquare individually on Robot2.");
        makeSquare.executeANDlog(robot2);


        // execute the programs

        System.out.println("\nExecuting move_back_Program on Robot1:");
        move_back_Program.execute(robot1);

        System.out.println("\nExecuting square_cricle_Program on Robot2:");
        square_cricle_Program.execute(robot2);

        System.out.println("\nExecuting compact_and_empty_Program on Robot1:");
        compact_and_empty_Program.execute(robot1);

        // doing calculations

        System.out.println("\nDistance Calculation on move_back_Program: " + move_back_Program.calculate(distanceCalculator));
        System.out.println("Distance Calculation on square_cricle_Program: " + square_cricle_Program.calculate(distanceCalculator));
        System.out.println("Degrees Turned Calculation on move_back_Program: " + move_back_Program.calculate(turnCalculator));
        System.out.println("Degrees Turned Calculation on square_cricle_Program: " + square_cricle_Program.calculate(turnCalculator));
        System.out.println("Degrees NumCompacted Calculation on compact_and_empty_Program: " + compact_and_empty_Program.calculate(numCompactCalculator));

    }
}
