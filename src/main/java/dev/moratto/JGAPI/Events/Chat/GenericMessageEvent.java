package dev.moratto.JGAPI.Events.Chat;

import dev.moratto.JGAPI.Entities.Chat.ChatMessage;
import dev.moratto.JGAPI.Events.GenericEvent;

public class GenericMessageEvent extends GenericEvent {
    private ChatMessage message;

    public GenericMessageEvent(ChatMessage message) {
        this.message = message;
    }

    public ChatMessage getMessage() {
        return this.message;
    }
}
