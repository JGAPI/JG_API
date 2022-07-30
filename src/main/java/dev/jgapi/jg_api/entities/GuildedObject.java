package dev.jgapi.jg_api.entities;

import dev.jgapi.jg_api.JG_API;

public abstract class GuildedObject {
    protected JG_API jg_api;
    public GuildedObject(JG_API jg_api) {
        this.jg_api = jg_api;
    }
    public JG_API getJGAPI() {
        return this.jg_api;
    }
}
