package dev.jgapi.jg_api.entities.members;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;

import java.time.Instant;

public class User extends GuildedObject {
    private String id;
    private String name;
    private String type;
    private String avatar_uri;
    private String banner_uri;
    private Instant createdAt;

    /**
     * Created a User Object.
     * @param id The User's ID.
     * @param name The User's Name.
     * @param type The type of User. Either "client" or "bot".
     * @param createdAt The date of User Creation. Parsed to an {@link Instant} value.
     */
    public User(JG_API jg_api, String id, String name, String type, String avatar_uri, String banner_uri, Instant createdAt) {
        super(jg_api);
        this.id = id;
        this.name = name;
        this.type = type != null ? type : "user";
        this.avatar_uri = avatar_uri;
        this.banner_uri = banner_uri;
        this.createdAt = createdAt;
    }

    /**
     * @return The User's ID.
     */
    public String getId() {
        return this.id;
    }

    /**
     * @return The User's Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The User's type. <br> Either "client" or "bot".
     */
    public String getType() {
        return this.type;
    }

    /**
     * @return The User's creation date.
     */
    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public String getAvatar_uri() {
        return this.avatar_uri;
    }

    public String getBanner_uri() {
        return this.banner_uri;
    }
}
