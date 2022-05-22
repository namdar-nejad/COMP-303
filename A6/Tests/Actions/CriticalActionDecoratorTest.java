package Actions;

import BaseLogic.IllegalRobotStateException;
import Calculation.CalculationVisitor;
import Robot.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CriticalActionDecoratorTest {

    Robot testRobot;
    MockAction testAction = new MockAction();
    CriticalActionDecorator testCriticalAction = new CriticalActionDecorator(testAction);

    @BeforeEach
    public void setUp(){
        testRobot = new WallE();
    }

    // MOCK action that does nothing
    class MockAction extends Action{
        boolean actionExecuted = false;
        @Override
        protected void executeAction(Robot pBot) throws IllegalRobotStateException {
            actionExecuted = true;
        }

        @Override
        public double acceptCalVisitor(CalculationVisitor pVisitor) {
            return -1;
        }
    }

    @Test
    void executeActionTest() {
        try {
            testCriticalAction.executeAction(testRobot);
        } catch (IllegalRobotStateException e) {
            fail();
        }

        assertTrue(testAction.actionExecuted);
        assertTrue(testRobot.getBatteryCharge() == 100);
    }

    @Test
    void acceptCalVisitor() {
        assertTrue(testCriticalAction.acceptCalVisitor(null) == -1);
    }

    @Test
    void equalsTest() {
        Action a1 = new CriticalActionDecorator(MoveForwardAction.getInstance(10));
        Action a2 = new CriticalActionDecorator(MoveForwardAction.getInstance(10));
        Action a3 = new CriticalActionDecorator(MoveForwardAction.getInstance(100));

        assertTrue(a1.equals(a2));
        assertTrue(a1.equals(a1));
        assertFalse(a2.equals(a3));
        assertFalse(a2.equals(null));
        assertFalse(a2.equals(GrabObjectAction.getInstance()));
    }

    @Test
    void hashCodeTest() {
        Action a1 = new CriticalActionDecorator(MoveForwardAction.getInstance(10));
        Action a2 = new CriticalActionDecorator(MoveForwardAction.getInstance(10));
        Action a3 = new CriticalActionDecorator(MoveForwardAction.getInstance(100));

        assertTrue(a1.hashCode() == a2.hashCode());
        assertFalse(a3.hashCode() == a2.hashCode());
    }
}