package dev.jgapi.jg_api.events.listitem;

import dev.jgapi.jg_api.entities.listitems.ListItem;
import dev.jgapi.jg_api.JG_API;

public class ListItemCreatedEvent extends GenericListItemEvent {
    public ListItemCreatedEvent(JG_API jg_api, String serverId, ListItem listItem) {
        super(jg_api, serverId, listItem);
    }
}
