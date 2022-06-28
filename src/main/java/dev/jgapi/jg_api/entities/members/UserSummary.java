package dev.jgapi.jg_api.entities.members;

import dev.jgapi.jg_api.JG_API;
import org.json.JSONObject;

public class UserSummary {
    private String id;
    private String type;
    private String name;
    private String avatar_uri;

    public UserSummary(String id, String type, String name, String avatar_uri) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.avatar_uri = avatar_uri;
    }

    public static UserSummary parseUserSummaryObj(JSONObject userSummaryObj, JG_API jg_api) {
        return new UserSummary(
                userSummaryObj.getString("id"),
                userSummaryObj.optString("type", "user"),
                userSummaryObj.getString("name"),
                userSummaryObj.optString("avatar", null)
        );
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getAvatar_uri() {
        return this.avatar_uri;
    }
}
