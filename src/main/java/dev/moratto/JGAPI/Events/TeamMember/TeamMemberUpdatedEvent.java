package dev.moratto.JGAPI.Events.TeamMember;

import dev.moratto.JGAPI.Events.Event;
import dev.moratto.JGAPI.JG_API;

public class TeamMemberUpdatedEvent extends Event {
    private Object userInfo;
    public TeamMemberUpdatedEvent(JG_API jg_api, String serverId, Object userInfo) {
        super(jg_api, serverId);
        this.userInfo = userInfo;
    }

    public Object getUserInfo() {
        return this.userInfo;
    }
}
