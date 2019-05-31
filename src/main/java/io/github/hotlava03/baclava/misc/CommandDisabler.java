package io.github.hotlava03.baclava.misc;


import io.github.hotlava03.baclava.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
//TODO get this working; REMINDER: THIS FEATURE IS COMING SOON
public class CommandDisabler {
    public static void enableCmd(String cmdName) throws IOException {
        InputStream file = Main.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(file);
        String[] commands = {"reactIsDisabled", "evalIsDisabled", "fixGameIsDisabled", "nashornIsDisabled", "powerOffIsDisabled", "getPermsIsDisabled", "helpIsDisabled", "infoIsDisabled", "pingIsDisabled", "sayIsDisabled", "shuffleIsDisabled", "suggestIsDisabled", "uptimeIsDisabled"};
        for (String command : commands) {
            if (cmdName.equals(command) && properties.getProperty(command).equals("true"))
                properties.setProperty(command, "false");
        }
    }
    public static void disableCmd(String cmdName) throws IOException {
        InputStream file = Main.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(file);
        String[] commands = {"reactIsDisabled", "evalIsDisabled", "fixGameIsDisabled", "nashornIsDisabled", "powerOffIsDisabled", "getPermsIsDisabled", "helpIsDisabled", "infoIsDisabled", "pingIsDisabled", "sayIsDisabled", "shuffleIsDisabled", "suggestIsDisabled", "uptimeIsDisabled"};
        for (String command : commands) {
            if (cmdName.equals(command) && properties.getProperty(command).equals("false"))
                properties.setProperty(command, "true");
        }
    }
    public static boolean isEnabled(String cmdName) throws IOException {
        InputStream file = Main.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(file);
        String[] commands = {"reactIsDisabled", "evalIsDisabled", "fixGameIsDisabled", "nashornIsDisabled", "powerOffIsDisabled", "getPermsIsDisabled", "helpIsDisabled", "infoIsDisabled", "pingIsDisabled", "sayIsDisabled", "shuffleIsDisabled", "suggestIsDisabled", "uptimeIsDisabled"};
        boolean returnFix;
        for (String command : commands) {
            if(cmdName.equals(command) && properties.getProperty(command).equals("false")){
                returnFix = true;
                return true;
            }
        }
        if(!(returnFix = true))
           return false;
        else
            return true;
    }
}
