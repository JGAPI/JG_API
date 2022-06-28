package dev.jgapi.jg_api.entities.members;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.entities.memberbans.ServerMemberBan;
import dev.jgapi.jg_api.rest.RestAction;
import dev.jgapi.jg_api.util.UtilClass;
import org.json.JSONObject;

import java.time.Instant;

public class ServerMember extends GuildedObject {
    private User user;
    private int[] roleIds;
    private String nickname;
    private Instant joinedAt;
    private boolean isOwner;
    public ServerMember(JG_API jg_api, User user, int[] roleIds, String nickname, Instant joinedAt, boolean isOwner) {
        super(jg_api);
        this.user = user;
        this.roleIds = roleIds;
        this.nickname = nickname;
        this.joinedAt = joinedAt;
        this.isOwner = isOwner;
    }

    public static ServerMember parseServerMemberObj(JSONObject memberObj, JG_API jg_api) {
        return new ServerMember(
                jg_api,
                User.parseUserObj(memberObj.getJSONObject("user"), jg_api),
                UtilClass.toPrimitive(memberObj.getJSONArray("roleIds").toList().toArray(new Integer[0])),
                memberObj.optString("nickname", null),
                Instant.parse(memberObj.getString("joinedAt")),
                memberObj.optBoolean("isOwner", false)
        );
    }

    public User getUser() {
        return this.user;
    }

    public int[] getRoleIds() {
        return this.roleIds;
    }

    public String getNickname() {
        return this.nickname;
    }

    public Instant getJoinedAt() {
        return this.joinedAt;
    }

    public boolean isOwner() {
        return this.isOwner;
    }

    public RestAction<String> setNickname(String serverId, String nickname) {
        return jg_api.getRestClient().updateNickname(serverId, this.user.getId(), nickname);
    }
    public RestAction<Boolean> deleteNickname(String serverId) {
        return jg_api.getRestClient().deleteNickname(serverId, this.user.getId());
    }
    public RestAction<Boolean> kickMember(String serverId) {
        return jg_api.getRestClient().kickMember(serverId, this.user.getId());
    }
    public RestAction<ServerMemberBan> banMember(String serverId, String reason) {
        return jg_api.getRestClient().createServerBan(serverId, this.user.getId(), reason);
    }
}
