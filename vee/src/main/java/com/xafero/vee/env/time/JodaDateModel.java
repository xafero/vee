package com.xafero.vee.env.time;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.jdatepicker.AbstractDateModel;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

public class JodaDateModel extends AbstractDateModel<org.joda.time.LocalDate> {

	private final Locale locale;

	public JodaDateModel(Locale locale) {
		this.locale = locale;
	}

	@Override
	protected Calendar toCalendar(LocalDate from) {
		DateTime dt = from.toDateTimeAtStartOfDay();
		return toJava(dt, locale);
	}

	@Override
	protected LocalDate fromCalendar(Calendar from) {
		DateTime dt = toJoda(from);
		return dt.toLocalDate();
	}

	public static DateTime toJoda(Calendar from) {
		TimeZone tz = from.getTimeZone();
		DateTimeZone jodaTz = DateTimeZone.forID(tz.getID());
		return new DateTime(from.getTimeInMillis(), jodaTz);
	}

	public static Calendar toJava(DateTime from, Locale locale) {
		return from.toCalendar(locale);
	}
}