package dev.moratto.JGAPI.Entities.Webhooks;

import java.time.Instant;

public class Webhook {
    private String id;
    private String name;
    private String serverId;
    private String channelId;
    private Instant createdAt;
    private String createdBy;
    private Instant deletedAt;
    private String token;
}
