package dev.jgapi.jg_api.events.teammember;

import dev.jgapi.jg_api.entities.members.ServerMember;
import dev.jgapi.jg_api.JG_API;

public class TeamMemberJoinedEvent extends GenericTeamMemberEvent {
    public TeamMemberJoinedEvent(JG_API jg_api, String serverId, ServerMember serverMember) {
        super(jg_api, serverId, serverMember);
    }
}
