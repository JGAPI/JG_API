package dev.moratto.JGAPI;

import dev.moratto.JGAPI.Events.Chat.ChatMessageCreatedEvent;
import dev.moratto.JGAPI.Events.Chat.ChatMessageDeletedEvent;
import dev.moratto.JGAPI.Events.Chat.ChatMessageUpdatedEvent;

public abstract class ListenerAdapter {
    public void onChatMessageCreatedEvent(ChatMessageCreatedEvent event) {}
    public void onChatMessageDeletedEvent(ChatMessageDeletedEvent event) {}
    public void onChatMessageUpdatedEvent(ChatMessageUpdatedEvent event) {}
}
