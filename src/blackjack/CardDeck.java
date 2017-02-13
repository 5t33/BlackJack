package blackjack;


public class CardDeck {
	
	public Card[] deck;
	public int topCard;
	
	/**
	 *Constructor initiates the card deck array
	 *and creates one card of each suit. */
	public CardDeck() {
		deck = new Card[52];
		topCard = 0;
		for(int i = 0; i < 52; i++) {
			
			if(i < 13) {		deck[i] = new Card(i + 1,Card.Suit.HEARTS);} 
			else if(i < 26) {	deck[i] = new Card(i - 12, Card.Suit.DIAMONDS);} 
			else if(i < 39 ) {	deck[i] = new Card(i - 25, Card.Suit.CLUBS);}
			else {				deck[i] = new Card(i - 38, Card.Suit.SPADES);}
		
		}

	}
	
	/**
	 *getTopCard method returns the card that
	 *is located at the index of the topCard instance
	 *variable.*/
	public Card getTopCard() {
		Card top = deck[topCard];
		topCard++;
		return top;
		
	}
	
	/**
	 *shuffle calls cut an interleave 5 times an returns
	 *a randomly sorted array of card objects.*/
	public void shuffle() {
		System.out.print("Shuffling cards...\n");
		for(int i = 1; i <= 5; i++) {
			cut();
			interleave();
		}
	}
	
	/**
	 *Interleave splits the deck into two halves, then 
	 *places the cards back into the deck in a random order.   
	 */
	public void interleave() {
		Card[] topHalf = Card.copyOfRange(deck, 0, 25);
		Card[] bottomHalf = Card.copyOfRange(deck, 26, 51);
		int topCurr = 0, bottomCurr = 0, i = 0, increment;
		
		
		
		while(i < 52){
			increment = (int)Math.floor(Math.random() * 2.0);
					
				if(increment == 0 && topCurr < 26) {
				
					deck[i] = new Card(topHalf[topCurr].value,topHalf[topCurr].suit );
					topCurr ++;
				} else if(increment == 1 && bottomCurr < 26) {
					deck[i] = new Card(bottomHalf[bottomCurr].value,bottomHalf[bottomCurr].suit );
					
					bottomCurr ++;
				} else {
					if(topCurr < 26) {
						deck[i] = new Card(topHalf[topCurr].value,topHalf[topCurr].suit );
						topCurr ++;
					} else if(bottomCurr < 26){
						
						deck[i] = new Card(bottomHalf[bottomCurr].value,bottomHalf[bottomCurr].suit );
						
						bottomCurr ++;
					}
				}
				i++;
		}
			
		
	}
	
	/**
	 * cut splits the deck into a bottom and a top half and switches them.
	 */
	public void cut() {	
		Card[] topHalf = Card.copyOfRange(deck, 0, 25);
		Card[] bottomHalf = Card.copyOfRange(deck, 26, 51);
		
		for(int i = 0; i < 26; i++) {
			deck[i] = topHalf[i];
			deck[i+26] = bottomHalf[i];
		}
		
	}
	
	
	
}
