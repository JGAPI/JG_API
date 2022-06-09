package dev.JGAPI.JG_API.Rest;

import dev.JGAPI.JG_API.Entities.Chat.ChatMessage;
import dev.JGAPI.JG_API.Entities.Docs.Doc;
import dev.JGAPI.JG_API.Entities.Forums.ForumThread;
import dev.JGAPI.JG_API.Entities.ListItems.ListItem;
import dev.JGAPI.JG_API.Entities.MemberBans.ServerMemberBan;
import dev.JGAPI.JG_API.Entities.Webhooks.Webhook;

public class RestClient {
    public RestAction createChannelMessage(long channelId, ChatMessage message) {
        return null;
    }
    public RestAction getMessages(long channelId) {
        return null;
    }
    public RestAction getMessage(long channelId, long messageId) {
        return null;
    }
    public RestAction updateMessage(long channelId, long messageId, ChatMessage message) {
        return null;
    }
    public RestAction deleteMessage(long channelId, long messageId) {
        return null;
    }
    public RestAction updateNickname(long serverId, long userId, String nickname) {
        return null;
    }
    public RestAction deleteNickname(long serverId, long userId) {
        return null;
    }
    public RestAction getMember(long serverId, long userId) {
        return null;
    }
    public RestAction kickMember(long serverId, long userId) {
        return null;
    }
    public RestAction getMembers(long serverId) {
        return null;
    }
    public RestAction createServerBan(long serverId, long userId, ServerMemberBan serverMemberBan) {
        return null;
    }
    public RestAction getServerBan(long serverId, long userId) {
        return null;
    }
    public RestAction deleteServerBan(long serverId, long userId) {
        return null;
    }
    public RestAction getServerBans(long serverId) {
        return null;
    }
    public RestAction createForumThread(long channelId, ForumThread forumThread) {
        return null;
    }
    public RestAction createListItem(long channelId, ListItem listItem) {
        return null;
    }
    public RestAction getListItems(long channelId) {
        return null;
    }
    public RestAction getListItem(long channelId, long listItemId) {
        return null;
    }
    public RestAction updateListItem(long channelId, long listItemId, ListItem listItem) {
        return null;
    }
    public RestAction deleteListitem(long channelId, long listItemId) {
        return null;
    }
    public RestAction completeListItem(long channelId, long listItemId) {
        return null;
    }
    public RestAction uncompleteListItem(long channelId, long listItemId) {
        return null;
    }
    public RestAction createDoc(long channelId, Doc doc) {
        return null;
    }
    public RestAction getDocs(long channelId) {
        return null;
    }
    public RestAction getDoc(long channelId, long docId) {
        return null;
    }
    public RestAction updateDoc(long channelId, long docId) {
        return null;
    }
    public RestAction deleteDoc(long channelId, long docId) {
        return null;
    }
    public RestAction addReactionEmote(long channelId, long contentId, long emoteId) {
        return null;
    }
    public RestAction awardMemberXP(long serverId, long userId) {
        return null;
    }
    public RestAction awardRoleXP(long serverId, long roleId) {
        return null;
    }
    public RestAction getSocialLinks(long serverId, long userId, String type) {
        return null;
    }
    public RestAction addMemberToGroup(long groupId, long userId) {
        return null;
    }
    public RestAction removeMemberFromGroup(long groupId, long userId) {
        return null;
    }
    public RestAction addRoleToMember(long serverId, long userId, long roleId) {
        return null;
    }
    public RestAction removeRoleFromMember(long serverId, long userId, long roleId) {
        return null;
    }
    public RestAction getMemberRoles(long serverId, long userId) {
        return null;
    }
    public RestAction createWebhook(long serverId, Webhook webhook) {
        return null;
    }
    public RestAction getWebhooks(long serverId) {
        return null;
    }
    public RestAction getWebhook(long serverId, long webhookId) {
        return null;
    }
    public RestAction updateWebhook(long serverId, long webhookId, Webhook webhook) {
        return null;
    }
    public RestAction deleteWebhook(long serverId, long webhookId) {
        return null;
    }
}
