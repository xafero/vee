package com.xafero.vee.util;

import java.net.URI;
import java.net.URISyntaxException;

public final class Strings {

	private Strings() {
	}

	public static <T> Object join(String separator, Iterable<T> items) {
		StringBuilder bld = new StringBuilder();
		for (T item : items) {
			if (bld.length() >= 1)
				bld.append(separator);
			bld.append(item);
		}
		return bld.toString();
	}

	public static URI toURI(String args) {
		try {
			return new URI(args);
		} catch (URISyntaxException e) {
			return null;
		}
	}
}