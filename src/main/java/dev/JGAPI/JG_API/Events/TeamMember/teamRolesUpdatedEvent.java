package dev.JGAPI.JG_API.Events.TeamMember;

import dev.JGAPI.JG_API.JG_API;
import dev.JGAPI.JG_API.Events.Event;

public class teamRolesUpdatedEvent extends Event {
    private Object memberRoleIds;
    public teamRolesUpdatedEvent(JG_API jg_api, String serverId, Object[] memberRoleIds) {
        super(jg_api, serverId);
        this.memberRoleIds = memberRoleIds;
    }
    public Object getMemberRoleIds() {
        return this.memberRoleIds;
    }
}
