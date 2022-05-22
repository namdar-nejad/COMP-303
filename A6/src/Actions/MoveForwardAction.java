package Actions;

import BaseLogic.IllegalRobotStateException;
import Robot.Robot;
import Calculation.CalculationVisitor;

import java.util.HashMap;

/**
 * Flyweight class that represents a Action object used to
 * "Move forward some distance: ensure the arm is retracted, then move forward"
 */
public final class MoveForwardAction extends BasicAction {

    // the distance to move forward
    private final double A_DISTANCE;

    // cache to store the created objects
    private static HashMap<Double, MoveForwardAction> aFactory = new HashMap<Double, MoveForwardAction>();

    /**
     * private method to create a MoveForwardAction object
     * @param pDistance distance to move forward
     */
    private MoveForwardAction(double pDistance) {
        A_DISTANCE = pDistance;
    }

    /**
     * Moves forward the given distance. <p>
     * Process: <p>
     * 1. Check if the distance if greater than 0 <p>
     * 2. Check if the arm is retracted <p>
     * 3. Move A_DISTANCE by calling moveRobot(A_DISTANCE) <p>
     *
     * @param pBot Robot to execute the Action on
     * @throws IllegalRobotStateException if the distance if less than 0 || the arm is not retracted
     */
    @Override
    protected void executeBasicAction(Robot pBot) throws IllegalRobotStateException {
        if(A_DISTANCE < 0){
            throw new IllegalRobotStateException("Distance must be greater than 0 in MoveForwardAction");
        }
        else if(! pBot.getArmState().equals(Robot.ArmState.RETRACTED)) {
            throw new IllegalRobotStateException("Arm must be retracted for MoveForwardAction");
        }
        else{
            pBot.moveRobot(A_DISTANCE);
        }
    }

    /**
     * @return distance the Action will move
     */
    public double getDistance(){
        return this.A_DISTANCE;
    }

    /**
     * method to get an instance on the MoveForwardAction object with the passed distance
     * @param pDistance distance to move
     * @return MoveForwardAction with pDistance to move
     * @pre pDistance > 0
     */
    public static MoveForwardAction getInstance(double pDistance){
        assert pDistance > 0;

        if(aFactory.containsKey(pDistance)) {
            return aFactory.get(pDistance);
        }
        else{
            MoveForwardAction pNewAction = new MoveForwardAction(pDistance);
            aFactory.put(pDistance, pNewAction);

            return pNewAction;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double acceptCalVisitor(CalculationVisitor pVisitor) {
        return pVisitor.visit(this);
    }
}
