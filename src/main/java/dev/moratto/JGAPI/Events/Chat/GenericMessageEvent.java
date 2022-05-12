package dev.moratto.JGAPI.Events.Chat;

import dev.moratto.JGAPI.Entities.Chat.ChatMessage;
import dev.moratto.JGAPI.Events.Event;
import dev.moratto.JGAPI.JG_API;

public abstract class GenericMessageEvent extends Event {
    ChatMessage message;

    public GenericMessageEvent(JG_API jg_api, String serverId, ChatMessage message) {
        super(jg_api, serverId);
        this.message = message;
    }

    public ChatMessage getMessage() {
        return this.message;
    }
}
