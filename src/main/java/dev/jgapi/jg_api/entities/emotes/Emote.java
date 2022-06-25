package dev.jgapi.jg_api.entities.emotes;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;

public class Emote extends GuildedObject {
    private int id;
    private String name;
    private String url;
    public Emote(JG_API jg_api) {
        super(jg_api);
    }
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getUrl() {
        return this.url;
    }
}
