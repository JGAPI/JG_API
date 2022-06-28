package dev.jgapi.jg_api.events.teammember;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.members.MemberRoleIds;
import dev.jgapi.jg_api.events.Event;

public class TeamRolesUpdatedEvent extends Event {
    private MemberRoleIds[] memberRoleIds;
    public TeamRolesUpdatedEvent(JG_API jg_api, String serverId, MemberRoleIds[] memberRoleIds) {
        super(jg_api, serverId);
        this.memberRoleIds = memberRoleIds;
    }
    public MemberRoleIds[] getMemberRoleIds() {
        return this.memberRoleIds;
    }
}
