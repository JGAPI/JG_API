package dev.jgapi.jg_api.rest;

import cn.hutool.http.Method;

public class Routing {

    public enum Status {
        CODE_200(200, "The request was successful"),
        CODE_201(201, "The content was created"),
        CODE_204(204, "No content returned"),
        CODE_400(400, "The request was unacceptable, often due to missing parameters"),
        CODE_401(401, "The access token is missing or invalid"),
        CODE_403(403, "The bot does not have the necessary permissions"),
        CODE_404(404, "The requested resource does not exist"),
        CODE_409(409, "The request conflicted with another request"),
        CODE_500(500, "Something went wrong on our end"),
        CODE_502(502, "Server overloaded or unavailable... Please try again in 30+ seconds"),
        CODE_503(503, "Server overloaded or unavailable... Please try again in 30+ seconds"),
        CODE_504(504, "Server overloaded or unavailable... Please try again in 30+ seconds");
        private int statusCode;
        private String msg;
        Status(int statusCode, String msg) {
            this.statusCode = statusCode;
            this.msg = msg;
        }
        int getStatusCode() {
            return this.statusCode;
        }
        String getMsg() {
            return this.msg;
        }
    }

    public Status getStatusFromCode(int code) {
        for (Status status : Status.values()) {
            if (status.getStatusCode() == code)
                return status;
        }
        return null;
    }

