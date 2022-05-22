package Actions;

import BaseLogic.IllegalRobotStateException;
import Robot.Robot;
import Calculation.CalculationVisitor;

/**
 * Singleton class that represents a Action object used to
 * "Grab an object: extend the arm, close the gripper, retract the arm"
 */
public final class GrabObjectAction extends BasicAction {

    // hold the only instance of the GrabObjectAction object
    private static final GrabObjectAction A_INSTANCE = new GrabObjectAction();

    // it's a singleton
    private GrabObjectAction(){
    }

    /**
     * Grabs an object using the gripper. <p>
     * Process: <p>
     * 1. Check if the gripper is open <p>
     * 2. Check if the arm is extended <p>
     * 3. Extend arm by calling extendArm() <p>
     * 4. Close gripper by calling closeGripper() <p>
     * 5. Retract arm by calling retractArm() <p>
     *
     * @param pBot Robot to execute the Action on
     * @throws IllegalRobotStateException if the gripper is not open || the arm is extended
     */
    @Override
    protected void executeBasicAction(Robot pBot) throws IllegalRobotStateException {

        if(!(pBot.getGripperState().equals(Robot.GripperState.OPEN))){
            throw new IllegalRobotStateException("Gripper must be open for GrabObjectAction");
        }
        else if(pBot.getArmState().equals(Robot.ArmState.EXTENDED)){
            throw new IllegalRobotStateException("Arm must not be extended for GrabObjectAction");
        }
        else{
            // we know that the arm is retracted
            pBot.extendArm();

            // we know that the gripper is open
            pBot.closeGripper();

            // we know that the arm is extended
            pBot.retractArm();
        }
    }

    /**
     * @return the only instance of a GrabObjectAction object
     */
    public static GrabObjectAction getInstance(){
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
