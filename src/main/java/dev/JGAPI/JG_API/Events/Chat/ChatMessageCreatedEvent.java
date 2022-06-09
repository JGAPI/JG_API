package dev.JGAPI.JG_API.Events.Chat;

import dev.JGAPI.JG_API.Entities.Chat.ChatMessage;
import dev.JGAPI.JG_API.JG_API;

public class ChatMessageCreatedEvent extends GenericMessageEvent {
    public ChatMessageCreatedEvent(JG_API jg_api, String serverId, ChatMessage message) {
        super(jg_api, serverId, message);
    }
}
