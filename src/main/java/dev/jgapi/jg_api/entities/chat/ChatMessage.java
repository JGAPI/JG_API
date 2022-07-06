package dev.jgapi.jg_api.entities.chat;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.entities.channels.Mentions;
import dev.jgapi.jg_api.entities.channels.ServerChannel;
import dev.jgapi.jg_api.entities.chat.embeds.*;
import dev.jgapi.jg_api.rest.RestAction;
import dev.jgapi.jg_api.util.UtilClass;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ChatMessage extends GuildedObject {
    private String id;
    private String type;
    private String serverId;
    private ServerChannel channel;
    private String content;
    private ChatEmbed[] embeds;
    private String[] replyMessageIds;
    private boolean isPrivate;
    private boolean isSilent;
    private Mentions mentions;
    private Instant createdAt;
    private String createdBy;
    private String createdByWebhookId;
    private Instant updatedAt;
    private Instant deletedAt;

    public ChatMessage(JG_API jg_api, String id, String type, String serverId, ServerChannel channel, String content, ChatEmbed[] embeds, String[] replyMessageIds, boolean isPrivate, boolean isSilent, Mentions mentions, Instant createdAt, String createdBy, String createdByWebhookId, Instant updatedAt, Instant deletedAt) {
        super(jg_api);
        this.id = id;
        this.type = type;
        this.serverId = serverId;
        this.channel = channel;
        this.content = content;
        this.embeds = embeds;
        this.replyMessageIds = replyMessageIds;
        this.isPrivate = isPrivate;
        this.isSilent = isSilent;
        this.mentions = mentions;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.createdByWebhookId = createdByWebhookId;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static ChatMessage parseChatMessageObj(JSONObject chatMessageObj, JG_API jg_api) {
        JSONObject mentionsObj = chatMessageObj.optJSONObject("mentions", null);
        JSONArray replyMessageIdsObj = chatMessageObj.optJSONArray("replyMessageIds");
        List<String> replyMessageIdsList = new ArrayList<>();
        for (int i = 0; i < replyMessageIdsObj.length(); i++) {
            replyMessageIdsList.add(replyMessageIdsObj.optString(i));
        }
        String[] replyMessageIds = replyMessageIdsList.toArray(new String[0]);
        JSONArray embedsObj = chatMessageObj.getJSONArray("embeds");
        List<ChatEmbed> embedList = new ArrayList<>();
        for (int i = 0; i < embedsObj.length(); i++) {
            JSONObject embedObject = embedsObj.getJSONObject(i);
            String title = embedObject.optString("title", null);
            String description = embedObject.optString("description", null);
            String url = embedObject.optString("url", null);
            int color = embedObject.optInt("color", -1);
            JSONObject footObj = embedObject.optJSONObject("footer");
            EmbedFooter footer = new EmbedFooter(footObj.optString("icon_url", null), footObj.optString("text", null));
            String timestamp = embedObject.optString("timestamp", null);
            EmbedThumbnail embedThumbnail = new EmbedThumbnail(embedObject.optJSONObject("thumbnail").optString("url", null));
            EmbedImage embedImage = new EmbedImage(embedObject.optJSONObject("image").optString("url", null));
            JSONObject authorObj = embedObject.optJSONObject("author");
            EmbedAuthor embedAuthor = new EmbedAuthor(authorObj.optString("name", null), authorObj.optString("url", null), authorObj.optString("icon_url", null));
            JSONArray fields = embedObject.optJSONArray("fields");
            List<EmbedField> fieldList = new ArrayList<>();
            for (int j = 0; j < fields.length(); j++) {
                JSONObject field = fields.getJSONObject(j);
                EmbedField embedField = new EmbedField(field.optString("name", null), field.optString("value", null), field.optBoolean("inline", false));
                fieldList.add(embedField);
            }
            EmbedField[] embedFields = fieldList.toArray(new EmbedField[0]);
            embedList.add(new ChatEmbed(jg_api, title, description, url, color, footer, Instant.parse(timestamp), embedThumbnail, embedImage, embedAuthor, embedFields));
        }
        ChatEmbed[] embeds = embedList.toArray(new ChatEmbed[0]);
        return new ChatMessage(
                jg_api,
                chatMessageObj.getString("id"),
                chatMessageObj.getString("type"),
                chatMessageObj.optString("serverId", null),
                new ServerChannel(
                        jg_api,
                        chatMessageObj.getString("channelId"),
                        null, null, null, null, null, null,
                        chatMessageObj.optString("serverId", null),
                        null, -1, null, false, null, null
                ),
                chatMessageObj.getString("content"), embeds, replyMessageIds,
                chatMessageObj.optBoolean("isPrivate", false),
                chatMessageObj.optBoolean("isSilent", false),
                mentionsObj == null ? null : Mentions.parseMentionsObj(mentionsObj),
                Instant.parse(chatMessageObj.getString("createdAt")),
                chatMessageObj.getString("createdBy"),
                chatMessageObj.optString("createdByWebhookId", null),
                UtilClass.parseStringOrNull(chatMessageObj.optString("updatedAt", null)),
                UtilClass.parseStringOrNull(chatMessageObj.optString("deletedAt", null))
        );
    }

    public String getId() {
        return this.id;
    }
    public String getType() {
        return this.type;
    }
    public String getServerId() {
        return this.serverId;
    }
    public ServerChannel getChannel() {
        return this.channel;
    }
    public String getContent() {
        return this.content;
    }
    public ChatEmbed[] getEmbeds() {
        return this.embeds;
    }
    public String[] getReplyMessageIds() {
        return this.replyMessageIds;
    }
    public boolean isPrivate() {
        return this.isPrivate;
    }
    public boolean isSilent() {
        return this.isSilent;
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

    public RestAction<ChatMessage> delete() {
        return this.jg_api.getRestClient().deleteMessage(this.getChannel().getId(), this.id);
    }
    public RestAction<ChatMessage> setContent(String content) {
        return this.jg_api.getRestClient().updateMessage(this.getChannel().getId(), this.id, content, this.embeds);
    }
    public RestAction<ChatMessage> setEmbeds(ChatEmbed[] embeds) {
        return this.jg_api.getRestClient().updateMessage(this.getChannel().getId(), this.id, this.content, embeds);
    }
    public RestAction<ChatMessage> update(String content, ChatEmbed[] embeds) {
        return this.jg_api.getRestClient().updateMessage(this.getChannel().getId(), this.id, content, embeds);
    }

    public RestAction<ChatMessage> reply(String content, ChatEmbed[] embeds, boolean isPrivate, boolean isSilent) {
        return jg_api.getRestClient().createChannelMessage(
                this.getChannel().getId(),
                isPrivate,
                isSilent,
                new String[]{this.getId()},
                content,
                embeds
        );
    }
    public RestAction<ChatMessage> reply(String content, boolean isPrivate, boolean isSilent) {
        return this.reply(content, null, isPrivate, isSilent);
    }
    public RestAction<ChatMessage> reply(ChatEmbed[] embeds, boolean isPrivate, boolean isSilent) {
        return this.reply(null, embeds, isPrivate, isSilent);
    }
    public RestAction<ChatMessage> reply(String content) {
        return this.reply(content, null, false, false);
    }
    public RestAction<ChatMessage> reply(ChatEmbed[] embeds) {
        return this.reply(null, embeds, false, false);
    }
}
