package dev.moratto.JGAPI.Events;

import dev.moratto.JGAPI.JG_API;

public abstract class Event implements GenericEvent {
    protected final JG_API jg_api;
    protected final String serverId;
    public Event(JG_API jg_api, String serverId) {
        this.jg_api = jg_api;
        this.serverId = serverId;
    }
    @Override
    public JG_API getJGAPI() {
        return this.jg_api;
    }
    @Override
    public String getServerId() {
        return this.serverId;
    }
}
