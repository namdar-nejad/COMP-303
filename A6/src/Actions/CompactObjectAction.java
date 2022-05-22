package Actions;

import BaseLogic.IllegalRobotStateException;
import Robot.Robot;
import Calculation.CalculationVisitor;

/**
 * Singleton class that represents a Action object used to
 * "Compact an object: ensure the gripper holds an object, then compact the object"
 */
public final class CompactObjectAction extends BasicAction {

    // holds the only instance of the CompactObjectAction object
    private static final CompactObjectAction A_INSTANCE = new CompactObjectAction();

    // it's a singleton
    private CompactObjectAction(){
    }

    /**
     * Compact the objects in the Robot's storage. <p>
     * Process: <p>
     * 1. Check that the gripper is holding an object <p>
     * 2. Check that there are less than 10 objects in the robots compactor <p>
     * 3. Compact the objects by calling compact on pBot <p>
     * After this Action the Gripper will remain open.
     *
     * @param pBot Robot to execute the Action on
     * @throws IllegalRobotStateException if the compactor is holding more than 10 objects || gripper is empty
     * @post gripper is open
     */
    @Override
    protected void executeBasicAction(Robot pBot) throws IllegalRobotStateException {
        if(!pBot.getGripperState().equals(Robot.GripperState.HOLDING_OBJECT)){
            throw new IllegalRobotStateException("Gripper must be holding an object for CompactObjectAction");
        }
        else if(pBot.getCompactorLevel() > 10){
            throw new IllegalRobotStateException("Compactor must be holding less than 10 objects for CompactObjectAction");
        }
        else{
            pBot.compact();
        }
    }

    /**
     * @return the only instance of a CompactObjectAction object
     */
    public static CompactObjectAction getInstance(){
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
