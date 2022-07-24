package dev.jgapi.jg_api.events.calendar.rsvp;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.calendars.rsvp.CalendarEventRsvp;
import dev.jgapi.jg_api.events.Event;

public abstract class GenericCalendarEventRsvpEvent extends Event {
    CalendarEventRsvp calendarEventRsvp;
    public GenericCalendarEventRsvpEvent(JG_API jg_api, String serverId, CalendarEventRsvp calendarEventRsvp) {
        super(jg_api, serverId);
        this.calendarEventRsvp = calendarEventRsvp;
    }

    public CalendarEventRsvp getCalendarEventRsvp() {
        return this.calendarEventRsvp;
    }
}
