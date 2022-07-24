package dev.jgapi.jg_api.events.forums;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.forums.ForumTopic;

public class ForumTopicCreatedEvent extends GenericForumEvent {
    public ForumTopicCreatedEvent(JG_API jg_api, String serverId, ForumTopic forumTopic) {
        super(jg_api, serverId, forumTopic);
    }
}
