package dev.jgapi.jg_api.events.calendar;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.calendars.CalendarEvent;

public class CalendarEventDeletedEvent extends GenericCalendarEventEvent {
    public CalendarEventDeletedEvent(JG_API jg_api, String serverId, CalendarEvent calendarEvent) {
        super(jg_api, serverId, calendarEvent);
    }
}
