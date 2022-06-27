package dev.jgapi.jg_api.entities.emotes;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.rest.RestAction;

public class Emote extends GuildedObject {
    private int id;
    private String name;
    private String url;

    public Emote(JG_API jg_api, int id, String name, String url) {
        super(jg_api);
        this.id = id;
        this.name = name;
        this.url = url;
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
