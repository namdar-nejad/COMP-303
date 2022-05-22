package BaseLogic;

import Actions.Action;
import Robot.Robot;

/**
 * Strategy to use for logging
 */
public interface LoggingStrategy {

    /**
     * method to add a log
     * @param pAction Action that was just executed
     * @param pBot Robot that the Action was just executed on
     */
    public void log(Action pAction, Robot pBot);
}
