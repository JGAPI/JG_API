package dev.jgapi.jg_api.events.forums;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.forums.ForumTopic;
import dev.jgapi.jg_api.events.Event;

public abstract class GenericForumEvent extends Event {
    ForumTopic forumTopic;
    public GenericForumEvent(JG_API jg_api, String serverId, ForumTopic forumTopic) {
        super(jg_api, serverId);
        this.forumTopic = forumTopic;
    }
    public ForumTopic getForumTopic() {
        return this.forumTopic;
    }
}
