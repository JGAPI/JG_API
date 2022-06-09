package dev.JGAPI.JG_API.Events.TeamMember;

import dev.JGAPI.JG_API.Entities.MemberBans.ServerMemberBan;
import dev.JGAPI.JG_API.JG_API;

public class TeamMemberBannedEvent extends GenericTeamMemberBanEvent {
    public TeamMemberBannedEvent(JG_API jg_api, String serverId, ServerMemberBan serverMemberBan) {
        super(jg_api, serverId, serverMemberBan);
    }
}
