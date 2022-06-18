package dev.jgapi.jg_api.rest;

import dev.jgapi.jg_api.JG_API;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class RestAction<T> {
    private long sequenceNumber;
    private Request request;
    private JG_API jg_api;
    private Consumer<? super T> onSuccess;
    private Consumer<? super Throwable> onFailure;
    public RestAction(long sequenceNumber, Request request, JG_API jg_api) {
        this.request = request;
        this.sequenceNumber = sequenceNumber;
        this.jg_api = jg_api;
    }

    public Consumer<? super T> getOnSuccess() {
        return this.onSuccess;
    }

    public Consumer<? super Throwable> getOnFailure() {
        return this.onFailure;
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

    public RestAction<T> queue() {
        return this;
    }

    public RestAction<T> queue(Consumer<? super T> success) {
        this.queue(success, null);
        return this;
    }

    public RestAction<T> queue(Consumer<? super T> success, Consumer<? super Throwable> failure) {
        this.onSuccess = success;
        this.onFailure = failure;
        this.jg_api.queueRestAction(this);
        return this;
    }

    public RestAction<T> submit() {
        return this;
    }

    public RestAction<T> completeAfter(TimeUnit unit, int delay) {
        return null;
    }
}
