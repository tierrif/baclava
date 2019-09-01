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

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.github.hotlava03.baclava.events.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.json.JSONException;

import java.time.Instant;

/**
 * Call APIs with this component. Main component for >>cat and >>dog.
 */
public class APICallComponent {

    /**
     * <p>
     *     Sends an Embed message with the image gotten
     *     through the URL.
     * </p>
     *
     * @param url The URL for the API to be called
     * @param event The command event from the command that calls this API
     * @param titleText The title of the embed.
     * @param key JSON key
     */
    public void callAPI(CommandEvent event, String url, String titleText, String key) {
        Unirest.get(url).asJsonAsync(new Callback<>() {

            @Override
            public void completed(HttpResponse<JsonNode> response) {
                try {
                    event.replyEmbed(embed(event, response, titleText, key).build());
                } catch (JSONException e) {
                    e.printStackTrace();
                    event.reply("An internal error has occurred (JSONException).");
                }
            }

            @Override
            public void failed(UnirestException e) {
                event.reply("Failed to get an API response.\n"+e);
                e.printStackTrace();
            }

            @Override
            public void cancelled() {
                event.reply("API request canceled.");
            }
        });
    }

    private EmbedBuilder embed(CommandEvent event, HttpResponse<JsonNode> response, String titleText, String key) throws JSONException {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(titleText,response.getBody().getObject().getString(key));
        embed.setImage(response.getBody().getObject().getString(key));
        embed.setTimestamp(Instant.now());
        embed.setAuthor(event.getMember().getEffectiveName(), null, event.getUser().getAvatarUrl());
        return embed;
    }
}
