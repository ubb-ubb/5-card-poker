package com.ubb.game;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

// Instructions class extended from MainFrame
public class Instructions extends MainFrame{
	
	JLabel inst;
	
	Instructions() {
		
		this.setLocation(700, 0);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setSize(500,535);
		this.getContentPane().setBackground(new Color(100,100,100));
		this.setTitle("Prize Table!");
		this.setVisible(true);
		
		labelText.setVisible(false);	
		startButton.setVisible(false);
		
		
		JLabelExtension imageCard1;
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src\\img\\prizeTable.png"));
		} 
		catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimg = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);		
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		
		
		imageCard1 = new JLabelExtension();
		imageCard1.setIcon(imageIcon);
		imageCard1.setBounds(0,0,500,500);
		
		
		this.add(imageCard1);
		imageCard1.setVisible(true);
		
		
		
	}

}
