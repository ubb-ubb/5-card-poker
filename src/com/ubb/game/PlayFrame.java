package com.ubb.game;
// Imported libraries.
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This class extends from MainFrame that extended from JFrame. 
 * Implements ActionListener and MouseListener.
 */
public class PlayFrame extends MainFrame implements ActionListener, MouseListener{
	
	// Arraylist for Buttons and JLabelExtension.
	// Created for accessing buttons and images with ActionListener and MouseListener.
	public ArrayList<JButton> buttonList = new ArrayList<JButton>();
	public ArrayList<JLabelExtension> cards = new ArrayList<JLabelExtension>();
	
	JButton genButton;
	User playerOne = new User();
	JLabelExtension imageCard1; 
	JLabel valueCard;
	Poker handValue;
	Cards card;
	Cards[] selectedCards = new Cards[5];
	JFormattedTextField txtField;
	JLabel labelBalance;
	JLabel wonText;
	JLabel nameText;
	JLabel underlayLabel;
	
	
	
	// Final Variables to set Button position.		
	private final int  Y_POS = 192;
	private final int X_POS = 95;
	// Final Variable to set Poker Hand Card Count.
	private final int HAND_CARD_COUNT = 5;
	
	// PlayFrame constructor with User Class parameter.	
	PlayFrame(User user) {
		
		// Configuring properties of PlayFrame.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(640,480);		
		this.setTitle("Poker Play Frame");
		this.setVisible(true);
		
		// Constructor parameter assigned to PlayFrame attribute.
		this.playerOne = user;
		
		// Hiding textFieldBox and startButton from MainFrame.
		t.setVisible(false);
		startButton.setVisible(false);
		
		// Configuring and adding green underlay image to game board.
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src\\img\\overlay2.png"));
		} 
		catch (IOException e) {
		    e.printStackTrace();
		}		
		Image dimg = img.getScaledInstance(640, 480, Image.SCALE_SMOOTH);		
		ImageIcon imageIcon = new ImageIcon(dimg);		
		underlayLabel = new JLabelExtension();
		underlayLabel.setIcon(imageIcon);
		underlayLabel.setBounds(0,0,640,480);
		underlayLabel.setVisible(true);
		this.add(underlayLabel);
		
		
		
		// Configuring and addition of balance text as JLabel.
		labelBalance = new JLabel();
		labelBalance.setText("Balance: " + Integer.toString(playerOne.getMoney()) +"$");
		labelBalance.setBounds(30,5,300,25);
		labelBalance.setFont(new Font("Verdana", Font.BOLD,15));
		labelBalance.setVisible(true);
		this.add(labelBalance);
		
		
		// Configuring and addition of name text as JLabel.
		nameText = new JLabel();
		nameText.setText("Player: " + playerOne.getName());
		nameText.setBounds(30,30,300,25);
		nameText.setFont(new Font("Verdana", Font.BOLD,15));
		nameText.setVisible(true);
		this.add(nameText);
		
		// Configuring and addition of Bet formattedTextField as JLabel.
		txtField = new JFormattedTextField();
		txtField.setVisible(true);
		txtField.setBounds(30,340,90,35);
		txtField.setFont(new Font("Verdana", Font.BOLD,15));
		txtField.setBackground(new Color(255,255,255));
		txtField.setForeground(new Color(0,0,0));
		txtField.setHorizontalAlignment(JFormattedTextField.CENTER);
		txtField.setBorder(BorderFactory.createEtchedBorder());
		this.add(txtField);
		
		
		// Adding buttons to this JFrame with the help of class method.
		addButton("BET", 30 ,380 ,90, 30,true);		
		addButton("END GAME", 520 ,380 ,90, 30, true);
		addButton("SHUFFLE", 405 ,380,90,30, false);
		
