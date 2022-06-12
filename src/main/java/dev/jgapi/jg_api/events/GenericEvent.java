package dev.jgapi.jg_api.events;

import dev.jgapi.jg_api.JG_API;

public interface GenericEvent {
    JG_API getJGAPI();
    String getServerId();
}