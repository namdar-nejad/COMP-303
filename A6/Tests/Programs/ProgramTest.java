package Programs;

import Actions.*;
import BaseLogic.IllegalRobotStateException;
import Calculation.CalculationVisitor;
import Calculation.DistanceTraveledVisitor;
import Robot.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

    Robot testRobot = new WallE();

    MockAction1 mockAction1 = new MockAction1();
    MockAction2 mockAction2 = new MockAction2();

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

    Program testProgram = null;
    Field f1 = null;
    LinkedList<Action> testQueue = null;

    @BeforeEach
    public void setUp(){
        testProgram = new Program();
        try {
            f1 = testProgram.getClass().getDeclaredField("aActionQueue");
            f1.setAccessible(true);
            testQueue = (LinkedList<Action>) f1.get(testProgram);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }


    @Test
    void appendTest() {
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.append(EmptyCompactorAction.getInstance());
        assertTrue(testQueue.getLast() == EmptyCompactorAction.getInstance());
    }

    @Test
    void prependTest() {
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.prepend(GrabObjectAction.getInstance());
        assertTrue(testQueue.getFirst() == GrabObjectAction.getInstance());
    }

    @Test
    void addAtTest() {
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.addAt(EmptyCompactorAction.getInstance(), 3);
        assertTrue(testQueue.get(3) == EmptyCompactorAction.getInstance());
    }

    @Test
    void addBeforeTest() {
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.append(EmptyCompactorAction.getInstance());
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.addBefore(EmptyCompactorAction.getInstance(), GrabObjectAction.getInstance());
        assertTrue(testQueue.get(0) == EmptyCompactorAction.getInstance());
        assertTrue(testQueue.get(2) == EmptyCompactorAction.getInstance());
        assertTrue(testQueue.get(4) == EmptyCompactorAction.getInstance());
        assertTrue(testQueue.get(6) == EmptyCompactorAction.getInstance());
        assertFalse(testQueue.get(7) == EmptyCompactorAction.getInstance());
    }

    @Test
    void removeAtTest() {
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.addAt(EmptyCompactorAction.getInstance(), 3);
        testProgram.removeAt(3);
        assertTrue(testQueue.get(3) == GrabObjectAction.getInstance());
        assertEquals(testProgram.size(), 4);
    }

    @Test
    void removeAllTest() {
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.append(GrabObjectAction.getInstance());
        testProgram.addAt(EmptyCompactorAction.getInstance(), 3);
        testProgram.removeAll(GrabObjectAction.getInstance());
        assertEquals(testProgram.size(), 1);
    }

    @Test
    void sizeTest() {
        assertTrue(testProgram.size() == 0);
        testProgram.append(EmptyCompactorAction.getInstance());
        assertTrue(testProgram.size() == 1);
        testProgram.append(EmptyCompactorAction.getInstance());
        assertTrue(testProgram.size() == 2);
    }

    @Test
    void getNumInstancesTest() {
        assertTrue(testProgram.getNumInstances(EmptyCompactorAction.getInstance()) == 0);
        testProgram.append(EmptyCompactorAction.getInstance());
        assertTrue(testProgram.getNumInstances(EmptyCompactorAction.getInstance()) == 1);
        testProgram.append(GrabObjectAction.getInstance());
        assertTrue(testProgram.getNumInstances(EmptyCompactorAction.getInstance()) == 1);
        assertTrue(testProgram.getNumInstances(GrabObjectAction.getInstance()) == 1);
    }

    @Test
    void executeTest() {
        Program executeTestProgram = new Program(mockAction1, mockAction2);

        executeTestProgram.execute(testRobot);

        assertTrue(mockAction1.ActionExecuted);
        assertTrue(mockAction2.ActionExecuted);
    }

    @Test
    void iteratorTest() {
        assertTrue(testProgram.iterator() != null);

    }

    @Test
    void calculateTest() {
        Program executeTestProgram = new Program(mockAction1, mockAction2);
        assertEquals(executeTestProgram.calculate(new DistanceTraveledVisitor()), -3);
    }

    @Test
    public void linkedListConstructorTest(){
        LinkedList<Action> aList = new LinkedList<>();
        aList.add(GrabObjectAction.getInstance());
        testProgram = new Program(aList);
        assertTrue(testProgram.size() == 1);
    }
}