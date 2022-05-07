package dev.moratto.JGAPI.Entities.Chat;

public class ChatEmbed {
    private String title;
    private String description;
    private String url;
    private int color;
    private class Footer {
        private String icon_url;
        private String text;
    }
    private String timestamp;
    private class Thumbnail {
        private String url;
    }
    private class Image {
        private String url;
    }
    private class Author {
        private String name;
        private String url;
        private String icon_url;
    }
    private class Field {
        private String name;
        private String value;
        private boolean inline;
    }
    private Field[] fields;
}
