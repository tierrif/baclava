/*
 *    Copyright 2019 HotLava03
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.github.hotlava03.baclava.components;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * <p>
 *     The component for the >>uptime command.
 *     Can be used anywhere though, as the method(s)
 *     are pretty universal.
 * </p>
 */
public class UptimeComponent {

    /**
     * <p>
     *     Can be used anywhere to get the bot's
     *     uptime easily.
     * </p>
     *
     * @return The bot's uptime in a formatted way.
     */
    public String getUptime() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        long uptime = runtime.getUptime();
        long seconds = uptime / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        String time;
        if(days > 0)
            time = ("My uptime is: "+days + " days, " + hours % 24 + " hours, " + minutes % 60 + " minutes and " + seconds % 60 + " seconds.");
        else if((hours % 24) > 0)
            time = ("My uptime is: "+ hours % 24 + " hours, " + minutes % 60 + " minutes and " + seconds % 60 + " seconds.");
        else if((minutes % 60) > 0)
            time = ("My uptime is: "+ minutes % 60 + " minutes and " + seconds % 60 + " seconds.");
        else
            time = "I was just powered on, and my uptime is: "+seconds % 60+" seconds. Breh, Lava just woke up.";
        return time;
    }
}
