package dev.moratto.JGAPI.Events.TeamMember;

import dev.moratto.JGAPI.Entities.MemberBans.ServerMemberBan;
import dev.moratto.JGAPI.Events.Event;
import dev.moratto.JGAPI.JG_API;

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
