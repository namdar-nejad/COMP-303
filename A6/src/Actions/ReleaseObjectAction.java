package Actions;

import Robot.Robot;
import Calculation.CalculationVisitor;
import BaseLogic.IllegalRobotStateException;

/**
 * Singleton class that represents a Action object used to
 * "Release an object: ensure the arm is retracted, then open the gripper"
 */
public final class ReleaseObjectAction extends BasicAction {

    // holds the only instance of the ReleaseObjectAction object
    private static ReleaseObjectAction A_INSTANCE = new ReleaseObjectAction();

    // it's a singleton
    private ReleaseObjectAction(){
    }

    /**
     * Releases the object held in the gripper. <p>
     * Process: <p>
     * 1. Check if the arm is retracted <p>
     * 2. Check if the gripper is open <p>
     * 3. Open gripper <p>
     *
     * @param pBot Robot to execute the Action on
     * @throws IllegalRobotStateException if the gripper is open || the arm is not retracted
     */
    @Override
    protected void executeBasicAction(Robot pBot) throws IllegalRobotStateException {
        if(!pBot.getArmState().equals(Robot.ArmState.RETRACTED)){
            throw new IllegalRobotStateException("Arm must be retracted for ReleaseObjectAction");
        }
        else if(pBot.getGripperState().equals(Robot.GripperState.OPEN)){
            throw new IllegalRobotStateException("Gripper must not be open for ReleaseObjectAction");
        }
        else{
            pBot.openGripper();
        }
    }

    /**
     * @return the only instance of a ReleaseObjectAction object
     */
    public static ReleaseObjectAction getInstance(){
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
