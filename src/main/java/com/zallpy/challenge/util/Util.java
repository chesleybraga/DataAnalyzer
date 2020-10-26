package com.zallpy.challenge.util;

import java.util.Collection;

/**
 * @author Chesley Braga
 */
public final class Util {

    private Util() {
	throw new IllegalStateException("Utility class");
    }

    public static boolean isEmpty(String value) {
	return ((value == null) || (value.trim().length() == 0));
    }

    public static boolean isEmpty(Collection<?> collection) {
	return ((collection == null) || collection.isEmpty());
    }

    public static <T> boolean isEmpty(T[] array) {
	return ((array == null) || (array.length == 0));
    }
}
