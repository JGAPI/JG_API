package dev.moratto.JGAPI.Events.TeamMember;

import dev.moratto.JGAPI.Entities.Members.ServerMember;
import dev.moratto.JGAPI.Events.Event;
import dev.moratto.JGAPI.JG_API;

public abstract class GenericTeamMemberEvent extends Event {
    ServerMember serverMember;

    public GenericTeamMemberEvent(JG_API jg_api, String serverId, ServerMember serverMember) {
        super(jg_api, serverId);
        this.serverMember = serverMember;
    }

    public ServerMember getServerMember() {
        return this.serverMember;
    }
}
