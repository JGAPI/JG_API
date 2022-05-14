package dev.moratto.JGAPI.Entities.Members;

import java.time.Instant;

public class ServerMember {
    private User user;
    private int[] roleIds;
    private String nickname;
    private Instant joinedAt;
    private boolean isOwner;
}
