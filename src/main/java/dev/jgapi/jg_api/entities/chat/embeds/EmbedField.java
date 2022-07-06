package dev.jgapi.jg_api.entities.chat.embeds;

public class EmbedField {
    private String name;
    private String value;
    private boolean inline;
    public EmbedField(String name, String value, boolean inline) {
        this.name = name;
        this.value = value;
        this.inline = inline;
    }
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
