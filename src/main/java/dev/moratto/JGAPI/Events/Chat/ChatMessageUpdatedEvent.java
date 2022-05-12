package dev.moratto.JGAPI.Events.Chat;

import dev.moratto.JGAPI.Entities.Chat.ChatMessage;

public class ChatMessageUpdatedEvent extends GenericMessageEvent {
    private String serverId;
    private ChatMessage message;
    public ChatMessageUpdatedEvent(String serverId, ChatMessage message) {
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
