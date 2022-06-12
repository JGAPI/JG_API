package dev.jgapi.jg_api.events.teammember;

import dev.jgapi.jg_api.entities.memberbans.ServerMemberBan;
import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.events.Event;

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
