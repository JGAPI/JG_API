package dev.JGAPI.JG_API.Events.ListItem;

import dev.JGAPI.JG_API.Entities.ListItems.ListItem;
import dev.JGAPI.JG_API.JG_API;

public class ListItemCompletedEvent extends GenericListItemEvent {
    public ListItemCompletedEvent(JG_API jg_api, String serverId, ListItem listItem) {
        super(jg_api, serverId, listItem);
    }
}
