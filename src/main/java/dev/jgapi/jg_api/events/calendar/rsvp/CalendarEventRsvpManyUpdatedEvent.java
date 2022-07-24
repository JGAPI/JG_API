package dev.jgapi.jg_api.events.calendar.rsvp;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.calendars.rsvp.CalendarEventRsvp;
import dev.jgapi.jg_api.events.Event;

public class CalendarEventRsvpManyUpdatedEvent extends Event {
    CalendarEventRsvp[] calendarEventRsvps;
    public CalendarEventRsvpManyUpdatedEvent(JG_API jg_api, String serverId, CalendarEventRsvp[] calendarEventRsvp) {
        super(jg_api, serverId);
        this.calendarEventRsvps = calendarEventRsvp;
    }
    public CalendarEventRsvp[] getCalendarEventRsvps() {
        return this.calendarEventRsvps;
    }
}
