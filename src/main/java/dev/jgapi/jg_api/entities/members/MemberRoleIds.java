package dev.jgapi.jg_api.entities.members;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.util.UtilClass;
import org.json.JSONObject;

public class MemberRoleIds extends GuildedObject {
    String userId;
    int[] roleIds;

    public MemberRoleIds(JG_API jg_api, String userId, int[] roleIds) {
        super(jg_api);
        this.userId = userId;
        this.roleIds = roleIds;
    }

    public static MemberRoleIds parseMemberRoleIdsObj(JSONObject memberRoleIdsObj, JG_API jg_api) {
        return new MemberRoleIds(
                jg_api,
                memberRoleIdsObj.getString("userId"),
                UtilClass.toPrimitive(memberRoleIdsObj.getJSONArray("roleIds").toList().toArray(Integer[]::new))
        );
    }

    public String getUserId() {
        return userId;
    }

    public int[] getRoleIds() {
        return roleIds;
    }
}
