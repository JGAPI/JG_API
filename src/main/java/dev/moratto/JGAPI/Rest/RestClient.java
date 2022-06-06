package dev.moratto.JGAPI.Rest;

import dev.moratto.JGAPI.Entities.Chat.ChatMessage;
import dev.moratto.JGAPI.Entities.Docs.Doc;
import dev.moratto.JGAPI.Entities.Forums.ForumThread;
import dev.moratto.JGAPI.Entities.ListItems.ListItem;
import dev.moratto.JGAPI.Entities.MemberBans.ServerMemberBan;
import dev.moratto.JGAPI.Entities.Webhooks.Webhook;

public class RestClient {
    public RestAction createChannelMessage(long channelId, ChatMessage message) {}
    public RestAction getMessages(long channelId) {}
    public RestAction getMessage(long channelId, long messageId) {}
    public RestAction updateMessage(long channelId, long messageId, ChatMessage message) {}
    public RestAction deleteMessage(long channelId, long messageId) {}
    public RestAction updateNickname(long serverId, long userId, String nickname) {}
    public RestAction deleteNickname(long serverId, long userId) {}
    public RestAction getMember(long serverId, long userId) {}
    public RestAction kickMember(long serverId, long userId) {}
    public RestAction getMembers(long serverId) {}
    public RestAction createServerBan(long serverId, long userId, ServerMemberBan serverMemberBan) {}
    public RestAction getServerBan(long serverId, long userId) {}
    public RestAction deleteServerBan(long serverId, long userId) {}
    public RestAction getServerBans(long serverId) {}
    public RestAction createForumThread(long channelId, ForumThread forumThread) {}
    public RestAction createListItem(long channelId, ListItem listItem) {}
    public RestAction getListItems(long channelId) {}
    public RestAction getListItem(long channelId, long listItemId) {}
    public RestAction updateListItem(long channelId, long listItemId, ListItem listItem) {}
    public RestAction deleteListitem(long channelId, long listItemId) {}
    public RestAction completeListItem(long channelId, long listItemId) {}
    public RestAction uncompleteListItem(long channelId, long listItemId) {}
    public RestAction createDoc(long channelId, Doc doc) {}
    public RestAction getDocs(long channelId) {}
    public RestAction getDoc(long channelId, long docId) {}
    public RestAction updateDoc(long channelId, long docId) {}
    public RestAction deleteDoc(long channelId, long docId) {}
    public RestAction addReactionEmote(long channelId, long contentId, long emoteId) {}
    public RestAction awardMemberXP(long serverId, long userId) {}
    public RestAction awardRoleXP(long serverId, long roleId) {}
    public RestAction getSocialLinks(long serverId, long userId, String type) {}
    public RestAction addMemberToGroup(long groupId, long userId) {}
    public RestAction removeMemberFromGroup(long groupId, long userId) {}
    public RestAction addRoleToMember(long serverId, long userId, long roleId) {}
    public RestAction removeRoleFromMember(long serverId, long userId, long roleId) {}
    public RestAction getMemberRoles(long serverId, long userId) {}
    public RestAction createWebhook(long serverId, Webhook webhook) {}
    public RestAction getWebhooks(long serverId) {}
    public RestAction getWebhook(long serverId, long webhookId) {}
    public RestAction updateWebhook(long serverId, long webhookId, Webhook webhook) {}
    public RestAction deleteWebhook(long serverId, long webhookId) {}
}
