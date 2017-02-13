package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

import blackjack.BlackJack;
import blackjack.Card;
import blackjack.CardDeck;
import blackjack.Dealer;
import blackjack.Game;
import blackjack.Player;

public class UnitTests {
	private final int HIT = 1;
	private final int STAY = 2;
	private final int BUST = 3;
	PrintStream stdout = System.out;
	
	/*Tests the player and dealer reset methods to make sure both
	 *hands are set to empty,the dealer's deck is shuffled, and the
	 *top card variable is set to 0.*/
	@Test public void reset() {
		
		Player player = new Player();
		Dealer dealer = new Dealer();
		
		player.addToHand(new Card(1, Card.Suit.SPADES));
		player.addToHand(new Card(10, Card.Suit.SPADES));
		player.addToHand(new Card(13, Card.Suit.DIAMONDS));

		dealer.addToHand(new Card(1, Card.Suit.HEARTS));
		dealer.addToHand(new Card(11, Card.Suit.HEARTS));
		dealer.addToHand(new Card(12, Card.Suit.CLUBS));
		
		dealer.deck.getTopCard();
		dealer.deck.getTopCard();
			
		player.reset();
		dealer.reset();
		
		
		assertTrue(player.hand.size() == 0);
		assertTrue(dealer.hand.size() == 0);
		assertTrue(dealer.deck.topCard == 0);
		
		
	}
	
	
	/* tests that the correct requested segment of a card array is returned*/
	@Test public void testCopyOfRange() {
		CardDeck testDeck = new CardDeck();
		
		Card[] copy = Card.copyOfRange(testDeck.deck, 0, 20);
		
		for(int i = 0; i <= 20; i++) {
			System.out.print(i + "\n");
			assertTrue(copy[i].value == testDeck.deck[i].value &&
					   copy[i].suit == testDeck.deck[i].suit); 
		}
		
		
		
	}
	
	/*Tests shuffle method for randomness */
	@Test public void testShuffle() {
		CardDeck shuffledDeck = new CardDeck();
		CardDeck unshuffledDeck = new CardDeck();
		shuffledDeck.shuffle();	
		int sameCards = 0;
		for(int i = 0; i < 52; i ++) {
			if(shuffledDeck.deck[i].suit == unshuffledDeck.deck[i].suit &&
					shuffledDeck.deck[i].value == unshuffledDeck.deck[i].value) {
				sameCards ++;
				
			}
		}	
		assertTrue(sameCards < 5);
		
		
	}
	
	/*Tests card toString */
	@Test public void testCardToString() {
		Card card1 = new Card(3, Card.Suit.SPADES);
		Card card2 = new Card(13, Card.Suit.HEARTS);
		Card card3 = new Card(1, Card.Suit.CLUBS);
		Card card4 = new Card(11, Card.Suit.DIAMONDS);
		CardDeck deck = new CardDeck();
		
		for(int i = 0; i < 52; i ++) {
			assertTrue(deck.deck[i].toString().length() >= 10); 
		}
		assertTrue(card1.toString().equals("Three of spades"));
		assertTrue(card2.toString().equals("King of hearts"));
		assertTrue(card3.toString().equals("Ace of clubs"));
		assertTrue(card4.toString().equals("Jack of diamonds"));
	}
	
	/*
	 *Tests addToHand method that takes an array as well as the
	 *handToString method.  
	 */
	
	@Test public void testHandToString() {
		
		Player player = new Player();
		Card card1 = new Card(1, Card.Suit.SPADES);
		Card card2 = new Card(2, Card.Suit.HEARTS);
		Card card3 = new Card(12, Card.Suit.CLUBS);
		Card card4 = new Card(5, Card.Suit.DIAMONDS);
		Card[] hand = {card1, card2, card3, card4};
		
		player.addToHand(hand);
	
		assertTrue(player.handToString().equals("Ace of spades, Two of hearts, Queen of clubs, Five of diamonds\n"));
	}
	
