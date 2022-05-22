package Actions;

import BaseLogic.IllegalRobotStateException;
import Robot.Robot;
import Calculation.CalculationVisitor;
import BaseLogic.Logger;

/**
    represent an abstract Action class, all Action must extend this class
    Action objects must be immutable.
 */
public abstract class Action {

    /**
     * Method to execute an Action.
     * The only way of executing an Action from other packages.
     * This methods calls the executeAction() of the concede implementation of the Action in the subclasses
     * and logs the Action by calling addLog() after completion of the concrete Action. <p>
     *
     * @param pBot Robot to execute the Action on
     * @throws IllegalRobotStateException If the Action doesn't have it's necessary prerequisites satisfied
     * - propagated from executeAction()
     * @pre pBot != null
     */
    public final void executeANDlog(Robot pBot) throws IllegalRobotStateException {
        assert pBot != null;

        executeAction(pBot);

        Logger.log(this, pBot);
    }

    /**
     * Method to execute the concrete Action, called by execute()
     * @param pBot Robot to execute the Action on
     * @throws IllegalRobotStateException If the Action doesn't have it's necessary prerequisites satisfied
     */
    protected abstract void executeAction(Robot pBot) throws IllegalRobotStateException;

    /**
     * Method to accept a CalculationVisitor, the correct Action object will be passed to the CalculationVisitor and the
     * corresponding overloaded method for the Action will be used for the calculation process.
     * @param pVisitor CalculationVisitor to use for calculation
     * @return the double resulting from the calculation implemented in the CalculationVisitor for the given Action object
     * @pre pVisitor != null
     */
    public abstract double acceptCalVisitor(CalculationVisitor pVisitor);

}