package dev.JGAPI.JG_API.Events.TeamWebhook;

import dev.JGAPI.JG_API.Entities.Webhooks.Webhook;
import dev.JGAPI.JG_API.Events.Event;
import dev.JGAPI.JG_API.JG_API;

public abstract class GenericTeamWebhookEvent extends Event {
    Webhook webhook;

    public GenericTeamWebhookEvent(JG_API jg_api, String serverId, Webhook webhook) {
        super(jg_api, serverId);
        this.webhook = webhook;
    }

    public Webhook getWebhook() {
        return this.webhook;
    }
}
