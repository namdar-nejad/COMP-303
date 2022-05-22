package Actions;

import Robot.Robot;
import Calculation.CalculationVisitor;
import BaseLogic.IllegalRobotStateException;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Class to create ComplexActions by combining BasicActions
 */
public class ComplexAction extends Action {

    // ArrayList of basic actions
    private final LinkedList<Action> ACTION_QUEUE;


    /**
     * Overloaded constructor for ComplexAction
     * @param pActionQueue an ArrayList of Actions,
     *                 indexed in the order the actions should be preformed,
     *                 starting from Action at index 0 and ending with the last Action
     *
     * @pre pActions != null
     */
    public ComplexAction(LinkedList<Action> pActionQueue){
        assert pActionQueue != null;

        this.ACTION_QUEUE = new LinkedList<>(pActionQueue);
    }

    /**
     * Overloaded constructor for ComplexAction
     * @param pActions a set of Actions
     *                 given in the order the actions should be preformed,
     *                 starting from the first Action passed and ending with the last Action
     * @pre pActions != null
     */
    public ComplexAction(Action ... pActions){

        LinkedList<Action> pActionQueue = new LinkedList<>();

        for(Action e : pActions){
            pActionQueue.add(e);
        }

        this.ACTION_QUEUE = new LinkedList<>(pActionQueue);
    }


    /**
     * Executes all the Actions in the ComplexAction. In the given order, from the first Action to the last Action.
     * If the preconditions of a given Action is not respected, the BaseLogic.IllegalRobotStateException exception
     * thrown by the Action.
     * @param pBot Robot to execute the actions on
     * @throws IllegalRobotStateException If any of the preconditions of a given Action is not respected (propagated by execute())
     * @pre pBot !=  null
     */
    @Override
    protected void executeAction(Robot pBot) throws IllegalRobotStateException {
        for(Action e : ACTION_QUEUE){
            e.executeANDlog(pBot);
        }
    }

    /**
     * Calls the acceptCalVisitor of every BasicAction in the ComplexAction and accumulates the results.
     * @param pVisitor Other.CalculationVisitor to use for calculation
     * @return the accumulated result from applying the Other.CalculationVisitor to all the BasicActions in the ComplexAction
     * @pre pVisitor != null
     */
    @Override
    public double acceptCalVisitor(CalculationVisitor pVisitor) {

        double pAcc = 0;
        for(Action e: ACTION_QUEUE){
            pAcc += e.acceptCalVisitor(pVisitor);
        }

        return pAcc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexAction that = (ComplexAction) o;
        return Objects.equals(ACTION_QUEUE, that.ACTION_QUEUE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ACTION_QUEUE);
    }

}
