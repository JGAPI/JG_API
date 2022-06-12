package dev.jgapi.jg_api.events.teamwebhook;

import dev.jgapi.jg_api.entities.webhooks.Webhook;
import dev.jgapi.jg_api.JG_API;

public class TeamWebhookCreatedEvent extends GenericTeamWebhookEvent {
    public TeamWebhookCreatedEvent(JG_API jg_api, String serverId, Webhook webhook) {
        super(jg_api, serverId, webhook);
    }
}
