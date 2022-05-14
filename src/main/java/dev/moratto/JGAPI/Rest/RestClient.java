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
    private ChatMessage createChannelMessage(long channelId, ChatMessage message) {}
    private ChatMessage[] getMessages(long channelId) {}
    private ChatMessage getMessage(long channelId, long messageId) {}
    private ChatMessage updateMessage(long channelId, long messageId, ChatMessage message) {}
    public boolean deleteMessage(long channelId, long messageId) {}
    private String updateNickname(long serverId, long userId, String nickname) {}
    private boolean deleteNickname(long serverId, long userId) {}
    private ServerMember getMember(long serverId, long userId) {}
    private boolean kickMember(long serverId, long userId) {}
    private ServerMemberSummary[] getMembers(long serverId) {}
    private ServerMemberBan createServerBan(long serverId, long userId, ServerMemberBan serverMemberBan) {}
    private ServerMemberBan getServerBan(long serverId, long userId) {}
    private boolean deleteServerBan(long serverId, long userId) {}
    private ServerMemberBan[] getServerBan(long serverId) {}
    private ForumThread createForumThread(long channelId, ForumThread forumThread) {}
    private ListItem createListItem(long channelId, ListItem listItem) {}
    private ListItemSummary[] getListItems(long channelId) {}
    private ListItem getListItem(long channelId, long listItemId) {}
    private ListItem updateListItem(long channelId, long listItemId, ListItem listItem) {}
    private boolean deleteListitem(long channelId, long listItemId) {}
    private boolean completeListItem(long channelId, long listItemId) {}
    private boolean uncompleteListItem(long channelId, long listItemId) {}
    private Doc createDoc(long channelId, Doc doc) {}
    private Doc[] getDocs(long channelId) {}
    private Doc getDoc(long channelId, long docId) {}
    private Doc updateDoc(long channelId, long docId) {}
    private boolean deleteDoc(long channelId, long docId) {}
    private boolean addReactionEmote(long channelId, long contentId, long emoteId) {}
    private int awardMemberXP(long serverId, long userId) {}
    private int awardRoleXP(long serverId, long roleId) {}
    private JSONObject getSocialLinks(long serverId, long userId, String type) {}
    private boolean addMemberToGroup(long groupId, long userId) {}
    private boolean removeMemberFromGroup(long groupId, long userId) {}
    private boolean addRoleToMember(long serverId, long userId, long roleId) {}
    private boolean removeRoleFromMember(long serverId, long userId, long roleId) {}
    private int[] getMemberRoles(long serverId, long userId) {}
    private Webhook createWebhook(long serverId, Webhook webhook) {}
    private Webhook[] getWebhooks(long serverId) {}
    private Webhook getWebhook(long serverId, long webhookId) {}
    private Webhook updateWebhook(long serverId, long webhookId, Webhook webhook) {}
    private boolean deleteWebhook(long serverId, long webhookId) {}
}
