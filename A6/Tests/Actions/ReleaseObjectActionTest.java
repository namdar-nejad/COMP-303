package Actions;

import BaseLogic.IllegalRobotStateException;
import Robot.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ReleaseObjectActionTest {
    Robot testRobot;
    ReleaseObjectAction testAction = ReleaseObjectAction.getInstance();

    @BeforeEach
    public void setUp(){
        testRobot = new WallE();
    }

    @Test
    public void executeBasicAction_armStateTest(){
        try {
            ChangePrivateWallEFields.changeArmState(testRobot, Robot.ArmState.EXTENDED);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }

        assertThrows(IllegalRobotStateException.class, () -> {testAction.executeAction(testRobot);});
    }

    @Test
    public void executeBasicAction_gripperStateTest(){
        try {
            ChangePrivateWallEFields.changeArmState(testRobot, Robot.ArmState.RETRACTED);
            ChangePrivateWallEFields.changeGripperState(testRobot, Robot.GripperState.OPEN);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }

        assertThrows(IllegalRobotStateException.class, () -> {testAction.executeAction(testRobot);});
    }

    @Test
    public void executeBasicActionTest(){
        try {
            ChangePrivateWallEFields.changeCompactLevel(testRobot, 2);
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
        assertEquals(testAction.acceptCalVisitor(new MockCalVisitor()), -5);
    }

    @Test
    public void getInstaceTest(){
        assertTrue(testAction == ReleaseObjectAction.getInstance());
    }

}