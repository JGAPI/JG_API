package dev.moratto.JGAPI;

import dev.moratto.JGAPI.Events.Chat.ChatMessageCreateEvent;
import dev.moratto.JGAPI.Events.Chat.ChatMessageDeleteEvent;
import dev.moratto.JGAPI.Events.Chat.ChatMessageUpdateEvent;

public abstract class ListenerAdapter {
    public void onChatMessageCreatedEvent(ChatMessageCreateEvent event) {}
    public void onChatMessageDeletedEvent(ChatMessageDeleteEvent event) {}
    public void onChatMessageUpdatedEvent(ChatMessageUpdateEvent event) {}
}
