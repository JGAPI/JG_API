package dev.jgapi.jg_api.events.calendar.rsvp;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.calendars.rsvp.CalendarEventRsvp;

public class CalendarEventRsvpDeletedEvent extends GenericCalendarEventRsvpEvent {
    public CalendarEventRsvpDeletedEvent(JG_API jg_api, String serverId, CalendarEventRsvp calendarEventRsvp) {
        super(jg_api, serverId, calendarEventRsvp);
    }
}
