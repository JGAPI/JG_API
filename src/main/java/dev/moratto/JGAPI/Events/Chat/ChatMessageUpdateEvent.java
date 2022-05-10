package dev.moratto.JGAPI.Events.Chat;

import dev.moratto.JGAPI.Entities.Chat.ChatMessage;

public class ChatMessageUpdateEvent {
    private String serverId;
    private ChatMessage message;
    public ChatMessageUpdateEvent(String serverId, ChatMessage message) {
        this.serverId = serverId;
        this.message = message;
    }
}
