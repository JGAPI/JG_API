package dev.jgapi.jg_api.events.teamchannel;

import dev.jgapi.jg_api.entities.channels.ServerChannel;
import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.events.Event;

public abstract class GenericTeamChannelEvent extends Event {
    ServerChannel channel;
    public GenericTeamChannelEvent(JG_API jg_api, String serverId, ServerChannel channel) {
        super(jg_api, serverId);
        this.channel = channel;
    }
}
