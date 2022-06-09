package dev.moratto.JGAPI.Events.TeamChannel;

import dev.moratto.JGAPI.Entities.Channels.ServerChannel;
import dev.moratto.JGAPI.JG_API;

public class TeamChannelDeletedEvent extends GenericTeamChannelEvent {
    public TeamChannelDeletedEvent(JG_API jg_api, String serverId, ServerChannel channel) {
        super(jg_api, serverId, channel);
    }
}
