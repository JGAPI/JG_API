package dev.JGAPI.JG_API.Rest;

import java.util.concurrent.TimeUnit;

public class RestAction<T> {
    private int sequenceNumber;
    private Request request;
    public RestAction(int sequenceNumber, Request request) {
        this.request = request;
        this.sequenceNumber = sequenceNumber;
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    private class RestActionResponse<T> {}

    public RestAction<T> queue() {
        return null;
    }

    public RestAction<T> submit() {
        return null;
    }

    public RestActionResponse<T> complete() {
        return null;
    }

    public RestActionResponse<T> completeAfter(TimeUnit unit, int delay) {
        return null;
    }
}
