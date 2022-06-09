package dev.moratto.JGAPI.Events.TeamMember;

import dev.moratto.JGAPI.Events.Event;
import dev.moratto.JGAPI.JG_API;

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
