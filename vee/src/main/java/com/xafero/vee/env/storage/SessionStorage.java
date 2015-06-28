package com.xafero.vee.env.storage;

import java.util.Properties;

public class SessionStorage extends Properties implements IStorage<String, Object> {

	private static final long serialVersionUID = 7937375945158306084L;

	@Override
	public Object getItem(String key) {
		return getProperty(key);
	}

	@Override
	public Object setItem(String key, Object value) {
		return setProperty(key, value + "");
	}

	@Override
	public Object removeItem(String key) {
		return remove(key);
	}
}