	/*tests addToHand single card and ProcessHandValue methods, 
	 * including the instance of several aces
	 **/
	@Test public void testAddToHandandProcessHand() {
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();	
		Dealer dealer = new Dealer();
		
		player1.addToHand(new Card(3, Card.Suit.SPADES));
		player1.addToHand(new Card(10, Card.Suit.SPADES));
		player1.addToHand(new Card(13, Card.Suit.DIAMONDS));
		
		player2.addToHand(new Card(1, Card.Suit.HEARTS));
		player2.addToHand(new Card(10, Card.Suit.HEARTS));
		player2.addToHand(new Card(13, Card.Suit.CLUBS));
		
		player3.addToHand(new Card(1, Card.Suit.HEARTS));
		player3.addToHand(new Card(1, Card.Suit.DIAMONDS));
		player3.addToHand(new Card(1, Card.Suit.CLUBS));
		player3.addToHand(new Card(1, Card.Suit.SPADES));
		
		dealer.addToHand(new Card(1, Card.Suit.HEARTS));
		dealer.addToHand(new Card(7, Card.Suit.HEARTS));
		dealer.addToHand(new Card(13, Card.Suit.CLUBS));
		
		
		assertTrue(player1.processHandValue() == 23);
		assertTrue(player2.processHandValue() == 21);
		assertTrue(player3.processHandValue() == 14);
		assertTrue(dealer.processHandValue() == 18);
		
		
	}
	
	
	/*Tests dealer's hitStayBust method*/
	@Test public void hitStayBustDealer() {
		Dealer dealer1 = new Dealer();
		Dealer dealer2 = new Dealer();
		Dealer dealer3 = new Dealer();
		Dealer dealer4 = new Dealer();
		
		
		dealer1.addToHand(new Card(1, Card.Suit.SPADES));
		dealer1.addToHand(new Card(10, Card.Suit.SPADES));
		dealer1.addToHand(new Card(13, Card.Suit.DIAMONDS));
		
		dealer2.addToHand(new Card(3, Card.Suit.SPADES));
		dealer2.addToHand(new Card(10, Card.Suit.SPADES));
		dealer2.addToHand(new Card(13, Card.Suit.DIAMONDS));
		
		dealer3.addToHand(new Card(1, Card.Suit.SPADES));
		dealer3.addToHand(new Card(6, Card.Suit.SPADES));
		dealer3.addToHand(new Card(13, Card.Suit.DIAMONDS));
		
		dealer4.addToHand(new Card(1, Card.Suit.SPADES));
		dealer4.addToHand(new Card(5, Card.Suit.SPADES));
		dealer4.addToHand(new Card(13, Card.Suit.DIAMONDS));
		
		assertTrue(dealer1.hitStayBust() == STAY);
		assertTrue(dealer2.hitStayBust() == BUST);
		assertTrue(dealer3.hitStayBust() == STAY);
		assertTrue(dealer4.hitStayBust() == HIT);
		
		
	}
	
	
	
	/*
	 * Test determines whether the main method waits for
	 * correct input when incorrect input is received. 
	 */
	@Test public void incorrectStartGameInput(){
		try {
			System.setIn(new FileInputStream("src/tests/incorrectStartGameInput.txt"));
			try {
				System.setOut(new PrintStream(new File("src/tests/incorrectStartGameOutput.txt")));
				String[] args = {};	
				BlackJack.main(args);
				
								
				try {
					Scanner scanner = new Scanner(new File("src/tests/incorrectStartGameOutput.txt"));
					String currLine = "";
					int i = 0;
					
					while(i < 6 && (currLine = scanner.nextLine()) != null) {
						if(i == 0) {
							assertTrue(currLine.equals( "Welcome to BlackJack. Press 1 to start a new game. Press 2 to exit."));
						}else if(i < 5) {
							
							assertTrue(currLine.equals( "Invalid selection, please choose 1 or 2."));
							
						} else {
							assertTrue(currLine.equals( "Goodbye."));
						}
						
						i++;
					}
					scanner.close();
					
				} catch (Exception e){
						e.printStackTrace();
						
				}
				
				
				System.setOut(stdout);
			} catch (Exception e){
				
				e.printStackTrace();
			}
		
			System.setIn(System.in);
		} catch(Exception fileNotFound) {
			fileNotFound.printStackTrace();
			
			
		}
 	}
	
