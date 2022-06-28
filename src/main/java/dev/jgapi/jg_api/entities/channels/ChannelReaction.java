package dev.jgapi.jg_api.entities.channels;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.entities.emotes.Emote;
import org.json.JSONObject;

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

    public static ChannelReaction parseChannelReactionObj(JSONObject reactionObj, JG_API jg_api) {
        return new ChannelReaction(
                jg_api,
                reactionObj.getString("channelId"),
                reactionObj.getString("messageId"),
                reactionObj.getString("createdBy"),
                Emote.parseEmoteObj(reactionObj.getJSONObject("emote"))
        );
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
