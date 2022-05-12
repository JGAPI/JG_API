package dev.moratto.JGAPI.Websocket;

import dev.moratto.JGAPI.Entities.Chat.ChatEmbed;
import dev.moratto.JGAPI.Entities.Chat.ChatMessage;
import dev.moratto.JGAPI.Events.Chat.ChatMessageCreatedEvent;
import dev.moratto.JGAPI.Events.Chat.ChatMessageUpdatedEvent;
import dev.moratto.JGAPI.ListenerAdapter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.Instant;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.URI;

public class WebSocketManager extends ListenerAdapter {
    private WebSocket webSocket;
    private WebSocketListener webSocketListener;
    private URI guildedWebSocketUri;
    private HttpClient httpClient;
    private String clientToken;

    /**
     * Constructs a WebSocketManager object.
     * @param clientToken The client's token to log in with.
     */
    public WebSocketManager(String clientToken) {
        try {
            this.clientToken = clientToken;

            guildedWebSocketUri = new URI("wss://api.guilded.gg/v1/websocket");
            webSocketListener = new WebSocketListener(this);
            httpClient = HttpClient.newHttpClient();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void parseWebsocketMessage(CharSequence data) {
        try {
            Object obj = new JSONParser().parse(data.toString());
            JSONObject jo = (JSONObject) obj;
            if (!jo.containsKey("t") || !jo.containsKey("d"))
                return;
            String eventType = (String) jo.get("t");
            JSONObject jsonData = (JSONObject) jo.get("d");
            switch (eventType) {
                case "ChatMessageCreated":
                case "ChatMessageUpdated":
                    // Set up the ChatMessage, then run the event
                    String serverId = (String) jsonData.get("serverId");
                    JSONObject message = (JSONObject) jsonData.get("message");
                    String messageId = (String) message.get("id");
                    String messageType = (String) message.get("type");
                    String channelId = (String) message.get("channelId");
                    String content = (String) message.get("content");
                    boolean isPrivate = (boolean) message.get("isPrivate");
                    Instant createdAt = Instant.parse((String) message.get("createdAt"));
                    String createdBy = (String) message.get("createdBy");
                    String mServerId = (String) message.get("serverId");
                    ChatEmbed[] embeds = new ChatEmbed[] {};
                    String[] replyMessageIds = new String[] {};
                    String createdByWebhookId = null;
                    Instant updatedAt = null;
                    // TODO Need to parse `embeds` and the rest below it still
                    if (eventType.equals("ChatMessageCreated"))
                        onChatMessageCreatedEvent(new ChatMessageCreatedEvent(serverId, new ChatMessage(messageId, messageType, mServerId, channelId, content, embeds, replyMessageIds, isPrivate, createdAt, createdBy, createdByWebhookId, updatedAt)));
                    else
                        onChatMessageUpdatedEvent(new ChatMessageUpdatedEvent(serverId, new ChatMessage(messageId, messageType, mServerId, channelId, content, embeds, replyMessageIds, isPrivate, createdAt, createdBy, createdByWebhookId, updatedAt)));
                    break;
                case "ChatMessageDeleted":
                    break;
                case "TeamMemberJoined":
                    break;
                case "TeamMemberRemoved":
                    break;
                case "TeamMemberBanned":
                    break;
                case "TeamMemberUnbanned":
                    break;
                case "TeamMemberUpdated":
                    break;
                case "teamRolesUpdated":
                    break;
                case "TeamChannelCreated":
                    break;
                case "TeamChannelUpdated":
                    break;
                case "TeamChannelDeleted":
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
        } catch (ParseException e) {
            throw new RuntimeException(e);
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
