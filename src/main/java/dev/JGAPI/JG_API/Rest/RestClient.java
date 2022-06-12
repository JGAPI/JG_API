package dev.JGAPI.JG_API.Rest;

import cn.hutool.json.JSONObject;
import dev.JGAPI.JG_API.Entities.Chat.ChatMessage;
import dev.JGAPI.JG_API.Entities.Docs.Doc;
import dev.JGAPI.JG_API.Entities.Forums.ForumTopic;
import dev.JGAPI.JG_API.Entities.ListItems.ListItem;
import dev.JGAPI.JG_API.Entities.MemberBans.ServerMemberBan;
import dev.JGAPI.JG_API.Entities.Webhooks.Webhook;
import dev.JGAPI.JG_API.JG_API;

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
    public RestAction createChannelMessage(long channelId, ChatMessage message) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Messages.CREATE_MESSAGE, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getMessages(long channelId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Messages.GET_MESSAGES, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getMessage(long channelId, long messageId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Messages.GET_MESSAGE, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction updateMessage(long channelId, long messageId, ChatMessage message) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Messages.UPDATE_MESSAGE, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction deleteMessage(long channelId, long messageId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Messages.DELETE_MESSAGE, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction updateNickname(long serverId, long userId, String nickname) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Members.UPDATE_NICKNAME, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction deleteNickname(long serverId, long userId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Members.DELETE_NICKNAME, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getMember(long serverId, long userId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Members.GET_MEMBER, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction kickMember(long serverId, long userId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Members.KICK_MEMBER, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getMembers(long serverId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Members.GET_MEMBERS, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction createServerBan(long serverId, long userId, ServerMemberBan serverMemberBan) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.MemberBans.BAN_MEMBER, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getServerBan(long serverId, long userId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.MemberBans.GET_MEMBER_BAN, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction deleteServerBan(long serverId, long userId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.MemberBans.DELETE_MEMBER_BAN, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getServerBans(long serverId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.MemberBans.GET_BANS, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction createForumThread(long channelId, ForumTopic forumTopic) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Forums.CREATE_TOPIC, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction createListItem(long channelId, ListItem listItem) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.ListItems.CREATE_LIST_ITEM, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getListItems(long channelId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.ListItems.GET_LIST_ITEMS, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getListItem(long channelId, long listItemId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.ListItems.GET_LIST_ITEM, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction updateListItem(long channelId, long listItemId, ListItem listItem) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.ListItems.UPDATE_LIST_ITEM, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction deleteListitem(long channelId, long listItemId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.ListItems.DELETE_LIST_ITEM, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction completeListItem(long channelId, long listItemId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.ListItems.COMPLETE_LIST_ITEM, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction uncompleteListItem(long channelId, long listItemId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.ListItems.UNCOMPLETE_LIST_ITEM, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction createDoc(long channelId, Doc doc) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Docs.CREATE_DOC, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getDocs(long channelId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Docs.GET_DOCS, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getDoc(long channelId, long docId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Docs.GET_DOC, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction updateDoc(long channelId, long docId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Docs.UPDATE_DOC, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction deleteDoc(long channelId, long docId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Docs.DELETE_DOC, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction addReactionEmote(long channelId, long contentId, long emoteId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Reactions.ADD_REACTION_EMOTE, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction awardMemberXP(long serverId, long userId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Server_XP.AWARD_XP_TO_MEMBER, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction awardRoleXP(long serverId, long roleId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Server_XP.AWARD_XP_TO_ROLE, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getSocialLinks(long serverId, long userId, String type) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Social_Links.GET_MEMBER_SOCIAL_LINKS, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction addMemberToGroup(long groupId, long userId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Group_Memberships.ADD_MEMBER_TO_GROUP, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction removeMemberFromGroup(long groupId, long userId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Group_Memberships.REMOVE_MEMBER_FROM_GROUP, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction addRoleToMember(long serverId, long userId, long roleId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Role_Memberships.ADD_ROLE_TO_MEMBER, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction removeRoleFromMember(long serverId, long userId, long roleId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Role_Memberships.REMOVE_ROLE_FROM_MEMBER, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getMemberRoles(long serverId, long userId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Role_Memberships.GET_MEMBER_ROLES, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction createWebhook(long serverId, Webhook webhook) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Webhooks.CREATE_WEBHOOK, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getWebhooks(long serverId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Webhooks.GET_WEBHOOKS, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction getWebhook(long serverId, long webhookId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Webhooks.GET_WEBHOOK, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction updateWebhook(long serverId, long webhookId, Webhook webhook) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Webhooks.UPDATE_WEBHOOK, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
    public RestAction deleteWebhook(long serverId, long webhookId) {
        JSONObject body = new JSONObject();
        // TODO Set up the body of the request
        Request request = new Request(Routing.Webhooks.DELETE_WEBHOOK, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request);
    }
}
