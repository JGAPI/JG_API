package dev.jgapi.jg_api.entities.chat.embeds;

public class EmbedFooter {
    private String icon_url;
    private String text;
    public EmbedFooter(String icon_url, String text) {
        this.icon_url = icon_url;
        this.text = text;
    }
    public String getIcon_url() {
        return this.icon_url;
    }
    public String getText() {
        return this.text;
    }
}
