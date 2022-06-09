package dev.JGAPI.JG_API.Entities.Webhooks;

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
    public Webhook(String id, String name, String serverId, String channelId, Instant createdAt, String createdBy, Instant deletedAt, String token) {
        this.id = id;
        this.name = name;
        this.serverId = serverId;
        this.channelId = channelId;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.deletedAt = deletedAt;
        this.token = token;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getServerId() {
        return this.serverId;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Instant getDeletedAt() {
        return this.deletedAt;
    }

    public String getToken() {
        return this.token;
    }
}
