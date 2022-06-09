package dev.JGAPI.JG_API.Events.TeamChannel;

import dev.JGAPI.JG_API.JG_API;
import dev.JGAPI.JG_API.Entities.Channels.ServerChannel;
import dev.JGAPI.JG_API.Events.Event;

public abstract class GenericTeamChannelEvent extends Event {
    ServerChannel channel;
    public GenericTeamChannelEvent(JG_API jg_api, String serverId, ServerChannel channel) {
        super(jg_api, serverId);
        this.channel = channel;
    }
}
