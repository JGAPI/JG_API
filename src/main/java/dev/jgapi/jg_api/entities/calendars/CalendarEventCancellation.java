package dev.jgapi.jg_api.entities.calendars;

public class CalendarEventCancellation {
    String description;
    String createdBy;

    public CalendarEventCancellation(String description, String createdBy) {
        this.description = description;
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}
