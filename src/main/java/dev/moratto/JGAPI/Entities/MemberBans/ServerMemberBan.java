package dev.moratto.JGAPI.Entities.MemberBans;

import dev.moratto.JGAPI.Entities.Members.UserSummary;

import java.time.Instant;

public class ServerMemberBan {
    private UserSummary user;
    private String reason;
    private String createdBy;
    private Instant createdAt;
}
