package dev.jgapi.jg_api.entities.emotes;

import org.json.JSONObject;

public class Emote {
    private int id;
    private String name;
    private String url;

    public Emote(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public static Emote parseEmoteObj(JSONObject emoteObj) {
        return new Emote(
                emoteObj.getInt("id"),
                emoteObj.getString("name"),
                emoteObj.getString("url")
        );
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
