package dev.jgapi.jg_api.entities.chat.embeds;

public class EmbedAuthor {
    private String name;
    private String url;
    private String icon_url;
    public EmbedAuthor(String name, String url, String icon_url) {
        this.name = name;
        this.url = url;
        this.icon_url = icon_url;
    }
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
