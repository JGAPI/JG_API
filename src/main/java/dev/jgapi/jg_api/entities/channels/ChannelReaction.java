package dev.jgapi.jg_api.entities.channels;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.entities.emotes.Emote;

public class ChannelReaction extends GuildedObject {
    String channelId;
    String messageId;
    String createdBy;
    Emote emote;

    public ChannelReaction(JG_API jg_api, String channelId, String messageId, String createdBy, Emote emote) {
        super(jg_api);
        this.channelId = channelId;
        this.messageId = messageId;
        this.createdBy = createdBy;
        this.emote = emote;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Emote getEmote() {
        return emote;
    }
}
