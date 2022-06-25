package dev.jgapi.jg_api.events.library;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.events.Event;

public class ServerRemovedEvent extends Event {
    public ServerRemovedEvent(JG_API jg_api, String serverId) {
        super(jg_api, serverId);
    }
}
