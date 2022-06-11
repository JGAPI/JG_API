package dev.JGAPI.JG_API.Events.TeamWebhook;

import dev.JGAPI.JG_API.Entities.Webhooks.Webhook;
import dev.JGAPI.JG_API.JG_API;

public class TeamWebhookUpdatedEvent extends GenericTeamWebhookEvent {
    public TeamWebhookUpdatedEvent(JG_API jg_api, String serverId, Webhook webhook) {
        super(jg_api, serverId, webhook);
    }
}
