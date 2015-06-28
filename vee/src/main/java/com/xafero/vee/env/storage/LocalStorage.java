package com.xafero.vee.env.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.joda.time.DateTime;

public class LocalStorage extends Properties implements IStorage<String, Object> {

	private static final long serialVersionUID = -2884251507056784325L;
	private static final File storageFile = new File("storage.xml");

	public LocalStorage() {
		reload();
	}

	private void reload() {
		try {
			if (storageFile.exists())
				loadFromXML(new FileInputStream(storageFile));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void rewrite() {
		try {
			storeToXML(new FileOutputStream(storageFile), "Written at " + DateTime.now());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Object getItem(String key) {
		reload();
		return getProperty(key);
	}

	@Override
	public Object setItem(String key, Object value) {
		Object old = setProperty(key, value + "");
		rewrite();
		return old;
	}

	@Override
	public Object removeItem(String key) {
		Object old = remove(key);
		rewrite();
		return old;
	}
}