	/*
	 * Test checks that correct output takes place after correct
	 * input is received in the main method, then checks that player hitStayBust
	 * method waits for correct input when receiving incorrect output.
	 * 
	 */
	@Test public void incorrectHitStayBustInput(){
		try {
			System.setIn(new FileInputStream("src/tests/incorrectHitStayBustInput.txt"));
			try {
				System.setOut(new PrintStream(new File("src/tests/incorrectHitStayBustOutput.txt")));
				String[] args = {};	
				BlackJack.main(args);
				
								
				try {
					Scanner scanner = new Scanner(new File("src/tests/incorrectHitStayBustOutput.txt"));
					String currLine = "";
					int i = 0;
					
					while(i < 12 && (currLine = scanner.nextLine()) != null) {
						switch(i) {
						case 0:
							assertTrue(currLine.equals("Welcome to BlackJack. Press 1 to start a new game. Press 2 to exit."));
							break;
						case 1:
							assertTrue(currLine.equals("Starting Game..."));
							break;
						case 2:
							assertTrue(currLine.equals("Shuffling cards..."));
							break;
						case 3:
							assertTrue(currLine.equals("Dealing hands..."));
							break;
						case 4:			
							
							assertTrue(currLine.substring(currLine.length() - 15,currLine.length()).equals("dealt to Player"));
							break;
						case 5:
							assertTrue(currLine.substring(currLine.length() - 15,currLine.length()).equals("dealt to Player"));
							break;
						case 6:
							assertTrue(currLine.equals("Enter 1 to hit, or 2 to stay."));
							break;
						case 7:
							assertTrue(currLine.equals("Invalid choice. Please enter 1 or 2."));
							break;
						case 8:
							assertTrue(currLine.equals("Invalid choice. Please enter 1 or 2."));
							break;
						case 9:
							assertTrue(currLine.equals("Invalid choice. Please enter 1 or 2."));
							break;
						case 10:
							assertTrue(currLine.equals("Invalid choice. Please enter 1 or 2."));
							break;
						case 11:
							assertTrue(currLine.substring(currLine.length() - 15,currLine.length()).equals("dealt to Player"));
							break;
						}
						
						i++;
					}
					scanner.close();
					
				} catch (Exception e){
						e.printStackTrace();
						
				}
				
				
				System.setOut(stdout);
			} catch (Exception e){
				
				e.printStackTrace();
			}
		
			System.setIn(System.in);
		} catch(Exception fileNotFound) {
			fileNotFound.printStackTrace();
			
			
		}
 	}
	
	
	/* 
	 * Test checks determine winner method including double aces;
	 */
	@Test public void determineWinner() {
		Player player = new Player();
		Dealer dealer = new Dealer();
		
		player.addToHand(new Card(1, Card.Suit.SPADES));
		player.addToHand(new Card(10, Card.Suit.SPADES));
		player.addToHand(new Card(13, Card.Suit.DIAMONDS));
		
		dealer.addToHand(new Card(1, Card.Suit.HEARTS));
		dealer.addToHand(new Card(11, Card.Suit.HEARTS));
		dealer.addToHand(new Card(12, Card.Suit.CLUBS));
		
		assertTrue(Game.determineWinner(player,dealer).equals("Tie.\n"));
		player.reset();
		dealer.reset();
		
		player.addToHand(new Card(2, Card.Suit.SPADES));
		player.addToHand(new Card(3, Card.Suit.SPADES));
		player.addToHand(new Card(9, Card.Suit.DIAMONDS));
		
		dealer.addToHand(new Card(3, Card.Suit.HEARTS));
		dealer.addToHand(new Card(6, Card.Suit.HEARTS));
		dealer.addToHand(new Card(5, Card.Suit.CLUBS));
		
		
		assertTrue(Game.determineWinner(player,dealer).equals("Tie.\n"));
		player.reset();
		dealer.reset();
		
		player.addToHand(new Card(1, Card.Suit.SPADES));
		player.addToHand(new Card(13, Card.Suit.SPADES));
		player.addToHand(new Card(5, Card.Suit.DIAMONDS));
		
		
		dealer.addToHand(new Card(3, Card.Suit.HEARTS));
		dealer.addToHand(new Card(5, Card.Suit.HEARTS));
		dealer.addToHand(new Card(13, Card.Suit.CLUBS));
	
		assertTrue(Game.determineWinner(player,dealer).equals("Dealer wins!\n"));
		
		player.reset();
		dealer.reset();
		
		player.addToHand(new Card(1, Card.Suit.SPADES));
		player.addToHand(new Card(1, Card.Suit.HEARTS));
		player.addToHand(new Card(9, Card.Suit.DIAMONDS));
		
		
		dealer.addToHand(new Card(5, Card.Suit.SPADES));
		dealer.addToHand(new Card(5, Card.Suit.HEARTS));
		dealer.addToHand(new Card(13, Card.Suit.CLUBS));
	
		assertTrue(Game.determineWinner(player,dealer).equals("Player wins!\n"));
		
		
	}
	
	/*
	 * Test checks if program completes when the player chooses to play a second hand.
	 * Program does not check whether the intermediate steps are correct. 
	 */
	@Test public void playAnotherHand() {
		try {
			System.setIn(new FileInputStream("src/tests/playAnotherHandInput.txt"));
			try {
				System.setOut(new PrintStream(new File("src/tests/playAnotherHandOutput.txt")));
				String[] args = {};	
				BlackJack.main(args);
				
								
				try {
					Scanner scanner = new Scanner(new File("src/tests/playAnotherHandOutput.txt"));
					String currLine = "";
					boolean done = false;
					
					while( !done && (currLine = scanner.nextLine()) != null) {
						if(currLine.equals(("Goodbye."))) {
							done = true;
						}
						
					}
					scanner.close();
					
				} catch (Exception e){
						e.printStackTrace();
						
				}
				
				
				System.setOut(stdout);
			} catch (Exception e){
				
				e.printStackTrace();
			}
		
			System.setIn(System.in);
		} catch(Exception fileNotFound) {
			fileNotFound.printStackTrace();
			
			
		}
	}
	

}
