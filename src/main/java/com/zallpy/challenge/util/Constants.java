package com.zallpy.challenge.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Chesley Braga
 */
public final class Constants {

    private Constants() {
	throw new IllegalStateException("Utility class");
    }

    public static final Path PATH_ROOT = Paths.get(System.getProperty("user.home"));
    public static final Path PATH_DEFAULT = PATH_ROOT.resolve("data");
    public static final Path PATH_INPUT = PATH_DEFAULT.resolve("in");
    public static final Path PATH_OUTPUT = PATH_DEFAULT.resolve("out");
    public static final Collection<String> ACCEPTED_EXTENSIONS = Arrays.asList(".bat");

    public static final String SEPARATOR_FIELDS = "ç";
    public static final String SEPARATOR_ITEMS = ",";
    public static final String SEPARATOR_ITEM_FIELDS = "-";

    public static final String CODE_SALESMAN = "001";
    public static final String CODE_CLIENT = "002";
    public static final String CODE_SALE = "003";

    public static final String EXTENSION_OUTPUT_PREFIX = ".done";
    public static final String EXTENSION_CHARACTER = ".";
    public static final String EMPTY = "";
}
