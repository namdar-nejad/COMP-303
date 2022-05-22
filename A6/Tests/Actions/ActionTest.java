package Actions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import BaseLogic.*;
import Calculation.*;
import Robot.*;

class ActionTest {

    MockActionClass testAction = new MockActionClass();
    Robot robot1 = new WallE();

    // MOCK class extending Action
    class MockActionClass extends Action{

        // will be set to true if executeAction() is called
        boolean executeActionCalled = false;

        @Override
        protected void executeAction(Robot pBot){
            executeActionCalled = true;
        }

        @Override
        public double acceptCalVisitor(CalculationVisitor pVisitor) {
            return 0;
        }
    }

    // MOCK logger class
    class MockLogger implements LoggingStrategy{

        // will be set to true if log() is called
        boolean logged = false;

        @Override
        public void log(Action pAction, Robot pBot) {
            logged = true;
        }
    }

    @Test
    public void executeANDlog_executeActionIsCalled(){

        try {
            testAction.executeANDlog(robot1);
        } catch (IllegalRobotStateException e) {
            fail();
        }

        assertEquals(testAction.executeActionCalled, true);
    }

    @Test
    public void executeANDlog_ActionIsLogged(){

        MockLogger testLogger = new MockLogger();
        Logger.setLoggingStrategy(testLogger);

        try {
            testAction.executeANDlog(robot1);
        } catch (IllegalRobotStateException e) {
            fail();
        }

        assertEquals(testLogger.logged, true);
    }

    @Test
    public void nullRobotTest(){
        try {
            testAction.executeANDlog(null);
        } catch (IllegalRobotStateException e) {
            fail();
        } catch (AssertionError e){
            // pass
        }
    }

}