		addButton("SWAP CARDS", 155 ,380 ,90, 30, false);
		addButton("CONTINUE", 280,380,90,30, false);
		addButton("PRIZE TABLE", 520 ,10 ,90, 25, true);
	}
	
	/**
	 * @param txt, text content in button.
	 * @param xPos, x position
	 * @param yPos, y position
	 * @param width
	 * @param height
	 * @param enable, visibility condition
	 * Configures and adds new button with button text, positions, and visibility condition. 
	 */	
	public void addButton(String txt, int xPos, int yPos, int width, int height, boolean enable) {
		
		//Creates new JButton, parameters are assigned to properties.
		genButton = new JButton();
		genButton.setBounds(xPos, yPos, width, height);
		genButton.addActionListener(this);
		genButton.setText(txt);
		genButton.setFocusable(false);
		genButton.setEnabled(enable);		
		genButton.setHorizontalTextPosition(JButton.CENTER);
		genButton.setVerticalTextPosition(JButton.BOTTOM);
		genButton.setFont(new Font("Verdana", Font.BOLD,10));
		genButton.setIconTextGap(15);
		genButton.setForeground(Color.green);
		genButton.setBackground(Color.darkGray);
		genButton.setBorder(BorderFactory.createEtchedBorder());	
		this.add(genButton);
		
		// genButton Object is appended to buttonList ArrayList. Initiated as class attribute.
		buttonList.add(genButton);
	
		
	}
	
	@Override
	/**
	 * Implementation of ActionListener, if button is clicked, execute.
	 * actionPerformed method is overriden from ActionListener.
	 */
	public void actionPerformed(ActionEvent e){

		// buttonList ArrayList stores our all buttons that we appended in addButton method. 
		// In this method we decide what each buttons do.
		// BET Button.
		if (e.getSource() == buttonList.get(0)) {

			// If condition confirms that user has enough money or bet more than 0 before BET executed.
			if (Integer.parseInt(txtField.getText()) != 0 && Integer.parseInt(txtField.getText()) <= playerOne.getMoney()) {
				
				// Loop creates new Five cards on table.
				for (int i = 0; i<HAND_CARD_COUNT;i++) {				
					Cards card2 = new Cards();
					
					// addCard method creates visual cards to table.
					// addCard(card2.id,xPos,yPos)
					addCard(Integer.toString(card2.getId()), X_POS+(i*90) , Y_POS);
					
					// Cards are stored in an array.
					selectedCards[i] = card2;
				}
				
				// After BET button clicked, BET button is unclickable, SWAP CARDS, CONTINUEi SHUFFLE Button clickable. 
				buttonList.get(0).setEnabled(false);
				buttonList.get(2).setEnabled(true);
				buttonList.get(3).setEnabled(true);
				buttonList.get(4).setEnabled(true);

				// Evaluate 5 card's hand value in showValueHands method. 
				showValueHands(selectedCards, true);
				
				// Bet Money is subtracted from Balance.
				playerOne.setMoney(playerOne.getMoney() - Integer.parseInt(txtField.getText()));
				labelBalance.setText("Balance: " + Integer.toString(playerOne.getMoney()) +"$");
				
				// Bet Quantity TextField is disabled.
				txtField.setEnabled(false);

				this.revalidate();
				this.repaint();	
			}
		}
		
		// END GAME Button
		// This methods ends the game, directs to MainFrame and saves user name and balance left to highscore.txt file.
		else if (e.getSource() == buttonList.get(1)) {

			
			try {				
				// Opens highscore.txt file. true means data will be appended.
				FileWriter myWriterF = new FileWriter("highscore.txt", true);
				myWriterF.write(playerOne.getName() +",");
				myWriterF.write(Integer.toString(playerOne.getMoney()));
				myWriterF.write("\n");
				myWriterF.close();
			}
		
			catch (IOException ae) {
				System.out.println("An error occurred.");
				ae.printStackTrace();
			}
			
			
			this.revalidate();
			this.repaint();
			
			// Closes PlayFrame
			this.dispose();
			
			// Creates new MainFrame
			MainFrame mf = new MainFrame();
		}

		// SHUFFLE Button
		// Restarts hand at any moment of the game unless it is not at the start. Bet money is lost if game is not ended.
		else if (e.getSource() == buttonList.get(2)) {

			// Appended ImageJLabel cards in ArrayList is set invisible.
			for (int i = 0; i<cards.size();i++) {				
				cards.get(i).setVisible(false);
			}
			
			// Clear all Image JLabel cards from ArrayList.
			cards.clear();
			
			// Clear all Cards from choosenCards ArrayList (Static).
			Cards.choosenCards.clear();
			
			// SHUFFLE, SWAP CARDS, CONTINUE buttons are disabled.
			// BET button is enabled.
			buttonList.get(0).setEnabled(true);
			buttonList.get(2).setEnabled(false);
			buttonList.get(3).setEnabled(false);
			buttonList.get(4).setEnabled(false);
			
			// Poker hand value text is disabled.
			// How much money lost or won text is disabled.
			// Input box for bet size is enabled.
			valueCard.setVisible(false);
			wonText.setVisible(false);
			txtField.setEnabled(true);

			this.revalidate();
			this.repaint();	
		}

		// SWAP CARDS Button.
		// Draw and swap new cards for selected cards.
		else if (e.getSource() == buttonList.get(3)) {

			// SWAP CARDS and CONTINUE Button disabled.
			buttonList.get(3).setEnabled(false);
			buttonList.get(4).setEnabled(false);
			
			// Search for selected cards.
			// Remove selected cards
			// Add new card for removed cards.
			for (int i = 0; i < cards.size(); i++) {
				if (cards.get(i).isSelected_image()) {
					
					// Initiate new card
					Cards card2 = new Cards();
					
					// Add card to selectedCards array.
					selectedCards[i] = card2;
					
					// addCard(card2.id,xPos,yPos)
					addCard(Integer.toString(card2.getId()),cards.get(i).getX(),cards.get(i).getY()+25);
					cards.get(i).setVisible(false);
					valueCard.setVisible(false);
				}				
			}
			
			// Calculate hand value after swapping cards.
			String handValue = showValueHands(selectedCards, true);
			youWon(selectedCards, true);


			// Calculate won money after swapping card and add to balance text.
			if (handValue.equals("Royal Flush")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 5000);
			}

			else if (handValue.equals("Straight Flush")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 1000);
			}
			else if (handValue.equals("Flush")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 30);
			}
			else if (handValue.equals("Four Of a Kind")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 100);
			}
			else if (handValue.equals("Full House")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 50);
			}
			else if (handValue.equals("Straight")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 10);
			}
			else if (handValue.equals("Three of a Kind")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 5);
			}
			else if (handValue.equals("Two Pairs")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 2);
			}
			else if (handValue.equals("Pairs")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 1);
			}
			else if (handValue.equals("High Card")) {
				playerOne.setMoney(playerOne.getMoney());
			}

			
			// Balance text is updated according to card hand value.
			labelBalance.setText("Balance: " + Integer.toString(playerOne.getMoney()) +"$");
			
			// If player has 0 money after swapping cards, game is over, buttons are disabled, only end game is left.
			if (playerOne.getMoney()==0) {

				labelBalance.setText("GAME OVER");
				buttonList.get(0).setEnabled(false);
				buttonList.get(2).setEnabled(false);

				buttonList.get(3).setEnabled(false);
				buttonList.get(4).setEnabled(false);

			}

			this.revalidate();
			this.repaint();	
		}

		// CONTINUE Button 
		// If player is happy with first draw, game continues.
		// Does exact same functions as SWAP CARDS, other than swapping cards.
		else if (e.getSource() == buttonList.get(4)) {

			String handValue = showValueHands(selectedCards, false);
			youWon(selectedCards, true);

			if (handValue.equals("Royal Flush")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 5000);
			}			
			else if (handValue.equals("Straight Flush")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 1000);
			}
			else if (handValue.equals("Flush")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 30);
			}
			else if (handValue.equals("Four Of a Kind")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 100);
			}
			else if (handValue.equals("Full House")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 50);
			}
			else if (handValue.equals("Straight")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 10);
			}
			else if (handValue.equals("Three of a Kind")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 5);
			}
			else if (handValue.equals("Two Pairs")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 2);
			}
			else if (handValue.equals("Pairs")) {
				playerOne.setMoney(playerOne.getMoney() + Integer.parseInt(txtField.getText()) * 1);
			}
			else if (handValue.equals("High Card")) {
				playerOne.setMoney(playerOne.getMoney());
			}

			labelBalance.setText("Balance: " + Integer.toString(playerOne.getMoney()) +"$");
			
			
			// If money is 0 after continuing game, game is over, only end game button is clickable.
			if (playerOne.getMoney()==0) {

				labelBalance.setText("GAME OVER");
				buttonList.get(0).setEnabled(false);
				buttonList.get(2).setEnabled(false);
			}

			buttonList.get(3).setEnabled(false);
			buttonList.get(4).setEnabled(false);

			this.revalidate();
			this.repaint();	



		}
		// PRIZE TABLE Button
		// Initiates new frame, for looking how much money could be won.
		else if (e.getSource() == buttonList.get(5)) {
			
			// Initiating Instructions Class
			Instructions instFrame = new Instructions();
		}
	}


	/**
	 * @param path, image path
	 * @param xPos, X position of image
	 * @param yPos, Y position of image
	 * Configures and adds card image to game board.
	 * Each card image has name 1 to 52.png, matched with Cards and Poker Class id.
	 * addCard method used under BET and SWAP CARD button.
	 */
