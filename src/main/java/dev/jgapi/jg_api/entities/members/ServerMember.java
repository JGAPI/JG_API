package dev.jgapi.jg_api.entities.members;

import java.time.Instant;

public class ServerMember {
    private User user;
    private int[] roleIds;
    private String nickname;
    private Instant joinedAt;
    private boolean isOwner;
    public ServerMember(User user, int[] roleIds, String nickname, Instant joinedAt, boolean isOwner) {
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
}
