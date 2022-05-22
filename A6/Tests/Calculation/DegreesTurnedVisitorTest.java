package Calculation;

import Actions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DegreesTurnedVisitorTest {

    DegreesTurnedVisitor testVisitor = new DegreesTurnedVisitor();

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
        assertTrue(MoveForwardAction.getInstance(10).acceptCalVisitor(testVisitor) == 0);
    }

    @Test
    void TurnActionTest() {
        assertTrue(TurnAction.getInstance(45).acceptCalVisitor(testVisitor) == 45);
        assertTrue(TurnAction.getInstance(-45).acceptCalVisitor(testVisitor) == 45);
    }
}