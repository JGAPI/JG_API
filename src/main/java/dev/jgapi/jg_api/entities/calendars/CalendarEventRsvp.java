package dev.jgapi.jg_api.entities.calendars;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;

import java.time.Instant;
import java.util.UUID;

public class CalendarEventRsvp extends GuildedObject {
    private  int calendarEventId;
    private UUID channelId;
    private String serverId;
    private String userId;
    private String status;
    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;

    public CalendarEventRsvp(JG_API jg_api, int calendarEventId, UUID channelId, String serverId, String userId, String status, String createdBy, Instant createdAt, String updatedBy, Instant updatedAt) {
        super(jg_api);
        this.calendarEventId = calendarEventId;
        this.channelId = channelId;
        this.serverId = serverId;
        this.userId = userId;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }

    public int getCalendarEventId() {
        return this.calendarEventId;
    }

    public UUID getChannelId() {
        return this.channelId;
    }

    public String getServerId() {
        return this.serverId;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getStatus() {
        return this.status;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }
}
