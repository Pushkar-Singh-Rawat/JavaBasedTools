package com.screencap.ui;

import javax.swing.UIManager.*;
import com.screencap.utils.CreateImageDocument;
import com.screencap.utils.ImageList;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;

import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class AppUi {

	JTextField fileName;
	JLabel errorLable;
	JLabel devLabel1;
	JLabel devLabel2;
	JLabel devLabel3;
	ArrayList<Object> listOfImages;
	CreateImageDocument createDoc;
	static int size;
	static File file;
	ImageList imageList;
	static boolean upflag = false;


	public AppUi() {
		UIManager.put("nimbusBase", new Color(255,255,255));
		UIManager.put("nimbusBlueGrey", new Color(255,255,255)); //components
		UIManager.put("control", new Color(255,255,255)); //frame color
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		}
		listOfImages = new ArrayList<Object>();
		createDoc=new CreateImageDocument();

		JFrame frame = new JFrame("ScreenCap-By Pushkar");
		Image icon = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\application_resources\\images\\logo2.png");    
		frame.setIconImage(icon);

		JButton snapButton = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\application_resources\\images\\cap1.png"));
		JButton stopButton = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\application_resources\\images\\save2.png"));
		JButton startButton = new JButton(new ImageIcon(System.getProperty("user.dir")+"\\application_resources\\images\\play1.png"));

		devLabel1=new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\application_resources\\images\\devCont.png"));
		errorLable = new JLabel();
		fileName = new JTextField("Enter file name");
		fileName.setFocusable(true);
		if (upflag == false) {
			snapButton.setEnabled(false);
			stopButton.setEnabled(false);
		} else {

		}
		fileName.setForeground(new Color(153,153,153));
		fileName.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				fileName.setForeground(new Color(153,153,153));

			}

			@Override
			public void focusGained(FocusEvent e) {
				fileName.setText("");

			}
		});
		snapButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				listOfImages=imageList.getImagesList();
				System.out.println("jbutton class:listOfImages size"+listOfImages.size());

			}
		});

		stopButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (!listOfImages.isEmpty()) {
					createDoc.createDocumentOfImages(listOfImages,fileName.getText());
				} else {
					System.out.println("arraylist is empty");
					System.exit(0);
				}

			}
		});

		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				imageList=new ImageList();
				upflag = true;


				String getFileName = fileName.getText();
				if(!getFileName.equals("")){
					file = new File(System.getProperty("user.dir")+"\\screenshots\\" + getFileName + ".docx");
					file.getParentFile().mkdirs();
					try {
						if (file.exists()) {

							errorLable.setIcon(new ImageIcon(System.getProperty("user.dir")+"application_resources\\images\\saveFailed.png"));

						}
						if(!file.exists()){
							snapButton.setEnabled(true);
							stopButton.setEnabled(true);
							startButton.setEnabled(false);
							errorLable.setIcon(new ImageIcon(System.getProperty("user.dir")+"application_resources\\images\\saved.png"));
						}

						if (file.createNewFile()) {
							System.out.println(file.getAbsolutePath());
						} else {
							System.out.println("try again!!!");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		snapButton.setBounds(0, 0, 100, 60);
		startButton.setBounds(100, 0, 100, 60);
		stopButton.setBounds(200, 0, 100, 60);
		fileName.setBounds(80, 70, 140, 30);
		errorLable.setBounds(135, 90, 100, 50);
		devLabel1.setBounds(210, 70, 100,48);

		devLabel1.setToolTipText("Report Bugs");

		frame.add(fileName);
		frame.add(snapButton);
		frame.add(stopButton);
		frame.add(startButton);
		frame.add(errorLable);
		frame.add(devLabel1);
		frame.setAlwaysOnTop(true);
		frame.setSize(306, 160);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new AppUi();
	}

}
