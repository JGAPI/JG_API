package dev.jgapi.jg_api.entities.members;

public class UserSummary {
    private String id;
    private String type;
    private String name;
    private String avatar_uri;
    public UserSummary(String id, String type, String name, String avatar_uri) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.avatar_uri = avatar_uri;
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

    public String getAvatar_uri() {
        return this.avatar_uri;
    }
}
