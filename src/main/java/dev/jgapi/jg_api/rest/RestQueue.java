package dev.jgapi.jg_api.rest;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import dev.jgapi.jg_api.entities.StringEntity;
import dev.jgapi.jg_api.entities.chat.ChatMessage;
import dev.jgapi.jg_api.entities.server.ServerModel;
import dev.jgapi.jg_api.exceptions.ReturnTypeException;

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
    public void processQueue() {
        if (this.isQueueEmpty()) return;
        // It's not empty, we want to process it
        RestAction<?> action = this.RestQueue.get(0);
        Request request = action.getRequest();
        String endpointReplace = request.getRoute().getRoute();
        for (String key : request.getRouteReplacements().keySet()) {
            String value = request.getRouteReplacements().get(key);
            endpointReplace = endpointReplace.replace(key, value);
        }
        HttpRequest httpRequest = new HttpRequest(request.getRoute().getUrl() + request.getRoute().getVersion() + endpointReplace);
        httpRequest.method(request.getRoute().getMethod());
        for (String headerKey : request.getHeaders().keySet()) {
            String headerVal = request.getHeaders().get(headerKey);
            httpRequest.header(headerKey, headerVal);
        }
        httpRequest.timeout(this.TIMEOUT);
        String body = "";
        httpRequest.body(body);
        HttpResponse resp = httpRequest.execute();
        switch (resp.getStatus()) {
            case 200:
            case 201:
                // TODO Accept onSuccess consumer with right type of response needed
                // action.getOnSuccess().accept();
                action.getOnSuccess().accept(processAction(resp.body(), request.getRoute().getReturnType()));
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
        switch (returnType) {
            case NONE:
                return (T) Boolean.TRUE;
            case ServerModel:
                return null;
            case ServerChannel:
                return null;
            case ChatMessage:
                return null;
            case ChatMessage_Arr:
                return null;
            case Nickname:
               return (T) "";
            case ServerMember:
                return null;
            case ServerMemberSummary_Arr:
                return null;
            case ServerMemberBan:
                return null;
            case ServerMemberBan_Arr:
                return null;
            case ForumTopic:
                return null;
            case ListItem:
                return null;
            case ListItemSummary_Arr:
                return null;
            case ListItem_Update_Obj:
                return null;
            case Doc:
                return null;
            case Doc_Arr:
                return null;
            case XP_Member_Total:
                return null;
            case XP_Role_Total:
                return null;
            case Social_Links_Obj:
                return null;
            case MemberRoles:
                return null;
            case Webhook:
                return null;
            case Webhook_Arr:
                return null;

        }
        return null;
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
