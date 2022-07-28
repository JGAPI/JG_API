package dev.jgapi.jg_api.entities.forums;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.entities.channels.Mentions;
import dev.jgapi.jg_api.util.UtilClass;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;

public class ForumTopic extends GuildedObject {
    private int id;
    private String serverId;
    private String channelId;
    private String title;
    private String content;
    private Instant createdAt;
    private String createdBy;
    private String createdByWebhookId;
    private Instant updatedAt;
    private Mentions mentions;
    public ForumTopic(JG_API jg_api, int id, String serverId, String channelId, String title, String content, Instant createdAt, String createdBy, String createdByWebhookId, Instant updatedAt, Mentions mentions) {
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
        this.mentions = mentions;
    }

    public static ForumTopic parseForumTopicObj(JSONObject forumTopicObj, JG_API jg_api) {
        JSONObject mentionsObj = forumTopicObj.optJSONObject("mentions");
        Mentions mentions = null;
        if (mentionsObj != null) {
            mentions = Mentions.parseMentionsObj(mentionsObj);
        }
        return new ForumTopic(
                jg_api,
                forumTopicObj.getInt("id"),
                forumTopicObj.getString("serverId"),
                forumTopicObj.getString("channelId"),
                forumTopicObj.optString("title", null),
                forumTopicObj.optString("content", null),
                Instant.parse(forumTopicObj.getString("createdAt")),
                forumTopicObj.getString("createdBy"),
                forumTopicObj.optString("createdByWebhookId", null),
                UtilClass.parseStringOrNull(forumTopicObj.optString("updatedAt", null)),
                mentions
        );
    }

    public int getId() {
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
