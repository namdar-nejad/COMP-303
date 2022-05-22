package Programs;

import Actions.Action;
import BaseLogic.IllegalRobotStateException;
import Calculation.CalculationVisitor;
import Robot.*;
import java.util.*;

/**
 * class used to create, edit and maintain Programs from a set of Actions
 */
public class Program implements Iterable{

    /*
        list of actions to preform
        starting from the action at index 0
        remember that Actions are immutable
     */
    private LinkedList<Action> aActionQueue;

    /**
     * Overloaded constructor for Program.Program
     * @param pActions an ArrayList of Actions,
     *                 indexed in the order the actions should be preformed,
     *                 starting from Action at index 0 and ending with the last Action
     *
     * @pre pActions != null
     */
    public Program(LinkedList<Action> pActions){
        assert pActions != null;
        // ok since Actions are immutable
        aActionQueue = new LinkedList<Action>(pActions);
    }

    /**
     * Overloaded constructor for Program.Program
     * @param pActions a set of Actions
     *                 given in the order the actions should be preformed,
     *                 starting from the first Action passed and ending with the last Action
     * @pre pActions != null
     */
    public Program(Action ... pActions){
        assert pActions != null;

        aActionQueue = new LinkedList<>();

        for(Action e : pActions){
            aActionQueue.add(e);     // ok since Actions are immutable
        }
    }

    /**
     * Method to add an Action to the end of the program
     * @param pAction Action to add to the end of the Program.Program
     * @pre pAction != null
     */
    public void append(Action pAction){
        assert pAction != null;
        aActionQueue.add(pAction);
    }

    /**
     * Method to add an Action to the start of the program
     * @param pAction Action to add to the start of the Program.Program
     * @pre pAction != null
     */
    public void prepend(Action pAction) {
        assert pAction != null;
        aActionQueue.add(0, pAction);
    }

    /**
     * Method to add an Action at a specific step in the program
     * @param pAction Action to add to the program
     * @param pIndex Step of the program that the Action should be executed, starting from 0 and ending at size()-1
     * @pre pIndex >= 0 && pIndex <= size() - 1 && pAction != null
     */
    public void addAt(Action pAction, int pIndex){
        assert pIndex >= 0 && pIndex <= size() - 1 && pAction != null;
        aActionQueue.add(pIndex, pAction);
    }


    /**
     * Method to make sure that every Action of a particular class has a specific Action before it.
     * If there is a pActionToAdd before an instance of pAddBefore then nothing will happen,
     * but if there isn't then an action of type pActionToAdd will be added before it.
     *
     * @param pActionToAdd Action to add before pAddBefore
     * @param pAddBefore Action check if there is a pActionToAdd before it
     */
    public void addBefore(Action pActionToAdd, Action pAddBefore){
        LinkedList<Action> tempList = new LinkedList<>(aActionQueue);
        aActionQueue.clear();

        for (int i=0; i<tempList.size(); i++){

            if(tempList.get(i).equals(pAddBefore)){
                if(i == 0){
                    aActionQueue.add(pActionToAdd);
                }
                else if(! tempList.get(i-1).equals(pActionToAdd)){
                    aActionQueue.add(pActionToAdd);
                }
            }

            aActionQueue.add(tempList.get(i));
        }
    }

    /**
     * Removes the Action at step pIndex
     * @param pIndex Index of the Action to remove
     * @return The Action that was removed
     * @pre pIndex > 0 && pIndex < size() -1
     */
    public Action removeAt(int pIndex){
        return aActionQueue.remove(pIndex);
    }

    /**
     * Clears all the pAction instances from the Program.Program
     * @param pAction Action type to get remove of
     * @return the number of Actions removed
     */
    public int removeAll(Action pAction){
        int i = 0;
        LinkedList<Action> tempList = new LinkedList<>();

        for(Action e : aActionQueue){
            if(e.equals(pAction)) i++;
            else tempList.add(e);
        }

        aActionQueue = tempList;
        return i;
    }

    /**
     * @return number of Action steps in the Program.Program
     */
    public int size(){
        return aActionQueue.size();
    }

    /**
     * Returns the number of instances of pAction in the Program.Program
     * @param pAction Action to count the instances of
     * @return number of instances of pAction in the Program.Program
     */
    public int getNumInstances(Action pAction){
        int i =0;

        for(Action e : aActionQueue){
            if(e.equals(pAction)) i++;
        }

        return i;
    }

    /**
     * Executes all the Actions in the Program.Program. In the given order, from the first Action to the last Action.
     * If the preconditions of a given Action is not respected, the BaseLogic.IllegalRobotStateException exception thrown by the
     * Action will be caught and the printStackTrace will be printed and the rest of the Actions will not be executed.
     * @param pBot Robot to execute the actions on
     * @pre pBot !=  null
     */
    public void execute(Robot pBot) {
        assert pBot !=  null;

        try{
            for(Action e : aActionQueue){
                e.executeANDlog(pBot);
            }
        } catch (IllegalRobotStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return an iterator over Actions of the Program.Program.
     */
    @Override
    public Iterator iterator() {
        return aActionQueue.iterator();
    }

    /**
     * Calculates a result from applying the given Other.CalculationVisitor to all the Actions in the Program.Program.
     * The Other.CalculationVisitor will be applied to all the BasicActions in the Program.Program and recuses to the Action the ComplexActions.
     * The program will not be executed.
     * @param pVisitor a Other.CalculationVisitor to use
     * @return the accumulated result from applying the Other.CalculationVisitor to all the Actions in the Program.Program
     * @pre pVisitor != null
     */
    public double calculate(CalculationVisitor pVisitor){
        assert pVisitor != null;

        double rtn = 0;

        for(Action e : aActionQueue){
            rtn += e.acceptCalVisitor(pVisitor);
        }

        return rtn;
    }
}