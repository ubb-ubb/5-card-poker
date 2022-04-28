package com.ubb.game;

// Poker class
// Attributes, five card Array

/**
 * Poker class 
 * Attributes, five card Array
 * Created for calculating 5 hand card
 */
public class Poker {
	
	private Cards[] fiveCard;
	
	
	// Constructor
	Poker(Cards[] fiveCard) {
		
		this.fiveCard = fiveCard;
	}
		
	/**
	 * Determines and returns hands value
	 * @return String, 5 hand cards value as string.
	 */
	public String setHandValue() {
		
		if (this.cardValues() && this.sequentialHand() && this.colorHand()){
		
			return "Royal Flush";
		}
		
		else if (this.sequentialHand() && this.colorHand()){
			
			return "Straight Flush";
		}
		else if(this.colorHand()){
			
			return "Flush";			
		}
		else if(this.cardPairs() == 6){
			return "Four Of a Kind";
		}
		else if(this.cardPairs() == 5){
			
			return "Full House";
		}
		else if(this.sequentialHand()){
			
			return "Straight";
		}
		else if(this.cardPairs() == 4){
			
			return "Three of a Kind";			
		}
		else if(this.cardPairs() == 3){
		
			return "Two Pairs";
		}
		
		else if(this.cardPairs() == 2){
		
			return "Pairs";
		}
		else if(this.cardPairs() == 0){
		
			return "High Card";
		}		
			return "High Card";
	}
	
	
	/**
	 * Determines  --full house, four of a kind, three of a kind, two pairs, pairs.
	 * @return int, 0 no pair, 2 one pair, 3 two pairs, 4 three of a kind, 5 full house , 6 four of a kind
	 */
	public int cardPairs() {
		
				
		
		int arr[] = {fiveCard[0].getValue(),fiveCard[1].getValue() ,fiveCard[2].getValue(),fiveCard[3].getValue(),fiveCard[4].getValue()};
		
		// returns: 0 no pair, 2 one pair, 3 two pairs, 4 three of a kind, 5 full house , 6 four of a kind...	
		return uniqueValues(bubbleSort(arr));
	}
	

	
	/**
	 * returns if 5 card is sequential or not.
	 * @return boolean, if true, 5 hand is sequential.
	 */
	public boolean sequentialHand() {
		
		int arr[] = {fiveCard[0].getValue(),fiveCard[1].getValue() ,fiveCard[2].getValue(),fiveCard[3].getValue(),fiveCard[4].getValue()};
		return sequenceCheck(bubbleSort(arr));
	}
	

	/**
	 * Calculates if 5 cards are same color or not.
	 * @return boolean, if true 5 hand shape is all same.
	 */
	public boolean colorHand() {
		
		int shapeCount[] = new int[4];
		
		for (Cards card : fiveCard) {
			
			
			
			if (card.getShape() == 1) {shapeCount[0]++;}
			else if (card.getShape()== 2) {shapeCount[1]++;}
			else if (card.getShape()== 3) {shapeCount[2]++;}
			else if (card.getShape()== 4) {shapeCount[3]++;}
			
		}
		
		
		return getMaximum(shapeCount) == 5;
		
		
	}
	
	
	/**
	 * 
	 * @return true if cardValue total is 60.
	 */
	public boolean cardValues() {
		
		int k = 0;
		
		for (Cards card: fiveCard) {
			
			if (card.getValue()==1) {
				
				k += k + 14;
			}
			else {
			k += card.getValue();
			}
		}
		
		if (k == 60) {
			return true;
		}
		else {
			return false;
		}
	}
	

	/**
	 * 
	 * @param arr
	 * @return maximum in an array.
	 */
	public int getMaximum(int[] arr) {
		
		int max = 0;
		for (int obj : arr) {
			
			if (obj>max) {
				
				max = obj;
				
			}
			
		}
		return max;
		
	}
	
	/**
	 * bubble sort array min to max.
	 * @param arr
	 * @return int array.
	 */
	public int[] bubbleSort(int[] arr) {
		
		int n = arr.length;
		int tmp;
		boolean sequence = true;
		
		for (int i = 0; i<n;i++) {
			for (int j = i; j<n;j++) {
			
				if (arr[i]>arr[j]) {
					
					tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
				
				
			}
			}
		return arr;
		
		
	}
	

	/**
	 * Sequence check, if array is sequential return true else false.
	 * @param arr
	 * @return boolean.
	 */
	public boolean sequenceCheck(int[] arr) {
		
		int n = arr.length;
		int tmp;
		boolean sequence = true;
		
		int[] arrAce = new int[n];
		boolean aceSequence = true;
		
		for (int i = 0; i<n;i++) {
			
			if(arr[i] == 1) {
				
				arrAce[i] = 14;
				
			}
			else {
				
				arrAce[i] = arr[i];
			}
			
		}
		
		arrAce = bubbleSort(arrAce);
		
		for (int i = 0; i<n-1;i++) {
			if(arrAce[i+1]-arrAce[i] !=1) {
				aceSequence=false;
			}
		}
		
		
		
		for (int i = 0; i<n-1;i++) {
			if(arr[i+1]-arr[i] !=1) {
				sequence=false;
			}
		}
		
		
		return sequence||aceSequence;
		
		
	}
	
	
	/**
	 * 
	 * @param arr
	 * @return int = 0 no pair, 2 one pair, 3 two pairs, 4 three of a kind, 5 full house , 6 four of a kind
	 */
	public int uniqueValues(int[] arr) {
		
		int n = arr.length;
		int tmp=0;
		int checkOne = 0;
		int checkZero = 0;
		int leftPointer = 0;
		int max= 0;
				
		while (leftPointer < n - 1) {			
			
			if (arr[leftPointer] == arr[leftPointer+1]) {
				
				tmp++;
				leftPointer++;
				checkOne++;
				checkZero = 0;
			}
			else {
				leftPointer++;
				checkZero++;
				checkOne = 0;
			}		
			if (checkOne > max) {
				
				max = checkOne;
			}
			
			
			
		}
			return max+tmp;
		}
	
	
		
	}
	
	