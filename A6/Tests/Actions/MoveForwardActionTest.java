package Actions;

import BaseLogic.IllegalRobotStateException;
import Robot.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveForwardActionTest {
    Robot testRobot;
    MoveForwardAction testAction = MoveForwardAction.getInstance(10);

    @BeforeEach
    public void setUp(){
        testRobot = new WallE();
    }

    @Test
    public void executeBasicAction_armStateTest(){

        try {
            ChangePrivateWallEFields.changeArmState(testRobot, Robot.ArmState.EXTENDED);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            fail();
        }

        assertThrows(IllegalRobotStateException.class, () -> {testAction.executeAction(testRobot);});
    }

    @Test
    public void executeBasicActionTest(){
        try {
            ChangePrivateWallEFields.changeArmState(testRobot, Robot.ArmState.RETRACTED);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }

        try{
            testAction.executeAction(testRobot);
        }
        catch (IllegalRobotStateException e) {
            fail();
        }
    }

    @Test
    public void acceptCalVisitorTest(){
        assertEquals(testAction.acceptCalVisitor(new MockCalVisitor()), -4);
    }

    @Test
    public void getInstaceTest(){
        assertTrue(MoveForwardAction.getInstance(100) == MoveForwardAction.getInstance(100));
        assertFalse(MoveForwardAction.getInstance(99) == MoveForwardAction.getInstance(100));
    }

    @Test
    public void getDistanceTest(){
        assertTrue(testAction.getDistance() == 10);
        assertTrue(MoveForwardAction.getInstance(100).getDistance() == 100);
    }
}