package dev.jgapi.jg_api.events.teammember;

import dev.jgapi.jg_api.entities.memberbans.ServerMemberBan;
import dev.jgapi.jg_api.JG_API;

public class TeamMemberBannedEvent extends GenericTeamMemberBanEvent {
    public TeamMemberBannedEvent(JG_API jg_api, String serverId, ServerMemberBan serverMemberBan) {
        super(jg_api, serverId, serverMemberBan);
    }
}
