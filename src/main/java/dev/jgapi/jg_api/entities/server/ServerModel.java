package dev.jgapi.jg_api.entities.server;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import org.json.JSONObject;

import java.time.Instant;

public class ServerModel extends GuildedObject {
    private String id;
    private String ownerId;
    private String type;
    private String name;
    private String url;
    private String about;
    private String avatar;
    private String banner;
    private String timezone;
    private boolean isVerified;
    private String defaultChannelId;
    private Instant createdAt;

    public ServerModel(JG_API jg_api, String id, String ownerId, String type, String name, String url, String about, String avatar, String banner, String timezone, boolean isVerified, String defaultChannelId, Instant createdAt) {
        super(jg_api);
        this.id = id;
        this.ownerId = ownerId;
        this.type = type;
        this.name = name;
        this.url = url;
        this.about = about;
        this.avatar = avatar;
        this.banner = banner;
        this.timezone = timezone;
        this.isVerified = isVerified;
        this.defaultChannelId = defaultChannelId;
        this.createdAt = createdAt;
    }

    public static ServerModel parseServerModelObj(JSONObject serverObj, JG_API jg_api) {
        return new ServerModel(
            jg_api,
            serverObj.getString("id"),
            serverObj.getString("ownerId"),
            serverObj.optString("type", null),
            serverObj.getString("name"),
            serverObj.optString("url", null),
            serverObj.optString("about", null),
            serverObj.optString("avatar", null),
            serverObj.optString("banner", null),
            serverObj.optString("timezone", null),
            serverObj.optBoolean("isVerified", false),
            serverObj.optString("defaultChannelId", null),
            Instant.parse(serverObj.getString("createdAt"))
        );
    }

    public String getId() {
        return this.id;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public String getAbout() {
        return this.about;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public String getBanner() {
        return this.banner;
    }

    public String getTimezone() {
        return this.timezone;
    }

    public boolean isVerified() {
        return this.isVerified;
    }

    public String getDefaultChannelId() {
        return this.defaultChannelId;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }
}
