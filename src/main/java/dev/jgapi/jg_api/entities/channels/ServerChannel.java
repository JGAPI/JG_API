package dev.jgapi.jg_api.entities.channels;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.entities.chat.ChatEmbed;
import dev.jgapi.jg_api.entities.chat.ChatMessage;
import dev.jgapi.jg_api.rest.RestAction;

import java.time.Instant;

public class ServerChannel extends GuildedObject {
    private String id;
    private String type;
    private String name;
    private String topic;
    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String serverId;
    private String parentId;
    private int categoryId;
    private String groupId;
    private boolean isPublic;
    private String archivedBy;
    private Instant archivedAt;
    public ServerChannel(JG_API jg_api, String id, String type, String name, String topic, Instant createdAt, String createdBy, Instant updatedAt, String serverId, String parentId, int categoryId, String groupId, boolean isPublic, String archivedBy, Instant archivedAt) {
        super(jg_api);
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

    public int getCategoryId() {
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

    public RestAction<ServerChannel> delete() {
        return this.jg_api.getRestClient().deleteChannel(this.id);
    }
    public RestAction<ServerChannel> update(String name, String topic, boolean isPublic) {
        return this.jg_api.getRestClient().updateChannel(this.id, name, topic, isPublic);
    }
    public RestAction<ServerChannel> setName(String name) {
        return this.jg_api.getRestClient().updateChannel(this.id, name, this.topic, this.isPublic);
    }
    public RestAction<ServerChannel> setTopic(String topic) {
        return this.jg_api.getRestClient().updateChannel(this.id, this.name, topic, this.isPublic);
    }
    public RestAction<ServerChannel> setPublic(boolean isPublic) {
        return this.jg_api.getRestClient().updateChannel(this.id, this.name, this.topic, isPublic);
    }

    public RestAction<ChatMessage[]> getMessages() {
        return this.jg_api.getRestClient().getMessages(this.id, Instant.now(), Instant.ofEpochSecond(0), 100, false);
    }

    public RestAction<ChatMessage> sendMessage(String content, boolean isPrivate, boolean isSilent, String[] replyMessageIds, ChatEmbed[] chatEmbeds) {
        return this.jg_api.getRestClient().createChannelMessage(this.id, isPrivate, isSilent, replyMessageIds, content, chatEmbeds);
    }

    public RestAction<ChatMessage> sendMessage(String content) {
        return this.sendMessage(content, false, false, null, null);
    }

    public RestAction<ChatMessage> sendMessage(ChatEmbed[] chatEmbeds) {
        return this.sendMessage(null, false, false, null, chatEmbeds);
    }
}
