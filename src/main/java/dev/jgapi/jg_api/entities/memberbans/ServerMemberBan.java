package dev.jgapi.jg_api.entities.memberbans;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.entities.members.UserSummary;
import dev.jgapi.jg_api.rest.RestAction;
import dev.jgapi.jg_api.util.UtilClass;
import org.json.JSONObject;

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

    public static ServerMemberBan parseServerMemberBanObj(JSONObject serverMemberBanObj, JG_API jg_api) {
        JSONObject userObj = serverMemberBanObj.getJSONObject("user");

        return new ServerMemberBan(
                jg_api,
                new UserSummary(
                        userObj.getString("id"),
                        userObj.optString("type", "user"),
                        userObj.getString("name"),
                        userObj.optString("avatar", null)
                ),
                serverMemberBanObj.optString("reason", null),
                serverMemberBanObj.getString("createdBy"),
                UtilClass.parseStringOrNull(serverMemberBanObj.optString("createdAt", null))
        );
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
