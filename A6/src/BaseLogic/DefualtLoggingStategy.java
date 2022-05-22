package BaseLogic;

import Actions.Action;
import Robot.Robot;

import java.io.File;
import java.util.ArrayList;

/**
 * default LoggingStrategy being used
 */
public class DefualtLoggingStategy implements LoggingStrategy {

    // ArrayList that keeps track of the logs
    private static ArrayList<String> aLogs = new ArrayList<String>();

    @Override
    public void log(Action pAction, Robot pBot) {
        /*
            call log with the ActionType and BatteryCharge level.
            used getClass().getName() because the Actions are grouped by their type and it
            would save us the burden of writing a name for all the action classes.
        */
        aLogs.add(0, pAction.getClass().getName() + " action performed, battery level is " + pBot.getBatteryCharge());
    }

    /**
     * method to get the string of the log
     * @return string of the log, with the newest log at the top
     */
    public static String getLogString(){
        String str = "Newest at the top: \n";
        if(aLogs.isEmpty()) str += "No Logs \n";
        else{
            for(String e : aLogs){
                str += e + "\n";
            }
        }

        return str;
    }
}
