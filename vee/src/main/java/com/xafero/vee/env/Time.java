package com.xafero.vee.env;

import java.awt.Window;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.JDatePanel;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.ReadablePartial;

import com.xafero.vee.env.time.DateDialog;
import com.xafero.vee.env.time.JodaDateModel;
import com.xafero.vee.env.time.XtraDateComponentFactory;

public class Time {

	public LocalDate chooseDate() {
		// Set up chooser
		Locale locale = currentLocale();
		JodaDateModel dateModel = new JodaDateModel(locale);
		AbstractFormatter dateFormat = null;
		XtraDateComponentFactory<JodaDateModel, LocalDate> factory;
		factory = new XtraDateComponentFactory<JodaDateModel, LocalDate>(dateModel, dateFormat, locale);
		JDatePanel chooser = factory.createJDatePanel();
		// Set up dialog
		Window owner = null;
		DateDialog<LocalDate> diag = new DateDialog<LocalDate>(owner, chooser);
		diag.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		diag.setLocationRelativeTo(null);
		diag.setTitle("Choose a date");
		diag.setModal(true);
		// Show it
		return diag.showDialog();
	}

	public Locale currentLocale() {
		return new Locale(System.getProperty("user.language"), System.getProperty("user.country"));
	}

	private Chronology getChronology(String chronology) throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String className = "org.joda.time.chrono." + chronology + "Chronology";
		Class<?> clazz = Class.forName(className);
		Method method = clazz.getMethod("getInstance");
		Object obj = method.invoke(null);
		return (Chronology) obj;
	}

	public DateTime convertInto(LocalDate date, String chronology) throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		DateTime dt = date.toDateTimeAtCurrentTime();
		Chronology chrono = getChronology(chronology);
		return dt.withChronology(chrono);
	}

	public LocalDate today() {
		return LocalDate.now();
	}

	public Days days(ReadablePartial start, ReadablePartial end) {
		return Days.daysBetween(start, end);
	}
}