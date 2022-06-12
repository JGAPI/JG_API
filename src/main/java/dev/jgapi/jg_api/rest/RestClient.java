package dev.jgapi.jg_api.rest;

import cn.hutool.json.JSONObject;
import dev.jgapi.jg_api.entities.chat.ChatMessage;
import dev.jgapi.jg_api.entities.docs.Doc;
import dev.jgapi.jg_api.entities.forums.ForumTopic;
import dev.jgapi.jg_api.entities.listitems.ListItem;
import dev.jgapi.jg_api.entities.memberbans.ServerMemberBan;
import dev.jgapi.jg_api.entities.webhooks.Webhook;
import dev.jgapi.jg_api.JG_API;

import java.time.Instant;
import java.util.HashMap;

public class RestClient {
    private JG_API jg_api;

    public RestClient(JG_API jg_api) {
        this.jg_api = jg_api;
    }

    private HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer ");
        headers.put("Accept", "application/json");
        headers.put("Content-type", "application/json");
        return headers;
    }
    public RestAction createChannelMessage(String channelId, ChatMessage message) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        body.put("isPrivate", message.isPrivate());
        body.put("isSilent", message.isSilent());
        body.put("replyMessageIds", message.getReplyMessageIds());
        body.put("content", message.getContent());
        body.put("embeds", message.getEmbeds());
        Request request = new Request(Routing.Messages.CREATE_MESSAGE, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getMessages(String channelId, Instant before, Instant after, int limit, boolean includePrivate) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        body.put("before", before.toString()); // TODO Needs to be ISO 8601 Timestamp
        body.put("after", after.toString()); // TODO Needs to be ISO 8601 Timestamp
        body.put("limit", limit);
        body.put("includePrivate", includePrivate);
        Request request = new Request(Routing.Messages.GET_MESSAGES, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getMessage(String channelId, String messageId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{messageId}", String.valueOf(messageId));
        // Set up the body of the request
        Request request = new Request(Routing.Messages.GET_MESSAGE, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction updateMessage(String channelId, String messageId, ChatMessage message) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{messageId}", String.valueOf(messageId));
        // Set up the body of the request
        body.put("content", message.getContent());
        body.put("embeds", message.getEmbeds());
        Request request = new Request(Routing.Messages.UPDATE_MESSAGE, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction deleteMessage(String channelId, String messageId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{messageId}", String.valueOf(messageId));
        // Set up the body of the request
        Request request = new Request(Routing.Messages.DELETE_MESSAGE, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction updateNickname(String serverId, String userId, String nickname) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        routeReplacements.put("{userId}", String.valueOf(userId));
        // Set up the body of the request
        body.put("nickname", nickname);
        Request request = new Request(Routing.Members.UPDATE_NICKNAME, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction deleteNickname(String serverId, String userId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        routeReplacements.put("{userId}", String.valueOf(userId));
        // Set up the body of the request
        Request request = new Request(Routing.Members.DELETE_NICKNAME, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getMember(String serverId, String userId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        routeReplacements.put("{userId}", String.valueOf(userId));
        // Set up the body of the request
        Request request = new Request(Routing.Members.GET_MEMBER, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction kickMember(String serverId, String userId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        routeReplacements.put("{userId}", String.valueOf(userId));
        // Set up the body of the request
        Request request = new Request(Routing.Members.KICK_MEMBER, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getMembers(String serverId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        // Set up the body of the request
        Request request = new Request(Routing.Members.GET_MEMBERS, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction createServerBan(String serverId, String userId, ServerMemberBan serverMemberBan) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        routeReplacements.put("{userId}", String.valueOf(userId));
        // Set up the body of the request
        body.put("reason", serverMemberBan.getReason());
        Request request = new Request(Routing.MemberBans.BAN_MEMBER, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getServerBan(String serverId, String userId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        routeReplacements.put("{userId}", String.valueOf(userId));
        // Set up the body of the request
        Request request = new Request(Routing.MemberBans.GET_MEMBER_BAN, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction deleteServerBan(String serverId, String userId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        routeReplacements.put("{userId}", String.valueOf(userId));
        // Set up the body of the request
        Request request = new Request(Routing.MemberBans.DELETE_MEMBER_BAN, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getServerBans(String serverId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        // Set up the body of the request
        Request request = new Request(Routing.MemberBans.GET_BANS, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction createForumTopic(String channelId, ForumTopic forumTopic) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        body.put("title", forumTopic.getTitle());
        body.put("content", forumTopic.getContent());
        Request request = new Request(Routing.Forums.CREATE_TOPIC, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction createListItem(String channelId, ListItem listItem) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        body.put("message", listItem.getMessage());
        body.put("note", listItem.getNote());
        Request request = new Request(Routing.ListItems.CREATE_LIST_ITEM, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getListItems(String channelId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        Request request = new Request(Routing.ListItems.GET_LIST_ITEMS, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getListItem(String channelId, String listItemId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{listItemId}", String.valueOf(listItemId));
        // Set up the body of the request
        Request request = new Request(Routing.ListItems.GET_LIST_ITEM, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction updateListItem(String channelId, String listItemId, ListItem listItem) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{listItemId}", String.valueOf(listItemId));
        // Set up the body of the request
        body.put("message", listItem.getMessage());
        Request request = new Request(Routing.ListItems.UPDATE_LIST_ITEM, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction deleteListitem(String channelId, String listItemId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{listItemId}", String.valueOf(listItemId));
        // Set up the body of the request
        Request request = new Request(Routing.ListItems.DELETE_LIST_ITEM, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction completeListItem(String channelId, String listItemId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{listItemId}", String.valueOf(listItemId));
        // Set up the body of the request
        Request request = new Request(Routing.ListItems.COMPLETE_LIST_ITEM, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction uncompleteListItem(String channelId, String listItemId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{listItemId}", String.valueOf(listItemId));
        // Set up the body of the request
        Request request = new Request(Routing.ListItems.UNCOMPLETE_LIST_ITEM, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction createDoc(String channelId, Doc doc) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        body.put("title", doc.getTitle());
        body.put("content", doc.getContent());
        Request request = new Request(Routing.Docs.CREATE_DOC, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getDocs(String channelId, Instant before, int limit) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        body.put("before", before); // TODO Needs to be ISO 8601 Timestamp
        body.put("limit", limit);
        Request request = new Request(Routing.Docs.GET_DOCS, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getDoc(String channelId, String docId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{docId}", docId);
        // Set up the body of the request
        Request request = new Request(Routing.Docs.GET_DOC, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction updateDoc(String channelId, String docId, Doc doc) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{docId}", docId);
        // Set up the body of the request
        body.put("title", doc.getTitle());
        body.put("content", doc.getContent());
        Request request = new Request(Routing.Docs.UPDATE_DOC, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction deleteDoc(String channelId, String docId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{docId}", docId);
        // Set up the body of the request
        Request request = new Request(Routing.Docs.DELETE_DOC, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction addReactionEmote(String channelId, String contentId, String emoteId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{contentId}", contentId);
        routeReplacements.put("{emoteId}", emoteId);
        // Set up the body of the request
        Request request = new Request(Routing.Reactions.ADD_REACTION_EMOTE, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction awardMemberXP(String serverId, String userId, int amount) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{userId}", userId);
        // Set up the body of the request
        body.put("amount", amount);
        Request request = new Request(Routing.Server_XP.AWARD_XP_TO_MEMBER, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction awardRoleXP(String serverId, String roleId, int amount) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{roleId}", roleId);
        // Set up the body of the request
        body.put("amount", amount);
        Request request = new Request(Routing.Server_XP.AWARD_XP_TO_ROLE, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getSocialLinks(String serverId, String userId, String type) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{userId}", userId);
        routeReplacements.put("{type}", type);
        // Set up the body of the request
        Request request = new Request(Routing.Social_Links.GET_MEMBER_SOCIAL_LINKS, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction addMemberToGroup(String groupId, String userId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{groupId}", groupId);
        routeReplacements.put("{userId}", userId);
        // Set up the body of the request
        Request request = new Request(Routing.Group_Memberships.ADD_MEMBER_TO_GROUP, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction removeMemberFromGroup(String groupId, String userId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{groupId}", groupId);
        routeReplacements.put("{userId}", userId);
        // Set up the body of the request
        Request request = new Request(Routing.Group_Memberships.REMOVE_MEMBER_FROM_GROUP, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction addRoleToMember(String serverId, String userId, int roleId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{userId}", userId);
        routeReplacements.put("{roleId}", String.valueOf(roleId));
        // Set up the body of the request
        Request request = new Request(Routing.Role_Memberships.ADD_ROLE_TO_MEMBER, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction removeRoleFromMember(String serverId, String userId, int roleId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{userId}", userId);
        routeReplacements.put("{roleId}", String.valueOf(roleId));
        // Set up the body of the request
        Request request = new Request(Routing.Role_Memberships.REMOVE_ROLE_FROM_MEMBER, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getMemberRoles(String serverId, String userId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{userId}", userId);
        // Set up the body of the request
        Request request = new Request(Routing.Role_Memberships.GET_MEMBER_ROLES, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction createWebhook(String serverId, Webhook webhook) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        // Set up the body of the request
        body.put("name", webhook.getName());
        body.put("channelId", webhook.getChannelId());
        Request request = new Request(Routing.Webhooks.CREATE_WEBHOOK, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getWebhooks(String serverId, String channelId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        // Set up the body of the request
        body.put("channelId", channelId);
        Request request = new Request(Routing.Webhooks.GET_WEBHOOKS, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getWebhook(String serverId, String webhookId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{webhookId}", webhookId);
        // Set up the body of the request
        Request request = new Request(Routing.Webhooks.GET_WEBHOOK, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction updateWebhook(String serverId, String webhookId, Webhook webhook) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{webhookId}", webhookId);
        // Set up the body of the request
        body.put("name", webhook.getName());
        body.put("channelId", webhook.getChannelId());
        Request request = new Request(Routing.Webhooks.UPDATE_WEBHOOK, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction deleteWebhook(String serverId, String webhookId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{webhookId}", webhookId);
        // Set up the body of the request
        Request request = new Request(Routing.Webhooks.DELETE_WEBHOOK, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
}
