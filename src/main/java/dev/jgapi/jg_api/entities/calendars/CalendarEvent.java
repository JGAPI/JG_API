package dev.jgapi.jg_api.entities.calendars;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.entities.channels.Mentions;
import dev.jgapi.jg_api.rest.RestAction;
import org.json.JSONObject;

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

    public static CalendarEvent parseCalendarEventObj(JSONObject calendarEventObj, JG_API jg_api) {
        JSONObject mentionsObj = calendarEventObj.optJSONObject("mentions", null);
        JSONObject cancellationObj = calendarEventObj.optJSONObject("cancellation", null);

        return new CalendarEvent(
                jg_api,
                calendarEventObj.getInt("id"),
                calendarEventObj.getString("serverId"),
                calendarEventObj.getString("channelId"),
                calendarEventObj.getString("name"),
                calendarEventObj.optString("description", null),
                calendarEventObj.optString("location", null),
                calendarEventObj.optString("url", null),
                calendarEventObj.optInt("color", 0),
                Instant.parse(calendarEventObj.getString("startsAt")),
                calendarEventObj.optInt("duration", 1),
                calendarEventObj.optBoolean("isPrivate", false),
                mentionsObj == null ? null : Mentions.parseMentionsObj(mentionsObj),
                Instant.parse(calendarEventObj.getString("createdAt")),
                cancellationObj == null ? null : new CalendarEventCancellation(
                        cancellationObj.optString("description", null),
                        cancellationObj.optString("createdBy", null)
                )
        );
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

    public RestAction<CalendarEvent> setName(String name) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, name, this.description, this.location, this.startsAt, this.url, this.color, this.duration, this.isPrivate);
    }

    public RestAction<CalendarEvent> setDescription(String description) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, this.name, description, this.location, this.startsAt, this.url, this.color, this.duration, this.isPrivate);
    }

    public RestAction<CalendarEvent> setLocation(String location) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, this.name, this.description, location, this.startsAt, this.url, this.color, this.duration, this.isPrivate);
    }

    public RestAction<CalendarEvent> setStart(Instant startsAt) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, this.name, this.description, this.location, startsAt, this.url, this.color, this.duration, this.isPrivate);
    }

    public RestAction<CalendarEvent> setURL(String url) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, this.name, this.description, this.location, this.startsAt, url, this.color, this.duration, this.isPrivate);
    }

    public RestAction<CalendarEvent> setColor(int color) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, this.name, this.description, this.location, this.startsAt, this.url, color, this.duration, this.isPrivate);
    }

    public RestAction<CalendarEvent> setDuration(int duration) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, this.name, this.description, this.location, this.startsAt, this.url, this.color, duration, this.isPrivate);
    }

    public RestAction<CalendarEvent> setPrivate(boolean isPrivate) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, this.name, this.description, this.location, this.startsAt, this.url, this.color, this.duration, isPrivate);
    }

    public RestAction<CalendarEvent> update(String name, String description, String location, Instant startsAt, String url, int color, int duration, boolean isPrivate) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, name, description, location, startsAt, url, color, duration, isPrivate);
    }
}
