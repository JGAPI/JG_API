package dev.jgapi.jg_api.rest;


import dev.jgapi.jg_api.entities.http.HttpResponseEntity;
import dev.jgapi.jg_api.util.RestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestQueue {
    private List<RestAction<?>> RestQueue = new ArrayList<>();
    private long seqNumber = 0;
    private final int TIMEOUT = 20000;
    public long getNextSequenceNumber() {
        this.seqNumber++;
        return (this.seqNumber);
    }
    public void queue(RestAction<?> restAction) {
        this.RestQueue.add(restAction);
    }
    public void reorganizeBySequenceNumbers() {
        // TODO Reorganize queue order by RestAction sequenceNumbers
    }
    public void removeBySequenceNumber(long seqNumber) {
        this.RestQueue.removeIf(item -> item.getSequenceNumber() == seqNumber);
    }

    public void processRestAction(RestAction<?> action) throws IOException {
        // It's not empty, we want to process it
        HttpResponseEntity resp = action.getRequest().execute(TIMEOUT);
        switch (resp.getResponseCode()) {
            case 200:
            case 201:
                if (action.getOnSuccess() != null)
                    action.getOnSuccess().accept(RestUtils.processAction(action.get_JGAPI(), resp.getResponse(), action.getRequest().getRoute().getReturnType()));
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
        RestAction<?> action = this.RestQueue.remove(0);
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
        return this.RestQueue.size() == 0;
    }
}
