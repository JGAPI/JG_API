package dev.jgapi.jg_api.entities.chat;

import java.time.Instant;

public class ChatEmbed {
    private String title;
    private String description;
    private String url;
    private int color;
    private class Footer {
        private String icon_url;
        private String text;
        public String getIcon_url() {
            return this.icon_url;
        }
        public String getText() {
            return this.text;
        }
    }
    private Footer footer;
    private Instant timestamp;
    private class Thumbnail {
        private String url;
        private String getUrl() {
            return this.url;
        }
    }
    private Thumbnail thumbnail;
    private class Image {
        private String url;
        private String getUrl() {
            return this.url;
        }
    }
    private Image image;
    private class Author {
        private String name;
        private String url;
        private String icon_url;
        private String getName() {
            return this.name;
        }
        private String getUrl() {
            return this.url;
        }
        private String getIcon_url() {
            return this.icon_url;
        }
    }
    private Author author;
    private class Field {
        private String name;
        private String value;
        private boolean inline;
        private String getName() {
            return this.name;
        }
        private String getValue() {
            return this.value;
        }
        private boolean getInline() {
            return this.inline;
        }
    }
    private Field[] fields;
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
    private Footer getFooter() {
        return this.footer;
    }
    private Instant getTimestamp() {
        return this.timestamp;
    }
    private Thumbnail getThumbnail() {
        return this.thumbnail;
    }
    private Image getImage() {
        return this.image;
    }
    private Author getAuthor() {
        return this.author;
    }
    private Field[] getFields() {
        return this.fields;
    }
}
