package dev.JGAPI.JG_API.Events.TeamWebhook;

import dev.JGAPI.JG_API.Entities.Webhooks.Webhook;
import dev.JGAPI.JG_API.JG_API;

public class TeamWebhookCreatedEvent extends GenericTeamWebhookEvent {
    public TeamWebhookCreatedEvent(JG_API jg_api, String serverId, Webhook webhook) {
        super(jg_api, serverId, webhook);
    }
}
