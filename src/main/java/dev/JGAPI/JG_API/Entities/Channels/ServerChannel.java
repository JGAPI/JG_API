package dev.JGAPI.JG_API.Entities.Channels;

import java.time.Instant;

public class ServerChannel {
    private String id;
    private String type;
    private String name;
    private String topic;
    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String serverId;
    private String parentId;
    private String categoryId;
    private String groupId;
    private boolean isPublic;
    private String archivedBy;
    private Instant archivedAt;
    public ServerChannel(String id, String type, String name, String topic, Instant createdAt, String createdBy, Instant updatedAt, String serverId, String parentId, String categoryId, String groupId, boolean isPublic, String archivedBy, Instant archivedAt) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.topic = topic;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.serverId = serverId;
        this.parentId = parentId;
        this.categoryId = categoryId;
        this.groupId = groupId;
        this.isPublic = isPublic;
        this.archivedBy = archivedBy;
        this.archivedAt = archivedAt;
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getTopic() {
        return this.topic;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public String getServerId() {
        return this.serverId;
    }

    public String getParentId() {
        return this.parentId;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public String getArchivedBy() {
        return this.archivedBy;
    }

    public Instant getArchivedAt() {
        return this.archivedAt;
    }
}
