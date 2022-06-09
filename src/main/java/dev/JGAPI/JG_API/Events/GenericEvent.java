package dev.JGAPI.JG_API.Events;

import dev.JGAPI.JG_API.JG_API;

public interface GenericEvent {
    JG_API getJGAPI();
    String getServerId();
}