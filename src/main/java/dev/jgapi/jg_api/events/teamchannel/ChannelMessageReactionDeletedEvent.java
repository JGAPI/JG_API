package dev.jgapi.jg_api.events.teamchannel;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.channels.ChannelReaction;

public class ChannelMessageReactionDeletedEvent extends GenericChannelMessageReactionEvent {
    public ChannelMessageReactionDeletedEvent(JG_API jg_api, String serverId, ChannelReaction reaction) {
        super(jg_api, serverId, reaction);
    }
}
