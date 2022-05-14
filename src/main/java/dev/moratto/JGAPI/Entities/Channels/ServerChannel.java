package dev.moratto.JGAPI.Entities.Channels;

import java.time.Instant;

public class ServerChannel {
    private String id;
    private String type;
    private String name;
    private String topic;
    private Instant createdAt;
    private Instant updatedAt;
    private String serverId;
    private String parentId;
    private String categoryId;
    private String groupId;
    private boolean isPublic;
    private String archivedBy;
    private Instant archivedAt;
}