    public static class Channels {
        public static final Routing CREATE_CHANNEL = new Routing(Method.POST, "/channels");
        public static final Routing GET_CHANNEL = new Routing(Method.GET, "/channels/{channelId}");
        public static final Routing UPDATE_CHANNEL = new Routing(Method.PATCH, "/channels/{channelId}");
        public static final Routing DELETE_CHANNEL = new Routing(Method.POST, "/channels/{channelId}");
    }
    public static class Messages {
        public static final Routing CREATE_MESSAGE = new Routing(Method.POST, "/channels/{channelId}/messages");
        public static final Routing GET_MESSAGES = new Routing(Method.GET, "/channels/{channelId}/messages");
        public static final Routing GET_MESSAGE = new Routing(Method.GET, "/channels/{channelId}/messages/{messageId}");
        public static final Routing UPDATE_MESSAGE = new Routing(Method.PUT, "/channels/{channelId}/messages/{messageId}");
        public static final Routing DELETE_MESSAGE = new Routing(Method.DELETE, "/channels/{channelId}/messages/{messageId}");
    }
    public static class Members {
        public static final Routing UPDATE_NICKNAME = new Routing(Method.PUT, "/servers/{serverId}/members/{userId}/nickname");
        public static final Routing DELETE_NICKNAME = new Routing(Method.DELETE, "/servers/{serverId}/members/{userId}/nickname");
        public static final Routing GET_MEMBER = new Routing(Method.GET, "/servers/{serverId}/members/{userId}");
        public static final Routing KICK_MEMBER = new Routing(Method.DELETE, "/servers/{serverId}/members/{userId}");
        public static final Routing GET_MEMBERS = new Routing(Method.GET, "/servers/{serverId}/members");
    }
    public static class MemberBans {
        public static final Routing BAN_MEMBER = new Routing(Method.POST, "/servers/{serverId}/bans/{userId}");
        public static final Routing GET_MEMBER_BAN = new Routing(Method.GET, "/servers/{serverId}/bans/{userId}");
        public static final Routing DELETE_MEMBER_BAN = new Routing(Method.DELETE, "/servers/{serverId}/bans/{userId}");
        public static final Routing GET_BANS = new Routing(Method.GET, "/servers/{serverId}/bans");
    }
    public static class Forums {
        public static final Routing CREATE_TOPIC = new Routing(Method.POST, "/channels/{channelId}/topics");
    }
    public static class ListItems {
        public static final Routing CREATE_LIST_ITEM = new Routing(Method.POST, "/channels/{channelId}/items");
        public static final Routing GET_LIST_ITEMS = new Routing(Method.GET, "/channels/{channelId}/items");
        public static final Routing GET_LIST_ITEM = new Routing(Method.GET, "/channels/{channelId}/{listItemId}");
        public static final Routing UPDATE_LIST_ITEM = new Routing(Method.PUT, "/channels/{channelId}/items/{listItemId}");
        public static final Routing DELETE_LIST_ITEM = new Routing(Method.DELETE, "/channels/{channelId}/items/{listItemId}");
        public static final Routing COMPLETE_LIST_ITEM = new Routing(Method.POST, "/channels/{channelId}/items/{listItemId}/complete");
        public static final Routing UNCOMPLETE_LIST_ITEM = new Routing(Method.DELETE, "/channels/{channelId}/items/{listItemId}/complete");
    }
    public static class Docs {
        public static final Routing CREATE_DOC = new Routing(Method.POST, "/channels/{channelId}/docs");
        public static final Routing GET_DOCS = new Routing(Method.GET, "/channels/{channelId}/docs");
        public static final Routing GET_DOC = new Routing(Method.GET, "/channels/{channelId}/docs/{docId}");
        public static final Routing UPDATE_DOC = new Routing(Method.PUT, "/channels/{channelId}/docs/{docId}");
        public static final Routing DELETE_DOC = new Routing(Method.DELETE, "/channels/{channelId}/docs/{docId}");
    }
    public static class Reactions {
        public static final Routing ADD_REACTION_EMOTE = new Routing(Method.PUT, "/channels/{channelId}/content/{contentId}/emotes/{emoteId}");
    }
    public static class Server_XP {
        public static final Routing AWARD_XP_TO_MEMBER = new Routing(Method.POST, "/servers/{serverId}/members/{userId}/xp");
        public static final Routing AWARD_XP_TO_ROLE = new Routing(Method.POST, "/servers/{serverId}/roles/{roleId}/xp");
    }
    public static class Social_Links {
        public static final Routing GET_MEMBER_SOCIAL_LINKS = new Routing(Method.GET, "/servers/{serverId}/members/{userId}/social-links/{type}");
    }
    public static class Group_Memberships {
        public static final Routing ADD_MEMBER_TO_GROUP = new Routing(Method.PUT, "/groups/{groupId}/members/{userId}");
        public static final Routing REMOVE_MEMBER_FROM_GROUP = new Routing(Method.DELETE, "/groups/{groupId}/members/{userId}");
    }
    public static class Role_Memberships {
        public static final Routing ADD_ROLE_TO_MEMBER = new Routing(Method.PUT, "/servers/{serverId}/members/{userId}/roles/{roleId}");
        public static final Routing REMOVE_ROLE_FROM_MEMBER = new Routing(Method.DELETE, "/servers/{serverId}/members/{userId}/roles/{roleId}");
        public static final Routing GET_MEMBER_ROLES = new Routing(Method.GET, "/servers/{serverId}/members/{userId}/roles");
    }
    public static class Webhooks {
        public static final Routing CREATE_WEBHOOK = new Routing(Method.POST, "/servers/{serverId}/webhooks");
        public static final Routing GET_WEBHOOKS = new Routing(Method.GET, "/servers/{serverId}/webhooks");
        public static final Routing GET_WEBHOOK = new Routing(Method.GET, "/servers/{serverId}/webhooks/{webhookId}");
        public static final Routing UPDATE_WEBHOOK = new Routing(Method.PUT, "/servers/{serverId}/webhooks/{webhookId}");
        public static final Routing DELETE_WEBHOOK = new Routing(Method.DELETE, "/servers/{serverId}/webhooks/{webhookId}");
    }

    private final String version = "v1";
    private final String url = "https://www.guilded.gg/api/";
    private final String route;
    private final Method method;
    public Routing(Method method, String route) {
        this.method = method;
        this.route = route;
    }

    public String getUrl() {
        return this.url;
    }

    public String getVersion() {
        return this.version;
    }

    public String getRoute() {
        return this.route;
    }

    public Method getMethod() {
        return this.method;
    }
}
