package dev.jgapi.jg_api.entities.members;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.util.UtilClass;
import org.json.JSONObject;

public class ServerMemberSummary extends GuildedObject {
    private UserSummary userSummary;
    private int[] roleIds;

    public ServerMemberSummary(JG_API jg_api, UserSummary user, int[] roleIds) {
        super(jg_api);
        this.userSummary = user;
        this.roleIds = roleIds;
    }

    public static ServerMemberSummary parseServerMemberSummaryObj(JSONObject serverMemberSummaryObj, JG_API jg_api) {
        return new ServerMemberSummary(
                jg_api,
                UserSummary.parseUserSummaryObj(serverMemberSummaryObj.getJSONObject("user"), jg_api),
                UtilClass.toPrimitive(serverMemberSummaryObj.getJSONArray("roleIds").toList().toArray(new Integer[0]))
        );
    }

    public UserSummary getUserSummary() {
        return this.userSummary;
    }

    public int[] getRoleIds() {
        return this.roleIds;
    }
}
