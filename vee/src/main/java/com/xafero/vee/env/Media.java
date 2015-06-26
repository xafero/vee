package com.xafero.vee.env;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JColorChooser;

public class Media {

	public Color chooseColor() {
		Component parent = null;
		String title = "Choose a color";
		Color initial = null;
		return JColorChooser.showDialog(parent, title, initial);
	}
}