package dev.jgapi.jg_api.rest;

import cn.hutool.http.HttpResponse;
import dev.jgapi.jg_api.entities.http.HttpResponseEntity;

import java.io.IOException;
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
    public void processQueue() throws IOException {
        if (this.isQueueEmpty()) return;
        // It's not empty, we want to process it
        RestAction<?> action = this.RestQueue.get(0);
        HttpResponseEntity resp = action.getRequest().execute(TIMEOUT);
        switch (resp.getStatus()) {
            case 200:
            case 201:
                action.getOnSuccess().accept(processAction(resp.getResponse(), action.getRequest().getRoute().getReturnType()));
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

    public <T> T processAction(String jsonResponse, Routing.ReturnType returnType) {
        // Example:
        // new RestAction<Webhook>(this.jg_api.getNextSeqNumber(), request, this.jg_api).queue(webhook -> { webhook.getServerId(); });
        return switch (returnType) {
            case NONE -> (T) Boolean.TRUE;
            case ServerModel -> null;
            case ServerChannel -> null;
            case ChatMessage -> null;
            case ChatMessage_Arr -> null;
            case Nickname -> (T) "";
            case ServerMember -> null;
            case ServerMemberSummary_Arr -> null;
            case ServerMemberBan -> null;
            case ServerMemberBan_Arr -> null;
            case ForumTopic -> null;
            case ListItem -> null;
            case ListItemSummary_Arr -> null;
            case ListItem_Update_Obj -> null;
            case Doc -> null;
            case Doc_Arr -> null;
            case XP_Member_Total -> null;
            case XP_Role_Total -> null;
            case Social_Links_Obj -> null;
            case MemberRoles -> null;
            case Webhook -> null;
            case Webhook_Arr -> null;
            case CalendarEvent -> null;
            case CalendarEvent_Arr -> null;
        };
    }

    public List<RestAction<?>> getQueuedRestActions() {
        return this.RestQueue;
    }
    public boolean containsSequenceNumber(long seqNumber) {
        long count = this.RestQueue.stream().filter(item -> item.getSequenceNumber() == seqNumber).count();
        return (count >= 1);
    }
    public boolean isQueueEmpty() {
        return this.RestQueue.isEmpty();
    }
}
