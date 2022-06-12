package dev.jgapi.jg_api.events.docs;

import dev.jgapi.jg_api.entities.docs.Doc;
import dev.jgapi.jg_api.JG_API;

public class DocUpdatedEvent extends GenericDocEvent {
    public DocUpdatedEvent(JG_API jg_api, String serverId, Doc doc) {
        super(jg_api, serverId, doc);
    }
}
