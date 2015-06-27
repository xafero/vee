package com.xafero.vee.env.time;

import java.util.Locale;
import java.util.Properties;

import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.DateModel;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePanel;
import org.jdatepicker.impl.JDatePanelImpl;

public class XtraDateComponentFactory<T extends DateModel<M>, M> extends JDateComponentFactory {

	private final T model;
	private final Properties i18nStrings;

	@SuppressWarnings("unchecked")
	public XtraDateComponentFactory(T model, AbstractFormatter dateFormat, Locale locale) {
		super((Class<T>) model.getClass(), dateFormat, locale);
		this.model = model;
		this.i18nStrings = locale == null ? getI18nStrings(Locale.getDefault()) : getI18nStrings(locale);
	}

	@Override
	public JDatePanel createJDatePanel() {
		return new JDatePanelImpl(model, i18nStrings);
	}
}