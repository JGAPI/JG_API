package dev.jgapi.jg_api.events.teammember;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.members.UserInfo;
import dev.jgapi.jg_api.events.Event;

public class TeamMemberUpdatedEvent extends Event {
    private UserInfo userInfo;
    public TeamMemberUpdatedEvent(JG_API jg_api, String serverId, UserInfo userInfo) {
        super(jg_api, serverId);
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }
}
