package dev.jgapi.jg_api.entities.chat.embeds;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;

import java.time.Instant;

public class ChatEmbed extends GuildedObject {
    private String title;
    private String description;
    private String url;
    private int color;

    public ChatEmbed(JG_API jg_api, String title, String description, String url, int color, EmbedFooter footer, Instant timestamp, EmbedThumbnail thumbnail, EmbedImage image, EmbedAuthor author, EmbedField[] fields) {
        super(jg_api);
        this.title = title;
        this.description = description;
        this.url = url;
        this.color = color;
        this.footer = footer;
        this.timestamp = timestamp;
        this.thumbnail = thumbnail;
        this.image = image;
        this.author = author;
        this.fields = fields;
    }

    private EmbedFooter footer;
    private Instant timestamp;
    private EmbedThumbnail thumbnail;
    private EmbedImage image;
    private EmbedAuthor author;
    private EmbedField[] fields;
    private String getTitle() {
        return this.title;
    }
    private String getDescription() {
        return this.description;
    }
    private String getUrl() {
        return this.url;
    }
    private int getColor() {
        return this.color;
    }
    private EmbedFooter getFooter() {
        return this.footer;
    }
    private Instant getTimestamp() {
        return this.timestamp;
    }
    private EmbedThumbnail getThumbnail() {
        return this.thumbnail;
    }
    private EmbedImage getImage() {
        return this.image;
    }
    private EmbedAuthor getAuthor() {
        return this.author;
    }
    private EmbedField[] getFields() {
        return this.fields;
    }
}
