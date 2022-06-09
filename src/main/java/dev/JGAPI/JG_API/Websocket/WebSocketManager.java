package dev.JGAPI.JG_API.Websocket;

import cn.hutool.json.JSONObject;
import dev.JGAPI.JG_API.Events.TeamMember.*;
import dev.JGAPI.JG_API.JG_API;
import dev.JGAPI.JG_API.Entities.Channels.Mentions;
import dev.JGAPI.JG_API.Entities.Channels.ServerChannel;
import dev.JGAPI.JG_API.Entities.Chat.ChatEmbed;
import dev.JGAPI.JG_API.Entities.Chat.ChatMessage;
import dev.JGAPI.JG_API.Entities.MemberBans.ServerMemberBan;
import dev.JGAPI.JG_API.Entities.Members.ServerMember;
import dev.JGAPI.JG_API.Entities.Members.User;
import dev.JGAPI.JG_API.Entities.Members.UserSummary;
import dev.JGAPI.JG_API.Events.Chat.ChatMessageCreatedEvent;
import dev.JGAPI.JG_API.Events.Chat.ChatMessageDeletedEvent;
import dev.JGAPI.JG_API.Events.Chat.ChatMessageUpdatedEvent;
import dev.JGAPI.JG_API.Events.TeamChannel.TeamChannelCreatedEvent;
import dev.JGAPI.JG_API.Events.TeamChannel.TeamChannelDeletedEvent;
import dev.JGAPI.JG_API.Events.TeamChannel.TeamChannelUpdatedEvent;
import dev.moratto.JGAPI.Events.TeamMember.*;
import dev.JGAPI.JG_API.ListenerAdapter;

import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.URI;
import java.time.Instant;

public class WebSocketManager extends ListenerAdapter {
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

