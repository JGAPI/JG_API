package dev.jgapi.jg_api.rest;

import java.util.ArrayList;
import java.util.List;

public class RestQueue {
    private List<RestAction> RestQueue = new ArrayList<>();
    private long seqNumber = 0;
    public void add(RestAction action) {
        this.RestQueue.add(action);
    }
    public long getNextSequenceNumber() {
        this.seqNumber++;
        return (this.seqNumber);
    }
    public void removeBySequenceNumber(long seqNumber) {}
    public void containsSequenceNumber(long seqNumber) {}
    public void processQueue() {}
    public boolean isQueueEmpty() {
        return this.RestQueue.isEmpty();
    }
}
