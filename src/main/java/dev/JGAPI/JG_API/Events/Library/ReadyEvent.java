package dev.JGAPI.JG_API.Events.Library;

import dev.JGAPI.JG_API.Events.Event;
import dev.JGAPI.JG_API.JG_API;

public class ReadyEvent extends Event {
    public ReadyEvent(JG_API jg_api) {
        super(jg_api, null);
    }
}
