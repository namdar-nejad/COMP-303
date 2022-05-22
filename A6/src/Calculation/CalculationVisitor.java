package Calculation;
import Actions.*;

/**
 * Visitor interface use for making calculation based on the Actions executed.
 * To be able to do computation on the Actions, you have to create a concrete implantation
 * of this interface and provide a way to do the calculation calculate for each BasicAction.
 * Refer to DistanceTraveledVisitor and DegreesTurnedVisitor as a reference.
 */
public interface CalculationVisitor {

    /**
     * method to do calculation when given an instance of a CompactObjectAction.
     * @param pCompactAction a pCompactAction BasicAction to use for calculations
     * @return result of the calculations
     */
    public double visit(CompactObjectAction pCompactAction);

    /**
     * method to do calculation when given an instance of a EmptyCompactorAction.
     * @param pEmptyAction a pCompactAction BasicAction to use for calculations
     * @return result of the calculations
     */
    public double visit(EmptyCompactorAction pEmptyAction);

    /**
     * method to do calculation when given an instance of a GrabObjectAction.
     * @param pGrabAction a pCompactAction BasicAction to use for calculations
     * @return result of the calculations
     */
    public double visit(GrabObjectAction pGrabAction);

    /**
     * method to do calculation when given an instance of a MoveForwardAction.
     * @param pMoveAction a pCompactAction BasicAction to use for calculations
     * @return result of the calculations
     */
    public double visit(MoveForwardAction pMoveAction);

    /**
     * method to do calculation when given an instance of a ReleaseObjectAction.
     * @param pReleaseAction a pCompactAction BasicAction to use for calculations
     * @return result of the calculations
     */
    public double visit(ReleaseObjectAction pReleaseAction);

    /**
     * method to do calculation when given an instance of a TurnAction.
     * @param pTurnAction a pCompactAction BasicAction to use for calculations
     * @return result of the calculations
     */
    public double visit(TurnAction pTurnAction);
}