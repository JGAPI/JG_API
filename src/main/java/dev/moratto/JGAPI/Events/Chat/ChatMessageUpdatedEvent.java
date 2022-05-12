package dev.moratto.JGAPI.Events.Chat;

import dev.moratto.JGAPI.Entities.Chat.ChatMessage;
import dev.moratto.JGAPI.JG_API;

public class ChatMessageUpdatedEvent extends GenericMessageEvent {
    public ChatMessageUpdatedEvent(JG_API jg_api, String serverId, ChatMessage message) {
        super(jg_api, serverId, message);
    }
}
