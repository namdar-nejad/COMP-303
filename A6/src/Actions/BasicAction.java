package Actions;

import BaseLogic.IllegalRobotStateException;
import Robot.Robot;

/**
    represents an abstract class, all BasicActions must extend this class.
    BasicAction: These basic actions are the fundamental building blocks for creating programs,
    and clients are not expected to have to create new basic actions.
 */
public abstract class BasicAction extends Action {

    /**
     * method to execute the concrete Action.
     * The method calls the preExecutionCheck() and then calls executeBasicAction()
     * defined in the concrete classes. After the execution of the BasicAction
     * has been completed the battery level of the Robot gets updated.
     *
     * @param pBot Robot to execute the Action on
     * @throws IllegalRobotStateException If the Action doesn't have it's necessary prerequisites satisfied
     *                                    - propagate from executeBasicAction()
     */
    protected final void executeAction(Robot pBot) throws IllegalRobotStateException {

        // do preExecution check
        preExecutionCheck(pBot);

        // execute the BasicAction
        this.executeBasicAction(pBot);

        // update battery level
        pBot.updateBatteryLevel();
    }

    /**
     * private method to be completed before the execution of a BasicAction.
     * Checks if the battery of the pBot is less than 5% and recharges the battery if it is.
     *
     * @param pBot Robot to check
     */
    private void preExecutionCheck(Robot pBot) {
        // check the state of the battery
        if (pBot.getBatteryCharge() <= 5) {
            // recharge if <= 5
            pBot.rechargeBattery();
        }
    }

    /**
     * method to execute the concrete BasicAction
     *
     * @param pBot Robot to execute the Action on
     * @throws IllegalRobotStateException If the Action doesn't have it's necessary prerequisites satisfied
     */
    protected abstract void executeBasicAction(Robot pBot) throws IllegalRobotStateException;


}

