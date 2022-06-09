package dev.JGAPI.JG_API.Events.TeamMember;

import dev.JGAPI.JG_API.JG_API;
import dev.JGAPI.JG_API.Events.Event;

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
