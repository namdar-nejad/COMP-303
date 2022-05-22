package Calculation;

import Actions.*;

public class DistanceTraveledVisitor implements CalculationVisitor {

    /**
     * {@inheritDoc}
     */
    @Override
    public double visit(CompactObjectAction pCompactAction) {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double visit(EmptyCompactorAction pEmptyAction) {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double visit(GrabObjectAction pGrabAction) {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double visit(MoveForwardAction pMoveAction) {
        return pMoveAction.getDistance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double visit(ReleaseObjectAction pReleaseAction) {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double visit(TurnAction pTurnAction) {
        return 0;
    }

}
