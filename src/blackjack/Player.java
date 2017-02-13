package blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	private final int HIT = 1;
	private final int STAY = 2;
	private final int BUST = 3;
	
	
	public ArrayList<Card> hand = new ArrayList<Card>();
	
	/*
	 * method returns the hand ArrayList as a string
	 */
	public String handToString() {
		String handString = "";
		
		for(int i = 0;i < hand.size(); i ++) {
			
			handString += hand.get(i).toString();
			
			if(i < hand.size() - 1) {
				handString += ", ";
			}
			
		
		}
		
		handString += "\n";
		
		return handString;
	}
	
	/*
	 *Method adds all cards to hand from input Array
	 */
	public void addToHand(Card[] cards) {
		for(int i = 0; i < cards.length; i ++) {
			hand.add(cards[i]);
			System.out.println(cards[i] + " dealt to " + this) ;
		}
	}
	
	/*
	 * Method adds a single card to the player's hand
	 */
	public void addToHand( Card card) {
		hand.add(card);
		System.out.println(card + " dealt to " + this);
	}
	
	/*
	 * Method sums the value of each card in the hand
	 * and returns the value as an int.
	 */
	public int processHandValue() {
		int finalValue = 0;
		int aces = getNumberOfAces();
		
		for(int i = 0; i < hand.size(); i ++) {
			
			if(hand.get(i).value > 9) {
				finalValue += 10;
			} else {
				finalValue += hand.get(i).value;
			}
			
		}
		
		if(aces > 0 ) {
			finalValue -= 1;
			finalValue += 11;
			
			if(finalValue > 21) {
				
				finalValue -= 11;
				finalValue += 1;
				
				return finalValue;
			} else {
				return finalValue;
			}
			
		} else {
		
			return finalValue;
		}
	}
	
	/*
	 * Method returns the amount of aces in the player's hand
	 */
	public int getNumberOfAces() {
		int aces = 0;
		
		for(int i = 0; i < hand.size(); i ++) {
		
			if(hand.get(i).value == 1) {
				aces ++;
			} 
		}
		
		return aces;
		
	}
	
	/*
	 * Method requests whether the player wants to hit, stay or bust,
	 * and returns the corresponding value.
	 */
	public int hitStayBust(Scanner scanner) {
		
		int handValue = processHandValue();
		if( handValue > 21) {
			
			return BUST;
		} else {
			boolean selectionMade = false;
			int choiceInt = 0;
			System.out.print("Enter 1 to hit, or 2 to stay.\n");
			String choice = "";
			
			while(!selectionMade) {
				choice = scanner.nextLine();
				
				if((choice).matches("1") ) {
					choiceInt = HIT;
					selectionMade = true;
				} else if ((choice).matches("2") ) {
					choiceInt = STAY;
					selectionMade = true;
				} else {
					System.out.print("Invalid choice. Please enter 1 or 2.\n");				
				}
				
				
			}
			
			return choiceInt;
					
		}
	}
	
	/* 
	 * Method sets the player's hand to a new empty arraylist
	 */
	public void reset() {
		hand = new ArrayList<Card>();
		return;
	}
	
	/*
	 *toString method returns "player" as opposed to "dealer" 
	 */
	
	public String toString() {
		return "Player";
	}
}
