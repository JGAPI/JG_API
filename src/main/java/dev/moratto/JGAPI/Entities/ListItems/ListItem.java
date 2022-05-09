package dev.moratto.JGAPI.Entities.ListItems;

public class ListItem {
    private String id;
    private String serverId;
    private String channelId;
    private String message;
    private String createdAt;
    private String createdby;
    private String createdbyWebhookId;
    private String updatedAt;
    private String updatedby;
    private String parentListItemId;
    private String completedAt;
    private class Note {
        private String createdAt;
        private String createdBy;
        private String updatedAt;
        private String updatedBy;
        private String content;
    }
}
