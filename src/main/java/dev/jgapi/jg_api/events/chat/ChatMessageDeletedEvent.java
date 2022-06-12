package dev.jgapi.jg_api.events.chat;

import dev.jgapi.jg_api.entities.chat.ChatMessage;
import dev.jgapi.jg_api.JG_API;

public class ChatMessageDeletedEvent extends GenericMessageEvent {
    public ChatMessageDeletedEvent(JG_API jg_api, String serverId, ChatMessage message) {
        super(jg_api, serverId, message);
    }
}
