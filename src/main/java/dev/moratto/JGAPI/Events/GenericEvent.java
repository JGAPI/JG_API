package dev.moratto.JGAPI.Events;

import dev.moratto.JGAPI.JG_API;

public interface GenericEvent {
    String getEventName();
    JG_API getJG_API();
}