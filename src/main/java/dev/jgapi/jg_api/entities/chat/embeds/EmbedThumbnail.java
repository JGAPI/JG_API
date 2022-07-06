package dev.jgapi.jg_api.entities.chat.embeds;

public class EmbedThumbnail {
    private String url;
    public EmbedThumbnail(String url) {
        this.url = url;
    }
    private String getUrl() {
        return this.url;
    }
}
