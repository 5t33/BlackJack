package blackjack;

public class Card {
		public enum Suit {CLUBS, SPADES, HEARTS, DIAMONDS};
		public int value;
		public Suit suit;
	
		
		/*
		 * Constructor initiates the card value and suit		
		 */
		public Card(int value, Suit suit) {
			this.value = value;
			this.suit = suit;
		}
		
		/*
		 * Method returns a Card subarray of the input original card array,
		 * starting from the input start index, and ending at the input end index.
		 */
		public static Card[] copyOfRange(Card[] original, int start, int end ) {
			
			Card[] segment = new Card[end - start + 1];
			
			for(int i = start; i <= end; i++) {
				segment[i - start] = new Card(original[i].value, original[i].suit);
			}
			
			return segment;
		}
	
		/* 
		 * Method returns a string representation of the card object
		 */
		public String toString() {
			String cardString = "";
			
			switch (this.value) {
				case 1: cardString += "Ace of "; 
						break;
				case 2: cardString += "Two of "; 
						break;
				case 3: cardString += "Three of "; 
						break;
				case 4: cardString += "Four of "; 
						break;
				case 5: cardString += "Five of "; 
						break;
				case 6: cardString += "Six of "; 
						break;
				case 7: cardString += "Seven of "; 
						break;
				case 8: cardString += "Eight of "; 
						break;
				case 9: cardString += "Nine of "; 
						break;
				case 10: cardString += "Ten of "; 
						break;
				case 11: cardString += "Jack of "; 
						break;
				case 12: cardString += "Queen of "; 
						break;
				case 13: cardString += "King of "; 
						break;
			}
			
			switch (this.suit) {
			case SPADES: cardString += "spades";
						break;
			case HEARTS: cardString += "hearts";
						break;
			case CLUBS: cardString += "clubs";
						break;
			case DIAMONDS: cardString += "diamonds";
						break;
			}
			
			return cardString;
		}
 		
}