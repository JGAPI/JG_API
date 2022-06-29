package dev.jgapi.jg_api.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RestQueue {
    private List<RestAction<?>> RestQueue = new ArrayList<>();
    private long seqNumber = 0;
    public long getNextSequenceNumber() {
        this.seqNumber++;
        return (this.seqNumber);
    }
    public void queue(RestAction<?> restAction) {
        this.RestQueue.add(restAction);
    }
    public void reorganizeBySequenceNumbers() {
        Collections.sort(this.RestQueue, Comparator.comparingLong(RestAction::getSequenceNumber));
    }
    public void removeBySequenceNumber(long seqNumber) {
        this.RestQueue.removeIf(item -> item.getSequenceNumber() == seqNumber);
    }

    public void processRestAction(RestAction<?> action) {
        // It's not empty, we want to process it
        try {
            action.complete();
        } catch (Exception ex) {
            // Ignore the errors cause it's already gonna be passed via onSuccess an onFailure
        }
    }

    public void processQueue() {
        if (this.isQueueEmpty()) return;
        // It's not empty, we want to process it
        RestAction<?> action = this.RestQueue.remove(0);
        this.processRestAction(action);
    }

    public void processQueue(int count) {
        for (int i=0; i < count; i++) {
            if (this.isQueueEmpty()) return;
            // It's not empty, we want to process it
            RestAction<?> action = this.RestQueue.remove(0);
            this.processRestAction(action);
        }
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
