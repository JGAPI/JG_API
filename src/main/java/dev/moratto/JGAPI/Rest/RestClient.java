package dev.moratto.JGAPI.Rest;

import cn.hutool.json.JSONObject;
import dev.moratto.JGAPI.Entities.Chat.ChatMessage;
import dev.moratto.JGAPI.Entities.Docs.Doc;
import dev.moratto.JGAPI.Entities.Forums.ForumThread;
import dev.moratto.JGAPI.Entities.ListItems.ListItem;
import dev.moratto.JGAPI.Entities.ListItems.ListItemSummary;
import dev.moratto.JGAPI.Entities.MemberBans.ServerMemberBan;
import dev.moratto.JGAPI.Entities.Members.ServerMember;
import dev.moratto.JGAPI.Entities.Members.ServerMemberSummary;
import dev.moratto.JGAPI.Entities.Webhooks.Webhook;

public class RestClient {
    public ChatMessage createChannelMessage(long channelId, ChatMessage message) {}
    public ChatMessage[] getMessages(long channelId) {}
    public ChatMessage getMessage(long channelId, long messageId) {}
    public ChatMessage updateMessage(long channelId, long messageId, ChatMessage message) {}
    public boolean deleteMessage(long channelId, long messageId) {}
    public String updateNickname(long serverId, long userId, String nickname) {}
    public boolean deleteNickname(long serverId, long userId) {}
    public ServerMember getMember(long serverId, long userId) {}
    public boolean kickMember(long serverId, long userId) {}
    public ServerMemberSummary[] getMembers(long serverId) {}
    public ServerMemberBan createServerBan(long serverId, long userId, ServerMemberBan serverMemberBan) {}
    public ServerMemberBan getServerBan(long serverId, long userId) {}
    public boolean deleteServerBan(long serverId, long userId) {}
    public ServerMemberBan[] getServerBan(long serverId) {}
    public ForumThread createForumThread(long channelId, ForumThread forumThread) {}
    public ListItem createListItem(long channelId, ListItem listItem) {}
    public ListItemSummary[] getListItems(long channelId) {}
    public ListItem getListItem(long channelId, long listItemId) {}
    public ListItem updateListItem(long channelId, long listItemId, ListItem listItem) {}
    public boolean deleteListitem(long channelId, long listItemId) {}
    public boolean completeListItem(long channelId, long listItemId) {}
    public boolean uncompleteListItem(long channelId, long listItemId) {}
    public Doc createDoc(long channelId, Doc doc) {}
    public Doc[] getDocs(long channelId) {}
    public Doc getDoc(long channelId, long docId) {}
    public Doc updateDoc(long channelId, long docId) {}
    public boolean deleteDoc(long channelId, long docId) {}
    public boolean addReactionEmote(long channelId, long contentId, long emoteId) {}
    public int awardMemberXP(long serverId, long userId) {}
    public int awardRoleXP(long serverId, long roleId) {}
    public JSONObject getSocialLinks(long serverId, long userId, String type) {}
    public boolean addMemberToGroup(long groupId, long userId) {}
    public boolean removeMemberFromGroup(long groupId, long userId) {}
    public boolean addRoleToMember(long serverId, long userId, long roleId) {}
    public boolean removeRoleFromMember(long serverId, long userId, long roleId) {}
    public int[] getMemberRoles(long serverId, long userId) {}
    public Webhook createWebhook(long serverId, Webhook webhook) {}
    public Webhook[] getWebhooks(long serverId) {}
    public Webhook getWebhook(long serverId, long webhookId) {}
    public Webhook updateWebhook(long serverId, long webhookId, Webhook webhook) {}
    public boolean deleteWebhook(long serverId, long webhookId) {}
}
