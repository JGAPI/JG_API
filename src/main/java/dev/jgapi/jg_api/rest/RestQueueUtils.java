package dev.jgapi.jg_api.rest;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.channels.Mentions;
import dev.jgapi.jg_api.entities.channels.ServerChannel;
import dev.jgapi.jg_api.entities.chat.ChatEmbed;
import dev.jgapi.jg_api.entities.chat.ChatMessage;
import dev.jgapi.jg_api.util.InstantHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;

public class RestQueueUtils {
    public static <T> T processAction(JG_API jg_api, String jsonResponse, Routing.ReturnType returnType) {
        // Example:
        // new RestAction<Webhook>(this.jg_api.getNextSeqNumber(), request, this.jg_api).queue(webhook -> { webhook.getServerId(); });
        JSONObject json = new JSONObject(jsonResponse);
        String serverId = json.optString("serverId", null);
        switch (returnType) {
            case NONE -> {
                return (T) Boolean.TRUE;
            }
            case ServerModel -> {
                return null;
            }
            case ServerChannel -> {
                JSONObject channel = json.getJSONObject("channel");
                return (T) new ServerChannel(
                        jg_api,
                        channel.getString("id"),
                        channel.getString("type"),
                        channel.getString("name"),
                        channel.optString("topic", null),
                        Instant.parse(channel.getString("createdAt")),
                        channel.getString("createdBy"),
                        InstantHelper.parseStringOrNull(channel.optString("updatedAt", null)),
                        channel.getString("serverId"),
                        channel.optString("parentId", null),
                        channel.optInt("categoryId", -1),
                        channel.getString("groupId"),
                        channel.optBoolean("isPublic", false),
                        channel.optString("archivedBy", null),
                        InstantHelper.parseStringOrNull(channel.optString("archivedAt", null)));
            }
            case ChatMessage -> {
                JSONObject chatMessage = json.getJSONObject("message");
                JSONArray embedsJSON = chatMessage.optJSONArray("embeds");
                String[] replyMessageIds = null;
                ChatEmbed[] embeds = null;
                Mentions mentions = null;
                return (T) new ChatMessage(
                        jg_api,
                        chatMessage.getString("id"),
                        chatMessage.getString("type"),
                        chatMessage.optString("serverId", null),
                        new ServerChannel(
                                jg_api,
                                chatMessage.getString("channelId"),
                                null, null, null, null, null, null,
                                chatMessage.optString("serverId", null),
                                null, -1, null, false, null, null
                        ),
                        chatMessage.getString("content"),
                        null, null,
                        chatMessage.optBoolean("isPrivate", false),
                        chatMessage.optBoolean("isSilent", false),
                        null,
                        Instant.parse(chatMessage.getString("createdAt")),
                        chatMessage.getString("createdBy"),
                        chatMessage.optString("createdByWebhookId", null),
                        InstantHelper.parseStringOrNull(chatMessage.optString("updatedAt", null)),
                        InstantHelper.parseStringOrNull(chatMessage.optString("deletedAt", null))
                );
            }
            case ChatMessage_Arr -> {
                // TODO
                return null;
            }
            case Nickname -> {
                // TODO
                return null;
            }
            case ServerMember -> {
                return null;
            }
            case ServerMemberSummary_Arr -> {
                // TODO
                return null;
            }
            case ServerMemberBan -> {
                return null;
            }
            case ServerMemberBan_Arr -> {
                // TODO
                return null;
            }
            case ForumTopic -> {
                return null;
            }
            case ListItem -> {
                return null;
            }
            case ListItemSummary_Arr -> {
                // TODO
                return null;
            }
            case ListItem_Update_Obj -> {
                // TODO
                return null;
            }
            case Doc -> {
                return null;
            }
            case Doc_Arr -> {
                // TODO
                return null;
            }
            case XP_Member_Total -> {
                // TODO
                return null;
            }
            case XP_Role_Total -> {
                // TODO
                return null;
            }
            case Social_Links_Obj -> {
                // TODO
                return null;
            }
            case MemberRoles -> {
                // TODO
                return null;
            }
            case Webhook -> {
                return null;
            }
            case Webhook_Arr -> {
                // TODO
                return null;
            }
            case CalendarEvent -> {
                return null;
            }
            case CalendarEvent_Arr -> {
                // TODO
                return null;
            }
        }
        return null;
    }
}
