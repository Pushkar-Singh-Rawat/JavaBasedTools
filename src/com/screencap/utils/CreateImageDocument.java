package com.screencap.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class CreateImageDocument {
	XWPFDocument doc;


	BufferedImage bi;
	public CreateImageDocument() {
		doc = new XWPFDocument();

	}

	public void createDocumentOfImages(ArrayList<Object> al, String fileName) {

		Iterator<Object> it = al.iterator();
		System.out.println("capture class:al size"+al.size());
		int i = 0;
		while (it.hasNext()) {
			bi = (BufferedImage) it.next();
			System.out.println(bi);
			File file = new File(System.getProperty("user.dir")+"//screenshots//s" + i + ".jpg");
			file.getParentFile().mkdirs();
			XWPFParagraph paragraph = doc.createParagraph();
			XWPFRun run = paragraph.createRun();
			System.out.println("no of times i is runnnig" + (i + 1));
			try {
				file.createNewFile();
				ImageIO.write(bi, "jpg", file);
				String imgFile = System.getProperty("user.dir")+"//screenshots//s" + i + ".jpg";
				FileInputStream fis = new FileInputStream(imgFile);

				run.addPicture(fis, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(400), Units.toEMU(300));

				FileOutputStream fos = new FileOutputStream(new File(System.getProperty("user.dir")+"//screenshots//" + fileName + ".docx"));
				doc.write(fos);
				run.addBreak();
				fis.close();
				fos.close();

				if (file.exists()) {
					file.delete();
					System.out.println("yes!  file exists but now deleting");
				}
				i++;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				file.delete();
			}


		}
		System.exit(0);
	}
}
