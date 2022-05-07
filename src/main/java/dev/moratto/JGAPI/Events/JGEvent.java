package dev.moratto.JGAPI.Events;

import dev.moratto.JGAPI.JG_API;

public class JGEvent implements GenericEvent {
    protected final JG_API jg_api;
    protected final String event_name;

    public JGEvent(JG_API jg_api, String event_name) {
        this.jg_api = jg_api;
        this.event_name = event_name;
    }

    @Override
    public String getEventName() {
        return this.event_name;
    }

    @Override
    public JG_API getJG_API() {
        return this.jg_api;
    }
}
