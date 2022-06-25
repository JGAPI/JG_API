package dev.jgapi.jg_api.websocket;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.ListenerAdapter;
import dev.jgapi.jg_api.entities.calendars.CalendarEvent;
import dev.jgapi.jg_api.entities.channels.ChannelReaction;
import dev.jgapi.jg_api.entities.channels.Mentions;
import dev.jgapi.jg_api.entities.channels.ServerChannel;
import dev.jgapi.jg_api.entities.chat.ChatEmbed;
import dev.jgapi.jg_api.entities.chat.ChatMessage;
import dev.jgapi.jg_api.entities.docs.Doc;
import dev.jgapi.jg_api.entities.emotes.Emote;
import dev.jgapi.jg_api.entities.listitems.ListItem;
import dev.jgapi.jg_api.entities.listitems.ListItemNote;
import dev.jgapi.jg_api.entities.memberbans.ServerMemberBan;
import dev.jgapi.jg_api.entities.members.ServerMember;
import dev.jgapi.jg_api.entities.members.User;
import dev.jgapi.jg_api.entities.members.UserSummary;
import dev.jgapi.jg_api.entities.webhooks.Webhook;
import dev.jgapi.jg_api.events.Event;
import dev.jgapi.jg_api.events.calendar.CalendarEventCreatedEvent;
import dev.jgapi.jg_api.events.calendar.CalendarEventDeletedEvent;
import dev.jgapi.jg_api.events.chat.ChatMessageCreatedEvent;
import dev.jgapi.jg_api.events.chat.ChatMessageDeletedEvent;
import dev.jgapi.jg_api.events.chat.ChatMessageUpdatedEvent;
import dev.jgapi.jg_api.events.docs.DocCreatedEvent;
import dev.jgapi.jg_api.events.docs.DocDeletedEvent;
import dev.jgapi.jg_api.events.docs.DocUpdatedEvent;
import dev.jgapi.jg_api.events.library.ServerAddedEvent;
import dev.jgapi.jg_api.events.library.ServerRemovedEvent;
import dev.jgapi.jg_api.events.listitem.*;
import dev.jgapi.jg_api.events.teamchannel.*;
import dev.jgapi.jg_api.events.teammember.*;
import dev.jgapi.jg_api.events.teamwebhook.TeamWebhookCreatedEvent;
import dev.jgapi.jg_api.events.teamwebhook.TeamWebhookUpdatedEvent;
import dev.jgapi.jg_api.exceptions.InvalidOperationException;
import org.json.JSONObject;

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
        String eventType = json.getString("t"); // An operation code corresponding to the nature of the sent message (for example, success, failure, etc.)
        JSONObject dataObj = json.getJSONObject("d");
        String msg_id_replay = json.getString("s"); // Message ID used for replaying events after a disconnect
        int opcode = json.getInt("op"); // Event name for the given message
        String server_id = dataObj.getString("serverId");

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
        Mentions mentions = null;
        Instant createdAt = null;
        String createdBy = null;
        String createdByWebhookId = null;
        Instant updatedAt = null;

        if (dataObj.has("message")) {
            messageObj = dataObj.getJSONObject("message");
            msg_id = messageObj.getString("id");
            type = messageObj.optString("type", null);
            mServer_id = messageObj.optString("serverId", null);
            channelId = messageObj.getString("channelId");
            content = messageObj.optString("content", null);
            embeds = new ChatEmbed[] {};
            replyMessageIds = new String[] {};
            isPrivate = messageObj.optBoolean("isPrivate", false);
            isSilent = messageObj.optBoolean("isSilent", false);
            mentions = null; // TODO Set up
            createdAt = Instant.parse(messageObj.getString("createdAt"));
            createdBy = messageObj.getString("createdBy");
            createdByWebhookId = messageObj.optString("createdByWebhookId", null);
            updatedAt = instantHelper(messageObj.optString("updatedAt", null));
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
                event = new ChatMessageCreatedEvent(this.jg_api, server_id, new ChatMessage(this.jg_api, msg_id, type, mServer_id, channelId, content, embeds, replyMessageIds, isPrivate, isSilent, mentions, createdAt, createdBy, createdByWebhookId, updatedAt));
                if (eventType.equals("ChatMessageCreated")) {
                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onChatMessageCreatedEvent((ChatMessageCreatedEvent) event);
                    }
                } else {
                    // It's updated, not created
                    event = new ChatMessageUpdatedEvent(this.jg_api, server_id, new ChatMessage(this.jg_api, msg_id, type, mServer_id, channelId, content, embeds, replyMessageIds, isPrivate, isSilent, mentions, createdAt, createdBy, createdByWebhookId, updatedAt));
                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onChatMessageUpdatedEvent((ChatMessageUpdatedEvent) event);
                    }
                }
                break;
            case "ChatMessageDeleted":
                event = new ChatMessageDeletedEvent(this.jg_api, server_id, new ChatMessage(this.jg_api, msg_id, type, mServer_id, channelId, content, embeds, replyMessageIds, isPrivate, isSilent, mentions, createdAt, createdBy, createdByWebhookId, updatedAt));
                for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                    adapter.onChatMessageDeletedEvent((ChatMessageDeletedEvent) event);
                }
                break;
            case "TeamMemberJoined":
                memberObj = dataObj.getJSONObject("member");
                userObj = memberObj.getJSONObject("user");
                String userId = userObj.getString("id");

                if (userId.equals(jg_api.getClientUser().getId())) {
                    event = new ServerAddedEvent(this.jg_api, server_id);

                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onServerAddedEvent((ServerAddedEvent) event);
                    }
                } else {
                    user = new User(
                            userObj.getString("id"),
                            userObj.getString("name"),
                            userObj.optString("type", "user"),
                            userObj.optString("avatar", ""),
                            userObj.optString("banner", ""),
                            Instant.parse(userObj.getString("createdAt"))
                    );

                    roleIds = toPrimitive(memberObj.getJSONArray("roleIds").toList().toArray(new Integer[0])); // TODO May need a fix for this, may not work
                    nickname = memberObj.optString("nickname", "");
                    isOwner = memberObj.optBoolean("isOwner", false);
                    joinedAt = Instant.parse(memberObj.getString("joinedAt"));
                    event = new TeamMemberJoinedEvent(this.jg_api, server_id, new ServerMember(this.jg_api, user, roleIds, nickname, joinedAt, isOwner));
                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onTeamMemberJoinedEvent((TeamMemberJoinedEvent) event);
                    }
                }
                break;
            case "TeamMemberRemoved":
                userId = dataObj.getString("userId");

                if (userId.equals(jg_api.getClientUser().getId())) {
                    event = new ServerRemovedEvent(this.jg_api, server_id);

                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onServerRemovedEvent((ServerRemovedEvent) event);
                    }
                } else {
                    boolean isKick = dataObj.optBoolean("isKick", false);
                    boolean isBan = dataObj.optBoolean("isBan", false);
                    event = new TeamMemberRemovedEvent(this.jg_api, server_id, userId, isKick, isBan);
                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onTeamMemberRemovedEvent((TeamMemberRemovedEvent) event);
                    }
                }
                break;
            case "TeamMemberBanned":
            case "TeamMemberUnbanned":
                serverBanObj = json.getJSONObject("d.serverMemberBan");
                userObj = json.getJSONObject("d.serverMemberBan.user");
                userSummary = new UserSummary(userObj.getString("id"), userObj.getString("type"), userObj.getString("name"), userObj.getString("avatar"));
                reason = serverBanObj.getString("reason");
                createdBy = serverBanObj.getString("createdBy");
                createdAt = Instant.parse(serverBanObj.getString("createdAt"));

                if (eventType.equals("TeamMemberBanned")) {
                    event = new TeamMemberBannedEvent(this.jg_api, server_id, new ServerMemberBan(this.jg_api, userSummary, reason, createdBy, createdAt));
                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onTeamMemberBannedEvent((TeamMemberBannedEvent) event);
                    }
                } else {
                    event = new TeamMemberUnbannedEvent(this.jg_api, server_id, new ServerMemberBan(this.jg_api, userSummary, reason, createdBy, createdAt));
                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onTeamMemberUnbannedEvent((TeamMemberUnbannedEvent) event);
                    }
                }
                break;
            case "TeamMemberUpdated":
                Object userInfo = dataObj.get("userInfo");
                event = new TeamMemberUpdatedEvent(this.jg_api, server_id, userInfo);
                for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                    adapter.onTeamMemberUpdatedEvent((TeamMemberUpdatedEvent) event);
                }
                break;
            case "teamRolesUpdated":
                Object[] memberRoleIds = dataObj.getJSONArray("memberRoleIds").toList().toArray();
                event = new teamRolesUpdatedEvent(this.jg_api, server_id, memberRoleIds);
                for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                    adapter.onTeamRolesUpdatedEvent((teamRolesUpdatedEvent) event);
                }
                break;
            case "TeamChannelCreated":
            case "TeamChannelUpdated":
            case "TeamChannelDeleted":
                serverChannelObj = json.getJSONObject("d.channel");
                String channel_id = serverChannelObj.getString("id");
                type = serverChannelObj.getString("type");
                String name = serverChannelObj.getString("name");
                String topic = serverChannelObj.getString("topic");
                createdAt = Instant.parse(serverChannelObj.getString("createdAt"));
                createdBy = serverChannelObj.getString("createdBy");
                updatedAt = Instant.parse(serverChannelObj.getString("updatedAt"));
                String serverId = serverChannelObj.getString("serverId");
                String parentId = serverChannelObj.getString("parentId");
                String categoryId = serverChannelObj.getString("categoryId");
                String groupId = serverChannelObj.getString("groupId");
                boolean isPublic = serverChannelObj.getBoolean("isPublic");
                String archivedBy = serverChannelObj.getString("archivedBy");
                Instant archivedAt = Instant.parse(serverChannelObj.getString("archivedAt"));
                ServerChannel serverChannel = new ServerChannel(this.jg_api, channel_id, type, name, topic, createdAt, createdBy, updatedAt, serverId, parentId, categoryId, groupId, isPublic, archivedBy, archivedAt);
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
                String webhook_id = webhookObj.getString("id");
                name = webhookObj.getString("name");
                serverId = webhookObj.getString("serverId");
                channelId = webhookObj.getString("channelId");
                createdAt = Instant.parse(webhookObj.getString("createdAt"));
                createdBy = webhookObj.getString("createdBy");
                Instant deletedAt = Instant.parse(webhookObj.getString("deletedAt"));
                String token = webhookObj.getString("token");
                if (eventType.equals("TeamWebhookCreated")) {
                    event = new TeamWebhookCreatedEvent(this.jg_api, server_id, new Webhook(this.jg_api, webhook_id, name, serverId, channelId, createdAt, createdBy, deletedAt, token));
                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onTeamWebhookCreatedEvent((TeamWebhookCreatedEvent) event);
                    }
                } else {
                    event = new TeamWebhookUpdatedEvent(this.jg_api, server_id, new Webhook(this.jg_api, webhook_id, name, serverId, channelId, createdAt, createdBy, deletedAt, token));
                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onTeamWebhookUpdatedEvent((TeamWebhookUpdatedEvent) event);
                    }
                }
                break;
            case "DocCreated":
            case "DocUpdated":
            case "DocDeleted":
                JSONObject docObj = dataObj.getJSONObject("doc");
                int docId = docObj.getInt("id");
                serverId = docObj.getString("serverId");
                channelId = docObj.getString("channelId");
                String title = docObj.getString("title");
                content = docObj.getString("content");
                mentions = null; // TODO Set up
                createdAt = Instant.parse(docObj.getString("createdAt"));
                createdBy = docObj.getString("createdBy");
                updatedAt = Instant.parse(docObj.getString("updatedAt"));
                String updatedBy = docObj.getString("updatedBy");
                switch (eventType) {
                    case "DocCreated":
                        event = new DocCreatedEvent(this.jg_api, server_id, new Doc(this.jg_api, docId, serverId, channelId, title, content, mentions, createdAt, createdBy, updatedAt, updatedBy));
                        for (ListenerAdapter adapters : this.jg_api.getListenerAdapters()) {
                            adapters.onDocCreatedEvent((DocCreatedEvent) event);
                        }
                        break;
                    case "DocUpdated":
                        event = new DocUpdatedEvent(this.jg_api, server_id, new Doc(this.jg_api, docId, serverId, channelId, title, content, mentions, createdAt, createdBy, updatedAt, updatedBy));
                        for (ListenerAdapter adapters : this.jg_api.getListenerAdapters()) {
                            adapters.onDocUpdatedEvent((DocUpdatedEvent) event);
                        }
                        break;
                    case "DocDeleted":
                        event = new DocDeletedEvent(this.jg_api, server_id, new Doc(this.jg_api, docId, serverId, channelId, title, content, mentions, createdAt, createdBy, updatedAt, updatedBy));
                        for (ListenerAdapter adapters : this.jg_api.getListenerAdapters()) {
                            adapters.onDocDeletedEvent((DocDeletedEvent) event);
                        }
                        break;
                }
                break;
            case "ListItemCreated":
            case "ListItemUpdated":
            case "ListItemDeleted":
            case "ListItemCompleted":
            case "ListItemUncompleted":
                JSONObject listItemObj = dataObj.getJSONObject("listItem");
                String listId = listItemObj.getString("id");
                serverId = listItemObj.getString("serverId");
                channelId = listItemObj.getString("channelId");
                String message = listItemObj.getString("message");
                mentions = null; // TODO Set up
                createdAt = Instant.parse(listItemObj.getString("createdAt"));
                createdBy = listItemObj.getString("createdBy");
                createdByWebhookId = listItemObj.getString("createdByWebhookId");
                updatedAt = Instant.parse(listItemObj.getString("updatedAt"));
                updatedBy = listItemObj.getString("updatedBy");
                String parentListItemId = listItemObj.getString("parentListItemId");
                Instant completedAt = Instant.parse(listItemObj.getString("completedAt"));
                String completedBy = listItemObj.getString("completedBy");
                JSONObject noteObj = listItemObj.getJSONObject("note");
                Mentions mentions2 = null; // TODO Set up
                ListItemNote note = new ListItemNote(mentions2, Instant.parse(noteObj.getString("createdAt")), noteObj.getString("createdBy"), Instant.parse(noteObj.getString("updatedAt")), noteObj.getString("updatedBy"), noteObj.getString("content"));
                ListItem listItem = new ListItem(this.jg_api, listId, serverId, channelId, message, mentions, createdAt, createdBy, createdByWebhookId, updatedAt, updatedBy, parentListItemId, completedAt, completedBy, note);
                switch (eventType) {
                    case "ListItemCreated":
                        event = new ListItemCreatedEvent(this.jg_api, server_id, listItem);
                        for (ListenerAdapter adapters : this.jg_api.getListenerAdapters()) {
                            adapters.onListItemCreatedEvent((ListItemCreatedEvent) event);
                        }
                        break;
                    case "ListItemUpdated":
                        event = new ListItemUpdatedEvent(this.jg_api, server_id, listItem);
                        for (ListenerAdapter adapters : this.jg_api.getListenerAdapters()) {
                            adapters.onListItemUpdatedEvent((ListItemUpdatedEvent) event);
                        }
                        break;
                    case "ListItemDeleted":
                        event = new ListItemDeletedEvent(this.jg_api, server_id, listItem);
                        for (ListenerAdapter adapters : this.jg_api.getListenerAdapters()) {
                            adapters.onListItemDeletedEvent((ListItemDeletedEvent) event);
                        }
                        break;
                    case "ListItemCompleted":
                        event = new ListItemCompletedEvent(this.jg_api, server_id, listItem);
                        for (ListenerAdapter adapters : this.jg_api.getListenerAdapters()) {
                            adapters.onListItemCompletedEvent((ListItemCompletedEvent) event);
                        }
                        break;
                    case "ListItemUncompleted":
                        event = new ListItemUncompletedEvent(this.jg_api, server_id, listItem);
                        for (ListenerAdapter adapters : this.jg_api.getListenerAdapters()) {
                            adapters.onListItemUncompletedEvent((ListItemUncompletedEvent) event);
                        }
                        break;
                }
                break;
            case "CalendarEventCreated":
            // TODO: Once CalendarEventupdated actually passes a CalendarEvent this needs to be uncommented.
            // case "CalendarEventUpdated":
            case "CalendarEventDeleted":
                JSONObject calendarEventObj = dataObj.getJSONObject("calendarEvent");
                //TODO: FIX MENTIONS & CANCELLATION
                CalendarEvent calendarEvent = new CalendarEvent(
                        this.jg_api,
                        calendarEventObj.getInt("id"),
                        calendarEventObj.getString("serverId"),
                        calendarEventObj.getString("channelId"),
                        calendarEventObj.getString("name"),
                        calendarEventObj.optString("description", null),
                        calendarEventObj.optString("location", null),
                        calendarEventObj.optString("url", null),
                        calendarEventObj.optInt("color", 0),
                        Instant.parse(calendarEventObj.getString("startsAt")),
                        calendarEventObj.optInt("duration", 1),
                        calendarEventObj.optBoolean("isPrivate", false),
                        null,
                        Instant.parse(calendarEventObj.getString("createdAt")),
                        null
                );

                switch (eventType) {
                    case "CalendarEventCreated" -> {
                        event = new CalendarEventCreatedEvent(this.jg_api, server_id, calendarEvent);
                        for (ListenerAdapter adapters : this.jg_api.getListenerAdapters()) {
                            adapters.onCalendarEventCreatedEvent((CalendarEventCreatedEvent) event);
                        }
                    }
                    // TODO: REFER TO COMMENT ON L407
                    /*
                    case "CalendarEventUpdated" -> {
                        event = new CalendarEventUpdatedEvent(this.jg_api, server_id, calendarEvent);
                        for (ListenerAdapter adapters : this.jg_api.getListenerAdapters()) {
                            adapters.onCalendarEventUpdatedEvent((CalendarEventUpdatedEvent) event);
                        }
                    }
                    */
                    case "CalendarEventDeleted" -> {
                        event = new CalendarEventDeletedEvent(this.jg_api, server_id, calendarEvent);
                        for (ListenerAdapter adapters : this.jg_api.getListenerAdapters()) {
                            adapters.onCalendarEventDeletedEvent((CalendarEventDeletedEvent) event);
                        }
                    }
                }

                break;
            case "ChannelMessageReactionCreated":
            case "ChannelMessageReactionDeleted":
                JSONObject reactionObj = dataObj.getJSONObject("reaction");
                JSONObject emoteObj = reactionObj.getJSONObject("emote");
                ChannelReaction reaction = new ChannelReaction(
                        this.jg_api,
                        reactionObj.getString("channelId"),
                        reactionObj.getString("messageId"),
                        reactionObj.getString("createdBy"),
                        new Emote(
                                this.jg_api,
                                emoteObj.getInt("id"),
                                emoteObj.getString("name"),
                                emoteObj.getString("url")
                        )
                );

                switch (eventType) {
                    case "ChannelMessageReactionCreated" -> {
                        event = new ChannelMessageReactionCreatedEvent(this.jg_api, server_id, reaction);
                        for (ListenerAdapter adapters : this.jg_api.getListenerAdapters()) {
                            adapters.onChannelMessageReactionCreatedEvent((ChannelMessageReactionCreatedEvent) event);
                        }
                    }
                    case "ChannelMessageReactionDeleted" -> {
                        event = new ChannelMessageReactionDeletedEvent(this.jg_api, server_id, reaction);
                        for (ListenerAdapter adapters : this.jg_api.getListenerAdapters()) {
                            adapters.onChannelMessageReactionDeletedEvent((ChannelMessageReactionDeletedEvent) event);
                        }
                    }
                }

                break;
        }
    }

    public void parseWebsocketWelcome(JSONObject json) throws InvalidOperationException {
        JSONObject dataObj = json.getJSONObject("d");
        JSONObject userObj = dataObj.getJSONObject("user");
        String user_id = userObj.getString("id");
        String name = userObj.getString("name");
        String type = "bot";
        Instant createdAt = Instant.parse(userObj.getString("createdAt"));

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

    private Instant instantHelper(String stringTime) {
        Instant instantTime = null;

        if (stringTime != null) {
            instantTime = Instant.parse(stringTime);
        }

        return instantTime;
    }
}
