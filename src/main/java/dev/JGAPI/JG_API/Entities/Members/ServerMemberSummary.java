package dev.JGAPI.JG_API.Entities.Members;

public class ServerMemberSummary {
    private UserSummary userSummary;
    private int[] roleIds;
    public ServerMemberSummary(UserSummary user, int[] roleIds) {
        this.userSummary = user;
        this.roleIds = roleIds;
    }

    public UserSummary getUserSummary() {
        return this.userSummary;
    }

    public int[] getRoleIds() {
        return this.roleIds;
    }
}
