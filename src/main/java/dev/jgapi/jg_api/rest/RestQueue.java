package dev.jgapi.jg_api.rest;


import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.entities.calendars.CalendarEvent;
import dev.jgapi.jg_api.entities.channels.Mentions;
import dev.jgapi.jg_api.entities.channels.ServerChannel;
import dev.jgapi.jg_api.entities.chat.ChatEmbed;
import dev.jgapi.jg_api.entities.chat.ChatMessage;
import dev.jgapi.jg_api.entities.docs.Doc;
import dev.jgapi.jg_api.entities.forums.ForumTopic;
import dev.jgapi.jg_api.entities.http.HttpResponseEntity;
import dev.jgapi.jg_api.entities.listitems.ListItem;
import dev.jgapi.jg_api.entities.memberbans.ServerMemberBan;
import dev.jgapi.jg_api.entities.members.ServerMember;
import dev.jgapi.jg_api.entities.server.ServerModel;
import dev.jgapi.jg_api.entities.webhooks.Webhook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class RestQueue {
    private List<RestAction<?>> RestQueue = new ArrayList<>();
    private long seqNumber = 0;
    private final int TIMEOUT = 20000;
    public void add(RestAction<Object> action) {
        this.RestQueue.add(action);
    }
    public long getNextSequenceNumber() {
        this.seqNumber++;
        return (this.seqNumber);
    }
    public void queue(RestAction<Object> restAction) {
        this.RestQueue.add(restAction);
    }
    public void removeBySequenceNumber(long seqNumber) {
        this.RestQueue.removeIf(item -> item.getSequenceNumber() == seqNumber);
    }

    public void processRestAction(RestAction<?> action) throws IOException {
        if (this.isQueueEmpty()) return;
        // It's not empty, we want to process it
        HttpResponseEntity resp = action.getRequest().execute(TIMEOUT);
        switch (resp.getResponseCode()) {
            case 200:
            case 201:
                action.getOnSuccess().accept(processAction(action.get_JGAPI(), resp.getResponse(), action.getRequest().getRoute().getReturnType()));
                break;
            case 204:
                // Error
                // TODO Accept onFailure consumer
                break;
            default:
                // Error
                // TODO Accept onFailure consumer
        }
    }

    public void processQueue() throws IOException {
        if (this.isQueueEmpty()) return;
        // It's not empty, we want to process it
        RestAction<?> action = this.RestQueue.get(0);
        this.processRestAction(action);
    }

    public <T> T processAction(JG_API jg_api, String jsonResponse, Routing.ReturnType returnType) {
        // Example:
        // new RestAction<Webhook>(this.jg_api.getNextSeqNumber(), request, this.jg_api).queue(webhook -> { webhook.getServerId(); });
        JSONObject json = new JSONObject(jsonResponse);
        String serverId = json.optString("serverId", null);
        switch (returnType) {
            case NONE -> {
                return (T) Boolean.TRUE;
            }
            case ServerModel -> {
                return null;
            }
            case ServerChannel -> {
                JSONObject channel = json.getJSONObject("channel");
                return (T) new ServerChannel(jg_api, channel.getString("id"), channel.getString("type"), channel.getString("name"), channel.optString("topic", ""), Instant.parse(channel.getString("createdAt")), channel.getString("createdBy"), Instant.parse(channel.optString("updatedAt", "")), channel.getString("serverId"), channel.optString("parentId", ""), channel.optString("parentId", ""), channel.getString("groupId"), channel.optBoolean("isPublic", false), channel.optString("archivedBy", null), Instant.parse(channel.optString("archivedAt", "")));
            }
            case ChatMessage -> {
                JSONObject chatMessage = json.getJSONObject("message");
                JSONArray embedsJSON = chatMessage.optJSONArray("embeds");
                String[] replyMessageIds = null;
                ChatEmbed[] embeds = null;
                Mentions mentions = null;
                return (T) new ChatMessage(jg_api, chatMessage.getString("id"), chatMessage.getString("type"), chatMessage.optString("serverId", ""), chatMessage.getString("channelId"), chatMessage.getString("content"), embeds, replyMessageIds, chatMessage.optBoolean("isPrivate", false), chatMessage.optBoolean("isSilent", false), mentions, Instant.parse(chatMessage.getString("createdAt")), chatMessage.getString("createdBy"), chatMessage.optString("createdByWebhookId", null), Instant.parse(chatMessage.optString("updatedAt", null)));
            }
            case ChatMessage_Arr -> {
                // TODO
                return null;
            }
            case Nickname -> {
                // TODO
                return null;
            }
            case ServerMember -> {
                return null;
            }
            case ServerMemberSummary_Arr -> {
                // TODO
                return null;
            }
            case ServerMemberBan -> {
                return null;
            }
            case ServerMemberBan_Arr -> {
                // TODO
                return null;
            }
            case ForumTopic -> {
                return null;
            }
            case ListItem -> {
                return null;
            }
            case ListItemSummary_Arr -> {
                // TODO
                return null;
            }
            case ListItem_Update_Obj -> {
                // TODO
                return null;
            }
            case Doc -> {
                return null;
            }
            case Doc_Arr -> {
                // TODO
                return null;
            }
            case XP_Member_Total -> {
                // TODO
                return null;
            }
            case XP_Role_Total -> {
                // TODO
                return null;
            }
            case Social_Links_Obj -> {
                // TODO
                return null;
            }
            case MemberRoles -> {
                // TODO
                return null;
            }
            case Webhook -> {
                return null;
            }
            case Webhook_Arr -> {
                // TODO
                return null;
            }
            case CalendarEvent -> {
                return null;
            }
            case CalendarEvent_Arr -> {
                // TODO
                return null;
            }
        }
        return null;
    }

    public List<RestAction<?>> getQueuedRestActions() {
        return this.RestQueue;
    }

    public boolean hasSequenceNumber(long seqNumber) {
        long count = this.RestQueue.stream().filter(item -> item.getSequenceNumber() == seqNumber).count();
        return (count >= 1);
    }
    public boolean isQueueEmpty() {
        return this.RestQueue.isEmpty();
    }
}
