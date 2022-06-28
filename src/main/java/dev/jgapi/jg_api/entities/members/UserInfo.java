package dev.jgapi.jg_api.entities.members;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import org.json.JSONObject;

public class UserInfo extends GuildedObject {
    String id;
    String nickname;

    public UserInfo(JG_API jg_api, String id, String nickname) {
        super(jg_api);
        this.id = id;
        this.nickname = nickname;
    }

    public static UserInfo parseUserInfoObj(JSONObject userInfoObj, JG_API jg_api) {
        return new UserInfo(
                jg_api,
                userInfoObj.getString("id"),
                userInfoObj.optString("nickname", null)
        );
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}
