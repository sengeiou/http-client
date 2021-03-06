package com.pdd.pop.sdk.common.util;

public class PreconditionUtil {
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
