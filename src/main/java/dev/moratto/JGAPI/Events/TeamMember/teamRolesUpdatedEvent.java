package dev.moratto.JGAPI.Events.TeamMember;

import dev.moratto.JGAPI.Events.Event;
import dev.moratto.JGAPI.JG_API;

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
