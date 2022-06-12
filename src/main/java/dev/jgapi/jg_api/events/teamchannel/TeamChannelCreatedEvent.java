package dev.jgapi.jg_api.events.teamchannel;

import dev.jgapi.jg_api.entities.channels.ServerChannel;
import dev.jgapi.jg_api.JG_API;

public class TeamChannelCreatedEvent extends GenericTeamChannelEvent {
    public TeamChannelCreatedEvent(JG_API jg_api, String serverId, ServerChannel channel) {
        super(jg_api, serverId, channel);
    }
}
