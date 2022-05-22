package Actions;

import BaseLogic.IllegalRobotStateException;
import Robot.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnActionTest {
    Robot testRobot;

    TurnAction testAction = TurnAction.getInstance(10);
    TurnAction testPosAction = TurnAction.getInstance(45);
    TurnAction testNegAction = TurnAction.getInstance(-90);

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
        assertEquals(testAction.acceptCalVisitor(new MockCalVisitor()), -6);
    }

    @Test
    public void getInstaceTest(){
        assertTrue(testAction == TurnAction.getInstance(10));
        assertTrue(testNegAction == TurnAction.getInstance(-90));
    }

    @Test
    public void getDistanceTest(){
        assertTrue(testAction.getAngle() == 10);
        assertTrue(TurnAction.getInstance(45).getAngle() == 45);
    }
}