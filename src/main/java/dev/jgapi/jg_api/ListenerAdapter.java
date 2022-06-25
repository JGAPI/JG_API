package dev.jgapi.jg_api;

import dev.jgapi.jg_api.events.calendar.*;
import dev.jgapi.jg_api.events.chat.*;
import dev.jgapi.jg_api.events.docs.*;
import dev.jgapi.jg_api.events.library.*;
import dev.jgapi.jg_api.events.listitem.*;
import dev.jgapi.jg_api.events.teamchannel.*;
import dev.jgapi.jg_api.events.teammember.*;
import dev.jgapi.jg_api.events.teamwebhook.*;

public abstract class ListenerAdapter {
    /**
     * Library Events
     * Useful events that we create on the Library that Guilded doesn't provide...
     */

    /**
     * Ran when the bot is successfully logged in and the ClientUser has been created.
     * @param event {@link ReadyEvent}
     */
    public void onReadyEvent(ReadyEvent event) {}

    /**
     * Ran when the bot has been added to a server.
     * @param event {@link ServerAddedEvent}
     */
    public void onServerAddedEvent(ServerAddedEvent event) {}
    /**
     * Ran when the bot has been removed from a server.
     * @param event {@link ServerRemovedEvent}
     */
    public void onServerRemovedEvent(ServerRemovedEvent event) {}

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
    public void onChannelMessageReactionCreatedEvent(ChannelMessageReactionCreatedEvent event) {}
    public void onChannelMessageReactionDeletedEvent(ChannelMessageReactionDeletedEvent event) {}

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

    /**
     * Calendar Events
     */
    public void onCalendarEventCreatedEvent(CalendarEventCreatedEvent event) {}
    public void onCalendarEventUpdatedEvent(CalendarEventUpdatedEvent event) {}
    public void onCalendarEventDeletedEvent(CalendarEventDeletedEvent event) {}
}
