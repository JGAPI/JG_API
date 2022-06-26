package dev.jgapi.jg_api.rest;


import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.channels.ServerChannel;
import dev.jgapi.jg_api.entities.chat.ChatMessage;
import dev.jgapi.jg_api.entities.http.HttpResponseEntity;
import dev.jgapi.jg_api.util.InstantHelper;
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
                action.getOnSuccess().accept(RestQueueUtils.processAction(action.get_JGAPI(), resp.getResponse(), action.getRequest().getRoute().getReturnType()));
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
