package dev.JGAPI.JG_API.Entities.Forums;

import java.time.Instant;

public class ForumTopic {
    private String id;
    private String serverId;
    private String channelId;
    private String title;
    private String content;
    private Instant createdAt;
    private String createdBy;
    private String createdByWebhookId;
    private Instant updatedAt;
    public ForumTopic(String id, String serverId, String channelId, String title, String content, Instant createdAt, String createdBy, String createdByWebhookId, Instant updatedAt) {
        this.id = id;
        this.serverId = serverId;
        this.channelId = channelId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.createdByWebhookId = createdByWebhookId;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return this.id;
    }

    public String getServerId() {
        return this.serverId;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public String getCreatedByWebhookId() {
        return this.createdByWebhookId;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }
}
