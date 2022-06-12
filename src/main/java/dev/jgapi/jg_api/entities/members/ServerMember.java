package dev.jgapi.jg_api.entities.members;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.rest.RestAction;

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

    public RestAction setNickname(String serverId, String nickname) {
        return jg_api.getRestClient().updateNickname(serverId, this.user.getId(), nickname);
    }
    public RestAction deleteNickname(String serverId) {
        return jg_api.getRestClient().deleteNickname(serverId, this.user.getId());
    }
    public RestAction kickMember(String serverId) {
        return jg_api.getRestClient().kickMember(serverId, this.user.getId());
    }
    public RestAction banMember(String serverId, String reason) {
        return jg_api.getRestClient().createServerBan(serverId, this.user.getId(), reason);
    }
}
