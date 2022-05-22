package Actions;

import BaseLogic.IllegalRobotStateException;
import Robot.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class GrabObjectActionTest {

    Robot testRobot;
    GrabObjectAction testAction = GrabObjectAction.getInstance();

    @BeforeEach
    public void setUp(){
        testRobot = new WallE();
    }

    @Test
    public void executeBasicAction_checksGripperStateTest(){

        try {
            ChangePrivateWallEFields.changeGripperState(testRobot, Robot.GripperState.EMPTY);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }

        assertThrows(IllegalRobotStateException.class, () -> {testAction.executeAction(testRobot);});
    }

    @Test
    public void executeBasicAction_checksArmStateTest(){
        try {
            ChangePrivateWallEFields.changeArmState(testRobot, Robot.ArmState.EXTENDED);
            ChangePrivateWallEFields.changeGripperState(testRobot, Robot.GripperState.OPEN);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }

        assertThrows(IllegalRobotStateException.class, () -> {testAction.executeAction(testRobot);});
    }

    @Test
    public void executeBasicActionTest(){
        try {
            ChangePrivateWallEFields.changeArmState(testRobot, Robot.ArmState.RETRACTED);
            ChangePrivateWallEFields.changeGripperState(testRobot, Robot.GripperState.OPEN);
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
        assertEquals(testAction.acceptCalVisitor(new MockCalVisitor()), -3);
    }

    @Test
    public void getInstaceTest(){
        assertTrue(testAction == GrabObjectAction.getInstance());
    }


}