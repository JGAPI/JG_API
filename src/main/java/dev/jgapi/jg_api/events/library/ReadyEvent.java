package dev.jgapi.jg_api.events.library;

import dev.jgapi.jg_api.events.Event;
import dev.jgapi.jg_api.JG_API;

public class ReadyEvent extends Event {
    public ReadyEvent(JG_API jg_api) {
        super(jg_api, null);
    }
}
