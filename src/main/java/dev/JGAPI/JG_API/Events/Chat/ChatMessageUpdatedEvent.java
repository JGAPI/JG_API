package dev.JGAPI.JG_API.Events.Chat;

import dev.JGAPI.JG_API.Entities.Chat.ChatMessage;
import dev.JGAPI.JG_API.JG_API;

public class ChatMessageUpdatedEvent extends GenericMessageEvent {
    public ChatMessageUpdatedEvent(JG_API jg_api, String serverId, ChatMessage message) {
        super(jg_api, serverId, message);
    }
}
