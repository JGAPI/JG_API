package dev.JGAPI.JG_API.Rest;

import java.util.concurrent.TimeUnit;

public class RestAction {
    private long sequenceNumber;
    private Request request;
    public RestAction(long sequenceNumber, Request request) {
        this.request = request;
        this.sequenceNumber = sequenceNumber;
    }

    public long getSequenceNumber() {
        return this.sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    private class RestActionResponse {}

    public RestAction queue() {
        return null;
    }

    public RestAction submit() {
        return null;
    }

    public RestActionResponse complete() {
        return null;
    }

    public RestActionResponse completeAfter(TimeUnit unit, int delay) {
        return null;
    }
}
