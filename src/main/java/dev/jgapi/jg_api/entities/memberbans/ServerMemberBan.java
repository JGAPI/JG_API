package dev.jgapi.jg_api.entities.memberbans;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.entities.members.UserSummary;
import dev.jgapi.jg_api.rest.RestAction;

import java.time.Instant;

public class ServerMemberBan extends GuildedObject {
    private UserSummary userSummary;
    private String reason;
    private String createdBy;
    private Instant createdAt;
    public ServerMemberBan(JG_API jg_api, UserSummary userSummary, String reason, String createdBy, Instant createdAt) {
        super(jg_api);
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

    public RestAction<Boolean> delete(String serverId) {
        return jg_api.getRestClient().deleteServerBan(serverId, this.userSummary.getId());
    }
}