public void addCard(String path,int xPos, int yPos) {
		
		// Configuring JLabelExtension properties, and adding to this PlayFrame.
		JLabelExtension imageCard1;		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src\\img\\"+path+".png"));
		} 
		catch (IOException e) {
		    e.printStackTrace();
		}
		// Image scaling.
		Image dimg = img.getScaledInstance(75, 109, Image.SCALE_SMOOTH);		
		// Assign image to ImageIcon.
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		// Initiate new JLabelExtension
		imageCard1 = new JLabelExtension();
		//Image icon is set.
		imageCard1.setIcon(imageIcon);
		imageCard1.setBounds(xPos,yPos,75,109);
		imageCard1.addMouseListener(this);
		imageCard1.setId(Integer.parseInt(path));
		imageCard1.setVisible(true);
		
		// Append imageCard1 to JLabelExtension to ArrayList.
		cards.add(imageCard1);		
		// Adds imageCard to this PlayFrame.
		this.add(imageCard1);
		
		
	}

/**
 * 
 * @param cards, Cards array
 * @param b, boolean visibility condition
 * @return String, value of 5 hand card series.
 * showValueHands returns 5 hand card poker hand value.
 * Parameters Cards[] array and boolean b that decided if this Jlabel will be added or not.
 */
public String showValueHands(Cards[] cards, boolean b) {
		
	// Initiate new Poker Class with name handValue,
	// Input constructor parameter cards
	Poker handValue = new Poker(cards);

	// If condition adds new Jlabel and set its text as handValue.
	if (b) {
	
	valueCard = new JLabel();
	valueCard.setText(handValue.setHandValue());
	valueCard.setBounds(0,70,640,40);
	valueCard.setFont(new Font("Verdana", Font.BOLD,25));
	valueCard.setBackground(new Color(100,100,100));
	valueCard.setForeground(new Color(0,0,0));
	valueCard.setHorizontalAlignment(JLabel.CENTER);
	valueCard.setVisible(true);	
	this.add(valueCard);
	}
	
	return handValue.setHandValue();
	
}

 

