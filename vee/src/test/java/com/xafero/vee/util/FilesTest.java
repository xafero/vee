package com.xafero.vee.util;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

public class FilesTest {

	@Test
	public void testGetExtension() {
		assertEquals("js", Files.getExtension(new File("test.doc.js")));
		assertEquals("js", Files.getExtension(new File("test.me.doc.js")));
		assertEquals("", Files.getExtension(new File("test")));
	}
}