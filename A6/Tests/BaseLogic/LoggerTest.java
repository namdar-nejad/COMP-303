package BaseLogic;

import Actions.Action;
import Actions.TurnAction;
import Robot.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {

    Robot testRobot = new WallE();
    MockLogger testLogger = new MockLogger();


    // MOCK loggingStrategy
    class MockLogger implements LoggingStrategy{

        boolean logged = false;

        @Override
        public void log(Action pAction, Robot pBot) {
            logged = true;
        }
    }

    @Test
    public void logTest(){
        Logger.setLoggingStrategy(testLogger);
        Logger.log(TurnAction.getInstance(10), testRobot);
        assertTrue(testLogger.logged);
    }

    @Test
    public void setLoggingStrategyTest(){
        MockLogger newLogger = new MockLogger();
        Logger.setLoggingStrategy(newLogger);

        assertTrue(Logger.getLoggingStrategy() == newLogger);
    }
}