package dev.moratto.JGAPI.Events.Chat;

import dev.moratto.JGAPI.Entities.Chat.ChatMessage;

public class ChatMessageUpdateEvent extends GenericMessageEvent {
    private String serverId;
    private ChatMessage message;
    public ChatMessageUpdateEvent(String serverId, ChatMessage message) {
        this.serverId = serverId;
        this.message = message;
    }
    public ChatMessage getMessage() {
        return this.message;
    }
    public String getServerId() {
        return this.serverId;
    }
}
