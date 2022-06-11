package dev.JGAPI.JG_API.Events.Docs;

import dev.JGAPI.JG_API.Entities.Docs.Doc;
import dev.JGAPI.JG_API.Events.Event;
import dev.JGAPI.JG_API.JG_API;

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
