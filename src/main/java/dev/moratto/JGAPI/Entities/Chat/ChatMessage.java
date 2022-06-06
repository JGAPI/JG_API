package dev.moratto.JGAPI.Entities.Chat;

import dev.moratto.JGAPI.Entities.Channels.Mentions;

import java.time.Instant;

public class ChatMessage {
    private String id;
    private String type;
    private String serverId;
    private String channelId;
    private String content;
    private ChatEmbed[] embeds;
    private String[] replyMessageIds;
    private boolean isPrivate;
    private boolean isSilent;
    private Mentions[] mentions;
    private Instant createdAt;
    private String createdBy;
    private String createdByWebhookId;
    private Instant updatedAt;

    public ChatMessage(String id, String type, String serverId, String channelId, String content, ChatEmbed[] embeds, String[] replyMessageIds, boolean isPrivate, boolean isSilent, Mentions[] mentions, Instant createdAt, String createdBy, String createdByWebhookId, Instant updatedAt) {
        this.id = id;
        this.type = type;
        this.serverId = serverId;
        this.channelId = channelId;
        this.content = content;
        this.embeds = embeds;
        this.replyMessageIds = replyMessageIds;
        this.isPrivate = isPrivate;
        this.isSilent = isSilent;
        this.mentions = mentions;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.createdByWebhookId = createdByWebhookId;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return this.id;
    }
    public String getType() {
        return this.type;
    }
    public String getServerId() {
        return this.serverId;
    }
    public String getChannelId() {
        return this.channelId;
    }
    public String getContent() {
        return this.content;
    }
    public ChatEmbed[] getEmbeds() {
        return this.embeds;
    }
    public String[] getReplyMessageIds() {
        return this.replyMessageIds;
    }
    public boolean isPrivate() {
        return this.isPrivate;
    }
    public boolean isSilent() {
        return this.isSilent;
    }
    public Mentions[] getMentions() {
        return this.mentions;
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
