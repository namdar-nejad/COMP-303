package Actions;

import BaseLogic.IllegalRobotStateException;
import Calculation.CalculationVisitor;
import Calculation.DistanceTraveledVisitor;
import Robot.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ComplexActionTest {

    MockAction1 mockAction1 = new MockAction1();
    MockAction2 mockAction2 = new MockAction2();

    ComplexAction testAction = new ComplexAction(mockAction1, mockAction2);
    Robot testRobot = new WallE();

    // Mock action
    class MockAction1 extends Action{
        boolean ActionExecuted = false;

        @Override
        protected void executeAction(Robot pBot) throws IllegalRobotStateException {
            ActionExecuted = true;
        }

        @Override
        public double acceptCalVisitor(CalculationVisitor pVisitor) {
            return -2;
        }
    }

    // Mock action
    class MockAction2 extends Action{
        boolean ActionExecuted = false;

        @Override
        protected void executeAction(Robot pBot) throws IllegalRobotStateException {
            ActionExecuted = true;
        }

        @Override
        public double acceptCalVisitor(CalculationVisitor pVisitor) {
            return -1;
        }
    }

    @Test
    public void executeActionTest() {
        try {
            testAction.executeAction(testRobot);
        } catch (IllegalRobotStateException e) {
            fail();
        }

        assertTrue(mockAction1.ActionExecuted);
        assertTrue(mockAction2.ActionExecuted);
    }

    @Test
    public void acceptCalVisitorTest() {
        assertEquals(testAction.acceptCalVisitor(new DistanceTraveledVisitor()), -3);
    }

    @Test
    public void testEqualsTest() {
        assertTrue(testAction.equals(new ComplexAction(mockAction1, mockAction2)));
        assertFalse(testAction.equals(new ComplexAction( mockAction2, mockAction1)));
        assertTrue(testAction.equals(testAction));
        assertFalse(testAction.equals(null));
        assertFalse(testAction.equals(TurnAction.getInstance(10)));
    }

    @Test
    public void testHashCodeTest() {
        assertTrue(testAction.hashCode() == (new ComplexAction(mockAction1, mockAction2)).hashCode());
    }

    @Test
    public void linkedListConstructorTest(){
        LinkedList<Action> list = new LinkedList<>();
        list.add(mockAction1);
        list.add(mockAction2);
        ComplexAction testAction2 = new ComplexAction(list);
        assertTrue(testAction.equals(testAction2));
    }

}