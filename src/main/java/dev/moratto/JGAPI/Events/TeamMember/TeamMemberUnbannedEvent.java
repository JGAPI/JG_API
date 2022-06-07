package dev.moratto.JGAPI.Events.TeamMember;

import dev.moratto.JGAPI.Entities.MemberBans.ServerMemberBan;
import dev.moratto.JGAPI.JG_API;

public class TeamMemberUnbannedEvent extends GenericTeamMemberBanEvent {
    public TeamMemberUnbannedEvent(JG_API jg_api, String serverId, ServerMemberBan serverMemberBan) {
        super(jg_api, serverId, serverMemberBan);
    }
}
