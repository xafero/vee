package com.xafero.vee.env;

import java.awt.Window;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.DateModel;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePanel;

public class Time {

	public Date chooseDate() {
		// Set up chooser
		Class<? extends DateModel<?>> dateModel = null;
		AbstractFormatter dateFormat = null;
		Locale locale = currentLocale();
		JDateComponentFactory factory = new JDateComponentFactory(dateModel, dateFormat, locale);
		JDatePanel chooser = factory.createJDatePanel();
		// Set up dialog
		Window owner = null;
		DateDialog<Calendar> diag = new DateDialog<Calendar>(owner, chooser);
		diag.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		diag.setLocationRelativeTo(null);
		diag.setTitle("Choose a date");
		diag.setModal(true);
		// Show it
		Calendar result = diag.showDialog();
		return result == null ? null : result.getTime();
	}

	public Locale currentLocale() {
		return new Locale(System.getProperty("user.language"), System.getProperty("user.country"));
	}
}