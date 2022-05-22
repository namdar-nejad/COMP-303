package Actions;

import BaseLogic.IllegalRobotStateException;
import Robot.Robot;
import Calculation.CalculationVisitor;

/**
 * Singleton class that represents a Action object used to
 * "Empty the compactor: empty the content of the compactor"
 */
public final class EmptyCompactorAction extends BasicAction {

    // holds the only instance of the EmptyCompactorAction object
    private static final EmptyCompactorAction A_INSTANCE = new EmptyCompactorAction();

    // it's a singleton
    private EmptyCompactorAction(){
    }

    /**
     * Empties the objects in the Robot's compactor. <p>
     * Process: <p>
     * 1. Checks if the compactor is already empty.<p>
     * 2. Empties the compactor by calling emptyCompactor() <p>
     *
     * @param pBot Robot to execute the Action on
     * @throws IllegalRobotStateException if the compactor is empty
     */
    @Override
    protected void executeBasicAction(Robot pBot) throws IllegalRobotStateException {
        if(pBot.getCompactorLevel() == 0){
            throw new IllegalRobotStateException("Compactor must not be empty for EmptyCompactorAction");
        }
        else{
            pBot.emptyCompactor();
        }
    }

    /**
     * @return the only instance of a EmptyCompactorAction object
     */
    public static EmptyCompactorAction getInstance(){
        return A_INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double acceptCalVisitor(CalculationVisitor pVisitor) {
        return pVisitor.visit(this);
    }
}
