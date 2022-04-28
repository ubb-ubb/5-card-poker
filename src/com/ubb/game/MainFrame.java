package com.ubb.game;

// Imported libraries.
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

// This class extends from JFrame from Swing Library.

/**
 * This class is used for creating new window with JFrame class methods.
 * MainFrame class extends from JFrame and implements from ActionListener class.
 */
public class MainFrame extends JFrame implements ActionListener{
	
	// Class attributes.
	JButton startButton;
	JLabel labelIcon;
	JLabel labelText;
	JLabel highScoreText;
	JTextField t;
	
	// Constructor.
	MainFrame() {
				
		// Configuration of 5 card draw game logo.		
		JLabelExtension imageCard1;		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src\\img\\pokerGame.png"));
		} 
		catch (IOException e) {
		    e.printStackTrace();
		}		
		Image dimg = img.getScaledInstance(240, 180, Image.SCALE_SMOOTH);		
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		imageCard1 = new JLabelExtension();
		imageCard1.setIcon(imageIcon);
		imageCard1.setVisible(true);
		
		//Configuration of game name text as JLabel		
		labelText = new JLabel();
		labelText.setText("5-CARD POKER GAME");		
		labelText.setFont(new Font("Verdana", Font.BOLD,35));
		labelText.setForeground(Color.BLACK);
		labelText.setVisible(true);
		//centers poker game label center...
		labelText.setLocation((this.getWidth()-labelText.getWidth())/2,50);
					
		// Configuration of high score and user name text as JLabel.
		highScoreText = new JLabel();		
		//Retrieving high score and name with help of getHighestScore method.
		highScoreText.setText("HIGH SCORE: " +getHighestScore() + " $");		
		highScoreText.setFont(new Font("Verdana", Font.BOLD, 15));
		highScoreText.setForeground(Color.BLACK);
		highScoreText.setVisible(true);
		
		//Configuration of start button with JButton.
		startButton = new JButton();
		startButton.addActionListener(this);
		startButton.setText("START GAME");
		startButton.setFocusable(true);
		startButton.setHorizontalTextPosition(JButton.CENTER);
		startButton.setVerticalTextPosition(JButton.BOTTOM);
		startButton.setFont(new Font("Verdana", Font.BOLD,10));
		startButton.setIconTextGap(15);
		startButton.setForeground(Color.green);
		startButton.setBackground(Color.darkGray);
		startButton.setBorder(BorderFactory.createEtchedBorder());		
		
		// Configuration of input text box with JTextField.
		t = new JTextField(16);
		t.setFont(new Font("Verdana", Font.BOLD,10));
		t.setVisible(true);
		t.setText("Enter Your Name");
	
		// Constructor of this class that extended from JFrame, configuration of new frame with below properties.
		// Jframe is created with MigLayout.
		// Does not need setBounds property for all items.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new MigLayout("wrap, insets 10, fill","[center]","[center]50[center]50[center]10[center]10[center]"));
		this.setSize(640,480);
		this.getContentPane().setBackground(new Color(132,204,121));
		this.setTitle("Poker");
		this.setVisible(true);
		
		// Addition of items to this frame
		// Second parameter in this.add method, configures MigLayout.
		this.add(imageCard1);
		this.add(labelText, "align center");
		this.add(highScoreText, "align center");
		this.add(t,"width 200,height 40, align right");
		this.add(startButton, "width 200, height 40, align right");
		}
	
	/**
	 * Implementation of ActionListener, if button is clicked, execute.
	 * actionPerformed method is overriden from ActionListener.
	 * @param ActionEvent e,
	 * @returns void.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			
			// Creating new User.
			User user2 = new User();
			// Get textinput from t(textfield) and assign to User.name
			user2.setName(t.getText());
			
			this.revalidate();
			this.repaint();	
			this.dispose();
						
			// Initiate new PlayFrame.
			// PlayFrame constructor has User Class variable. user2 is used for creating new PlayFrame.
			PlayFrame playFrame = new PlayFrame(user2);
		}
	}
	
	/**
	 * Get highest score from txt file in path (highscore.txt).
	 * Used in highScoreText Jlabel in constructor.
	 * @returns String highestScore as money, with player name.
	 * @throws FileNotFoundException.
	 */
	public String getHighestScore() {
		
		// initiate int max to find maximum in txt file.
		int max = 0;
		String name_max = "";
		
		try {
			
            File myObj = new File("highscore.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
				
            	String data = myReader.nextLine();				
            	String[] splitData = data.split(",");
            	
            	// If [1] is larger than max value, max = [1]
            	if (Integer.parseInt(splitData[1]) > max ) {
            	    max = Integer.parseInt(splitData[1]);
            		name_max = splitData[0];
            	}
            }
		}
		catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
		
		// Return name and high score.
		return name_max + " " + Integer.toString(max) ;
		
	}
}
