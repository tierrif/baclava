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

package io.github.hotlava03.baclava.commands;

import io.github.hotlava03.baclava.events.CommandEvent;
import net.dv8tion.jda.api.Permission;

/**
 * <p>
 *     Every command class in Baclava must extend
 *     {@link io.github.hotlava03.baclava.commands.Command}. After that,
 *     you will need to define AT LEAST the name and the category ({@link io.github.hotlava03.baclava.commands.Command#name},
 *     {@link io.github.hotlava03.baclava.commands.Command#category}) for it to execute properly. If the command doesn't register,
 *     the main thread will stop.
 * </p>
 */
public abstract class Command {

    protected String name;
    protected String description;
    protected String[] aliases = new String[]{};
    protected Permission[] requiredPermissions = new Permission[]{};
    protected Category category;
    protected String usage = "*no usage set*";

    protected Command() {

    }

    // Abstract method

    public abstract void onCommand(CommandEvent e);

    /**
     * @return The command's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The command's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return The command's aliases.
     */
    public String[] getAliases() {
        return this.aliases;
    }

    /**
     * @return The required permissions of the command.
     */
    public Permission[] getRequiredPermissions() {
        return this.requiredPermissions;
    }

    /**
     * @return The command's category as an enum.
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * @return The command's usage.
     */
    public String getUsage() {
        return this.usage;
    }
}
