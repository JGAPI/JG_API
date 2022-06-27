package dev.jgapi.jg_api.util;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.channels.Mentions;
import dev.jgapi.jg_api.entities.channels.ServerChannel;
import dev.jgapi.jg_api.entities.chat.ChatEmbed;
import dev.jgapi.jg_api.entities.chat.ChatMessage;
import dev.jgapi.jg_api.entities.members.ServerMember;
import dev.jgapi.jg_api.entities.members.User;
import dev.jgapi.jg_api.entities.server.ServerModel;
import dev.jgapi.jg_api.rest.Routing;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class RestUtils {
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
                return (T) new ServerModel(
                        jg_api,
                        json.getString("id"),
                        json.getString("ownerId"),
                        json.optString("type", null),
                        json.getString("name"),
                        json.optString("url", null),
                        json.optString("about", null),
                        json.optString("avatar", null),
                        json.optString("banner", null),
                        json.optString("timezone", null),
                        json.optBoolean("isVerified", false),
                        json.optString("defaultChannelId", null),
                        Instant.parse(json.getString("createdAt"))
                );
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
                JSONObject chatMessageObj = json.getJSONObject("message");
                return (T) parseChatMessageObj(chatMessageObj, jg_api);
            }
            case ChatMessage_Arr -> {
                List<ChatMessage> chatMessages = new ArrayList<>();
                JSONArray chatMessagesArr = json.getJSONArray("messages");

                for (int i = 0; i < chatMessagesArr.length(); i ++) {
                    JSONObject chatMessageObj = chatMessagesArr.getJSONObject(i);
                    chatMessages.add(parseChatMessageObj(chatMessageObj, jg_api));
                }

                return (T) chatMessages;
            }
            case Nickname -> {
                return (T) json.getString("nickname");
            }
            case ServerMember -> {
                JSONObject jMember = json.getJSONObject("member");
                JSONObject jUser = jMember.getJSONObject("user");
                User user = new User(jg_api, jUser.getString("id"), jUser.getString("name"), jUser.getString("type"), jUser.optString("avatar", null), jUser.optString("banner", null), Instant.parse(jUser.getString("createdAt")));
                int[] roleIds = null; // TODO Set this up
                return (T) new ServerMember(jg_api, user, roleIds, jMember.optString("nickname", null), Instant.parse(jMember.getString("joinedAt")), jMember.optBoolean("isOwner", false));
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

    // Util Methods
    private static ChatMessage parseChatMessageObj(JSONObject chatMessageObj, JG_API jg_api) {
        JSONArray embedsJSON = chatMessageObj.optJSONArray("embeds");
        String[] replyMessageIds = null; // TODO Set up
        ChatEmbed[] embeds = null; // TODO Set up
        Mentions mentions = null; // TODO Set up

        return new ChatMessage(
                jg_api,
                chatMessageObj.getString("id"),
                chatMessageObj.getString("type"),
                chatMessageObj.optString("serverId", null),
                new ServerChannel(
                        jg_api,
                        chatMessageObj.getString("channelId"),
                        null, null, null, null, null, null,
                        chatMessageObj.optString("serverId", null),
                        null, -1, null, false, null, null
                ),
                chatMessageObj.getString("content"),
                null, null,
                chatMessageObj.optBoolean("isPrivate", false),
                chatMessageObj.optBoolean("isSilent", false),
                null,
                Instant.parse(chatMessageObj.getString("createdAt")),
                chatMessageObj.getString("createdBy"),
                chatMessageObj.optString("createdByWebhookId", null),
                InstantHelper.parseStringOrNull(chatMessageObj.optString("updatedAt", null)),
                InstantHelper.parseStringOrNull(chatMessageObj.optString("deletedAt", null))
        );
    }
}
