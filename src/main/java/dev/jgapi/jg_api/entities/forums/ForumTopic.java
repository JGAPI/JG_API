package dev.jgapi.jg_api.entities.forums;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.util.UtilClass;
import org.json.JSONObject;

import java.time.Instant;

public class ForumTopic extends GuildedObject {
    private String id;
    private String serverId;
    private String channelId;
    private String title;
    private String content;
    private Instant createdAt;
    private String createdBy;
    private String createdByWebhookId;
    private Instant updatedAt;
    public ForumTopic(JG_API jg_api, String id, String serverId, String channelId, String title, String content, Instant createdAt, String createdBy, String createdByWebhookId, Instant updatedAt) {
        super(jg_api);
        this.id = id;
        this.serverId = serverId;
        this.channelId = channelId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.createdByWebhookId = createdByWebhookId;
        this.updatedAt = updatedAt;
    }

    public static ForumTopic parseForumTopicObj(JSONObject forumTopicObj, JG_API jg_api) {
        return new ForumTopic(
                jg_api,
                forumTopicObj.getString("id"),
                forumTopicObj.getString("serverId"),
                forumTopicObj.getString("channelId"),
                forumTopicObj.optString("title", null),
                forumTopicObj.optString("content", null),
                Instant.parse(forumTopicObj.getString("createdAt")),
                forumTopicObj.getString("createdBy"),
                forumTopicObj.optString("createdByWebhookId", null),
                UtilClass.parseStringOrNull(forumTopicObj.optString("updatedAt", null))
        );
    }

    public String getId() {
        return this.id;
    }

    public String getServerId() {
        return this.serverId;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
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
