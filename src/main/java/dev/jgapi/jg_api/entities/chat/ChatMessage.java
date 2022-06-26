package dev.jgapi.jg_api.entities.chat;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.entities.channels.Mentions;
import dev.jgapi.jg_api.entities.channels.ServerChannel;
import dev.jgapi.jg_api.rest.RestAction;

import java.time.Instant;

public class ChatMessage extends GuildedObject {
    private String id;
    private String type;
    private String serverId;
    private ServerChannel channel;
    private String content;
    private ChatEmbed[] embeds;
    private String[] replyMessageIds;
    private boolean isPrivate;
    private boolean isSilent;
    private Mentions mentions;
    private Instant createdAt;
    private String createdBy;
    private String createdByWebhookId;
    private Instant updatedAt;
    private Instant deletedAt;

    public ChatMessage(JG_API jg_api, String id, String type, String serverId, ServerChannel channel, String content, ChatEmbed[] embeds, String[] replyMessageIds, boolean isPrivate, boolean isSilent, Mentions mentions, Instant createdAt, String createdBy, String createdByWebhookId, Instant updatedAt, Instant deletedAt) {
        super(jg_api);
        this.id = id;
        this.type = type;
        this.serverId = serverId;
        this.channel = channel;
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
        this.deletedAt = deletedAt;
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
    public ServerChannel getChannel() {
        return this.channel;
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
    public Mentions getMentions() {
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

    public RestAction delete() {
        return jg_api.getRestClient().deleteMessage(this.getChannel().getId(), this.id);
    }
    public RestAction update(String content, ChatEmbed[] embeds) {
        return jg_api.getRestClient().updateMessage(this.getChannel().getId(), this.id, content, embeds);
    }
    public RestAction setContent(String content) {
        return jg_api.getRestClient().updateMessage(this.getChannel().getId(), this.id, content, this.embeds);
    }
    public RestAction setEmbeds(ChatEmbed[] embeds) {
        return jg_api.getRestClient().updateMessage(this.getChannel().getId(), this.id, this.content, embeds);
    }
}
