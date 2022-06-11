package dev.JGAPI.JG_API.Events.ListItem;

import dev.JGAPI.JG_API.Entities.ListItems.ListItem;
import dev.JGAPI.JG_API.JG_API;

public class ListItemCreatedEvent extends GenericListItemEvent {
    public ListItemCreatedEvent(JG_API jg_api, String serverId, ListItem listItem) {
        super(jg_api, serverId, listItem);
    }
}
