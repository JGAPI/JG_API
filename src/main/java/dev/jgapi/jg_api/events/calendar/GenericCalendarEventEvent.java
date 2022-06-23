package dev.jgapi.jg_api.events.calendar;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.calendars.CalendarEvent;
import dev.jgapi.jg_api.events.Event;

public abstract class GenericCalendarEventEvent extends Event {
    private CalendarEvent calendarEvent;
    public GenericCalendarEventEvent(JG_API jg_api, String serverId, CalendarEvent calendarEvent) {
        super(jg_api, serverId);
        this.calendarEvent = calendarEvent;
    }
    public CalendarEvent getCalendarEvent() {
        return this.calendarEvent;
    }
}
