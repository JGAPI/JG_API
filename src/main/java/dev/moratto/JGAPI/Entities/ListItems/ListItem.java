package dev.moratto.JGAPI.Entities.ListItems;

import java.time.Instant;

public class ListItem {
    private String id;
    private String serverId;
    private String channelId;
    private String message;
    private Instant createdAt;
    private String createdby;
    private String createdbyWebhookId;
    private Instant updatedAt;
    private String updatedby;
    private String parentListItemId;
    private Instant completedAt;
    private class Note {
        private Instant createdAt;
        private String createdBy;
        private Instant updatedAt;
        private String updatedBy;
        private String content;
    }
}
