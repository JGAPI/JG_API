package dev.JGAPI.JG_API.Events.TeamMember;

import dev.JGAPI.JG_API.JG_API;
import dev.JGAPI.JG_API.Events.Event;

public class TeamMemberRemovedEvent extends Event {
    private String userId;
    private boolean isKick;
    private boolean isBan;
    public TeamMemberRemovedEvent(JG_API jg_api, String serverId, String userId, boolean isKick, boolean isBan) {
        super(jg_api, serverId);
        this.userId = userId;
        this.isKick = isKick;
        this.isBan = isBan;
    }

    public String getServerId() {
        return this.serverId;
    }
    public String getUserId() {
        return this.userId;
    }
    public boolean isKick() {
        return this.isKick;
    }
    public boolean isBan() {
        return this.isBan;
    }
}
