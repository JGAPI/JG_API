package dev.jgapi.jg_api.entities.members;

import dev.jgapi.jg_api.JG_API;
import dev.jgapi.jg_api.entities.GuildedObject;
import org.json.JSONObject;

public class SocialLink extends GuildedObject {
    String handle;
    String serviceId;
    String type;

    public SocialLink(JG_API jg_api, String handle, String serviceId, String type) {
        super(jg_api);
        this.handle = handle;
        this.serviceId = serviceId;
        this.type = type;
    }

    public static SocialLink parseSocialLinkObj(JSONObject socialLinkObj, JG_API jg_api) {
        return new SocialLink(
                jg_api,
                socialLinkObj.optString("handle", null),
                socialLinkObj.optString("serviceId", null),
                socialLinkObj.getString("type")
        );
    }

    public String getHandle() {
        return handle;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getType() {
        return type;
    }
}
