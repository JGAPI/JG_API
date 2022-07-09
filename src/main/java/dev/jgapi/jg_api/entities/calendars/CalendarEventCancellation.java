package dev.jgapi.jg_api.entities.calendars;

public class CalendarEventCancellation {
    String description;
    String createdBy;

    /**
     *
     * @param description
     * @param createdBy
     */
    public CalendarEventCancellation(String description, String createdBy) {
        this.description = description;
        this.createdBy = createdBy;
    }

    /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }
}
