package dev.jgapi.jg_api.events.chat;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.chat.ChatMessage;
import dev.jgapi.jg_api.events.Event;

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
