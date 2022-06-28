package dev.jgapi.jg_api.util;

import java.time.Instant;

public class UtilClass {

    public static Instant parseStringOrNull(String string) {
        if (string != null ) {
            Instant.parse(string);
        }

        return null;
    }

    public static int[] toPrimitive(Integer[] IntegerArray) {
        int[] result = new int[IntegerArray.length];

        for (int i = 0; i < IntegerArray.length; i++) {
            result[i] = IntegerArray[i];
        }

        return result;
    }

}
