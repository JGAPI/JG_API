package dev.jgapi.jg_api.rest;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.http.HttpResponseEntity;
import dev.jgapi.jg_api.exceptions.RestActionException;
import dev.jgapi.jg_api.util.RestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class RestAction<T> {
    private long sequenceNumber;
    private Request request;
    private JG_API jg_api;
    private Consumer<? super T> onSuccess;
    private Consumer<? super Throwable> onFailure;
    private int TIMEOUT = 20000;
    public RestAction(long sequenceNumber, Request request, JG_API jg_api) {
        this.request = request;
        this.sequenceNumber = sequenceNumber;
        this.jg_api = jg_api;
    }

    public JG_API get_JGAPI() {
        return this.jg_api;
    }

    public int getTimeout() {
        return this.TIMEOUT;
    }

    public void setTimeout(int timeout) {
        this.TIMEOUT = timeout;
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
        this.jg_api.reorganizeRestQueue();
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

    public T complete() throws IOException, RestActionException {
        // Execute the RestAction right away
        HttpResponseEntity resp = this.getRequest().execute(TIMEOUT);
        System.out.println(resp.getResponse());
        T returnVal = RestUtils.processAction(this.get_JGAPI(), resp.getResponse(), this.getRequest().getRoute().getReturnType());
        switch (resp.getResponseCode()) {
            case 200:
            case 201:
                if (this.onSuccess != null)
                    this.onSuccess.accept(returnVal);
                return returnVal;
            default:
                // Error
                if (this.onFailure != null)
                    if (returnVal instanceof Throwable)
                        this.onFailure.accept((Throwable) returnVal);
        }
        if (this.onFailure != null)
            this.onFailure.accept(new RestActionException("RestAction processing failed... Check your code please."));
        throw new RestActionException("RestAction processing failed... Check your code please.");
    }

    public void completeAfter(TimeUnit unit, int delay) {
        completeAfter(unit, delay, null, null);
    }

    public void completeAfter(TimeUnit unit, int delay, Consumer<? super T> success, Consumer<? super Throwable> failure) {
        this.onSuccess = success;
        this.onFailure = failure;
        RestAction<T> restAction = this;
        Runnable task = () -> {
            try {
                restAction.complete();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
        this.jg_api.getExecutorService().schedule(task, delay, unit);
    }
}
