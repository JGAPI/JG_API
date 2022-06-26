package dev.jgapi.jg_api.events.teammember;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.events.Event;

public class TeamRolesUpdatedEvent extends Event {
    private Object memberRoleIds;
    public TeamRolesUpdatedEvent(JG_API jg_api, String serverId, Object[] memberRoleIds) {
        super(jg_api, serverId);
        this.memberRoleIds = memberRoleIds;
    }
    public Object getMemberRoleIds() {
        return this.memberRoleIds;
    }
}
