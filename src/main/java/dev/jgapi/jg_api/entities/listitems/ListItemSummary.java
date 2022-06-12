package dev.jgapi.jg_api.entities.listitems;

import java.time.Instant;

public class ListItemSummary {
    private String id;
    private String serverId;
    private String channelId;
    private String message;
    private Instant createdAt;
    private String createdBy;
    private String createdByWebhookId;
    private Instant updatedAt;
    private String updatedBy;
    private String parentListItemId;
    private Instant completedAt;
    private String completedBy;
    private ListItemNote note;
    public ListItemSummary(String id, String serverId, String channelId, String message, Instant createdAt, String createdBy, String createdByWebhookId, Instant updatedAt, String updatedBy, String parentListItemId, Instant completedAt, String completedBy, ListItemNote note) {
        this.id = id;
        this.serverId = serverId;
        this.channelId = channelId;
        this.message = message;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.createdByWebhookId = createdByWebhookId;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.parentListItemId = parentListItemId;
        this.completedAt = completedAt;
        this.completedBy = completedBy;
        this.note = note;
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

    public String getMessage() {
        return this.message;
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

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public String getParentListItemId() {
        return this.parentListItemId;
    }

    public Instant getCompletedAt() {
        return this.completedAt;
    }

    public String getCompletedBy() {
        return this.completedBy;
    }

    public ListItemNote getNote() {
        return this.note;
    }
}
