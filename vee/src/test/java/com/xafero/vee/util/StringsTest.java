package com.xafero.vee.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class StringsTest {

	@Test
	public void testJoin() {
		assertEquals("a, b, c", Strings.join(", ", Arrays.asList("a", "b", "c")));
	}
}