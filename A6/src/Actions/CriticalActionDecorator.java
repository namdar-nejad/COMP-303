package Actions;

import Robot.Robot;
import Calculation.CalculationVisitor;
import BaseLogic.IllegalRobotStateException;

import java.util.Objects;

/**
 * Decorator class, used to make any action critical "force the battery to charge before executing the Action".
 */
public class CriticalActionDecorator extends Action {

    // Action to make critical
    private final Action A_ACTION;

    /**
     * constructor of the CriticalActionDecorator class
     * @param pAction the Action to make critical
     */
    public CriticalActionDecorator(Action pAction){
        this.A_ACTION = pAction;
    }

    /**
     * method to execute the Action but critically (force the battery to charge before executing the Action).
     * @param pBot Robot to execute the Action on
     * @throws IllegalRobotStateException If any of the preconditions of a given Action is not respected (propagated by execute())
     */
    @Override
    protected void executeAction(Robot pBot) throws IllegalRobotStateException {
        // do battery recharge
        pBot.rechargeBattery();

        // execute action
        A_ACTION.executeAction(pBot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double acceptCalVisitor(CalculationVisitor pVisitor) {
        return A_ACTION.acceptCalVisitor(pVisitor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CriticalActionDecorator that = (CriticalActionDecorator) o;
        return Objects.equals(A_ACTION, that.A_ACTION);
    }

    @Override
    public int hashCode() {
        return Objects.hash(A_ACTION);
    }

}
