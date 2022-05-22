package Actions;

import BaseLogic.IllegalRobotStateException;
import Robot.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CompactObjectActionTest {

    Robot testRobot;
    CompactObjectAction testAction = CompactObjectAction.getInstance();

    @BeforeEach
    public void setUp(){
        testRobot = new WallE();
    }

    @Test
    public void executeBasicAction_checksGripperStateTest(){
        testRobot.openGripper();
        assertThrows(IllegalRobotStateException.class, () -> {testAction.executeAction(testRobot);});
    }

    @Test
    public void executeBasicAction_checksCompactorLevelTest(){

        try {
            ChangePrivateWallEFields.changeCompactLevel(testRobot, 11);
            ChangePrivateWallEFields.changeGripperState(testRobot, Robot.GripperState.HOLDING_OBJECT);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }

        assertThrows(IllegalRobotStateException.class, () -> {testAction.executeAction(testRobot);});
    }

    @Test
    public void executeBasicActionTest(){

        try {
            ChangePrivateWallEFields.changeCompactLevel(testRobot, 2);
            ChangePrivateWallEFields.changeGripperState(testRobot, Robot.GripperState.HOLDING_OBJECT);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }

        try{
            testAction.executeAction(testRobot);
        }
        catch (IllegalRobotStateException e) {
            fail();
        }

        assertEquals(testRobot.getGripperState(), Robot.GripperState.OPEN);
    }


    @Test
    public void acceptCalVisitorTest(){
        assertEquals(testAction.acceptCalVisitor(new MockCalVisitor()), -1);
    }

    @Test
    public void getInstaceTest(){
        assertTrue(testAction == CompactObjectAction.getInstance());
    }


}