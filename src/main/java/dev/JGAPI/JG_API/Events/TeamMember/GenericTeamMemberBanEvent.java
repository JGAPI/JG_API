package dev.JGAPI.JG_API.Events.TeamMember;

import dev.JGAPI.JG_API.JG_API;
import dev.JGAPI.JG_API.Entities.MemberBans.ServerMemberBan;
import dev.JGAPI.JG_API.Events.Event;

public abstract class GenericTeamMemberBanEvent extends Event {
    ServerMemberBan serverMemberBan;

    public GenericTeamMemberBanEvent(JG_API jg_api, String serverId, ServerMemberBan serverMemberBan) {
        super(jg_api, serverId);
        this.serverMemberBan = serverMemberBan;
    }

    public ServerMemberBan getServerMemberBan() {
        return this.serverMemberBan;
    }
}
