package dev.jgapi.jg_api.entities.channels;

import dev.jgapi.jg_api.util.UtilClass;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Mentions {
    private String[] users;
    private String[] channels;
    private int[] roles;
    private boolean everyone;
    private boolean here;

    public Mentions(String[] users, String[] channels, int[] roles, boolean everyone, boolean here) {
        this.users = users;
        this.channels = channels;
        this.roles = roles;
        this.everyone = everyone;
        this.here = here;
    }

    public static Mentions parseMentionsObj(JSONObject mentionsObj) {
        JSONArray usersArr = mentionsObj.optJSONArray("users");
        JSONArray channelsArr = mentionsObj.optJSONArray("channels");
        JSONArray rolesArr = mentionsObj.optJSONArray("roles");

        List<String> users = new ArrayList<>();
        List<String> channels = new ArrayList<>();
        List<Integer> roles = new ArrayList<>();

        if (usersArr != null && !usersArr.isEmpty()) {
            for (int i = 0; i < usersArr.length(); i++) {
                JSONObject userObj = usersArr.getJSONObject(i);
                users.add(userObj.getString("id"));
            }
        }
        if (channelsArr != null && !channelsArr.isEmpty()) {
            for (int i = 0; i < channelsArr.length(); i++) {
                JSONObject channelObj = channelsArr.getJSONObject(i);
                channels.add(channelObj.getString("id"));
            }
        }
        if (rolesArr != null && !rolesArr.isEmpty()) {
            for (int i = 0; i < rolesArr.length(); i++) {
                JSONObject roleObj = rolesArr.getJSONObject(i);
                roles.add(roleObj.getInt("id"));
            }
        }

        return new Mentions(
                users.toArray(String[]::new),
                channels.toArray(String[]::new),
                UtilClass.toPrimitive(roles.toArray(Integer[]::new)),
                mentionsObj.optBoolean("everyone", false),
                mentionsObj.optBoolean("here", false)
        );
    }

    public String[] getUsers() {
        return users;
    }

    public String[] getChannels() {
        return channels;
    }

    public int[] getRoles() {
        return roles;
    }

    public boolean isEveryone() {
        return everyone;
    }

    public boolean isHere() {
        return here;
    }
}
