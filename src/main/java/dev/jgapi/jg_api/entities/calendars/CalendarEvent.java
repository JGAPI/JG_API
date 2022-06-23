package dev.jgapi.jg_api.entities.calendars;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.entities.channels.Mentions;

import java.time.Instant;

public class CalendarEvent extends GuildedObject {
    private int calendarId;
    private String serverId;
    private String channelId;
    private String name;
    private String description;
    private String location;
    private String url;
    private int color;
    private Instant startsAt;
    private int duration;
    private boolean isPrivate;
    private Mentions mentions;
    private Instant createdAt;
    private Object cancellation;
    public CalendarEvent(JG_API jg_api, int calendarId, String serverId, String channelId, String name, String description, String location, String url, int color, Instant startsAt, int duration, boolean isPrivate, Mentions mentions, Instant createdAt, Object cancellation) {
        super(jg_api);
        this.calendarId = calendarId;
        this.serverId = serverId;
        this.channelId = channelId;
        this.name = name;
        this.description = description;
        this.location = location;
        this.url = url;
        this.color = color;
        this.startsAt = startsAt;
        this.duration = duration;
        this.isPrivate = isPrivate;
        this.mentions = mentions;
        this.createdAt = createdAt;
        this.cancellation = cancellation;
    }

    public int getCalendarId() {
        return this.calendarId;
    }

    public String getServerId() {
        return this.serverId;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLocation() {
        return this.location;
    }

    public String getUrl() {
        return this.url;
    }

    public int getColor() {
        return this.color;
    }

    public Instant getStartsAt() {
        return this.startsAt;
    }

    public int getDuration() {
        return this.duration;
    }

    public boolean isPrivate() {
        return this.isPrivate;
    }

    public Mentions getMentions() {
        return this.mentions;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Object getCancellation() {
        return this.cancellation;
    }
}
