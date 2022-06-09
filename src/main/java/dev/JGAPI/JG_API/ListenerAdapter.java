package dev.JGAPI.JG_API;

import dev.JGAPI.JG_API.Events.Chat.ChatMessageCreatedEvent;
import dev.JGAPI.JG_API.Events.Chat.ChatMessageDeletedEvent;
import dev.JGAPI.JG_API.Events.Chat.ChatMessageUpdatedEvent;
import dev.JGAPI.JG_API.Events.Docs.DocCreatedEvent;
import dev.JGAPI.JG_API.Events.Docs.DocDeletedEvent;
import dev.JGAPI.JG_API.Events.Docs.DocUpdatedEvent;
import dev.JGAPI.JG_API.Events.ListItem.*;
import dev.JGAPI.JG_API.Events.TeamChannel.TeamChannelCreatedEvent;
import dev.JGAPI.JG_API.Events.TeamChannel.TeamChannelDeletedEvent;
import dev.JGAPI.JG_API.Events.TeamChannel.TeamChannelUpdatedEvent;
import dev.JGAPI.JG_API.Events.TeamMember.*;
import dev.JGAPI.JG_API.Events.TeamWebhook.TeamWebhookCreatedEvent;
import dev.JGAPI.JG_API.Events.TeamWebhook.TeamWebhookUpdatedEvent;
import dev.moratto.JGAPI.Events.ListItem.*;
import dev.moratto.JGAPI.Events.TeamMember.*;

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
