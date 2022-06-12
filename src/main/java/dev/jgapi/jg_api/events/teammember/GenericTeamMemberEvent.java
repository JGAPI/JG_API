package dev.jgapi.jg_api.events.teammember;

import dev.jgapi.jg_api.entities.members.ServerMember;
import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.events.Event;

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
