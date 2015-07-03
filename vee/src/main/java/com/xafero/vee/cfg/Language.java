package com.xafero.vee.cfg;

import java.net.URI;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias(value = "Language")
public class Language {

	@XStreamAsAttribute
	private String name;

	@XStreamImplicit(itemFieldName = "extension")
	private List<String> extensions;

	private URI require;
	private License license;

	public String getName() {
		return name;
	}

	public List<String> getExtensions() {
		return extensions;
	}

	public URI getRequire() {
		return require;
	}

	public License getLicense() {
		return license;
	}
}