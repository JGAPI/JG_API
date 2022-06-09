package dev.JGAPI.JG_API.Events.TeamMember;

import dev.JGAPI.JG_API.Entities.Members.ServerMember;
import dev.JGAPI.JG_API.JG_API;

public class TeamMemberJoinedEvent extends GenericTeamMemberEvent {
    public TeamMemberJoinedEvent(JG_API jg_api, String serverId, ServerMember serverMember) {
        super(jg_api, serverId, serverMember);
    }
}
