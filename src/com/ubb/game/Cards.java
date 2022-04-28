package com.ubb.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Cards class 
// Attributes; int id, shape, value, boolean control.
// Final CARD_COUNT = 51 since rand between 0. row and 51. row
// Static attributes cardDatabase and choosenCards

/**
 * Attributes; int id, shape, value, boolean control.
 * Final CARD_COUNT = 51 since rand between 0. row and 51. row
 * Static attributes cardDatabase and choosenCards
 */
public class Cards {
	
	private int id;
	private int shape;
	private int value;
	Random rand;
	private final int CARD_COUNT = 52;
	
	public static final ArrayList<Integer[]> cardDatabase = readCard();
	public static ArrayList<Integer> choosenCards = new ArrayList<Integer>();
	private boolean control = true;
	
	
	// Constructor
	Cards() {
		
		if (choosenCards.size() != 52) {
		// Creating random value between 0-51;
		rand = new Random();
		int randInt = 0;
		
		// Checking until any card id is not found in choosenCard arrayList.
		while (control) {
			
			randInt = rand.nextInt(CARD_COUNT);
			
			if (!choosenCards.contains(randInt)) {
				control = false;
			}
			
			
			
		}
		
		this.id = cardDatabase.get(randInt)[0];
		this.value = cardDatabase.get(randInt)[1];
		this.shape = cardDatabase.get(randInt)[2];			
		choosenCards.add(randInt);
		}
	
				
	}
	
	
	/**
	 * @return ArrayList, cards readen from txt file, card.txt
	 * static method that reads txt from path. adds to ArrayList, returns this Arraylist
	 */
	public static ArrayList<Integer[]> readCard() {
		
		ArrayList<Integer[]> cards = new ArrayList<Integer[]>();
		try {			
            File myObj = new File("cards.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

            	String data = myReader.nextLine();
				String[] splitData = data.split(",");
            	Integer[] intSplitData = {Integer.parseInt(splitData[0]),Integer.parseInt(splitData[1]),Integer.parseInt(splitData[2])};
            	cards.add(intSplitData);
            }
		}
		catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
		return cards;
	
}

	//getters and setters.
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShape() {
		return shape;
	}

	public void setShape(int shape) {
		this.shape = shape;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
		
	/**
	 * For checking cards are equal to each other.
	 */
	public boolean equals(Object o) {
		
		if (!(o instanceof Cards)) {
			
			return false;
		}
		
		Cards c = (Cards) o;
		
		if (c.getId() == id) {
			
			return true;
		}
		else {
			return false;
		}
	
	}
}


	

