package com.xafero.vee.env;

import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;

public class Window {

	public void alert(Object obj) {
		Component parent = null;
		Object message = obj.toString();
		String title = "Alarm";
		int messageType = JOptionPane.INFORMATION_MESSAGE;
		JOptionPane.showMessageDialog(parent, message, title, messageType);
	}

	public boolean confirm(Object obj) {
		Component parent = null;
		Object message = obj.toString();
		String title = "Confirmation";
		int optionType = JOptionPane.OK_CANCEL_OPTION;
		int messageType = JOptionPane.WARNING_MESSAGE;
		return JOptionPane.showConfirmDialog(parent, message, title, optionType, messageType) == JOptionPane.OK_OPTION;
	}

	public void open(Object obj) throws IOException, URISyntaxException {
		Desktop.getDesktop().browse(new URI(obj.toString()));
	}

	public void open(File file) throws IOException {
		Desktop.getDesktop().open(file);
	}

	public String prompt(Object obj) {
		Component parent = null;
		Object message = obj.toString();
		String title = "Question";
		int messageType = JOptionPane.QUESTION_MESSAGE;
		return JOptionPane.showInputDialog(parent, message, title, messageType);
	}
}