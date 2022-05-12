package dev.moratto.JGAPI.Events;

public abstract class GenericEvent {
    int op;
    String eventId;
    String eventType;
    String serverId;
    String data;

    public int getOp() {
        return this.op;
    }

    public String getEventId() {
        return this.eventId;
    }

    public String getEventType() {
        return this.eventType;
    }

    public String getServerId() {
        return this.serverId;
    }

    public String getData() {
        return this.data;
    }
}