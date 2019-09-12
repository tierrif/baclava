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

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

public class RetrieveUserComponent {

    private Guild guild;

    public RetrieveUserComponent(Guild guild) {
        this.guild = guild;
    }

    public User getUserByMention(String mention) {

        String id = mention.replace("<@", "")
                .replace("!","") // Sometimes ! isn't in mentions, so let's keep it separately
                .replace(">","");

        try {
            return guild.getJDA().getUserById(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Member getMemberByMention(String mention) {
        System.out.println("this reached");

        String id = mention.replace("<@", "")
                .replace("!","")
                .replace(">","");

        System.out.println("this reached 1");

        try {
            return guild.getMemberById(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public User getUserByEffectiveName(String effectiveName) {
        if (guild.getMembersByEffectiveName(effectiveName, true).size() == 0) return null;
        return guild.getMembersByEffectiveName(effectiveName, true).get(0).getUser();
    }

    public Member getMemberByEffectiveName(String effectiveName) {
        if (guild.getMembersByEffectiveName(effectiveName, true).size() == 0) return null;
        return guild.getMembersByEffectiveName(effectiveName, true).get(0);
    }

    public User getUserByName(String name) {
        if (guild.getMembersByName(name, true).size() == 0) return null;
        return guild.getMembersByName(name, true).get(0).getUser();
    }

    public Member getMemberByName(String name) {
        if (guild.getMembersByName(name, true).size() == 0) return null;
        return guild.getMembersByName(name, true).get(0);
    }

    public User getUserByTag(String tag) {
        Member member = guild.getMemberByTag(tag);
        if (member == null) return null;
        return member.getUser();
    }

    public Member getMemberByTag(String tag) {
        return guild.getMemberByTag(tag);
    }
}
