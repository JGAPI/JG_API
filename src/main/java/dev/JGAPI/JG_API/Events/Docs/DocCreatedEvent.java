package dev.JGAPI.JG_API.Events.Docs;

import dev.JGAPI.JG_API.Entities.Docs.Doc;
import dev.JGAPI.JG_API.JG_API;

public class DocCreatedEvent extends GenericDocEvent {
    public DocCreatedEvent(JG_API jg_api, String serverId, Doc doc) {
        super(jg_api, serverId, doc);
    }
}
