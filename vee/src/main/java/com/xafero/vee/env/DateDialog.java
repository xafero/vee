package com.xafero.vee.env;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.jdatepicker.JDatePanel;

@SuppressWarnings("serial")
class DateDialog<T> extends JDialog implements ActionListener {

	private final JDatePanel chooser;

	private JButton okBtn;
	private JButton cancelBtn;

	private T result;

	public DateDialog(Window owner, JDatePanel chooser) {
		super(owner);
		this.chooser = chooser;
		initalize();
	}

	private void initalize() {
		// Content panel
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add((Component) chooser, BorderLayout.CENTER);
		// Control panel
		JPanel control = new JPanel();
		control.add(okBtn = new JButton("OK"));
		control.add(cancelBtn = new JButton("Cancel"));
		// Main panel
		Container main = getContentPane();
		main.setLayout(new BorderLayout());
		main.add(content, BorderLayout.CENTER);
		main.add(control, BorderLayout.SOUTH);
		// Add actions
		okBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}

	public T showDialog() {
		pack();
		setVisible(true);
		return getResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent evt) {
		setVisible(false);
		dispose();
		if (!evt.getSource().equals(okBtn))
			return;
		result = (T) chooser.getModel().getValue();
	}

	public T getResult() {
		return result;
	}
}