/**
 * youWon calculates 5 hand card poker hand value.
 * @param cards, Cards array
 * @param b, boolean visibility condition
 * @return String, returns how much money won or lost.
 */
public String youWon(Cards[] cards, boolean b) {
	
	Poker handValue = new Poker(cards);
	
	// If condition adds new Jlabel and set its text as how much money player won.
	if (b) {
	wonText = new JLabel();
	wonText.setForeground(new Color(28,90,59));
	
	if (handValue.setHandValue().equals("Royal Flush")) {
		wonText.setText("YOU WIN: " + Integer.toString(Integer.parseInt(txtField.getText()) * 5000)  +"$");
		
	}			
	else if (handValue.setHandValue().equals("Straight Flush")) {
		wonText.setText("YOU WIN: " + Integer.toString(Integer.parseInt(txtField.getText()) * 1000)+"$");
		
	}
	else if (handValue.setHandValue().equals("Flush")) {
		wonText.setText("YOU WIN: " + Integer.toString(Integer.parseInt(txtField.getText()) * 30)+"$");
		
	}
	else if (handValue.setHandValue().equals("Four Of a Kind")) {
		wonText.setText("YOU WIN: " + Integer.toString(Integer.parseInt(txtField.getText()) * 100)+"$");
		
	}
	else if (handValue.setHandValue().equals("Full House")) {
		wonText.setText("YOU WIN: " + Integer.toString(Integer.parseInt(txtField.getText()) * 50)+"$");
		
	}
	else if (handValue.setHandValue().equals("Straight")) {
		wonText.setText("YOU WIN: " + Integer.toString(Integer.parseInt(txtField.getText()) * 10)+"$");
		
	}
	else if (handValue.setHandValue().equals("Three of a Kind")) {
		wonText.setText("YOU WIN: " + Integer.toString(Integer.parseInt(txtField.getText()) * 5)+"$");
		
	}
	else if (handValue.setHandValue().equals("Two Pairs")) {
		wonText.setText("YOU WIN: " + Integer.toString(Integer.parseInt(txtField.getText()) * 2)+"$");
		
	}
	else if (handValue.setHandValue().equals("Pairs")) {
		wonText.setText("YOU WIN: "+Integer.toString(Integer.parseInt(txtField.getText()) * 1)+"$");
		
	}
	else if (handValue.setHandValue().equals("High Card")) {
		wonText.setText("YOU LOST: "+Integer.toString(Integer.parseInt(txtField.getText()) * 1)+"$");
		wonText.setForeground(new Color(159,57,57));
	
	}
	
	
	
	wonText.setBounds(0,110,640,40);	
	wonText.setFont(new Font("Verdana", Font.BOLD,25));	
	wonText.setHorizontalAlignment(JLabel.CENTER);	
	wonText.setVisible(true);	
	this.add(wonText);	
	}	
	return handValue.setHandValue();
	
}


