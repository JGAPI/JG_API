package dev.moratto.JGAPI;

import dev.moratto.JGAPI.Events.Chat.ChatMessageCreatedEvent;
import dev.moratto.JGAPI.Events.Chat.ChatMessageDeletedEvent;
import dev.moratto.JGAPI.Events.Chat.ChatMessageUpdatedEvent;
import dev.moratto.JGAPI.Events.Docs.DocCreatedEvent;
import dev.moratto.JGAPI.Events.Docs.DocDeletedEvent;
import dev.moratto.JGAPI.Events.Docs.DocUpdatedEvent;
import dev.moratto.JGAPI.Events.ListItem.*;
import dev.moratto.JGAPI.Events.TeamChannel.TeamChannelCreatedEvent;
import dev.moratto.JGAPI.Events.TeamChannel.TeamChannelDeletedEvent;
import dev.moratto.JGAPI.Events.TeamChannel.TeamChannelUpdatedEvent;
import dev.moratto.JGAPI.Events.TeamWebhook.TeamWebhookCreatedEvent;
import dev.moratto.JGAPI.Events.TeamMember.*;
import dev.moratto.JGAPI.Events.TeamWebhook.TeamWebhookUpdatedEvent;

public abstract class ListenerAdapter {
    /**
     * ChatMessage Events
     */
    public void onChatMessageCreatedEvent(ChatMessageCreatedEvent event) {}
    public void onChatMessageDeletedEvent(ChatMessageDeletedEvent event) {}
    public void onChatMessageUpdatedEvent(ChatMessageUpdatedEvent event) {}

    /**
     * TeamMember Events
     */
    public void onTeamMemberJoinedEvent(TeamMemberJoinedEvent event) {}
    public void onTeamMemberRemovedEvent(TeamMemberRemovedEvent event) {}
    public void onTeamMemberUpdatedEvent(TeamMemberUpdatedEvent event) {}
    public void onTeamMemberBannedEvent(TeamMemberBannedEvent event) {}
    public void onTeamMemberUnbannedEvent(TeamMemberUnbannedEvent event) {}
    public void onTeamRolesUpdatedEvent(teamRolesUpdatedEvent event) {}

    /**
     * TeamChannel Events
     */
    public void onTeamChannelCreatedEvent(TeamChannelCreatedEvent event) {}
    public void onTeamChannelUpdatedEvent(TeamChannelUpdatedEvent event) {}
    public void onTeamChannelDeletedEvent(TeamChannelDeletedEvent event) {}

    /**
     * TeamWebhook Events
     */
    public void onTeamWebhookCreatedEvent(TeamWebhookCreatedEvent event) {}
    public void onTeamWebhookUpdatedEvent(TeamWebhookUpdatedEvent event) {}

    /**
     * Doc Events
     */
    public void onDocCreatedEvent(DocCreatedEvent event) {}
    public void onDocUpdatedEvent(DocUpdatedEvent event) {}
    public void onDocDeletedEvent(DocDeletedEvent event) {}

    /**
     * ListItem Events
     */
    public void onListItemCreatedEvent(ListItemCreatedEvent event) {}
    public void onListItemUpdatedEvent(ListItemUpdatedEvent event) {}
    public void onListItemDeletedEvent(ListItemDeletedEvent event) {}
    public void onListItemCompletedEvent(ListItemCompletedEvent event) {}
    public void onListItemUncompletedEvent(ListItemUncompletedEvent event) {}
}
