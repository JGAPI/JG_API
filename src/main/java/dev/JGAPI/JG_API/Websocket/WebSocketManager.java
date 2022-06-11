package dev.JGAPI.JG_API.Websocket;

import cn.hutool.json.JSONObject;
import dev.JGAPI.JG_API.Entities.Channels.Mentions;
import dev.JGAPI.JG_API.Entities.Channels.ServerChannel;
import dev.JGAPI.JG_API.Entities.Chat.ChatEmbed;
import dev.JGAPI.JG_API.Entities.Chat.ChatMessage;
import dev.JGAPI.JG_API.Entities.MemberBans.ServerMemberBan;
import dev.JGAPI.JG_API.Entities.Members.ServerMember;
import dev.JGAPI.JG_API.Entities.Members.User;
import dev.JGAPI.JG_API.Entities.Members.UserSummary;
import dev.JGAPI.JG_API.Entities.Webhooks.Webhook;
import dev.JGAPI.JG_API.Events.Chat.ChatMessageCreatedEvent;
import dev.JGAPI.JG_API.Events.Chat.ChatMessageDeletedEvent;
import dev.JGAPI.JG_API.Events.Chat.ChatMessageUpdatedEvent;
import dev.JGAPI.JG_API.Events.Event;
import dev.JGAPI.JG_API.Events.TeamChannel.TeamChannelCreatedEvent;
import dev.JGAPI.JG_API.Events.TeamChannel.TeamChannelDeletedEvent;
import dev.JGAPI.JG_API.Events.TeamChannel.TeamChannelUpdatedEvent;
import dev.JGAPI.JG_API.Events.TeamMember.*;
import dev.JGAPI.JG_API.Events.TeamWebhook.TeamWebhookCreatedEvent;
import dev.JGAPI.JG_API.Events.TeamWebhook.TeamWebhookUpdatedEvent;
import dev.JGAPI.JG_API.Exceptions.InvalidOperationException;
import dev.JGAPI.JG_API.JG_API;
import dev.JGAPI.JG_API.ListenerAdapter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.Instant;

public class WebSocketManager {
    private WebSocket webSocket;
    private WebSocketListener webSocketListener;
    private URI guildedWebSocketUri;
    private HttpClient httpClient;
    private String clientToken;
    private JG_API jg_api;

