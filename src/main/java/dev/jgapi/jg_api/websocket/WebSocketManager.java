package dev.jgapi.jg_api.websocket;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.ListenerAdapter;
import dev.jgapi.jg_api.entities.calendars.CalendarEvent;
import dev.jgapi.jg_api.entities.calendars.rsvp.CalendarEventRsvp;
import dev.jgapi.jg_api.entities.channels.ChannelReaction;
import dev.jgapi.jg_api.entities.channels.ServerChannel;
import dev.jgapi.jg_api.entities.chat.ChatMessage;
import dev.jgapi.jg_api.entities.docs.Doc;
import dev.jgapi.jg_api.entities.forums.ForumTopic;
import dev.jgapi.jg_api.entities.listitems.ListItem;
import dev.jgapi.jg_api.entities.memberbans.ServerMemberBan;
import dev.jgapi.jg_api.entities.members.MemberRoleIds;
import dev.jgapi.jg_api.entities.members.ServerMember;
import dev.jgapi.jg_api.entities.members.User;
import dev.jgapi.jg_api.entities.members.UserInfo;
import dev.jgapi.jg_api.entities.webhooks.Webhook;
import dev.jgapi.jg_api.events.calendar.CalendarEventCreatedEvent;
import dev.jgapi.jg_api.events.calendar.CalendarEventDeletedEvent;
import dev.jgapi.jg_api.events.calendar.CalendarEventUpdatedEvent;
import dev.jgapi.jg_api.events.calendar.rsvp.CalendarEventRsvpDeletedEvent;
import dev.jgapi.jg_api.events.calendar.rsvp.CalendarEventRsvpManyUpdatedEvent;
import dev.jgapi.jg_api.events.calendar.rsvp.CalendarEventRsvpUpdatedEvent;
import dev.jgapi.jg_api.events.chat.ChatMessageCreatedEvent;
import dev.jgapi.jg_api.events.chat.ChatMessageDeletedEvent;
import dev.jgapi.jg_api.events.chat.ChatMessageUpdatedEvent;
import dev.jgapi.jg_api.events.docs.DocCreatedEvent;
import dev.jgapi.jg_api.events.docs.DocDeletedEvent;
import dev.jgapi.jg_api.events.docs.DocUpdatedEvent;
import dev.jgapi.jg_api.events.forums.ForumTopicCreatedEvent;
import dev.jgapi.jg_api.events.forums.ForumTopicDeletedEvent;
import dev.jgapi.jg_api.events.forums.ForumTopicUpdatedEvent;
import dev.jgapi.jg_api.events.library.ServerAddedEvent;
import dev.jgapi.jg_api.events.library.ServerRemovedEvent;
import dev.jgapi.jg_api.events.listitem.*;
import dev.jgapi.jg_api.events.teamchannel.*;
import dev.jgapi.jg_api.events.teammember.*;
import dev.jgapi.jg_api.events.teamwebhook.TeamWebhookCreatedEvent;
import dev.jgapi.jg_api.events.teamwebhook.TeamWebhookUpdatedEvent;
import dev.jgapi.jg_api.exceptions.InvalidOperationException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    public void parseWebsocketMessage(JSONObject json) {
        String eventType = json.getString("t"); // An operation code corresponding to the nature of the sent message (for example, success, failure, etc.)
        JSONObject dataObj = json.getJSONObject("d");
        String messageReplayId = json.getString("s"); // Message ID used for replaying events after a disconnect
        int opCode = json.getInt("op"); // Event name for the given message
        String serverId = dataObj.getString("serverId");

        switch (eventType) {
            case "ChatMessageCreated", "ChatMessageUpdated", "ChatMessageDeleted" -> {
                ChatMessage chatMessage = ChatMessage.parseChatMessageObj(dataObj.getJSONObject("message"), jg_api);

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

                if (this.jg_api.getClientUser().getId().equals(userObj.getString("id"))) {
                    for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                        listenerAdapter.onServerAddedEvent(new ServerAddedEvent(this.jg_api, serverId));
                    }
                } else {
                    ServerMember serverMember = ServerMember.parseServerMemberObj(memberObj,jg_api);

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
                ServerMemberBan serverMemberBan = ServerMemberBan.parseServerMemberBanObj(dataObj.getJSONObject("serverMemberBan"), jg_api);

                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "TeamMemberBanned" -> listenerAdapter.onTeamMemberBannedEvent(new TeamMemberBannedEvent(this.jg_api, serverId, serverMemberBan));
                        case "TeamMemberUnbanned" -> listenerAdapter.onTeamMemberUnbannedEvent(new TeamMemberUnbannedEvent(this.jg_api, serverId, serverMemberBan));
                    }
                }
            }
            case "TeamMemberUpdated" -> {
                UserInfo userInfo = UserInfo.parseUserInfoObj(dataObj.getJSONObject("userInfo"), jg_api);

                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    listenerAdapter.onTeamMemberUpdatedEvent(new TeamMemberUpdatedEvent(this.jg_api, serverId, userInfo));
                }
            }
            case "teamRolesUpdated" -> {
                JSONArray memberRoleIdsArr = dataObj.getJSONArray("memberRoleIds");
                List<MemberRoleIds> memberRoleIds = new ArrayList<>();

                for (int i = 0; i < memberRoleIdsArr.length(); i++) {
                    JSONObject memberRoleIdsObj = memberRoleIdsArr.getJSONObject(i);
                    memberRoleIds.add(MemberRoleIds.parseMemberRoleIdsObj(memberRoleIdsObj, jg_api));
                }

                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    listenerAdapter.onTeamRolesUpdatedEvent(new TeamRolesUpdatedEvent(this.jg_api, serverId, memberRoleIds.toArray(MemberRoleIds[]::new)));
                }
            }
            case "TeamChannelCreated", "TeamChannelUpdated", "TeamChannelDeleted" -> {
                ServerChannel serverChannel = ServerChannel.parseServerChannelObj(dataObj.getJSONObject("channel"), jg_api);

                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "TeamChannelCreated" -> listenerAdapter.onTeamChannelCreatedEvent(new TeamChannelCreatedEvent(this.jg_api, serverId, serverChannel));
                        case "TeamChannelUpdated" -> listenerAdapter.onTeamChannelUpdatedEvent(new TeamChannelUpdatedEvent(this.jg_api, serverId, serverChannel));
                        case "TeamChannelDeleted" -> listenerAdapter.onTeamChannelDeletedEvent(new TeamChannelDeletedEvent(this.jg_api, serverId, serverChannel));
                    }
                }
            }
            case "TeamWebhookCreated", "TeamWebhookUpdated" -> {
                Webhook webhook = Webhook.parseWebhookObj(dataObj.getJSONObject("webhook"), jg_api);

                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "TeamWebhookCreated" -> listenerAdapter.onTeamWebhookCreatedEvent(new TeamWebhookCreatedEvent(this.jg_api, serverId, webhook));
                        case "TeamWebhookUpdated" -> listenerAdapter.onTeamWebhookUpdatedEvent(new TeamWebhookUpdatedEvent(this.jg_api, serverId, webhook));
                    }
                }
            }
            case "DocCreated", "DocUpdated", "DocDeleted" -> {
                Doc doc = Doc.parseDocObj(dataObj.getJSONObject("doc"), jg_api);

                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "DocCreated" -> listenerAdapter.onDocCreatedEvent(new DocCreatedEvent(this.jg_api, serverId, doc));
                        case "DocUpdated" -> listenerAdapter.onDocUpdatedEvent(new DocUpdatedEvent(this.jg_api, serverId, doc));
                        case "DocDeleted" -> listenerAdapter.onDocDeletedEvent(new DocDeletedEvent(this.jg_api, serverId, doc));
                    }
                }
            }
            case "ListItemCreated", "ListItemUpdated", "ListItemDeleted", "ListItemCompleted", "ListItemUncompleted" -> {
                ListItem listItem = ListItem.parseListItemObj(dataObj.getJSONObject("listItem"), jg_api);

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
            case "CalendarEventCreated", "CalendarEventUpdated", "CalendarEventDeleted" -> {
                CalendarEvent calendarEvent = CalendarEvent.parseCalendarEventObj(dataObj.getJSONObject("calendarEvent"), jg_api);

                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "CalendarEventCreated" -> listenerAdapter.onCalendarEventCreatedEvent(new CalendarEventCreatedEvent(this.jg_api, serverId, calendarEvent));
                        case "CalendarEventDeleted" -> listenerAdapter.onCalendarEventDeletedEvent(new CalendarEventDeletedEvent(this.jg_api, serverId, calendarEvent));
                        case "CalendarEventUpdated" -> listenerAdapter.onCalendarEventUpdatedEvent(new CalendarEventUpdatedEvent(this.jg_api, serverId, calendarEvent));
                    }
                }
            }
            case "ChannelMessageReactionCreated", "ChannelMessageReactionDeleted" -> {
                ChannelReaction channelReaction = ChannelReaction.parseChannelReactionObj(dataObj.getJSONObject("reaction"), jg_api);

                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "ChannelMessageReactionCreated" -> listenerAdapter.onChannelMessageReactionCreatedEvent(new ChannelMessageReactionCreatedEvent(this.jg_api, serverId, channelReaction));
                        case "ChannelMessageReactionDeleted" -> listenerAdapter.onChannelMessageReactionDeletedEvent(new ChannelMessageReactionDeletedEvent(this.jg_api, serverId, channelReaction));
                    }
                }
            }
            case "ForumTopicCreated", "ForumTopicUpdated", "ForumTopicDeleted" -> {
                ForumTopic forumTopic = ForumTopic.parseForumTopicObj(dataObj.getJSONObject("forumTopic"), jg_api);
                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "ForumTopicCreated" -> listenerAdapter.onForumTopicCreatedEvent(new ForumTopicCreatedEvent(this.jg_api, serverId, forumTopic));
                        case "ForumTopicUpdated" -> listenerAdapter.onForumTopicUpdatedEvent(new ForumTopicUpdatedEvent(this.jg_api, serverId, forumTopic));
                        case "ForumTopicDeleted" -> listenerAdapter.onForumTopicDeletedEvent(new ForumTopicDeletedEvent(this.jg_api, serverId, forumTopic));
                    }
                }
            }
            case "CalendarEventRsvpUpdated", "CalendarEventRsvpDeleted" -> {
                CalendarEventRsvp calendarEventRsvp = CalendarEventRsvp.parseCalendarEventRsvpObject(dataObj.getJSONObject("calendarEventRsvp"), jg_api);
                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    switch (eventType) {
                        case "CalendarEventRsvpUpdated" -> listenerAdapter.onCalendarEventRsvpUpdatedEvent(new CalendarEventRsvpUpdatedEvent(this.jg_api, serverId, calendarEventRsvp));
                        case "CalendarEventRsvpDeleted" -> listenerAdapter.onCalendarEventRsvpDeletedEvent(new CalendarEventRsvpDeletedEvent(this.jg_api, serverId, calendarEventRsvp));
                    }
                }
            }
            case "CalendarRsvpManyUpdated" -> {
                JSONArray calendarEventRsvps = dataObj.getJSONArray("calendarEventRsvps");
                List<CalendarEventRsvp> calendarEventRsvpList = new ArrayList<>();
                for (int i = 0; i < calendarEventRsvps.length(); i++) {
                    JSONObject calendarEventRsvpObj = calendarEventRsvps.getJSONObject(i);
                    CalendarEventRsvp calendarEventRsvp = CalendarEventRsvp.parseCalendarEventRsvpObject(calendarEventRsvpObj, jg_api);
                    calendarEventRsvpList.add(calendarEventRsvp);
                }
                for (ListenerAdapter listenerAdapter : this.jg_api.getListenerAdapters()) {
                    listenerAdapter.onCalendarEventRsvpManyUpdatedEvent(new CalendarEventRsvpManyUpdatedEvent(this.jg_api, serverId, calendarEventRsvpList.toArray(CalendarEventRsvp[]::new)));
                }
            }
            default -> System.out.println("Unhandled eventType: " + eventType);
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
}
