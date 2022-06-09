package dev.JGAPI.JG_API.Events.TeamMember;

import dev.JGAPI.JG_API.JG_API;
import dev.JGAPI.JG_API.Entities.Members.ServerMember;
import dev.JGAPI.JG_API.Events.Event;

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
