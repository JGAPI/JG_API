package dev.moratto.JGAPI.Rest;

public class Routing {
    public enum Method {
        GET("GET"),
        POST("POST");
        private String method;
        Method(String method) {
            this.method = method;
        }
        String getMethod() {
            return this.method;
        }
    }
    public static class Channels {
        public static final Routing CREATE_CHANNEL = new Routing(Method.POST, "/channels");
        public static final Routing GET_CHANNEL = new Routing(Method.GET, "/channels/{channelId}");
        public static final Routing DELETE_CHANNEL = new Routing(Method.POST, "/channels/{channelId}");
    }
    public static class Messages {}
    public static class Members {}
    public static class MemberBans {}
    public static class Forums {}
    public static class ListItems {}
    public static class Docs {}
    public static class Reactions {}
    public static class Server_XP {}
    public static class Social_Links {}
    public static class Group_Memberships {}
    public static class Role_Memberships {}
    public static class Webhooks {}

    private final String version = "v1";
    private final String route;
    private final Method method;
    public Routing(Method method, String route) {
        this.method = method;
        this.route = route;
    }
}
