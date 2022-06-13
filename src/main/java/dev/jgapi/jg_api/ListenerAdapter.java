package dev.jgapi.jg_api;

import dev.jgapi.jg_api.events.chat.ChatMessageCreatedEvent;
import dev.jgapi.jg_api.events.chat.ChatMessageDeletedEvent;
import dev.jgapi.jg_api.events.chat.ChatMessageUpdatedEvent;
import dev.jgapi.jg_api.events.docs.DocCreatedEvent;
import dev.jgapi.jg_api.events.docs.DocDeletedEvent;
import dev.jgapi.jg_api.events.docs.DocUpdatedEvent;
import dev.jgapi.jg_api.events.library.ReadyEvent;
import dev.jgapi.jg_api.events.listitem.*;
import dev.jgapi.jg_api.events.teamchannel.TeamChannelCreatedEvent;
import dev.jgapi.jg_api.events.teamchannel.TeamChannelDeletedEvent;
import dev.jgapi.jg_api.events.teamchannel.TeamChannelUpdatedEvent;
import dev.jgapi.jg_api.events.teammember.*;
import dev.jgapi.jg_api.events.teamwebhook.TeamWebhookCreatedEvent;
import dev.jgapi.jg_api.events.teamwebhook.TeamWebhookUpdatedEvent;

public abstract class ListenerAdapter {
    /**
     * Ready Event
     */
    public void onReadyEvent(ReadyEvent event) {}

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
