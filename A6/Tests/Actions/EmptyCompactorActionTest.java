package Actions;

import BaseLogic.IllegalRobotStateException;
import Robot.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class EmptyCompactorActionTest {
    Robot testRobot;
    EmptyCompactorAction testAction = EmptyCompactorAction.getInstance();

    @BeforeEach
    public void setUp(){
        testRobot = new WallE();
    }

    @Test
    public void executeBasicAction_checksCompactorLevelTest(){

        try {
            ChangePrivateWallEFields.changeCompactLevel(testRobot, 0);
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
        assertEquals(testAction.acceptCalVisitor(new MockCalVisitor()), -2);
    }

    @Test
    public void getInstaceTest(){
        assertTrue(testAction == EmptyCompactorAction.getInstance());
    }

}