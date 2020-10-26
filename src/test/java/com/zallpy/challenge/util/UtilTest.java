package com.zallpy.challenge.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chesley Braga
 */
public class UtilTest {

    @Test
    public void isEmptyStringTest() throws Exception {
	Assert.assertTrue(Util.isEmpty((String) null));
	Assert.assertTrue(Util.isEmpty(""));
	Assert.assertTrue(Util.isEmpty(" "));
	Assert.assertTrue(Util.isEmpty("  "));
    }

    @Test
    public void isNotEmptyStringTest() throws Exception {
	Assert.assertFalse(Util.isEmpty("OK"));
	Assert.assertFalse(Util.isEmpty("OK "));
	Assert.assertFalse(Util.isEmpty(" OK"));
	Assert.assertFalse(Util.isEmpty(" OK "));
    }

    @Test
    public void isEmptyCollectionTest() throws Exception {
	Assert.assertTrue(Util.isEmpty((Vector<?>) null));
	Assert.assertTrue(Util.isEmpty(new ArrayList<>()));
	Assert.assertTrue(Util.isEmpty(new HashSet<>()));
    }

    @Test
    public void isNotEmptyCollectionTest() throws Exception {
	Assert.assertFalse(Util.isEmpty(Arrays.asList("")));
	Assert.assertFalse(Util.isEmpty(Arrays.asList("OK")));
    }

    @Test
    public void isEmptyArrayTest() throws Exception {
	Assert.assertTrue(Util.isEmpty((String[]) null));

	String[] array = {};
	Assert.assertTrue(Util.isEmpty(array));
    }

    @Test
    public void isNotEmptyArrayTest() throws Exception {
	String[] array = { "OK" };
	Assert.assertFalse(Util.isEmpty(array));
    }
}