@Override
/**
 * @param MouseEvent e, If any card image is clicked, execute below.
 * Overriden from MouseListener.
 */
public void mouseClicked(MouseEvent e) {
	
	// If mouse is clicked, search for any card is clicked.
	// Search in cards ArrayList that contains JLabel Images.
	for (int i = 0; i<cards.size(); i++) {
		
		// If mouse is clicked on image and image is not clicked before.
		if (e.getSource() == cards.get(i) && cards.get(i).isControl_case()) {
			
			// Change position.
			cards.get(i).setBounds(cards.get(i).getX(),cards.get(i).getY() - 25,75,109);
			// set Image as selected.
			cards.get(i).setSelected_image(true);
			// set Image as selected.
			cards.get(i).setControl_case(false);			
		}
		
		// If mouse is clicked on image and image is  clicked before.
		else if(e.getSource() == cards.get(i) && !cards.get(i).isControl_case()) {
			
			// Change position.
			cards.get(i).setBounds(cards.get(i).getX(),cards.get(i).getY() + 25,75,109);
			// set Image as selected.
			cards.get(i).setControl_case(true);
			// set Image as selected.
			cards.get(i).setSelected_image(false);
		}	
	}
}

// Below methods are not used.	
@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}


@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}


@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}


@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}


	

}
