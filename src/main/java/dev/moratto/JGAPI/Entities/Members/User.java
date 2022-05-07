package dev.moratto.JGAPI.Entities.Members;

import java.time.Instant;

public class User {
    private String id;
    private String name;
    private String type;
    private String $avatar_uri;
    private String banner_uri;
    private Instant createdAt = null;

    /**
     * Created a User Object.
     * @param id The User's ID.
     * @param name The User's Name.
     * @param type The type of User. Either "client" or "bot".
     * @param createdAt The date of User Creation. Parsed to an {@link Instant} value.
     */
    protected User(String id, String name, String type, String createdAt) {
        this.id = id;
        this.name = name;
        this.type = type != null ? type : "user";
        this.createdAt = Instant.parse(createdAt);
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
}
