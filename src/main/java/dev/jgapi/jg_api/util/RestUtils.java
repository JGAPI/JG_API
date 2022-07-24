package dev.jgapi.jg_api.util;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.calendars.CalendarEvent;
import dev.jgapi.jg_api.entities.calendars.rsvp.CalendarEventRsvp;
import dev.jgapi.jg_api.entities.channels.ServerChannel;
import dev.jgapi.jg_api.entities.chat.ChatMessage;
import dev.jgapi.jg_api.entities.docs.Doc;
import dev.jgapi.jg_api.entities.forums.ForumTopic;
import dev.jgapi.jg_api.entities.listitems.ListItem;
import dev.jgapi.jg_api.entities.memberbans.ServerMemberBan;
import dev.jgapi.jg_api.entities.members.ServerMember;
import dev.jgapi.jg_api.entities.members.ServerMemberSummary;
import dev.jgapi.jg_api.entities.members.SocialLink;
import dev.jgapi.jg_api.entities.server.ServerModel;
import dev.jgapi.jg_api.entities.webhooks.Webhook;
import dev.jgapi.jg_api.rest.Routing;
import org.json.JSONArray;
import org.json.JSONObject;

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
                return (T) ServerModel.parseServerModelObj(responseObj.getJSONObject("server"), jg_api);
            }
            case ServerChannel -> {
                return (T) ServerChannel.parseServerChannelObj(responseObj.getJSONObject("channel"), jg_api);
            }
            case ChatMessage -> {
                return (T) ChatMessage.parseChatMessageObj(responseObj.getJSONObject("message"), jg_api);
            }
            case ChatMessage_Arr -> {
                List<ChatMessage> chatMessages = new ArrayList<>();
                JSONArray chatMessagesArr = responseObj.getJSONArray("messages");

                for (int i = 0; i < chatMessagesArr.length(); i ++) {
                    chatMessages.add(ChatMessage.parseChatMessageObj(chatMessagesArr.getJSONObject(i), jg_api));
                }

                return (T) chatMessages.toArray(ChatMessage[]::new);
            }
            case Nickname -> {
                return (T) responseObj.getString("nickname");
            }
            case ServerMember -> {
                return (T) ServerMember.parseServerMemberObj(responseObj.getJSONObject("member"), jg_api);
            }
            case ServerMemberSummary_Arr -> {
                List<ServerMemberSummary> serverMemberSummaries = new ArrayList<>();
                JSONArray serverMembersArr = responseObj.getJSONArray("members");

                for (int i = 0; i < serverMembersArr.length(); i ++) {
                    serverMemberSummaries.add(ServerMemberSummary.parseServerMemberSummaryObj(serverMembersArr.getJSONObject(i), jg_api));
                }

                return (T) serverMemberSummaries.toArray(ServerMemberSummary[]::new);
            }
            case ServerMemberBan -> {
                return (T) ServerMemberBan.parseServerMemberBanObj(responseObj.getJSONObject("serverMemberBan"), jg_api);
            }
            case ServerMemberBan_Arr -> {
                List<ServerMemberBan> serverMemberBans = new ArrayList<>();
                JSONArray serverMemberBansArr = responseObj.getJSONArray("serverMemberBans");

                for (int i = 0; i < serverMemberBansArr.length(); i ++) {
                    serverMemberBans.add(ServerMemberBan.parseServerMemberBanObj(serverMemberBansArr.getJSONObject(i), jg_api));
                }

                return (T) serverMemberBans.toArray(ChatMessage[]::new);
            }
            case ListItem -> {
                return (T) ListItem.parseListItemObj(responseObj.getJSONObject("listItem"), jg_api);
            }
            case ListItem_Arr -> {
                List<ListItem> listItems = new ArrayList<>();
                JSONArray listItemsArr = responseObj.getJSONArray("listItems");

                for (int i = 0; i < listItemsArr.length(); i++) {
                    listItems.add(ListItem.parseListItemObj(listItemsArr.getJSONObject(i), jg_api));
                }

                return (T) listItems.toArray(ListItem[]::new);
            }
            case Doc -> {
                return (T) Doc.parseDocObj(responseObj.getJSONObject("doc"), jg_api);
            }
            case Doc_Arr -> {
                List<Doc> docs = new ArrayList<>();
                JSONArray docsArr = responseObj.getJSONArray("docs");

                for (int i = 0; i < docsArr.length(); i++) {
                    docs.add(Doc.parseDocObj(docsArr.getJSONObject(i), jg_api));
                }

                return (T) docs.toArray(Doc[]::new);
            }
            case XP_Member_Total -> {
                return (T) Integer.valueOf(responseObj.getInt("total"));
            }
            case Social_Links_Obj -> {
                return (T) SocialLink.parseSocialLinkObj(responseObj.getJSONObject("socialLink"), jg_api);
            }
            case MemberRoles -> {
                return (T) responseObj.getJSONArray("roleIds").toList().toArray(Integer[]::new);
            }
            case Webhook -> {
                return (T) Webhook.parseWebhookObj(responseObj.getJSONObject("webhook"), jg_api);
            }
            case Webhook_Arr -> {
                List<Webhook> webhooks = new ArrayList<>();
                JSONArray webhooksArr = responseObj.getJSONArray("webhooks");

                for (int i = 0; i < webhooksArr.length(); i++) {
                    webhooks.add(Webhook.parseWebhookObj(webhooksArr.getJSONObject(i), jg_api));
                }

                return (T) webhooks.toArray(Webhook[]::new);
            }
            case CalendarEvent -> {
                return (T) CalendarEvent.parseCalendarEventObj(responseObj.getJSONObject("calendarEvent"), jg_api);
            }
            case CalendarEvent_Arr -> {
                List<CalendarEvent> calendarEvents = new ArrayList<>();
                JSONArray calendarEventsArr = responseObj.getJSONArray("calendarEvents");

                for (int i = 0; i < calendarEventsArr.length(); i++) {
                    calendarEvents.add(CalendarEvent.parseCalendarEventObj(calendarEventsArr.getJSONObject(i), jg_api));
                }

                return (T) calendarEvents.toArray(CalendarEvent[]::new);
            }
            case ForumTopic -> {
                return (T) ForumTopic.parseForumTopicObj(responseObj.getJSONObject("forumTopic"), jg_api);
            }
            case ForumTopic_Arr -> {
                List<ForumTopic> forumTopics = new ArrayList<>();
                JSONArray forumTopicsArr = responseObj.getJSONArray("forumTopics");

                for (int i = 0; i < forumTopicsArr.length(); i++) {
                    forumTopics.add(ForumTopic.parseForumTopicObj(forumTopicsArr.getJSONObject(i), jg_api));
                }
                return (T) forumTopics.toArray(ForumTopic[]::new);
            }
            case CalendarEventRsvp -> {
                return (T) CalendarEventRsvp.parseCalendarEventRsvpObject(responseObj.getJSONObject("calendarEventRsvp"), jg_api);
            }
            case CalendarEventRsvp_Arr -> {
                List<CalendarEventRsvp> calendarEventRsvps = new ArrayList<>();
                JSONArray calendarEventRsvpsArr = responseObj.getJSONArray("calendarEventRsvps");

                for (int i = 0; i < calendarEventRsvpsArr.length(); i++) {
                    calendarEventRsvps.add(CalendarEventRsvp.parseCalendarEventRsvpObject(calendarEventRsvpsArr.getJSONObject(i), jg_api));
                }
                return (T) calendarEventRsvps.toArray(CalendarEventRsvp[]::new);
            }
        }
        return null;
    }
}
