package com.xafero.vee.env;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;

import com.xafero.vee.util.Files;

public class Media {

	public Color chooseColor() {
		Component parent = null;
		String title = "Choose a color";
		Color initial = null;
		return JColorChooser.showDialog(parent, title, initial);
	}

	public BufferedImage captureScreen(int monitor) throws AWTException {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] devices = env.getScreenDevices();
		if (devices.length < monitor)
			return null;
		GraphicsDevice dev = devices[monitor - 1];
		Robot robot = new Robot(dev);
		Rectangle rect = dev.getDefaultConfiguration().getBounds();
		return robot.createScreenCapture(rect);
	}

	public File write(RenderedImage img, String out) throws IOException {
		return write(img, new File(out));
	}

	public File write(RenderedImage img, File out) throws IOException {
		String fmt = Files.getExtension(out);
		if (ImageIO.write(img, fmt, out))
			return out;
		throw new UnsupportedOperationException("Format '" + fmt + "' is not supported!");
	}
}