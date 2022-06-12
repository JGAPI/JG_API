package dev.jgapi.jg_api.events.teammember;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.events.Event;

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
