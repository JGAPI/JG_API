package dev.jgapi.jg_api.entities.chat.embeds;

public class EmbedImage {
    private String url;
    public EmbedImage(String url) {
        this.url = url;
    }
    private String getUrl() {
        return this.url;
    }
}
