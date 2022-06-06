package dev.moratto.JGAPI.Entities.Channels;

public class Mentions {
    private Object[] users;
    private Object[] channels;
    private Object[] roles;
    private boolean everyone;
    private boolean here;
    public Mentions(Object[] users, Object[] channels, Object[] roles, boolean everyone, boolean here) {
        this.users = users;
        this.channels = channels;
        this.roles = roles;
        this.everyone = everyone;
        this.here = here;
    }
}
