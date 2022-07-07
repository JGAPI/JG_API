package dev.jgapi.jg_api.events.teamchannel;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.channels.ChannelReaction;
import dev.jgapi.jg_api.events.Event;

public class GenericChannelMessageReactionEvent extends Event {
    private ChannelReaction reaction;
    public GenericChannelMessageReactionEvent(JG_API jg_api, String serverId, ChannelReaction reaction) {
        super(jg_api, serverId);
        this.reaction = reaction;
    }
    public ChannelReaction getChannelReaction() {
        return this.reaction;
    }
}
