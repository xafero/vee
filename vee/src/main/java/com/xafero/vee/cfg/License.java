package com.xafero.vee.cfg;

import java.net.URI;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class License {

	@XStreamAsAttribute
	private String name;

	@XStreamAsAttribute
	private URI url;

	public String getName() {
		return name;
	}

	public URI getUrl() {
		return url;
	}
}