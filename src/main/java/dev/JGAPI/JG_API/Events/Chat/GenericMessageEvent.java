package dev.JGAPI.JG_API.Events.Chat;

import dev.JGAPI.JG_API.Entities.Chat.ChatMessage;
import dev.JGAPI.JG_API.JG_API;
import dev.JGAPI.JG_API.Events.Event;

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
