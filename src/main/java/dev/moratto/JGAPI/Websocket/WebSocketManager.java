package dev.moratto.JGAPI.Websocket;

import cn.hutool.json.JSONObject;
import dev.moratto.JGAPI.Entities.Channels.Mentions;
import dev.moratto.JGAPI.Entities.Chat.ChatEmbed;
import dev.moratto.JGAPI.Entities.Chat.ChatMessage;
import dev.moratto.JGAPI.Events.Chat.ChatMessageCreatedEvent;
import dev.moratto.JGAPI.Events.Chat.ChatMessageUpdatedEvent;
import dev.moratto.JGAPI.JG_API;
import dev.moratto.JGAPI.ListenerAdapter;

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

    public void parseWebsocketMessage(CharSequence data) {
        JSONObject json = new JSONObject(data.toString());
        String eventType = json.getStr("t"); // An operation code corresponding to the nature of the sent message (for example, success, failure, etc.)
        String msg_data = json.getStr("d"); // Data of any form depending on the underlying event
        String msg_id_replay = json.getStr("s"); // Message ID used for replaying events after a disconnect
        int opcode = json.getInt("op"); // Event name for the given message
        switch (eventType) {
            case "ChatMessageCreated":
            case "ChatMessageUpdated":
                String server_id = json.getStr("d.serverId");
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
                if (eventType.equals("ChatMessageCreated")) {
                    onChatMessageCreatedEvent(new ChatMessageCreatedEvent(this.jg_api, server_id, new ChatMessage(msg_id, type, mServer_id, channelId, content, embeds, replyMessageIds, isPrivate, isSilent, mentions, createdAt, createdBy, createdByWebhookId, updatedAt)));
                } else {
                    // It's updated
                    onChatMessageUpdatedEvent(new ChatMessageUpdatedEvent(this.jg_api, server_id, new ChatMessage(msg_id, type, mServer_id, channelId, content, embeds, replyMessageIds, isPrivate, isSilent, mentions, createdAt, createdBy, createdByWebhookId, updatedAt)));
                }
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
