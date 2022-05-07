package dev.moratto.JGAPI.Entities.Chat;

public class ChatMessage {
    private String id;
    private String type;
    private String serverId;
    private String channelId;
    private String content;
    private ChatEmbed[] embeds;
    private String[] replyMessageIds;
    private boolean isPrivate;
    private String createdAt;
    private String createdBy;
    private String createdByWebhookId;
    private String updatedAt;
}
