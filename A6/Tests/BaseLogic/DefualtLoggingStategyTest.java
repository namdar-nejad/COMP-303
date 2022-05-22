package BaseLogic;

import Actions.TurnAction;
import Robot.WallE;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DefualtLoggingStategyTest {

    DefualtLoggingStategy testStrategy = new DefualtLoggingStategy();

    @Test
    public void logTest(){

        Field f1 = null;
        ArrayList<String> testLogs = null;
        try {
            f1 = testStrategy.getClass().getDeclaredField("aLogs");
            f1.setAccessible(true);
            testLogs = (ArrayList<String>) f1.get(testStrategy);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }

        assertTrue(testLogs.isEmpty());

        testStrategy.log(TurnAction.getInstance(10), new WallE());

        try {
            testLogs = (ArrayList<String>) f1.get(testStrategy);
        } catch (IllegalAccessException e) {
            fail();
        }

        assertFalse(testLogs.isEmpty());
    }

    @Test
    public void toStringTest(){
        assertFalse(testStrategy.getLogString().isBlank());
    }

    @Test
    public void toStringEmptyTest(){
        testStrategy = new DefualtLoggingStategy();
        assertFalse(testStrategy.getLogString().isBlank());
    }
}