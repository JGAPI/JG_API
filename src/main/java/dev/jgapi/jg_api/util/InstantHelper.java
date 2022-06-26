package dev.jgapi.jg_api.util;

import java.time.Instant;

public class InstantHelper {

    public static Instant parseStringOrNull(String string) {
        if (string != null ) {
            Instant.parse(string);
        }

        return null;
    }

}
