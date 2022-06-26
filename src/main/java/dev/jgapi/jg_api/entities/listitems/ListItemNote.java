package dev.jgapi.jg_api.entities.listitems;

import dev.jgapi.jg_api.entities.channels.Mentions;

import java.time.Instant;

public class ListItemNote {
    private Mentions mentions;
    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
    private String content;
    public ListItemNote(Instant createdAt, String createdBy, Instant updatedAt, String updatedBy, Mentions mentions, String content) {
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.mentions = mentions;
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
