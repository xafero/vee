package com.xafero.vee.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Console {

	private final Logger logger;

	public Console(String name) {
		this.logger = LoggerFactory.getLogger(name);
	}

	public void log(Object obj) {
		logger.info(obj + "");
	}
}