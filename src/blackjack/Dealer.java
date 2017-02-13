
package blackjack;

public class Dealer extends Player{
	public CardDeck deck;
	private final int HIT = 1;
	private final int STAY = 2;
	private final int BUST = 3;
	
	
	
	public Dealer() {
		deck = new CardDeck();
	}
	
	/*Method returns two cards from the top of the deck
	 *in an array by calling the getTopCard method.*/
	public Card[] dealTwo() {
		
		Card[] twoCards = new Card[2];
		twoCards[0] = deck.getTopCard();
		twoCards[1] = deck.getTopCard();
		return twoCards;
	}
	/*Method returns a single card by calling the getTopCard
	 *method*/
	public Card dealOne() {
		return deck.getTopCard();
	}
	
	/*
	 * Method returns hit(1) if the current hand has a value
	 * under 17, stay(2) if the current hand value is between 17-21,
	 * and bust(3) if the current hand value is over 21.
	 */
	public int hitStayBust(){
		int handValue = processHandValue();
		if(handValue > 21) {
			return BUST;
		} else {
			if(handValue >= 17) {
				return STAY;
			} else {
				return HIT;
			}
		}
		
	}
	
	/*Method calls parent reset method, shuffles deck, and sets the
	 *deck array's top card instance variable to 0. */
	public void reset() {
		super.reset();
		deck.topCard = 0;
		deck.shuffle();
		return;
	}
	
	/*
	 *toString method returns "dealer" as opposed to "player" 
	 */
	public String toString() {
		return "Dealer";
	}
	
	
}
