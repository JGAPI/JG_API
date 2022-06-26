package dev.jgapi.jg_api.rest;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.channels.Mentions;
import dev.jgapi.jg_api.entities.channels.ServerChannel;
import dev.jgapi.jg_api.entities.chat.ChatEmbed;
import dev.jgapi.jg_api.entities.chat.ChatMessage;
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
                return (T) new ServerChannel(jg_api, channel.getString("id"), channel.getString("type"), channel.getString("name"), channel.optString("topic", ""), Instant.parse(channel.getString("createdAt")), channel.getString("createdBy"), Instant.parse(channel.optString("updatedAt", "")), channel.getString("serverId"), channel.optString("parentId", ""), channel.optString("parentId", ""), channel.getString("groupId"), channel.optBoolean("isPublic", false), channel.optString("archivedBy", null), Instant.parse(channel.optString("archivedAt", "")));
            }
            case ChatMessage -> {
                JSONObject chatMessage = json.getJSONObject("message");
                JSONArray embedsJSON = chatMessage.optJSONArray("embeds");
                String[] replyMessageIds = null;
                ChatEmbed[] embeds = null;
                Mentions mentions = null;
                return (T) new ChatMessage(jg_api, chatMessage.getString("id"), chatMessage.getString("type"), chatMessage.optString("serverId", ""), chatMessage.getString("channelId"), chatMessage.getString("content"), embeds, replyMessageIds, chatMessage.optBoolean("isPrivate", false), chatMessage.optBoolean("isSilent", false), mentions, Instant.parse(chatMessage.getString("createdAt")), chatMessage.getString("createdBy"), chatMessage.optString("createdByWebhookId", null), Instant.parse(chatMessage.optString("updatedAt", null)));
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
