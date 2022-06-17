package dev.jgapi.jg_api.rest;

import cn.hutool.json.JSONObject;
import dev.jgapi.jg_api.JG_API;

import java.util.concurrent.TimeUnit;

public class RestAction {
    private long sequenceNumber;
    private Request request;
    private JG_API jg_api;
    public RestAction(long sequenceNumber, Request request, JG_API jg_api) {
        this.request = request;
        this.sequenceNumber = sequenceNumber;
        this.jg_api = jg_api;
    }

    public Request getRequest() {
        return this.request;
    }

    public long getSequenceNumber() {
        return this.sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    private class RestActionResponse {
        private JSONObject response = new JSONObject();
        public RestActionResponse(JSONObject response) {}
    }

    public RestAction queue() {
        return this;
    }

    public RestAction submit() {
        return this;
    }

    public RestActionResponse complete() {
        RestActionResponse restAR = new RestActionResponse();
        return  restAR;
    }

    public RestActionResponse completeAfter(TimeUnit unit, int delay) {
        return null;
    }
}
