package dev.moratto.JGAPI.Events.TeamMember;

import dev.moratto.JGAPI.Entities.Members.ServerMember;
import dev.moratto.JGAPI.JG_API;

public class TeamMemberJoinedEvent extends GenericTeamMemberEvent {
    public TeamMemberJoinedEvent(JG_API jg_api, String serverId, ServerMember serverMember) {
        super(jg_api, serverId, serverMember);
    }
}