    /**
     * Constructs a WebSocketManager object.
     * @param clientToken The client's token to log in with.
     */
    public WebSocketManager(String clientToken, JG_API jg_api) {
        try {
            this.clientToken = clientToken;

            guildedWebSocketUri = new URI("wss://api.guilded.gg/v1/websocket");
            webSocketListener = new WebSocketListener(this);
            httpClient = HttpClient.newHttpClient();
            this.jg_api = jg_api;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int[] toPrimitive(Integer[] IntegerArray) {

        int[] result = new int[IntegerArray.length];
        for (int i = 0; i < IntegerArray.length; i++) {
            result[i] = IntegerArray[i];
        }
        return result;
    }

    public void parseWebsocketMessage(JSONObject json) {
        String eventType = json.getStr("t"); // An operation code corresponding to the nature of the sent message (for example, success, failure, etc.)
        String msg_data = json.getStr("d"); // Data of any form depending on the underlying event
        JSONObject dataObj = (JSONObject) json.getByPath("d");
        String msg_id_replay = json.getStr("s"); // Message ID used for replaying events after a disconnect
        int opcode = json.getInt("op"); // Event name for the given message
        String server_id = dataObj.getStr("serverId");

        /**
         * ChatMessage Data
         */
        JSONObject messageObj = null;
        String msg_id = null;
        String type = null;
        String mServer_id = null;
        String channelId = null;
        String content = null;
        ChatEmbed[] embeds = new ChatEmbed[] {};
        String[] replyMessageIds = new String[] {};
        boolean isPrivate = false;
        boolean isSilent = false;
        Mentions[] mentions = new Mentions[] {};
        Instant createdAt = null;
        String createdBy = null;
        String createdByWebhookId = null;
        Instant updatedAt = null;

        if (dataObj.containsKey("message")) {
            messageObj = (JSONObject) dataObj.getByPath("message");
            msg_id = messageObj.getStr("id");
            type = messageObj.getStr("type");
            mServer_id = messageObj.getStr("serverId");
            channelId = messageObj.getStr("channelId");
            content = messageObj.getStr("content");
            embeds = new ChatEmbed[] {};
            replyMessageIds = new String[] {};
            isPrivate = messageObj.getBool("isPrivate");
            isSilent = messageObj.containsKey("isSilent") ? messageObj.getBool("isSilent") : false;
            mentions = new Mentions[] {};
            createdAt = Instant.parse(messageObj.getStr("createdAt"));
            createdBy = messageObj.getStr("createdBy");
            createdByWebhookId = messageObj.getStr("createdByWebhookId");
            updatedAt = messageObj.containsKey("updatedAt") ? Instant.parse(messageObj.getStr("updatedAt")) : null;
        }

        /**
         * ServerMember Data
         */
        JSONObject memberObj = null;
        JSONObject userObj = null;
        String user_id = null;
        User user = null;
        int[] roleIds = null;
        String nickname = null;
        Instant joinedAt = null;
        boolean isOwner = false;
        UserSummary userSummary = null;
        String reason = null;

        /**
         * ServerMemberBan Data
         */
        JSONObject serverBanObj = null;

        /**
         * ServerChannel Data
         */
        JSONObject serverChannelObj = null;

        /**
         * Webhook Data
         */
        JSONObject webhookObj = null;

        Event event = null;

        switch (eventType) {
            case "ChatMessageCreated":
            case "ChatMessageUpdated":
                event = new ChatMessageCreatedEvent(this.jg_api, server_id, new ChatMessage(msg_id, type, mServer_id, channelId, content, embeds, replyMessageIds, isPrivate, isSilent, mentions, createdAt, createdBy, createdByWebhookId, updatedAt));
                if (eventType.equals("ChatMessageCreated")) {
                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onChatMessageCreatedEvent((ChatMessageCreatedEvent) event);
                    }
                } else {
                    // It's updated, not created
                    event = new ChatMessageUpdatedEvent(this.jg_api, server_id, new ChatMessage(msg_id, type, mServer_id, channelId, content, embeds, replyMessageIds, isPrivate, isSilent, mentions, createdAt, createdBy, createdByWebhookId, updatedAt));
                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onChatMessageUpdatedEvent((ChatMessageUpdatedEvent) event);
                    }
                }
                break;
            case "ChatMessageDeleted":
                event = new ChatMessageDeletedEvent(this.jg_api, server_id, new ChatMessage(msg_id, type, mServer_id, channelId, content, embeds, replyMessageIds, isPrivate, isSilent, mentions, createdAt, createdBy, createdByWebhookId, updatedAt));
                for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                    adapter.onChatMessageDeletedEvent((ChatMessageDeletedEvent) event);
                }
                break;
            case "TeamMemberJoined":
                memberObj = (JSONObject) json.getByPath("d.member");
                userObj = (JSONObject) json.getByPath("d.member.user");
                user = new User(userObj.getStr("id"), userObj.getStr("name"), userObj.getStr("type"), userObj.getStr("avatar"), userObj.getStr("banner"), Instant.parse(userObj.getStr("createdAt")));

                roleIds = toPrimitive(memberObj.getJSONArray("roleIds").toArray(new Integer[0])); // TODO May need a fix for this, may not work
                nickname = memberObj.getStr("nickname");
                joinedAt = Instant.parse(memberObj.getStr("joinedAt"));
                isOwner = memberObj.getBool("isOwner");
                event = new TeamMemberJoinedEvent(this.jg_api, server_id, new ServerMember(user, roleIds, nickname, joinedAt, isOwner));
                for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                    adapter.onTeamMemberJoinedEvent((TeamMemberJoinedEvent) event);
                }
                break;
            case "TeamMemberRemoved":
                user_id = dataObj.getStr("userId");
                boolean isKick = dataObj.getBool("isKick");
                boolean isBan = dataObj.getBool("isBan");
                event = new TeamMemberRemovedEvent(this.jg_api, server_id, user_id, isKick, isBan);
                for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                    adapter.onTeamMemberRemovedEvent((TeamMemberRemovedEvent) event);
                }
                break;
            case "TeamMemberBanned":
            case "TeamMemberUnbanned":
                serverBanObj = (JSONObject) json.getByPath("d.serverMemberBan");
                userObj = (JSONObject) json.getByPath("d.serverMemberBan.user");
                userSummary = new UserSummary(userObj.getStr("id"), userObj.getStr("type"), userObj.getStr("name"), userObj.getStr("avatar"));
                reason = serverBanObj.getStr("reason");
                createdBy = serverBanObj.getStr("createdBy");
                createdAt = Instant.parse(serverBanObj.getStr("createdAt"));

                if (eventType.equals("TeamMemberBanned")) {
                    event = new TeamMemberBannedEvent(this.jg_api, server_id, new ServerMemberBan(userSummary, reason, createdBy, createdAt));
                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onTeamMemberBannedEvent((TeamMemberBannedEvent) event);
                    }
                } else {
                    event = new TeamMemberUnbannedEvent(this.jg_api, server_id, new ServerMemberBan(userSummary, reason, createdBy, createdAt));
                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onTeamMemberUnbannedEvent((TeamMemberUnbannedEvent) event);
                    }
                }
                break;
            case "TeamMemberUpdated":
                Object userInfo = dataObj.getObj("userInfo");
                event = new TeamMemberUpdatedEvent(this.jg_api, server_id, userInfo);
                for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                    adapter.onTeamMemberUpdatedEvent((TeamMemberUpdatedEvent) event);
                }
                break;
            case "teamRolesUpdated":
                Object[] memberRoleIds = dataObj.getJSONArray("memberRoleIds").toArray();
                event = new teamRolesUpdatedEvent(this.jg_api, server_id, memberRoleIds);
                for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                    adapter.onTeamRolesUpdatedEvent((teamRolesUpdatedEvent) event);
                }
                break;
            case "TeamChannelCreated":
            case "TeamChannelUpdated":
            case "TeamChannelDeleted":
                serverChannelObj = (JSONObject) json.getByPath("d.channel");
                String channel_id = serverChannelObj.getStr("id");
                type = serverChannelObj.getStr("type");
                String name = serverChannelObj.getStr("name");
                String topic = serverChannelObj.getStr("topic");
                createdAt = Instant.parse(serverChannelObj.getStr("createdAt"));
                createdBy = serverChannelObj.getStr("createdBy");
                updatedAt = Instant.parse(serverChannelObj.getStr("updatedAt"));
                String serverId = serverChannelObj.getStr("serverId");
                String parentId = serverChannelObj.getStr("parentId");
                String categoryId = serverChannelObj.getStr("categoryId");
                String groupId = serverChannelObj.getStr("groupId");
                boolean isPublic = serverChannelObj.getBool("isPublic");
                String archivedBy = serverChannelObj.getStr("archivedBy");
                Instant archivedAt = Instant.parse(serverChannelObj.getStr("archivedAt"));
                ServerChannel serverChannel = new ServerChannel(channel_id, type, name, topic, createdAt, createdBy, updatedAt, serverId, parentId, categoryId, groupId, isPublic, archivedBy, archivedAt);
                switch (eventType) {
                    case "TeamChannelCreated" -> event = new TeamChannelCreatedEvent(this.jg_api, server_id, serverChannel);
                    case "TeamChannelUpdated" -> event = new TeamChannelUpdatedEvent(this.jg_api, server_id, serverChannel);
                    case "TeamChannelDeleted" -> event = new TeamChannelDeletedEvent(this.jg_api, server_id, serverChannel);
                }
                for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "TeamChannelCreated" -> adapter.onTeamChannelCreatedEvent((TeamChannelCreatedEvent) event);
                        case "TeamChannelUpdated" -> adapter.onTeamChannelUpdatedEvent((TeamChannelUpdatedEvent) event);
                        case "TeamChannelDeleted" -> adapter.onTeamChannelDeletedEvent((TeamChannelDeletedEvent) event);
                    }
                }
                break;
            case "TeamWebhookCreated":
            case "TeamWebhookUpdated":
                webhookObj = dataObj.getJSONObject("webhook");
                String webhook_id = webhookObj.getStr("id");
                name = webhookObj.getStr("name");
                serverId = webhookObj.getStr("serverId");
                channelId = webhookObj.getStr("channelId");
                createdAt = Instant.parse(webhookObj.getStr("createdAt"));
                createdBy = webhookObj.getStr("createdBy");
                Instant deletedAt = Instant.parse(webhookObj.getStr("deletedAt"));
                String token = webhookObj.getStr("token");
                if (eventType.equals("TeamWebhookCreated")) {
                    event = new TeamWebhookCreatedEvent(this.jg_api, server_id, new Webhook(webhook_id, name, serverId, channelId, createdAt, createdBy, deletedAt, token));
                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onTeamWebhookCreatedEvent((TeamWebhookCreatedEvent) event);
                    }
                } else {
                    event = new TeamWebhookUpdatedEvent(this.jg_api, server_id, new Webhook(webhook_id, name, serverId, channelId, createdAt, createdBy, deletedAt, token));
                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onTeamWebhookUpdatedEvent((TeamWebhookUpdatedEvent) event);
                    }
                }
                break;
            case "DocCreated":
                break;
            case "DocUpdated":
                break;
            case "DocDeleted":
                break;
            case "ListItemCreated":
                break;
            case "ListItemUpdated":
                break;
            case "ListItemDeleted":
                break;
            case "ListItemCompleted":
                break;
            case "ListItemUncompleted":
                break;
        }
    }

    public void parseWebsocketWelcome(JSONObject json) throws InvalidOperationException {
        String user_id = json.getByPath("d.user.id").toString();
        String name = json.getByPath("d.user.name").toString();
        String type = "bot";
        Instant createdAt = Instant.parse(json.getByPath("d.user.createdAt").toString());

        User clientUser = new User(user_id, name, type, null, null, createdAt);
        this.jg_api.setupClientUser(clientUser);
    }

    public void connect() {
        try {
            webSocket = httpClient.newWebSocketBuilder()
                    .header("Authorization", "Bearer " + this.clientToken)
                    .buildAsync(this.guildedWebSocketUri, this.webSocketListener)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebSocketListener getWebSocketListener() {
        return this.webSocketListener;
    }

    public void disconnect(String reason) {
        webSocket.sendClose(WebSocket.NORMAL_CLOSURE, reason);
    }

    public void disconnect() {
        this.disconnect("Requested by Client");
    }
}
