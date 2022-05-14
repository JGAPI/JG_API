package dev.moratto.JGAPI.Rest;

import dev.moratto.JGAPI.Entities.Chat.ChatMessage;
import dev.moratto.JGAPI.Entities.Docs.Doc;
import dev.moratto.JGAPI.Entities.Forums.ForumThread;
import dev.moratto.JGAPI.Entities.ListItems.ListItem;
import dev.moratto.JGAPI.Entities.ListItems.ListItemSummary;
import dev.moratto.JGAPI.Entities.MemberBans.ServerMemberBan;
import dev.moratto.JGAPI.Entities.Members.ServerMember;
import dev.moratto.JGAPI.Entities.Members.ServerMemberSummary;

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
}
