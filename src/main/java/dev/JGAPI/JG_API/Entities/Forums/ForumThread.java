package dev.JGAPI.JG_API.Entities.Forums;

import java.time.Instant;

public class ForumThread {
    private String id;
    private String serverId;
    private String channelId;
    private String title;
    private String content;
    private Instant createdAt;
    private String createdBy;
    private String createdByWebhookId;
    private Instant updatedAt;
}
