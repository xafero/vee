package com.xafero.vee.util;

import java.io.File;

public final class Files {

	private Files() {
	}

	public static String getExtension(File file) {
		return getExtension(file.getName());
	}

	private static String getExtension(String fileName) {
		int idx;
		if ((idx = fileName.lastIndexOf('.')) < 0)
			return "";
		return fileName.substring(idx + 1);
	}
}