package dev.moratto.JGAPI.Events.TeamChannel;

import dev.moratto.JGAPI.Entities.Channels.ServerChannel;
import dev.moratto.JGAPI.Events.Event;
import dev.moratto.JGAPI.JG_API;

public abstract class GenericTeamChannelEvent extends Event {
    ServerChannel channel;
    public GenericTeamChannelEvent(JG_API jg_api, String serverId, ServerChannel channel) {
        super(jg_api, serverId);
        this.channel = channel;
    }
}
