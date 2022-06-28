package dev.jgapi.jg_api.entities.webhooks;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.rest.RestAction;
import dev.jgapi.jg_api.util.UtilClass;
import org.json.JSONObject;

import java.time.Instant;

public class Webhook extends GuildedObject {
    private String id;
    private String name;
    private String serverId;
    private String channelId;
    private Instant createdAt;
    private String createdBy;
    private Instant deletedAt;
    private String token;

    public Webhook(JG_API jg_api, String id, String name, String serverId, String channelId, Instant createdAt, String createdBy, Instant deletedAt, String token) {
        super(jg_api);
        this.id = id;
        this.name = name;
        this.serverId = serverId;
        this.channelId = channelId;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.deletedAt = deletedAt;
        this.token = token;
    }

    public static Webhook parseWebhookObj(JSONObject webhookObj, JG_API jg_api) {
        return new Webhook(
                jg_api,
                webhookObj.getString("id"),
                webhookObj.getString("name"),
                webhookObj.getString("serverId"),
                webhookObj.getString("channelId"),
                Instant.parse(webhookObj.getString("createdAt")),
                webhookObj.getString("createdBy"),
                UtilClass.parseStringOrNull(webhookObj.optString("deletedAt", null)),
                webhookObj.optString("token", null)
        );
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getServerId() {
        return this.serverId;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Instant getDeletedAt() {
        return this.deletedAt;
    }

    public String getToken() {
        return this.token;
    }

    public RestAction delete() {
        return jg_api.getRestClient().deleteWebhook(this.serverId, this.id);
    }
    public RestAction update(String name, String channelId) {
        return jg_api.getRestClient().updateWebhook(this.serverId, this.id, name, channelId);
    }
    public RestAction setName(String name) {
        return jg_api.getRestClient().updateWebhook(this.serverId, this.id, name, this.channelId);
    }
    public RestAction setChannelId(String channelId) {
        return jg_api.getRestClient().updateWebhook(this.serverId, this.id, this.name, channelId);
    }
}
