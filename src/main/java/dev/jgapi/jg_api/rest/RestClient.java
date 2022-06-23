package dev.jgapi.jg_api.rest;

import cn.hutool.json.JSONObject;
import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.calendars.CalendarEvent;
import dev.jgapi.jg_api.entities.channels.ServerChannel;
import dev.jgapi.jg_api.entities.chat.ChatEmbed;
import dev.jgapi.jg_api.entities.chat.ChatMessage;
import dev.jgapi.jg_api.entities.docs.Doc;
import dev.jgapi.jg_api.entities.forums.ForumTopic;
import dev.jgapi.jg_api.entities.listitems.ListItem;
import dev.jgapi.jg_api.entities.listitems.ListItemNote;
import dev.jgapi.jg_api.entities.memberbans.ServerMemberBan;
import dev.jgapi.jg_api.entities.members.ServerMember;
import dev.jgapi.jg_api.entities.server.ServerModel;
import dev.jgapi.jg_api.entities.webhooks.Webhook;

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

    public RestAction<ServerModel> getServer(String serverId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        Request request = new Request(Routing.Servers.GET_SERVER, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ServerChannel> createChannel(ServerChannel channel) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        // Set up the body of the request
        body.set("name", channel.getName());
        body.set("topic", channel.getTopic());
        body.set("isPublic", channel.isPublic());
        body.set("type", channel.getType());
        body.set("serverId", channel.getServerId());
        body.set("groupId", channel.getGroupId());
        body.set("categoryId", channel.getCategoryId());
        Request request = new Request(Routing.Channels.CREATE_CHANNEL, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ServerChannel> createChannel(String name, String topic, boolean isPublic, String type, String serverId, String groupId, String categoryId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        // Set up the body of the request
        body.set("name", name);
        body.set("topic", topic);
        body.set("isPublic", isPublic);
        body.set("type", type);
        body.set("serverId", serverId);
        body.set("groupId", groupId);
        body.set("categoryId", categoryId);
        Request request = new Request(Routing.Channels.CREATE_CHANNEL, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ServerChannel> getChannel(String channelId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", channelId);
        // Set up the body of the request
        Request request = new Request(Routing.Channels.GET_CHANNEL, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ServerChannel> updateChannel(String channelId, ServerChannel channel) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", channelId);
        // Set up the body of the request
        body.set("name", channel.getName());
        body.set("topic", channel.getTopic());
        body.set("isPublic", channel.isPublic());
        Request request = new Request(Routing.Channels.UPDATE_CHANNEL, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ServerChannel> updateChannel(String channelId, String name, String topic, boolean isPublic) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", channelId);
        // Set up the body of the request
        body.set("name", name);
        body.set("topic", topic);
        body.set("isPublic", isPublic);
        Request request = new Request(Routing.Channels.UPDATE_CHANNEL, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ServerChannel> deleteChannel(String channelId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", channelId);
        // Set up the body of the request
        Request request = new Request(Routing.Channels.DELETE_CHANNEL, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ChatMessage> createChannelMessage(String channelId, ChatMessage message) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        body.set("isPrivate", message.isPrivate());
        body.set("isSilent", message.isSilent());
        body.set("replyMessageIds", message.getReplyMessageIds());
        body.set("content", message.getContent());
        body.set("embeds", message.getEmbeds());
        Request request = new Request(Routing.Messages.CREATE_MESSAGE, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ChatMessage> createChannelMessage(String channelId, boolean isPrivate, boolean isSilent, String[] replyMessageIds, String content, ChatEmbed[] embeds) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        body.set("isPrivate", isPrivate);
        body.set("isSilent", isSilent);
        body.set("replyMessageIds", replyMessageIds);
        body.set("content", content);
        body.set("embeds", embeds);
        Request request = new Request(Routing.Messages.CREATE_MESSAGE, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ChatMessage[]> getMessages(String channelId, Instant before, Instant after, int limit, boolean includePrivate) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        body.set("before", before.toString()); // TODO Needs to be ISO 8601 Timestamp
        body.set("after", after.toString()); // TODO Needs to be ISO 8601 Timestamp
        body.set("limit", limit);
        body.set("includePrivate", includePrivate);
        Request request = new Request(Routing.Messages.GET_MESSAGES, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ChatMessage> getMessage(String channelId, String messageId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{messageId}", String.valueOf(messageId));
        // Set up the body of the request
        Request request = new Request(Routing.Messages.GET_MESSAGE, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ChatMessage> updateMessage(String channelId, String messageId, ChatMessage message) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{messageId}", String.valueOf(messageId));
        // Set up the body of the request
        body.set("content", message.getContent());
        body.set("embeds", message.getEmbeds());
        Request request = new Request(Routing.Messages.UPDATE_MESSAGE, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ChatMessage> updateMessage(String channelId, String messageId, String content, ChatEmbed[] embeds) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{messageId}", String.valueOf(messageId));
        // Set up the body of the request
        body.set("content", content);
        body.set("embeds", embeds);
        Request request = new Request(Routing.Messages.UPDATE_MESSAGE, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ChatMessage> deleteMessage(String channelId, String messageId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{messageId}", String.valueOf(messageId));
        // Set up the body of the request
        Request request = new Request(Routing.Messages.DELETE_MESSAGE, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<String> updateNickname(String serverId, String userId, ServerMember serverMember) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        routeReplacements.put("{userId}", String.valueOf(userId));
        // Set up the body of the request
        body.set("nickname", serverMember.getNickname());
        Request request = new Request(Routing.Members.UPDATE_NICKNAME, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<String> updateNickname(String serverId, String userId, String nickname) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        routeReplacements.put("{userId}", String.valueOf(userId));
        // Set up the body of the request
        body.set("nickname", nickname);
        Request request = new Request(Routing.Members.UPDATE_NICKNAME, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Boolean> deleteNickname(String serverId, String userId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        routeReplacements.put("{userId}", String.valueOf(userId));
        // Set up the body of the request
        Request request = new Request(Routing.Members.DELETE_NICKNAME, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ServerMember> getMember(String serverId, String userId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        routeReplacements.put("{userId}", String.valueOf(userId));
        // Set up the body of the request
        Request request = new Request(Routing.Members.GET_MEMBER, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Boolean> kickMember(String serverId, String userId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        routeReplacements.put("{userId}", String.valueOf(userId));
        // Set up the body of the request
        Request request = new Request(Routing.Members.KICK_MEMBER, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ServerMember[]> getMembers(String serverId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        // Set up the body of the request
        Request request = new Request(Routing.Members.GET_MEMBERS, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ServerMemberBan> createServerBan(String serverId, String userId, ServerMemberBan serverMemberBan) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        routeReplacements.put("{userId}", String.valueOf(userId));
        // Set up the body of the request
        body.set("reason", serverMemberBan.getReason());
        Request request = new Request(Routing.MemberBans.BAN_MEMBER, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ServerMemberBan> createServerBan(String serverId, String userId, String reason) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        routeReplacements.put("{userId}", String.valueOf(userId));
        // Set up the body of the request
        body.set("reason", reason);
        Request request = new Request(Routing.MemberBans.BAN_MEMBER, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ServerMemberBan> getServerBan(String serverId, String userId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        routeReplacements.put("{userId}", String.valueOf(userId));
        // Set up the body of the request
        Request request = new Request(Routing.MemberBans.GET_MEMBER_BAN, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Boolean> deleteServerBan(String serverId, String userId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        routeReplacements.put("{userId}", String.valueOf(userId));
        // Set up the body of the request
        Request request = new Request(Routing.MemberBans.DELETE_MEMBER_BAN, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ServerMemberBan[]> getServerBans(String serverId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", String.valueOf(serverId));
        // Set up the body of the request
        Request request = new Request(Routing.MemberBans.GET_BANS, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ForumTopic> createForumTopic(String channelId, ForumTopic forumTopic) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        body.set("title", forumTopic.getTitle());
        body.set("content", forumTopic.getContent());
        Request request = new Request(Routing.Forums.CREATE_TOPIC, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ForumTopic> createForumTopic(String channelId, String title, String content) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        body.set("title", title);
        body.set("content", content);
        Request request = new Request(Routing.Forums.CREATE_TOPIC, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ListItem> createListItem(String channelId, ListItem listItem) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        body.set("message", listItem.getMessage());
        body.set("note", listItem.getNote());
        Request request = new Request(Routing.ListItems.CREATE_LIST_ITEM, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ListItem> createListItem(String channelId, String message, ListItemNote note) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        body.set("message", message);
        body.set("note", note);
        Request request = new Request(Routing.ListItems.CREATE_LIST_ITEM, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ListItem[]> getListItems(String channelId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        Request request = new Request(Routing.ListItems.GET_LIST_ITEMS, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ListItem> getListItem(String channelId, String listItemId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{listItemId}", String.valueOf(listItemId));
        // Set up the body of the request
        Request request = new Request(Routing.ListItems.GET_LIST_ITEM, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ListItem> updateListItem(String channelId, String listItemId, ListItem listItem) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{listItemId}", String.valueOf(listItemId));
        // Set up the body of the request
        body.set("message", listItem.getMessage());
        body.set("note", listItem.getNote());
        Request request = new Request(Routing.ListItems.UPDATE_LIST_ITEM, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<ListItem> updateListItem(String channelId, String listItemId, String message, ListItemNote note) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{listItemId}", String.valueOf(listItemId));
        // Set up the body of the request
        body.set("message", message);
        body.set("note", note);
        Request request = new Request(Routing.ListItems.UPDATE_LIST_ITEM, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Boolean> deleteListitem(String channelId, String listItemId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{listItemId}", String.valueOf(listItemId));
        // Set up the body of the request
        Request request = new Request(Routing.ListItems.DELETE_LIST_ITEM, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Boolean> completeListItem(String channelId, String listItemId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{listItemId}", String.valueOf(listItemId));
        // Set up the body of the request
        Request request = new Request(Routing.ListItems.COMPLETE_LIST_ITEM, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Boolean> uncompleteListItem(String channelId, String listItemId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{listItemId}", String.valueOf(listItemId));
        // Set up the body of the request
        Request request = new Request(Routing.ListItems.UNCOMPLETE_LIST_ITEM, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Doc> createDoc(String channelId, Doc doc) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        body.set("title", doc.getTitle());
        body.set("content", doc.getContent());
        Request request = new Request(Routing.Docs.CREATE_DOC, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Doc> createDoc(String channelId, String title, String content) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        body.set("title", title);
        body.set("content", content);
        Request request = new Request(Routing.Docs.CREATE_DOC, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Doc[]> getDocs(String channelId, Instant before, int limit) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        // Set up the body of the request
        body.set("before", before.toString());
        body.set("limit", limit);
        Request request = new Request(Routing.Docs.GET_DOCS, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Doc> getDoc(String channelId, int docId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{docId}", String.valueOf(docId));
        // Set up the body of the request
        Request request = new Request(Routing.Docs.GET_DOC, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Doc> updateDoc(String channelId, int docId, Doc doc) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{docId}", String.valueOf(docId));
        // Set up the body of the request
        body.set("title", doc.getTitle());
        body.set("content", doc.getContent());
        Request request = new Request(Routing.Docs.UPDATE_DOC, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Doc> updateDoc(String channelId, int docId, String title, String content) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{docId}", String.valueOf(docId));
        // Set up the body of the request
        body.set("title", title);
        body.set("content", content);
        Request request = new Request(Routing.Docs.UPDATE_DOC, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Boolean> deleteDoc(String channelId, int docId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{docId}", String.valueOf(docId));
        // Set up the body of the request
        Request request = new Request(Routing.Docs.DELETE_DOC, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Boolean> addReactionEmote(String channelId, String contentId, String emoteId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{channelId}", String.valueOf(channelId));
        routeReplacements.put("{contentId}", contentId);
        routeReplacements.put("{emoteId}", emoteId);
        // Set up the body of the request
        Request request = new Request(Routing.Reactions.ADD_REACTION_EMOTE, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Integer> awardMemberXP(String serverId, String userId, int amount) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{userId}", userId);
        // Set up the body of the request
        body.set("amount", amount);
        Request request = new Request(Routing.Server_XP.AWARD_XP_TO_MEMBER, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Boolean> awardRoleXP(String serverId, String roleId, int amount) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{roleId}", roleId);
        // Set up the body of the request
        body.set("amount", amount);
        Request request = new Request(Routing.Server_XP.AWARD_XP_TO_ROLE, routeReplacements, getHeaders(), body);
        return new RestAction(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<JSONObject> getSocialLinks(String serverId, String userId, String type) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{userId}", userId);
        routeReplacements.put("{type}", type);
        // Set up the body of the request
        Request request = new Request(Routing.Social_Links.GET_MEMBER_SOCIAL_LINKS, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Boolean> addMemberToGroup(String groupId, String userId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{groupId}", groupId);
        routeReplacements.put("{userId}", userId);
        // Set up the body of the request
        Request request = new Request(Routing.Group_Memberships.ADD_MEMBER_TO_GROUP, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Boolean> removeMemberFromGroup(String groupId, String userId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{groupId}", groupId);
        routeReplacements.put("{userId}", userId);
        // Set up the body of the request
        Request request = new Request(Routing.Group_Memberships.REMOVE_MEMBER_FROM_GROUP, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Boolean> addRoleToMember(String serverId, String userId, int roleId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{userId}", userId);
        routeReplacements.put("{roleId}", String.valueOf(roleId));
        // Set up the body of the request
        Request request = new Request(Routing.Role_Memberships.ADD_ROLE_TO_MEMBER, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Boolean> removeRoleFromMember(String serverId, String userId, int roleId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{userId}", userId);
        routeReplacements.put("{roleId}", String.valueOf(roleId));
        // Set up the body of the request
        Request request = new Request(Routing.Role_Memberships.REMOVE_ROLE_FROM_MEMBER, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Integer[]> getMemberRoles(String serverId, String userId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{userId}", userId);
        // Set up the body of the request
        Request request = new Request(Routing.Role_Memberships.GET_MEMBER_ROLES, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Webhook> createWebhook(String serverId, Webhook webhook) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        // Set up the body of the request
        body.set("name", webhook.getName());
        body.set("channelId", webhook.getChannelId());
        Request request = new Request(Routing.Webhooks.CREATE_WEBHOOK, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Webhook> createWebhook(String serverId, String name, String channelId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        // Set up the body of the request
        body.set("name", name);
        body.set("channelId", channelId);
        Request request = new Request(Routing.Webhooks.CREATE_WEBHOOK, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Webhook[]> getWebhooks(String serverId, String channelId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        // Set up the body of the request
        body.set("channelId", channelId);
        Request request = new Request(Routing.Webhooks.GET_WEBHOOKS, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Webhook> getWebhook(String serverId, String webhookId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{webhookId}", webhookId);
        // Set up the body of the request
        Request request = new Request(Routing.Webhooks.GET_WEBHOOK, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Webhook> updateWebhook(String serverId, String webhookId, Webhook webhook) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{webhookId}", webhookId);
        // Set up the body of the request
        body.set("name", webhook.getName());
        body.set("channelId", webhook.getChannelId());
        Request request = new Request(Routing.Webhooks.UPDATE_WEBHOOK, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Webhook> updateWebhook(String serverId, String webhookId, String name, String channelId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{webhookId}", webhookId);
        // Set up the body of the request
        body.set("name", name);
        body.set("channelId", channelId);
        Request request = new Request(Routing.Webhooks.UPDATE_WEBHOOK, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<Boolean> deleteWebhook(String serverId, String webhookId) {
        JSONObject body = new JSONObject();
        HashMap<String, String> routeReplacements = new HashMap<>();
        routeReplacements.put("{serverId}", serverId);
        routeReplacements.put("{webhookId}", webhookId);
        // Set up the body of the request
        Request request = new Request(Routing.Webhooks.DELETE_WEBHOOK, routeReplacements, getHeaders(), body);
        return new RestAction<>(this.jg_api.getNextSeqNumber(), request, this.jg_api);
    }
    public RestAction<CalendarEvent> createCalendarEvent(CalendarEvent calendarEvent) {
        return null;
    }
    public RestAction<CalendarEvent[]> getCalendarEvents(String channelId) {
        return null;
    }
    public RestAction<CalendarEvent> getCalendarEvent(String channelId, String calendarEventId) {
        return null;
    }
    public RestAction<CalendarEvent> updateCalendarEvent(String channelId, String calendarEventId, CalendarEvent calendarEvent) {
        return null;
    }
    public RestAction<Boolean> deleteCalendarEvent(String channelId, String calendarEventId) {
        return null;
    }
}
