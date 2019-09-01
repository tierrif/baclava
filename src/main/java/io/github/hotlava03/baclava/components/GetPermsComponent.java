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

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.EnumSet;

/**
 * <p>
 *     The base component for the GetPerms command ({@link io.github.hotlava03.baclava.commands.util.GetPermsCmd}).
 *     Can be used to get perms in a formatted way too, though.
 * </p>
 */
public class GetPermsComponent {

    /**
     * @param user The user you want to get the permissions from.
     * @return The permissions in a formatted way.
     */
    public String getPermsFromMember(Member user) {
        return format(user.getPermissions());
    }

    /**
     * @param role The role you want to get the permissions from.
     * @return The permissions in a formatted way.
     */
    public String getPermsFromRole(Role role) {
        return format(role.getPermissions());
    }

    private String format(EnumSet<Permission> permissions) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (Permission permission : permissions) {

            if (i++ == 0) {
                builder.append(permission.getName());
                continue;
            }

            builder.append(", ").append(permission.getName());
        }
        return builder.toString();
    }
}
