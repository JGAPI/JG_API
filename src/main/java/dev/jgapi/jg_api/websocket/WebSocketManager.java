package dev.jgapi.jg_api.websocket;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.ListenerAdapter;
import dev.jgapi.jg_api.entities.calendars.CalendarEvent;
import dev.jgapi.jg_api.entities.channels.ChannelReaction;
import dev.jgapi.jg_api.entities.channels.ServerChannel;
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
        String messageReplayId = json.getString("s"); // Message ID used for replaying events after a disconnect
        int opCode = json.getInt("op"); // Event name for the given message
        String serverId = dataObj.getString("serverId");

        switch (eventType) {
            case "ChatMessageCreated", "ChatMessageUpdated", "ChatMessageDeleted" -> {
                JSONObject messageObj = dataObj.getJSONObject("message");

                // TODO: Setup embeds, replyMessageIds, and mentions.
                ChatMessage chatMessage = new ChatMessage(
                        this.jg_api,
                        messageObj.getString("id"),
                        messageObj.optString("type", null),
                        messageObj.optString("serverId", null),
                        new ServerChannel(
                                this.jg_api,
                                messageObj.getString("channelId"),
                                null, null, null, null, null, null,
                                messageObj.optString("serverId", null),
                                null, -1, null, false, null, null
                        ),
                        messageObj.optString("content", null),
                        null, null,
                        messageObj.optBoolean("isPrivate", false),
                        messageObj.optBoolean("isSilent", false),
                        null,
                        instantHelper(messageObj.optString("createdAt", null)),
                        messageObj.getString("createdBy"),
                        messageObj.optString("createdByWebhookId", null),
                        instantHelper(messageObj.optString("updatedAt", null)),
                        instantHelper(messageObj.optString("deletedAt", null))
                );

                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "ChatMessageCreated" -> listenerAdapter.onChatMessageCreatedEvent(new ChatMessageCreatedEvent(this.jg_api, serverId, chatMessage));
                        case "ChatMessageUpdated" -> listenerAdapter.onChatMessageUpdatedEvent(new ChatMessageUpdatedEvent(this.jg_api, serverId, chatMessage));
                        case "ChatMessageDeleted" -> listenerAdapter.onChatMessageDeletedEvent(new ChatMessageDeletedEvent(this.jg_api, serverId, chatMessage));
                    }
                }
            }
            case "TeamMemberJoined" -> {
                JSONObject memberObj = dataObj.getJSONObject("member");
                JSONObject userObj = memberObj.getJSONObject("user");
                String userId = userObj.getString("userId");

                if (this.jg_api.getClientUser().getId().equals(userId)) {
                    for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                        listenerAdapter.onServerAddedEvent(new ServerAddedEvent(this.jg_api, serverId));
                    }
                } else {
                    ServerMember serverMember = new ServerMember(
                            this.jg_api,
                            new User(
                                    this.jg_api,
                                    userObj.getString("id"),
                                    userObj.optString("type", "user"),
                                    userObj.getString("name"),
                                    userObj.optString("avatar", null),
                                    userObj.optString("banner", null),
                                    Instant.parse(userObj.getString("createdAt"))
                            ),
                            toPrimitive(memberObj.getJSONArray("roleIds").toList().toArray(new Integer[0])),
                            memberObj.optString("nickname", null),
                            Instant.parse(memberObj.getString("joinedAt")),
                            memberObj.optBoolean("isOwner", false)
                    );

                    for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                        listenerAdapter.onTeamMemberJoinedEvent(new TeamMemberJoinedEvent(
                                this.jg_api,
                                serverId,
                                serverMember
                        ));
                    }
                }
            }
            case "TeamMemberRemoved" -> {
                String userId = dataObj.getString("userId");

                if (this.jg_api.getClientUser().getId().equals(userId)) {
                    for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                        listenerAdapter.onServerRemovedEvent(new ServerRemovedEvent(
                                this.jg_api,
                                serverId
                        ));
                    }
                } else {
                    for (ListenerAdapter adapter : this.jg_api.getListenerAdapters()) {
                        adapter.onTeamMemberRemovedEvent(new TeamMemberRemovedEvent(
                                this.jg_api,
                                serverId,
                                userId,
                                dataObj.optBoolean("isKick", false),
                                dataObj.optBoolean("isBan", false)
                        ));
                    }
                }
            }
            case "TeamMemberBanned", "TeamMemberUnbanned" -> {
                JSONObject serverBanObj = dataObj.getJSONObject("serverMemberBan");
                JSONObject userObj = dataObj.getJSONObject("user");

                ServerMemberBan serverMemberBan = new ServerMemberBan(
                        this.jg_api,
                        new UserSummary(
                                userObj.getString("id"),
                                userObj.optString("type", "user"),
                                userObj.getString("name"),
                                userObj.optString("avatar", null)
                        ),
                        serverBanObj.optString("reason", null),
                        serverBanObj.getString("createdBy"),
                        Instant.parse(serverBanObj.getString("createdAt"))
                );

                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "TeamMemberBanned" -> listenerAdapter.onTeamMemberBannedEvent(new TeamMemberBannedEvent(this.jg_api, serverId, serverMemberBan));
                        case "TeamMemberUnbanned" -> listenerAdapter.onTeamMemberUnbannedEvent(new TeamMemberUnbannedEvent(this.jg_api, serverId, serverMemberBan));
                    }
                }
            }
            case "TeamMemberUpdated" -> {
                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    listenerAdapter.onTeamMemberUpdatedEvent(new TeamMemberUpdatedEvent(
                            this.jg_api,
                            serverId,
                            dataObj.get("userInfo")
                    ));
                }
            }
            case "teamRolesUpdated" -> {
                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    listenerAdapter.onTeamRolesUpdatedEvent(new TeamRolesUpdatedEvent(
                            this.jg_api,
                            serverId,
                            dataObj.getJSONArray("memberRoleIds").toList().toArray()
                    ));
                }
            }
            case "TeamChannelCreated", "TeamChannelUpdated", "TeamChannelDeleted" -> {
                JSONObject serverChannelObj = dataObj.getJSONObject("channel");

                ServerChannel serverChannel = new ServerChannel(
                        this.jg_api,
                        serverChannelObj.getString("id"),
                        serverChannelObj.getString("type"),
                        serverChannelObj.getString("name"),
                        serverChannelObj.optString("topic", null),
                        Instant.parse(serverChannelObj.getString("createdAt")),
                        serverChannelObj.getString("createdBy"),
                        instantHelper(serverChannelObj.optString("updatedAt", null)),
                        serverChannelObj.getString("serverId"),
                        serverChannelObj.optString("parentId", null),
                        serverChannelObj.getInt("categoryId"),
                        serverChannelObj.getString("groupId"),
                        serverChannelObj.optBoolean("isPublic", false),
                        serverChannelObj.optString("archivedBy", null),
                        instantHelper(serverChannelObj.optString("archivedAt", null))
                );

                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "TeamChannelCreated" -> listenerAdapter.onTeamChannelCreatedEvent(new TeamChannelCreatedEvent(this.jg_api, serverId, serverChannel));
                        case "TeamChannelUpdated" -> listenerAdapter.onTeamChannelUpdatedEvent(new TeamChannelUpdatedEvent(this.jg_api, serverId, serverChannel));
                        case "TeamChannelDeleted" -> listenerAdapter.onTeamChannelDeletedEvent(new TeamChannelDeletedEvent(this.jg_api, serverId, serverChannel));
                    }
                }
            }
            case "TeamWebhookCreated", "TeamWebhookUpdated" -> {
                JSONObject webhookObj = dataObj.getJSONObject("webhook");

                Webhook webhook = new Webhook(
                        this.jg_api,
                        webhookObj.getString("id"),
                        webhookObj.getString("name"),
                        webhookObj.getString("serverId"),
                        webhookObj.getString("channelId"),
                        Instant.parse(webhookObj.getString("createdAt")),
                        webhookObj.getString("createdBy"),
                        instantHelper(webhookObj.optString("deletedAt", null)),
                        webhookObj.optString("token", null)
                );

                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "TeamWebhookCreated" -> listenerAdapter.onTeamWebhookCreatedEvent(new TeamWebhookCreatedEvent(this.jg_api, serverId, webhook));
                        case "TeamWebhookUpdated" -> listenerAdapter.onTeamWebhookUpdatedEvent(new TeamWebhookUpdatedEvent(this.jg_api, serverId, webhook));
                    }
                }
            }
            case "DocCreated", "DocUpdated", "DocDeleted" -> {
                JSONObject docObj = dataObj.getJSONObject("doc");

                // TODO: SETUP MENTIONS
                Doc doc = new Doc(
                        this.jg_api,
                        docObj.getInt("id"),
                        docObj.getString("serverId"),
                        docObj.getString("channelId"),
                        docObj.getString("title"),
                        docObj.getString("content"),
                        null,
                        Instant.parse(docObj.getString("createdAt")),
                        docObj.getString("createdBy"),
                        instantHelper(docObj.optString("updatedAt", null)),
                        docObj.optString("updatedBy", null)
                );

                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "DocCreated" -> listenerAdapter.onDocCreatedEvent(new DocCreatedEvent(this.jg_api, serverId, doc));
                        case "DocUpdated" -> listenerAdapter.onDocUpdatedEvent(new DocUpdatedEvent(this.jg_api, serverId, doc));
                        case "DocDeleted" -> listenerAdapter.onDocDeletedEvent(new DocDeletedEvent(this.jg_api, serverId, doc));
                    }
                }
            }
            case "ListItemCreated", "ListItemUpdated", "ListItemDeleted", "ListItemCompleted", "ListItemUncompleted" -> {
                JSONObject listItemObj = dataObj.getJSONObject("listItem");
                JSONObject noteObj = listItemObj.optJSONObject("note", null);

                // TODO: SETUP MENTIONS
                ListItem listItem = new ListItem(
                        this.jg_api,
                        listItemObj.getString("id"),
                        listItemObj.getString("serverId"),
                        listItemObj.getString("channelId"),
                        listItemObj.getString("message"),
                        null,
                        Instant.parse(listItemObj.getString("createdAt")),
                        listItemObj.getString("createdBy"),
                        listItemObj.optString("createdByWebhookId", null),
                        instantHelper(listItemObj.optString("updatedAt", null)),
                        listItemObj.optString("updatedBy", null),
                        listItemObj.optString("parentListItemId", null),
                        instantHelper(listItemObj.optString("completedAt", null)),
                        listItemObj.optString("completedBy", null),
                        noteObj == null ? null : new ListItemNote(
                                Instant.parse(noteObj.getString("createdAt")),
                                noteObj.getString("createdBy"),
                                instantHelper(noteObj.optString("updatedAt", null)),
                                noteObj.optString("updatedBy", null),
                                null,
                                noteObj.getString("content")
                        )
                );

                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "ListItemCreated" -> listenerAdapter.onListItemCreatedEvent(new ListItemCreatedEvent(this.jg_api, serverId, listItem));
                        case "ListItemUpdated" -> listenerAdapter.onListItemUpdatedEvent(new ListItemUpdatedEvent(this.jg_api, serverId, listItem));
                        case "ListItemDeleted" -> listenerAdapter.onListItemDeletedEvent(new ListItemDeletedEvent(this.jg_api, serverId, listItem));
                        case "ListItemCompleted" -> listenerAdapter.onListItemCompletedEvent(new ListItemCompletedEvent(this.jg_api, serverId, listItem));
                        case "ListItemUncompleted" -> listenerAdapter.onListItemUncompletedEvent(new ListItemUncompletedEvent(this.jg_api, serverId, listItem));
                    }
                }
            }
            // TODO: Once CalendarEventUpdated actually passes a CalendarEvent it needs to be added here.
            case "CalendarEventCreated", "CalendarEventDeleted" -> {
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

                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "CalendarEventCreated" -> listenerAdapter.onCalendarEventCreatedEvent(new CalendarEventCreatedEvent(this.jg_api, serverId, calendarEvent));
                        case "CalendarEventDeleted" -> listenerAdapter.onCalendarEventDeletedEvent(new CalendarEventDeletedEvent(this.jg_api, serverId, calendarEvent));
                    }
                }
            }
            case "ChannelMessageReactionCreated", "ChannelMessageReactionDeleted" -> {
                JSONObject reactionObj = dataObj.getJSONObject("reaction");
                JSONObject emoteObj = reactionObj.getJSONObject("emote");

                ChannelReaction channelReaction = new ChannelReaction(
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

                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "ChannelMessageReactionCreated" -> listenerAdapter.onChannelMessageReactionCreatedEvent(new ChannelMessageReactionCreatedEvent(this.jg_api, serverId, channelReaction));
                        case "ChannelMessageReactionDeleted" -> listenerAdapter.onChannelMessageReactionDeletedEvent(new ChannelMessageReactionDeletedEvent(this.jg_api, serverId, channelReaction));
                    }
                }
            }
        }
    }

    public void parseWebsocketWelcome(JSONObject json) throws InvalidOperationException {
        JSONObject dataObj = json.getJSONObject("d");
        JSONObject userObj = dataObj.getJSONObject("user");

        User clientUser = new User(
                this.jg_api,
                userObj.getString("id"),
                userObj.getString("name"),
                "bot", null, null,
                Instant.parse(userObj.getString("createdAt"))
        );


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
