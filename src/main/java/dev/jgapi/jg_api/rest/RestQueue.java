package dev.jgapi.jg_api.rest;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import dev.jgapi.jg_api.entities.chat.ChatMessage;

import java.lang.reflect.Type;
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
                action.getOnSuccess().accept();
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

    public <T> T processAction() {
        // Example:
        // TODO new RestAction<Webhook>(this.jg_api.getNextSeqNumber(), request, this.jg_api).queue(webhook -> { webhook.getServerId(); });
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
