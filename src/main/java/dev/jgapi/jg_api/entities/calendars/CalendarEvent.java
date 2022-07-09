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
    private CalendarEventCancellation cancellation;

    /**
     *
     * @param jg_api
     * @param calendarId
     * @param serverId
     * @param channelId
     * @param name
     * @param description
     * @param location
     * @param url
     * @param color
     * @param startsAt
     * @param duration
     * @param isPrivate
     * @param mentions
     * @param createdAt
     * @param cancellation
     */
    public CalendarEvent(JG_API jg_api, int calendarId, String serverId, String channelId, String name, String description, String location, String url, int color, Instant startsAt, int duration, boolean isPrivate, Mentions mentions, Instant createdAt, CalendarEventCancellation cancellation) {
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

    /**
     *
     * @param calendarEventObj
     * @param jg_api
     * @return CalendarEvent
     */
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

    /**
     *
     * @return calendarId
     */
    public int getCalendarId() {
        return this.calendarId;
    }

    /**
     *
     * @return serverId
     */
    public String getServerId() {
        return this.serverId;
    }

    /**
     *
     * @return channelId
     */
    public String getChannelId() {
        return this.channelId;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     *
     * @return location
     */
    public String getLocation() {
        return this.location;
    }

    /**
     *
     * @return url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     *
     * @return color
     */
    public int getColor() {
        return this.color;
    }

    /**
     *
     * @return startsAt
     */
    public Instant getStartsAt() {
        return this.startsAt;
    }

    /**
     *
     * @return duration
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     *
     * @return isPrivate
     */
    public boolean isPrivate() {
        return this.isPrivate;
    }

    /**
     *
     * @return Mentions
     */
    public Mentions getMentions() {
        return this.mentions;
    }

    /**
     *
     * @return createdAt
     */
    public Instant getCreatedAt() {
        return this.createdAt;
    }

    /**
     *
     * @return CalendarEventCancellation
     */
    public CalendarEventCancellation getCancellation() {
        return this.cancellation;
    }

    /**
     *
     * @param name
     * @return RestAction<CalendarEvent>
     */
    public RestAction<CalendarEvent> setName(String name) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, name, this.description, this.location, this.startsAt, this.url, this.color, this.duration, this.isPrivate);
    }

    /**
     *
     * @param description
     * @return RestAction<CalendarEvent>
     */
    public RestAction<CalendarEvent> setDescription(String description) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, this.name, description, this.location, this.startsAt, this.url, this.color, this.duration, this.isPrivate);
    }

    /**
     *
     * @param location
     * @return RestAction<CalendarEvent>
     */
    public RestAction<CalendarEvent> setLocation(String location) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, this.name, this.description, location, this.startsAt, this.url, this.color, this.duration, this.isPrivate);
    }

    /**
     *
     * @param startsAt
     * @return RestAction<CalendarEvent>
     */
    public RestAction<CalendarEvent> setStart(Instant startsAt) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, this.name, this.description, this.location, startsAt, this.url, this.color, this.duration, this.isPrivate);
    }

    /**
     *
     * @param url
     * @return RestAction<CalendarEvent>
     */
    public RestAction<CalendarEvent> setURL(String url) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, this.name, this.description, this.location, this.startsAt, url, this.color, this.duration, this.isPrivate);
    }

    /**
     *
     * @param color
     * @return RestAction<CalendarEvent>
     */
    public RestAction<CalendarEvent> setColor(int color) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, this.name, this.description, this.location, this.startsAt, this.url, color, this.duration, this.isPrivate);
    }

    /**
     *
     * @param duration
     * @return RestAction<CalendarEvent>
     */
    public RestAction<CalendarEvent> setDuration(int duration) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, this.name, this.description, this.location, this.startsAt, this.url, this.color, duration, this.isPrivate);
    }

    /**
     *
     * @param isPrivate
     * @return RestAction<CalendarEvent>
     */
    public RestAction<CalendarEvent> setPrivate(boolean isPrivate) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, this.name, this.description, this.location, this.startsAt, this.url, this.color, this.duration, isPrivate);
    }

    /**
     *
     * @return RestAction<Boolean>
     */
    public RestAction<Boolean> delete() {
        return this.jg_api.getRestClient().deleteCalendarEvent(this.channelId, this.calendarId);
    }

    /**
     *
     * @param name
     * @param description
     * @param location
     * @param startsAt
     * @param url
     * @param color
     * @param duration
     * @param isPrivate
     * @return CalendarEvent
     */
    public RestAction<CalendarEvent> update(String name, String description, String location, Instant startsAt, String url, int color, int duration, boolean isPrivate) {
        return this.jg_api.getRestClient().updateCalendarEvent(this.channelId, this.calendarId, name, description, location, startsAt, url, color, duration, isPrivate);
    }
}
