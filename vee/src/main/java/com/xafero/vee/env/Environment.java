package com.xafero.vee.env;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xafero.vee.util.Strings;

public class Environment {

	private static final Logger log = LoggerFactory.getLogger(Environment.class);

	private final File depsFld;

	public Environment() {
		depsFld = new File("deps");
		depsFld.mkdirs();
	}

	public Object createObject(Object obj) {
		return createObject(obj + "");
	}

	private Object createObject(String className) {
		try {
			Class<?> clazz = Class.forName(className);
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Object require(Object obj) throws IOException {
		return require(obj + "");
	}

	private Object require(String args) throws IOException {
		URI uri = Strings.toURI(args);
		if (uri == null)
			throw new UnsupportedOperationException("Only URIs are supported!");
		return require(uri);
	}

	private Object require(URI uri) throws IOException {
		if (uri.getScheme().equalsIgnoreCase("http") || uri.getScheme().equalsIgnoreCase("https"))
			return requireHttp(uri);
		if (uri.getScheme().equalsIgnoreCase("file"))
			return requireFile(uri);
		throw new UnsupportedOperationException("Unknown scheme '" + uri.getScheme() + "'!");
	}

	private Object requireFile(URI uri) {
		String path = (uri + "").replace("file://", "");
		File file = new File(path);
		return addJarToClassPath(file) ? file.getName() : null;
	}

	private Object requireHttp(URI uri) throws IOException {
		// Extract file name
		String path = uri + "";
		int lastSlash = path.lastIndexOf('/');
		String fileName = path.substring(lastSlash + 1);
		// Download file
		File file = new File(depsFld, fileName);
		if (!file.exists()) {
			log.info("Requesting '{}'...", uri);
			long size = download(uri, new FileOutputStream(file));
			log.info("Downloaded {} bytes.", size);
		}
		// Use file
		return addJarToClassPath(file) ? file.getName() : null;
	}

	private boolean addJarToClassPath(File file) {
		if (!file.getName().toLowerCase().endsWith(".jar") || !file.exists() || !file.canRead())
			return false;
		try {
			ClassLoader sysLoader = ClassLoader.getSystemClassLoader();
			Method meth = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			meth.setAccessible(true);
			meth.invoke(sysLoader, file.toURI().toURL());
			log.info("Required '{}'...", file);
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private int download(URI url, OutputStream out) throws IOException {
		CloseableHttpClient client = HttpClients.custom().build();
		try {
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse response = client.execute(get);
			try {
				byte[] array = EntityUtils.toByteArray(response.getEntity());
				out.write(array);
				out.close();
				return array.length;
			} finally {
				response.close();
			}
		} finally {
			client.close();
		}
	}
}