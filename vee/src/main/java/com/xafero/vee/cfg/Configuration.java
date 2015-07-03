package com.xafero.vee.cfg;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias(value = "Settings")
public class Configuration {

	private static final File defaultCfgFile = new File("config.xml");

	@XStreamImplicit
	private List<Language> languages;

	public List<Language> getLanguages() {
		return languages == null ? languages = new LinkedList<Language>() : languages;
	}

	public static Configuration from(File cfgFile) {
		cfgFile = cfgFile == null ? defaultCfgFile : cfgFile;
		XStream xml = new XStream();
		xml.processAnnotations(Configuration.class);
		Object obj = xml.fromXML(cfgFile);
		return (Configuration) obj;
	}
}