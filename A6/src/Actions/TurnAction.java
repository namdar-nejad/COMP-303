package Actions;

import Calculation.CalculationVisitor;
import BaseLogic.IllegalRobotStateException;
import Robot.Robot;

import java.util.HashMap;

/*
    Note: I decided to create this BasicAction instead of just
    a 90 degrees BasicAction given that it has more functionality.
 */
/**
 * Flyweight class that represents a Action object used to
 * "Turn some degrees to the right or left: ensure the arm is retracted, then turn clockwise or anti-clockwise"
 * (+ for clockwise and - for anticlockwise).
 */
public final class TurnAction extends BasicAction {

    // the angle to turn by
    private final double A_ANGLE;

    // cache to store the created objects
    private static HashMap<Double, TurnAction> aFactory = new HashMap<Double, TurnAction>();

    /**
     * private constructor for the TurnAction class
     * @param pAngle angle to turn by (+ for clockwise and - for anticlockwise)
     */
    private TurnAction(double pAngle){
        A_ANGLE = pAngle;
    }

    /**
     * Turn with the given angle. <p>
     * Process: <p>
     * 1. Check if the arm is retracted <p>
     * 2. Turn the robot by calling turnRobot() <p>
     *
     * @param pBot Robot to execute the Action on
     * @throws IllegalRobotStateException if the arm is not retracted
     */
    @Override
    protected void executeBasicAction(Robot pBot) throws IllegalRobotStateException {
        if(! pBot.getArmState().equals(Robot.ArmState.RETRACTED)){
            throw new IllegalRobotStateException("Arm must be retracted for TurnAction");
        }
        else{
            pBot.turnRobot(A_ANGLE);
        }
    }

    /**
     * @return the angle of turn
     */
    public double getAngle(){
        return this.A_ANGLE;
    }

    /**
     * method to get an instance of a TurnAction with pAngle
     * @param pAngle the angle to turn by in the TurnAction
     * @return TurnAction with pAngle
     */
    public static TurnAction getInstance(double pAngle){
        if(aFactory.containsKey(pAngle)) {
            return aFactory.get(pAngle);
        }
        else{
            TurnAction pNewAction = new TurnAction(pAngle);
            aFactory.put(pAngle, pNewAction);

            return pNewAction;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double acceptCalVisitor(CalculationVisitor pVisitor) {
        return pVisitor.visit(this);
    }
}