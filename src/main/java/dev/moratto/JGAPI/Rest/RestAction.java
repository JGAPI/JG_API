package dev.moratto.JGAPI.Rest;

public class RestAction {
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

    public RestAction execute() {}

    public String getResult() {
        return this.result;
    }
}
