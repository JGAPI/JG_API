package dev.JGAPI.JG_API.Events.ListItem;

import dev.JGAPI.JG_API.Entities.ListItems.ListItem;
import dev.JGAPI.JG_API.Events.Event;
import dev.JGAPI.JG_API.JG_API;

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