    public void parseWebsocketMessage(CharSequence data) {
        JSONObject json = new JSONObject(data.toString());
        String eventType = json.getStr("t"); // An operation code corresponding to the nature of the sent message (for example, success, failure, etc.)
        String msg_data = json.getStr("d"); // Data of any form depending on the underlying event
        String msg_id_replay = json.getStr("s"); // Message ID used for replaying events after a disconnect
        int opcode = json.getInt("op"); // Event name for the given message
        String server_id = json.getStr("d.serverId");

        /**
         * ChatMessage Data
         */
        String msg_id = json.getStr("d.message.id");
        String type = json.getStr("d.message.type");
        String mServer_id = json.getStr("d.message.serverId");
        String channelId = json.getStr("d.message.channelId");
        String content = json.getStr("d.message.content");
        ChatEmbed[] embeds = new ChatEmbed[] {};
        String[] replyMessageIds = new String[] {};
        boolean isPrivate = json.getBool("d.message.isPrivate");
        boolean isSilent = json.getBool("d.message.isSilent");
        Mentions[] mentions = new Mentions[] {};
        Instant createdAt = Instant.parse(json.getStr("d.message.createdAt"));
        String createdBy = json.getStr("d.message.createdBy");
        String createdByWebhookId = json.getStr("d.message.createdByWebhookId");
        Instant updatedAt = Instant.parse(json.getStr("d.message.updatedAt"));

        /**
         * ServerMember Data
         */
        String user_id = null;
        User user = null;
        int[] roleIds = null;
        String nickname = null;
        Instant joinedAt = null;
        boolean isOwner = false;
        UserSummary userSummary = null;
        String reason = null;
        switch (eventType) {
            case "ChatMessageCreated":
            case "ChatMessageUpdated":
                if (eventType.equals("ChatMessageCreated")) {
                    onChatMessageCreatedEvent(new ChatMessageCreatedEvent(this.jg_api, server_id, new ChatMessage(msg_id, type, mServer_id, channelId, content, embeds, replyMessageIds, isPrivate, isSilent, mentions, createdAt, createdBy, createdByWebhookId, updatedAt)));
                } else {
                    // It's updated, not created
                    onChatMessageUpdatedEvent(new ChatMessageUpdatedEvent(this.jg_api, server_id, new ChatMessage(msg_id, type, mServer_id, channelId, content, embeds, replyMessageIds, isPrivate, isSilent, mentions, createdAt, createdBy, createdByWebhookId, updatedAt)));
                }
                break;
            case "ChatMessageDeleted":
                onChatMessageDeletedEvent(new ChatMessageDeletedEvent(this.jg_api, server_id, new ChatMessage(msg_id, type, mServer_id, channelId, content, embeds, replyMessageIds, isPrivate, isSilent, mentions, createdAt, createdBy, createdByWebhookId, updatedAt)));
                break;
            case "TeamMemberJoined":
                user = new User(json.getStr("d.member.user.id"), json.getStr("d.member.user.name"), json.getStr("d.member.user.type"), json.getStr("d.member.user.avatar"), json.getStr("d.member.user.banner"), Instant.parse(json.getStr("d.member.user.createdAt")));
                roleIds = toPrimitive(json.getJSONArray("d.member.roleIds").toArray(new Integer[0])); // TODO May need a fix for this, may not work
                nickname = json.getStr("d.member.nickname");
                joinedAt = Instant.parse(json.getStr("d.member.joinedAt"));
                isOwner = json.getBool("d.member.isOwner");
                onTeamMemberJoinedEvent(new TeamMemberJoinedEvent(this.jg_api, server_id, new ServerMember(user, roleIds, nickname, joinedAt, isOwner)));
                break;
            case "TeamMemberRemoved":
                user_id = json.getStr("d.userId");
                boolean isKick = json.getBool("d.isKick");
                boolean isBan = json.getBool("d.isBan");
                onTeamMemberRemovedEvent(new TeamMemberRemovedEvent(this.jg_api, server_id, user_id, isKick, isBan));
                break;
            case "TeamMemberBanned":
            case "TeamMemberUnbanned":
                userSummary = new UserSummary(json.getStr("d.serverMemberBan.user.id"), json.getStr("d.serverMemberBan.user.type"), json.getStr("d.serverMemberBan.user.name"), json.getStr("d.serverMemberBan.user.avatar"));
                reason = json.getStr("d.serverMemberBan.reason");
                createdBy = json.getStr("d.serverMemberBan.createdBy");
                createdAt = Instant.parse(json.getStr("d.serverMemberBan.createdAt"));

                if (eventType.equals("TeamMemberBanned"))
                    onTeamMemberBannedEvent(new TeamMemberBannedEvent(this.jg_api, server_id, new ServerMemberBan(userSummary, reason, createdBy, createdAt)));
                else
                    onTeamMemberUnbannedEvent(new TeamMemberUnbannedEvent(this.jg_api, server_id, new ServerMemberBan(userSummary, reason, createdBy, createdAt)));
                break;
            case "TeamMemberUpdated":
                Object userInfo = json.getObj("d.userInfo");
                onTeamMemberUpdatedEvent(new TeamMemberUpdatedEvent(this.jg_api, server_id, userInfo));
                break;
            case "teamRolesUpdated":
                Object[] memberRoleIds = json.getJSONArray("d.memberRoleIds").toArray();
                onTeamRolesUpdatedEvent(new teamRolesUpdatedEvent(this.jg_api, server_id, memberRoleIds));
                break;
            case "TeamChannelCreated":
            case "TeamChannelUpdated":
            case "TeamChannelDeleted":
                String channel_id = json.getStr("d.channel.id");
                type = json.getStr("d.channel.type");
                String name = json.getStr("d.channel.name");
                String topic = json.getStr("d.channel.topic");
                createdAt = Instant.parse(json.getStr("d.channel.createdAt"));
                createdBy = json.getStr("d.channel.createdBy");
                updatedAt = Instant.parse(json.getStr("d.channel.updatedAt"));
                String serverId = json.getStr("d.channel.serverId");
                String parentId = json.getStr("d.channel.parentId");
                String categoryId = json.getStr("d.channel.categoryId");
                String groupId = json.getStr("d.channel.groupId");
                boolean isPublic = json.getBool("d.channel.isPublic");
                String archivedBy = json.getStr("d.channel.archivedBy");
                Instant archivedAt = Instant.parse(json.getStr("d.channel.archivedAt"));
                ServerChannel serverChannel = new ServerChannel(channel_id, type, name, topic, createdAt, createdBy, updatedAt, serverId, parentId, categoryId, groupId, isPublic, archivedBy, archivedAt);

                switch (eventType) {
                    case "TeamChannelCreated":
                        onTeamChannelCreatedEvent(new TeamChannelCreatedEvent(this.jg_api, server_id, serverChannel));
                        break;
                    case "TeamChannelUpdated":
                        onTeamChannelUpdatedEvent(new TeamChannelUpdatedEvent(this.jg_api, server_id, serverChannel));
                        break;
                    case "TeamChannelDeleted":
                        onTeamChannelDeletedEvent(new TeamChannelDeletedEvent(this.jg_api, server_id, serverChannel));
                        break;
                }
                break;
            case "TeamWebhookCreated":
                break;
            case "TeamWebhookUpdated":
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
