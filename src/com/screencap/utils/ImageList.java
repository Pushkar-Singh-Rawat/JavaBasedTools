package com.screencap.utils;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ImageList {
	Robot robot;
	String fileName;
	ArrayList<Object> imagesList;
	File file;
	FileOutputStream fos;
	int i;

	public ImageList() {
		try {
			robot = new Robot();
		} catch (AWTException e) {

			e.printStackTrace();
		}
		i = 0;
		imagesList = new ArrayList<Object>();
	}

	public ArrayList<Object> getImagesList() {
		try {

			i++;
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			imagesList.add(screenFullImage);
			System.out.println("size of al" + imagesList.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imagesList;
	}
}
