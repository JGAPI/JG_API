package dev.jgapi.jg_api.entities.memberbans;

import dev.jgapi.jg_api.entities.members.UserSummary;

import java.time.Instant;

public class ServerMemberBan {
    private UserSummary userSummary;
    private String reason;
    private String createdBy;
    private Instant createdAt;
    public ServerMemberBan(UserSummary userSummary, String reason, String createdBy, Instant createdAt) {
        this.userSummary = userSummary;
        this.reason = reason;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public UserSummary getUserSummary() {
        return this.userSummary;
    }

    public String getReason() {
        return this.reason;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }
}
