package com.xafero.vee.env;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class FileSystem {

	public String readAsText(Object obj) throws FileNotFoundException {
		return readAsText(obj + "");
	}

	private String readAsText(String path) throws FileNotFoundException {
		return readAsText(new File(path));
	}

	private String readAsText(File file) throws FileNotFoundException {
		String text;
		Scanner in = new Scanner(file, "UTF8");
		in.useDelimiter("#\\z");
		text = in.next();
		in.close();
		return text;
	}

	public File chooseFile(final String suffix) {
		String home = System.getProperty("user.home");
		File root = new File(home);
		JFileChooser chooser = new JFileChooser(root);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		FileFilter filter = new FileFilter() {
			public String getDescription() {
				return suffix.toUpperCase() + " files";
			}

			public boolean accept(File f) {
				return f.isDirectory() || f.getName().toLowerCase().endsWith(suffix.toLowerCase());
			}
		};
		chooser.addChoosableFileFilter(filter);
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.showOpenDialog(null);
		return chooser.getSelectedFile();
	}
}