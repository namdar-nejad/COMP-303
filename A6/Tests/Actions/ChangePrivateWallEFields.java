package Actions;

import Robot.*;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * provides methods to change the private feilds of an WallE object
 */
public class ChangePrivateWallEFields {

    public static void changeCompactLevel(Robot testRobot, int newLevel) throws NoSuchFieldException, IllegalAccessException {
        Field f1 = testRobot.getClass().getDeclaredField("compactedItems");
        f1.setAccessible(true);
        f1.set(testRobot, newLevel);
    }

    public static void changeGripperState(Robot testRobot, Robot.GripperState newState) throws NoSuchFieldException, IllegalAccessException {
        Field f1 = testRobot.getClass().getDeclaredField("gripperState");
        f1.setAccessible(true);
        f1.set(testRobot, newState);
    }

    public static void changeArmState(Robot testRobot, Robot.ArmState newState) throws IllegalAccessException, NoSuchFieldException {
        Field f1 = testRobot.getClass().getDeclaredField("armState");
        f1.setAccessible(true);
        f1.set(testRobot, newState);
    }

    public static void changeBatteryLevel(Robot testRobot, int newLevel) throws IllegalAccessException, NoSuchFieldException {
        Field f1 = testRobot.getClass().getDeclaredField("charge");
        f1.setAccessible(true);
        f1.set(testRobot, newLevel);
    }
}
