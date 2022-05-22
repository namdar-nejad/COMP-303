package Actions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import BaseLogic.*;
import Calculation.*;
import Robot.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class BasicActionTest {

    Robot testRobot = new WallE();
    mockBasicAction BasicActionTestObject = new mockBasicAction();

    // MOCK class extending BasicAction
    class mockBasicAction extends BasicAction {

        boolean executeBasicActionCalled = false;

        @Override
        protected void executeBasicAction(Robot pBot){
            executeBasicActionCalled = true;
            pBot.turnRobot(360);
        }

        @Override
        public double acceptCalVisitor(CalculationVisitor pVisitor) {
            return 0;
        }
    }


    @Test
    public void executeAction_executeBasicActionIsCalled(){

        try {
            BasicActionTestObject.executeAction(testRobot);
        } catch (IllegalRobotStateException e) {
            fail();
        }

        assertTrue(BasicActionTestObject.executeBasicActionCalled);

    }

    @Test
    public void executeAction_BattaryLevelIsUpdated(){

        testRobot.rechargeBattery();
        double oldLevel = testRobot.getBatteryCharge();

        try {
            BasicActionTestObject.executeAction(testRobot);
        } catch (IllegalRobotStateException e) {
            fail();
        }

        assertTrue(oldLevel > testRobot.getBatteryCharge());
    }

    @Test
    public void preExecutionCheckTest(){

        try {
            ChangePrivateWallEFields.changeBatteryLevel(testRobot, 4);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }

        try {
            Method privateStringMethod = BasicActionTestObject.getClass().getSuperclass()
                    .getDeclaredMethod("preExecutionCheck", Robot.class);

            privateStringMethod.setAccessible(true);

            privateStringMethod.invoke(BasicActionTestObject, testRobot);


        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            fail();
        }

        assertTrue(testRobot.getBatteryCharge() == 100);
    }

}