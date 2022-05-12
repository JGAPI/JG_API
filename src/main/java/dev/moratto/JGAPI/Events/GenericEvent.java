package dev.moratto.JGAPI.Events;

import dev.moratto.JGAPI.JG_API;

public interface GenericEvent {
    JG_API getJGAPI();
    String getServerId();
}