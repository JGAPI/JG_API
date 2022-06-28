package dev.jgapi.jg_api.util;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.calendars.CalendarEvent;
import dev.jgapi.jg_api.entities.calendars.CalendarEventCancellation;
import dev.jgapi.jg_api.entities.channels.Mentions;
import dev.jgapi.jg_api.entities.channels.ServerChannel;
import dev.jgapi.jg_api.entities.chat.ChatEmbed;
import dev.jgapi.jg_api.entities.chat.ChatMessage;
import dev.jgapi.jg_api.entities.docs.Doc;
import dev.jgapi.jg_api.entities.forums.ForumTopic;
import dev.jgapi.jg_api.entities.listitems.ListItem;
import dev.jgapi.jg_api.entities.listitems.ListItemNote;
import dev.jgapi.jg_api.entities.memberbans.ServerMemberBan;
import dev.jgapi.jg_api.entities.members.*;
import dev.jgapi.jg_api.entities.server.ServerModel;
import dev.jgapi.jg_api.entities.webhooks.Webhook;
import dev.jgapi.jg_api.rest.Routing;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class RestUtils {
    public static <T> T processAction(JG_API jg_api, String jsonResponse, Routing.ReturnType returnType) {
        JSONObject responseObj = new JSONObject(jsonResponse);

        switch (returnType) {
            case NONE -> {
                return (T) Boolean.TRUE;
            }
            case ServerModel -> {
                JSONObject serverObj = responseObj.getJSONObject("server");

                ServerModel serverModel = new ServerModel(
                        jg_api,
                        serverObj.getString("id"),
                        serverObj.getString("ownerId"),
                        serverObj.optString("type", null),
                        serverObj.getString("name"),
                        serverObj.optString("url", null),
                        serverObj.optString("about", null),
                        serverObj.optString("avatar", null),
                        serverObj.optString("banner", null),
                        serverObj.optString("timezone", null),
                        serverObj.optBoolean("isVerified", false),
                        serverObj.optString("defaultChannelId", null),
                        Instant.parse(serverObj.getString("createdAt"))
                );

                return (T) serverModel;
            }
            case ServerChannel -> {
                JSONObject channel = responseObj.getJSONObject("channel");

                ServerChannel serverChannel = new ServerChannel(
                        jg_api,
                        channel.getString("id"),
                        channel.getString("type"),
                        channel.getString("name"),
                        channel.optString("topic", null),
                        Instant.parse(channel.getString("createdAt")),
                        channel.getString("createdBy"),
                        InstantHelper.parseStringOrNull(channel.optString("updatedAt", null)),
                        channel.getString("serverId"),
                        channel.optString("parentId", null),
                        channel.optInt("categoryId", -1),
                        channel.getString("groupId"),
                        channel.optBoolean("isPublic", false),
                        channel.optString("archivedBy", null),
                        InstantHelper.parseStringOrNull(channel.optString("archivedAt", null))
                );

                return (T) serverChannel;
            }
            case ChatMessage -> {
                return (T) parseChatMessageObj(responseObj.getJSONObject("message"), jg_api);
            }
            case ChatMessage_Arr -> {
                List<ChatMessage> chatMessages = new ArrayList<>();
                JSONArray chatMessagesArr = responseObj.getJSONArray("messages");

                for (int i = 0; i < chatMessagesArr.length(); i ++) {
                    JSONObject chatMessageObj = chatMessagesArr.getJSONObject(i);
                    chatMessages.add(parseChatMessageObj(chatMessageObj, jg_api));
                }

                return (T) chatMessages.toArray(ChatMessage[]::new);
            }
            case Nickname -> {
                return (T) responseObj.getString("nickname");
            }
            case ServerMember -> {
                JSONObject memberObj = responseObj.getJSONObject("member");
                JSONObject userObj = memberObj.getJSONObject("user");

                ServerMember serverMember = new ServerMember(
                        jg_api,
                        new User(
                                jg_api,
                                userObj.getString("id"),
                                userObj.optString("type", "user"),
                                userObj.getString("name"),
                                userObj.optString("avatar", null),
                                userObj.optString("banner", null),
                                Instant.parse(userObj.getString("createdAt"))
                        ),
                        toPrimitive(memberObj.getJSONArray("roleIds").toList().toArray(Integer[]::new)),
                        memberObj.optString("nickname", null),
                        Instant.parse(memberObj.getString("joinedAt")),
                        memberObj.optBoolean("isOwner", false)
                );

                return (T) serverMember;
            }
            case ServerMemberSummary_Arr -> {
                List<ServerMemberSummary> serverMemberSummaries = new ArrayList<>();
                JSONArray serverMembersArr = responseObj.getJSONArray("members");

                for (int i = 0; i < serverMembersArr.length(); i ++) {
                    JSONObject memberObj = serverMembersArr.getJSONObject(i);
                    JSONObject userObj = memberObj.getJSONObject("user");

                    serverMemberSummaries.add(new ServerMemberSummary(
                            new UserSummary(
                                    userObj.getString("id"),
                                    userObj.optString("type", "user"),
                                    userObj.getString("name"),
                                    userObj.optString("avatar", null)
                            ),
                            toPrimitive(memberObj.getJSONArray("roleIds").toList().toArray(new Integer[0]))
                    ));
                }

                return (T) serverMemberSummaries.toArray(ServerMemberSummary[]::new);
            }
            case ServerMemberBan -> {
                return (T) parseServerMemberBanObj(responseObj.getJSONObject("serverMemberBan"), jg_api);
            }
            case ServerMemberBan_Arr -> {
                List<ServerMemberBan> serverMemberBans = new ArrayList<>();
                JSONArray serverMemberBansArr = responseObj.getJSONArray("serverMemberBans");

                for (int i = 0; i < serverMemberBansArr.length(); i ++) {
                    JSONObject serverMemberBanObj = serverMemberBansArr.getJSONObject(i);
                    serverMemberBans.add(parseServerMemberBanObj(serverMemberBanObj, jg_api));
                }

                return (T) serverMemberBans.toArray(ChatMessage[]::new);
            }
            case ForumTopic -> {
                JSONObject forumTopicObj = responseObj.getJSONObject("forumTopic");

                ForumTopic forumTopic = new ForumTopic(
                        jg_api,
                        forumTopicObj.getString("id"),
                        forumTopicObj.getString("serverId"),
                        forumTopicObj.getString("channelId"),
                        forumTopicObj.optString("title", null),
                        forumTopicObj.optString("content", null),
                        Instant.parse(forumTopicObj.getString("createdAt")),
                        forumTopicObj.getString("createdBy"),
                        forumTopicObj.optString("createdByWebhookId", null),
                        InstantHelper.parseStringOrNull(forumTopicObj.optString("updatedAt", null))
                );

                return (T) forumTopic;
            }
            case ListItem -> {
                return (T) parseListItemObj(responseObj.getJSONObject("listItem"), jg_api);
            }
            case ListItem_Arr -> {
                List<ListItem> listItems = new ArrayList<>();
                JSONArray listItemsArr = responseObj.getJSONArray("listItems");

                for (int i = 0; i < listItemsArr.length(); i++) {
                    JSONObject listItemObj = listItemsArr.getJSONObject(i);
                    listItems.add(parseListItemObj(listItemObj, jg_api));
                }

                return (T) listItems.toArray(ListItem[]::new);
            }
            case Doc -> {
                return (T) parseDocObj(responseObj.getJSONObject("doc"), jg_api);
            }
            case Doc_Arr -> {
                List<Doc> docs = new ArrayList<>();
                JSONArray docsArr = responseObj.getJSONArray("docs");

                for (int i = 0; i < docsArr.length(); i++) {
                    JSONObject docObj = docsArr.getJSONObject(i);
                    docs.add(parseDocObj(docObj, jg_api));
                }

                return (T) docs.toArray(Doc[]::new);
            }
            case XP_Member_Total -> {
                return (T) Integer.valueOf(responseObj.getInt("total"));
            }
            case Social_Links_Obj -> {
                JSONObject socialLinkObj = responseObj.getJSONObject("socialLink");

                SocialLink socialLink = new SocialLink(
                        jg_api,
                        socialLinkObj.optString("handle", null),
                        socialLinkObj.optString("serviceId", null),
                        socialLinkObj.getString("type")
                );

                return (T) socialLink;
            }
            case MemberRoles -> {
                return (T) responseObj.getJSONArray("roleIds").toList().toArray(Integer[]::new);
            }
            case Webhook -> {
                return (T) parseWebhookObj(responseObj.getJSONObject("webhook"), jg_api);
            }
            case Webhook_Arr -> {
                List<Webhook> webhooks = new ArrayList<>();
                JSONArray webhooksArr = responseObj.getJSONArray("webhooks");

                for (int i = 0; i < webhooksArr.length(); i++) {
                    JSONObject webhookObj = webhooksArr.getJSONObject(i);
                    webhooks.add(parseWebhookObj(webhookObj, jg_api));
                }

                return (T) webhooks.toArray(Webhook[]::new);
            }
            case CalendarEvent -> {
                return (T) parseCalendarEventObj(responseObj.getJSONObject("calendarEvent"), jg_api);
            }
            case CalendarEvent_Arr -> {
                List<CalendarEvent> calendarEvents = new ArrayList<>();
                JSONArray calendarEventsArr = responseObj.getJSONArray("calendarEvents");

                for (int i = 0; i < calendarEventsArr.length(); i++) {
                    JSONObject calendarEventObj = calendarEventsArr.getJSONObject(i);
                    calendarEvents.add(parseCalendarEventObj(calendarEventObj, jg_api));
                }

                return (T) calendarEvents.toArray(CalendarEvent[]::new);
            }
        }
        return null;
    }

    // Util Methods
    private static int[] toPrimitive(Integer[] IntegerArray) {
        int[] result = new int[IntegerArray.length];

        for (int i = 0; i < IntegerArray.length; i++) {
            result[i] = IntegerArray[i];
        }

        return result;
    }

    private static ChatMessage parseChatMessageObj(JSONObject chatMessageObj, JG_API jg_api) {
        JSONArray embedsJSON = chatMessageObj.optJSONArray("embeds");
        String[] replyMessageIds = null; // TODO Set up
        ChatEmbed[] embeds = null; // TODO Set up
        Mentions mentions = null; // TODO Set up

        return new ChatMessage(
                jg_api,
                chatMessageObj.getString("id"),
                chatMessageObj.getString("type"),
                chatMessageObj.optString("serverId", null),
                new ServerChannel(
                        jg_api,
                        chatMessageObj.getString("channelId"),
                        null, null, null, null, null, null,
                        chatMessageObj.optString("serverId", null),
                        null, -1, null, false, null, null
                ),
                chatMessageObj.getString("content"),
                null, null,
                chatMessageObj.optBoolean("isPrivate", false),
                chatMessageObj.optBoolean("isSilent", false),
                null,
                Instant.parse(chatMessageObj.getString("createdAt")),
                chatMessageObj.getString("createdBy"),
                chatMessageObj.optString("createdByWebhookId", null),
                InstantHelper.parseStringOrNull(chatMessageObj.optString("updatedAt", null)),
                InstantHelper.parseStringOrNull(chatMessageObj.optString("deletedAt", null))
        );
    }

    private static ServerMemberBan parseServerMemberBanObj(JSONObject serverMemberBanObj, JG_API jg_api) {
        JSONObject userObj = serverMemberBanObj.getJSONObject("user");

        return new ServerMemberBan(
                jg_api,
                new UserSummary(
                        userObj.getString("id"),
                        userObj.optString("type", "user"),
                        userObj.getString("name"),
                        userObj.optString("avatar", null)
                ),
                serverMemberBanObj.optString("reason", null),
                serverMemberBanObj.getString("createdBy"),
                InstantHelper.parseStringOrNull(serverMemberBanObj.optString("createdAt", null))
        );
    }

    private static ListItem parseListItemObj(JSONObject listItemObj, JG_API jg_api) {
        JSONObject noteObj = listItemObj.optJSONObject("note", null);

        return new ListItem(
                jg_api,
                listItemObj.getString("id"),
                listItemObj.getString("serverId"),
                listItemObj.getString("channelId"),
                listItemObj.getString("message"),
                null,
                Instant.parse(listItemObj.getString("createdAy")),
                listItemObj.getString("createdBy"),
                listItemObj.optString("createdByWebhookId", null),
                InstantHelper.parseStringOrNull(listItemObj.optString("updatedAt", null)),
                listItemObj.optString("updatedBy", null),
                listItemObj.optString("parentListItemId", null),
                InstantHelper.parseStringOrNull(listItemObj.optString("completedAt", null)),
                listItemObj.optString("completedBy", null),
                noteObj == null ? null : new ListItemNote(
                        Instant.parse(noteObj.getString("createdAt")),
                        noteObj.getString("createdBy"),
                        InstantHelper.parseStringOrNull(noteObj.optString("updatedAt", null)),
                        noteObj.optString("updatedBy", null),
                        null,
                        noteObj.getString("content")
                )
        );
    }

    private static Doc parseDocObj(JSONObject docObj, JG_API jg_api) {
        // TODO: Setup mentions.
        return new Doc(
                jg_api,
                docObj.getInt("id"),
                docObj.getString("serverId"),
                docObj.getString("channelId"),
                docObj.getString("title"),
                docObj.getString("content"),
                null,
                Instant.parse(docObj.getString("createdAt")),
                docObj.getString("createdBy"),
                InstantHelper.parseStringOrNull(docObj.optString("updatedAt", null)),
                docObj.optString("updatedBy", null)
       );
    }

    private static Webhook parseWebhookObj(JSONObject webhookObj, JG_API jg_api) {
        return new Webhook(
                jg_api,
                webhookObj.getString("id"),
                webhookObj.getString("name"),
                webhookObj.getString("serverId"),
                webhookObj.getString("channelId"),
                Instant.parse(webhookObj.getString("createdAt")),
                webhookObj.getString("createdBy"),
                InstantHelper.parseStringOrNull(webhookObj.optString("deletedAt", null)),
                webhookObj.optString("token", null)
        );
    }

    private static CalendarEvent parseCalendarEventObj(JSONObject calendarEventObj, JG_API jg_api) {
        JSONObject cancellationObj = calendarEventObj.optJSONObject("cancellation", null);

        return new CalendarEvent(
                jg_api,
                calendarEventObj.getInt("id"),
                calendarEventObj.getString("serverId"),
                calendarEventObj.getString("channelId"),
                calendarEventObj.getString("name"),
                calendarEventObj.optString("description", null),
                calendarEventObj.optString("location", null),
                calendarEventObj.optString("url", null),
                calendarEventObj.optInt("color", 0),
                Instant.parse(calendarEventObj.getString("startsAt")),
                calendarEventObj.optInt("duration", 1),
                calendarEventObj.optBoolean("isPrivate", false),
                null,
                Instant.parse(calendarEventObj.getString("createdAt")),
                cancellationObj == null ? null : new CalendarEventCancellation(
                        cancellationObj.optString("description", null),
                        cancellationObj.optString("createdBy", null)
                )
        );
    }
}
