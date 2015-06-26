package com.xafero.vee.cmd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import com.google.common.base.MoreObjects;
import com.xafero.vee.env.Console;
import com.xafero.vee.env.FileSystem;
import com.xafero.vee.env.Window;
import com.xafero.vee.util.Files;
import com.xafero.vee.util.Strings;

public class MainApp {

	private static final String APP_SHORT_NAME = "vee";
	private static final String APP_VER = "0.1-alpha";
	private static final String APP_SRC_URL = "https://github.com/xafero/vee";

	public static void main(String[] args) {
		// Define options
		Option help = new Option("?", "help", false, "print this message");
		Option version = new Option("v", "version", false, "print the version information and exit");
		Option eval = Option.builder("e").desc("evaluate script").argName("file").longOpt("eval").hasArg().build();
		Option list = new Option("l", "list", false, "print all available languages");
		// Collect them
		Options options = new Options();
		options.addOption(help);
		options.addOption(version);
		options.addOption(eval);
		options.addOption(list);
		// If nothing given, nothing will happen
		if (args == null || args.length < 1) {
			printHelp(options);
			return;
		}
		// Parse command line
		try {
			CommandLineParser parser = new DefaultParser();
			CommandLine line = parser.parse(options, args);
			// Work on it
			process(line, options);
		} catch (Throwable e) {
			System.err.printf("Error occurred: %n " + e.getMessage());
		}
	}

	private static void process(CommandLine line, Options options) throws ScriptException, IOException {
		if (line.hasOption('?')) {
			printHelp(options);
			return;
		}
		if (line.hasOption('v')) {
			printVersion();
			return;
		}
		if (line.hasOption('l')) {
			printLanguages();
			return;
		}
		if (line.hasOption('e')) {
			execute(line.getOptionValue('e'));
			return;
		}
		throw new UnsupportedOperationException("Unrecognized option: " + line.getArgList());
	}

	private static void printHelp(Options options) {
		String header = String.format("Ubiquitous scripting in an extensible environment%n%n");
		String footer = String.format("%nPlease report issues at " + APP_SRC_URL);
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(APP_SHORT_NAME, header, options, footer, true);
	}

	private static void printVersion() throws IOException {
		StringWriter writer;
		BufferedWriter out = new BufferedWriter(writer = new StringWriter());
		out.write(APP_SHORT_NAME + " " + APP_VER + " built on JSR 223.");
		out.newLine();
		out.newLine();
		out.write("Copyright (C) 2015 xafero");
		out.newLine();
		out.write("License AGPLv3+: GNU AGPL version 3 or later");
		out.newLine();
		out.write("<https://gnu.org/licenses/agpl.html>.");
		out.newLine();
		out.write("This is free software: you are free to change and redistribute it.");
		out.newLine();
		out.write("There is NO WARRANTY, to the extent permitted by law.");
		out.newLine();
		out.newLine();
		out.write("Please send bug reports and questions to <" + APP_SRC_URL + ">.");
		out.newLine();
		out.flush();
		System.out.print(writer.toString());
	}

	private static void printLanguages() {
		ScriptEngineManager mgr = new ScriptEngineManager();
		for (ScriptEngineFactory factory : mgr.getEngineFactories())
			System.out.println(MoreObjects.toStringHelper("").add("name", factory.getEngineName())
					.add("version", factory.getEngineVersion())
					.add("extensions", "[ " + Strings.join(" | ", factory.getExtensions()) + " ]")
					.add("mimeTypes", "[ " + Strings.join(" | ", factory.getMimeTypes()) + " ]")
					.add("aliases", "[ " + Strings.join(" | ", factory.getNames()) + " ]")
					.add("language", factory.getLanguageName() + " " + factory.getLanguageVersion()).omitNullValues()
					.toString().replace(", ", String.format(", %n ")));
	}

	private static void execute(String fileName) throws FileNotFoundException, ScriptException {
		File file = (new File(fileName)).getAbsoluteFile();
		if (!file.exists())
			throw new FileNotFoundException("There's no file named '" + file + "'!");
		String extension = Files.getExtension(file);
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByExtension(extension);
		Bindings env = engine.createBindings();
		inject(env, file);
		try {
			Object result = engine.eval(new FileReader(file), env);
			if (result != null)
				System.out.println(result.toString());
		} catch (ScriptException e) {
			throw e;
		}
	}

	private static void inject(Bindings env, File file) {
		env.put("window", new Window());
		env.put("console", new Console(file.getName()));
		env.put("fs", new FileSystem());
	}
}