package dev.jgapi.jg_api.events.docs;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.docs.Doc;
import dev.jgapi.jg_api.events.Event;

public class GenericDocEvent extends Event {
    Doc doc;
    public GenericDocEvent(JG_API jg_api, String serverId, Doc doc) {
        super(jg_api, serverId);
        this.doc = doc;
    }

    public Doc getDoc() {
        return this.doc;
    }
}
