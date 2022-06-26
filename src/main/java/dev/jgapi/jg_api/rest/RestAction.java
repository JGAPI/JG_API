package dev.jgapi.jg_api.rest;

import dev.jgapi.jg_api.JG_API;

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

    public JG_API get_JGAPI() {
        return this.jg_api;
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

    public void queue() {
        this.queue(null, null);
    }

    public void queue(Consumer<? super T> success) {
        this.queue(success, null);
    }

    public void queue(Consumer<? super T> success, Consumer<? super Throwable> failure) {
        this.onSuccess = success;
        this.onFailure = failure;
        this.jg_api.queueRestAction(this);
    }

    public void queueAfter(TimeUnit unit, int delay) {
        RestAction<T> restAction = this;
        Runnable task = () -> jg_api.queueRestAction(restAction);
        this.jg_api.getExecutorService().schedule(task, delay, unit);
    }

    public void queueAfter(TimeUnit unit, int delay, Consumer<? super T> success, Consumer<? super Throwable> failure) {
        this.onSuccess = success;
        this.onFailure = failure;
        RestAction<T> restAction = this;
        Runnable task = () -> jg_api.queueRestAction(restAction);
        this.jg_api.getExecutorService().schedule(task, delay, unit);
    }

    public T complete() {
        // Execute the RestAction right away
        // TODO
    }

    public void completeAfter(TimeUnit unit, int delay) {
        completeAfter(unit, delay, null, null);
    }

    public void completeAfter(TimeUnit unit, int delay, Consumer<? super T> success, Consumer<? super Throwable> failure) {
        this.onSuccess = success;
        this.onFailure = failure;
        RestAction<T> restAction = this;
        Runnable task = restAction::complete;
        this.jg_api.getExecutorService().schedule(task, delay, unit);
    }
}
