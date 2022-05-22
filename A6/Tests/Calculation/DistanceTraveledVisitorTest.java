package Calculation;

import Actions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceTraveledVisitorTest {

    DistanceTraveledVisitor testVisitor = new DistanceTraveledVisitor();

    @Test
    void CompactObjectActionTest() {
        assertTrue(CompactObjectAction.getInstance().acceptCalVisitor(testVisitor) == 0);
    }

    @Test
    void EmptyCompactorActionTest() {
        assertTrue(EmptyCompactorAction.getInstance().acceptCalVisitor(testVisitor) == 0);
    }

    @Test
    void GrabObjectActionTest() {
        assertTrue(GrabObjectAction.getInstance().acceptCalVisitor(testVisitor) == 0);
    }

    @Test
    void ReleaseObjectActionTest() {
        assertTrue(ReleaseObjectAction.getInstance().acceptCalVisitor(testVisitor) == 0);
    }

    @Test
    void MoveForwardActionTest() {
        assertTrue(MoveForwardAction.getInstance(10).acceptCalVisitor(testVisitor) == 10);
        assertTrue(MoveForwardAction.getInstance(100).acceptCalVisitor(testVisitor) == 100);
    }

    @Test
    void TurnActionTest() {
        assertTrue(TurnAction.getInstance(45).acceptCalVisitor(testVisitor) == 0);
    }
}