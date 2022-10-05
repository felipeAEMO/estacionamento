package one.digitalinnovation.estacionamento.util;

import java.util.UUID;

public class Util {

    private static String setUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getUUID() {
        return setUUID();
    }
}
