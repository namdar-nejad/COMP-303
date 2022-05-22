package Actions;

import Calculation.CalculationVisitor;

public class MockCalVisitor implements CalculationVisitor {
    @Override
    public double visit(CompactObjectAction pCompactAction) {
        return -1;
    }

    @Override
    public double visit(EmptyCompactorAction pEmptyAction) {
        return -2;
    }

    @Override
    public double visit(GrabObjectAction pGrabAction) {
        return -3;
    }

    @Override
    public double visit(MoveForwardAction pMoveAction) {
        return -4;
    }

    @Override
    public double visit(ReleaseObjectAction pReleaseAction) {
        return -5;
    }

    @Override
    public double visit(TurnAction pTurnAction) {
        return -6;
    }
}
