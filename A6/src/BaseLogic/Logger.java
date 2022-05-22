package BaseLogic;

import Actions.Action;
import Robot.Robot;

/**
 * Static class used to log every Actions preformed
 * Every action executed on any robot or through any program from the time the application in run will be logged.
 */
public final class Logger {

    private static LoggingStrategy aLoggingStrategy = new DefualtLoggingStategy();

    // it's a static class
    private Logger(){
    }

    /**
     * Method used to add a log to aLogs, called by the execute method in the Action abstract class.
     * Will call the log() method of the latest LoggingStrategy provided.
     * @param pAction Action that was preformed
     * @param pBot Robot that the action was preformed on
     */
    public static void log(Action pAction, Robot pBot){
        aLoggingStrategy.log(pAction, pBot);
    }

    /**
     * method to update the LoggingStrategy
     * @param pStrategy the new LoggingStrategy to set
     */
    public static void setLoggingStrategy(LoggingStrategy pStrategy){
        aLoggingStrategy = pStrategy;
    }

    /**
     * @return LoggingStrategy currently being used
     */
    public static LoggingStrategy getLoggingStrategy(){
        return aLoggingStrategy;
    }
}
