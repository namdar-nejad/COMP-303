package BaseLogic;

/**
 * Exception class for the Robot API
 */
public class IllegalRobotStateException extends Exception {
    /**
     * Exception the is thrown if any of the preconditions of an Action that is being executed is not satisfied.
     * @param pMessage message explaining the exception
     */
    public IllegalRobotStateException(String pMessage) {
        super(pMessage);
    }
}
