package dev.jgapi.jg_api.events.listitem;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.listitems.ListItem;
import dev.jgapi.jg_api.events.Event;

public class GenericListItemEvent extends Event {
    ListItem listItem;
    public GenericListItemEvent(JG_API jg_api, String serverId, ListItem listItem) {
        super(jg_api, serverId);
        this.listItem = listItem;
    }

    public ListItem getListItem() {
        return this.listItem;
    }
}
