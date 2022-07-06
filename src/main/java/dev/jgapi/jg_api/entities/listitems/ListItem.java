package dev.jgapi.jg_api.entities.listitems;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.entities.channels.Mentions;
import dev.jgapi.jg_api.rest.RestAction;
import dev.jgapi.jg_api.util.UtilClass;
import org.json.JSONObject;

import java.time.Instant;

public class ListItem extends GuildedObject {
    private String id;
    private String serverId;
    private String channelId;
    private String message;
    private Mentions mentions;
    private Instant createdAt;
    private String createdBy;
    private String createdByWebhookId;
    private Instant updatedAt;
    private String updatedBy;
    private String parentListItemId;
    private Instant completedAt;
    private String completedBy;
    private ListItemNote note;

    public ListItem(JG_API jg_api, String id, String serverId, String channelId, String message, Mentions mentions, Instant createdAt, String createdBy, String createdByWebhookId, Instant updatedAt, String updatedBy, String parentListItemId, Instant completedAt, String completedBy, ListItemNote note) {
        super(jg_api);
        this.id = id;
        this.serverId = serverId;
        this.channelId = channelId;
        this.message = message;
        this.mentions = mentions;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.createdByWebhookId = createdByWebhookId;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.parentListItemId = parentListItemId;
        this.completedAt = completedAt;
        this.completedBy = completedBy;
        this.note = note;
    }

    public static ListItem parseListItemObj(JSONObject listItemObj, JG_API jg_api) {
        JSONObject noteObj = listItemObj.optJSONObject("note", null);
        JSONObject mentionsObj = listItemObj.optJSONObject("mentions", null);

        return new ListItem(
                jg_api,
                listItemObj.getString("id"),
                listItemObj.getString("serverId"),
                listItemObj.getString("channelId"),
                listItemObj.getString("message"),
                mentionsObj == null ? null : Mentions.parseMentionsObj(mentionsObj),
                Instant.parse(listItemObj.getString("createdAy")),
                listItemObj.getString("createdBy"),
                listItemObj.optString("createdByWebhookId", null),
                UtilClass.parseStringOrNull(listItemObj.optString("updatedAt", null)),
                listItemObj.optString("updatedBy", null),
                listItemObj.optString("parentListItemId", null),
                UtilClass.parseStringOrNull(listItemObj.optString("completedAt", null)),
                listItemObj.optString("completedBy", null),
                noteObj == null ? null : new ListItemNote(
                        Instant.parse(noteObj.getString("createdAt")),
                        noteObj.getString("createdBy"),
                        UtilClass.parseStringOrNull(noteObj.optString("updatedAt", null)),
                        noteObj.optString("updatedBy", null),
                        null,
                        noteObj.getString("content")
                )
        );
    }

    public String getId() {
        return this.id;
    }

    public String getServerId() {
        return this.serverId;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public String getMessage() {
        return this.message;
    }

    public Mentions getMentions() {
        return this.mentions;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public String getCreatedByWebhookId() {
        return this.createdByWebhookId;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public String getParentListItemId() {
        return this.parentListItemId;
    }

    public Instant getCompletedAt() {
        return this.completedAt;
    }

    public String getCompletedBy() {
        return this.completedBy;
    }

    public ListItemNote getNote() {
        return this.note;
    }

    public RestAction<Boolean> delete() {
        return jg_api.getRestClient().deleteListitem(this.channelId, this.id);
    }
    public RestAction<ListItem> update(String message, ListItemNote note) {
        return this.jg_api.getRestClient().updateListItem(this.channelId, this.id, message, note);
    }
    public RestAction<ListItem> setMessage(String message) {
        return this.jg_api.getRestClient().updateListItem(this.channelId, this.id, message, this.note);
    }
    public RestAction<ListItem> setNote(ListItemNote note) {
        return this.jg_api.getRestClient().updateListItem(this.channelId, this.id, this.message, note);
    }
    public RestAction<Boolean> complete() {
        return this.jg_api.getRestClient().completeListItem(this.channelId, this.id);
    }
    public RestAction<Boolean> uncomplete() {
        return this.jg_api.getRestClient().uncompleteListItem(this.channelId, this.id);
    }
}
