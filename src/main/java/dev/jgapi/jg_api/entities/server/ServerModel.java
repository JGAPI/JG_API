package dev.jgapi.jg_api.entities.server;

import java.time.Instant;

public class ServerModel {
    private String id;
    private String ownerId;
    private String type;
    private String name;
    private String url;
    private String about;
    private String avatar;
    private String banner;
    private String timezone;
    private boolean isVerified;
    private String defaultChannelId;
    private Instant createdAt;
}
