package dev.jgapi.jg_api.entities.reactions;

import java.time.Instant;

public class ContentReaction {
    private int id;
    private String serverId;
    private Instant createdAt;
    private String createdBy;
    private String createdByWebhookId;
    public ContentReaction(int id, String serverId, Instant createdAt, String createdBy, String createdByWebhookId) {
        this.id = id;
        this.serverId = serverId;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.createdByWebhookId = createdByWebhookId;
    }

    public int getId() {
        return this.id;
    }

    public String getServerId() {
        return this.serverId;
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
}
