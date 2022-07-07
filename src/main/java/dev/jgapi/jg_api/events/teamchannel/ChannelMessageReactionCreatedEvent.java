package dev.jgapi.jg_api.events.teamchannel;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.channels.ChannelReaction;

public class ChannelMessageReactionCreatedEvent extends GenericChannelMessageReactionEvent {
    public ChannelMessageReactionCreatedEvent(JG_API jg_api, String serverId, ChannelReaction reaction) {
        super(jg_api, serverId, reaction);
    }
}
