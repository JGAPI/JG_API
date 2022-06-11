package dev.JGAPI.JG_API.Entities.ListItems;

import dev.JGAPI.JG_API.Entities.Channels.Mentions;

import java.time.Instant;

public class ListItemNote {
    private Mentions mentions;
    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
    private String content;
    public ListItemNote(Mentions mentions, Instant createdAt, String createdBy, Instant updatedAt, String updatedBy, String content) {
        this.mentions = mentions;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.content = content;
    }

    public Mentions getMentions() {
        return this.mentions;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public String getContent() {
        return content;
    }
}
