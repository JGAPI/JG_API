package dev.moratto.JGAPI.Rest;

public class RestAction<T> {
    private int sequenceNumber;
    private Request request;
    private String result = null;
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

    public Request getRequest() {
        return this.request;
    }

    public RestAction<T> queue() {}

    public RestAction<T> submit() {}

    public RestAction<T> complete() {}
}
