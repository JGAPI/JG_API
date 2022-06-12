package dev.jgapi.jg_api.events.teamwebhook;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.webhooks.Webhook;
import dev.jgapi.jg_api.events.Event;

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
