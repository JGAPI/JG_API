package dev.moratto.JGAPI.Entities.ListItems;

public class ListItemSummary {
    private String id;
    private String serverId;
    private String channelId;
    private String message;
    private String createdAt;
    private String createdBy;
    private String createdByWebhookId;
    private String updatedAt;
    private String updatedBy;
    private String parentListItemId;
    private String completedAt;
    private String completedby;
    private class Note {
        private String createdAt;
        private String createdBy;
        private String updatedAt;
        private String updatedBy;
    }
}
