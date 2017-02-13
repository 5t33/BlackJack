package blackjack;

import java.util.Scanner;

public class Game {
	private final int HIT = 1;
	private final int STAY = 2;
	private final int BUST = 3;
	
	public Player player;
	public Dealer dealer;
	Scanner scanner;
	
	public Game() {
		player = new Player();
		dealer = new Dealer();
		
	}
	
	/*
	 * Constructor initiates the scanner and starts the game
	 * by calling the dealHands method.
	 */
	public void startGame(Scanner scanner) {	
		System.out.print("Starting Game...\n");
		this.scanner = scanner;	
		dealHands();
		return;
	}
	
	/*
	 * Method deals either 1 or two cards to the parameter
	 * player. If the input amount is not 2, the method deals 1
	 * by default.
	 */
	public void dealToPlayer(Player player, int amount) {
		if(amount == 2) {
			player.addToHand(dealer.dealTwo());
			processChoice(player);
		} else {
			player.addToHand(dealer.dealOne());
			processChoice(player);
		}
		
	}
	
	/*
	 * Method calls the hitStayBust method of the input player
	 * object then calls deal if hitStayBust returns hit, does
	 * nothing if hitStayBust returns stay. The method prints
	 * an appropriate message for each possibility. 
	 */
	public void processChoice(Player player) {
		int hitStayBust;
		if(player instanceof Dealer) {
			hitStayBust = ((Dealer) player).hitStayBust();
		} else {
			hitStayBust = player.hitStayBust(scanner);
		}
		
		if(hitStayBust == HIT) {
			dealToPlayer(player, 1);
			return;
		} else if(hitStayBust == STAY) {
			System.out.print(player + " stays.\n");
			return;
		} else if (hitStayBust == BUST) {
			System.out.print(player + " busts!\n");
			return;
		}
		
		return;
	}
	
	/* 
	 * Method calls reset method then deals two cards to each
	 * player in succession, then calls determine winner, 
	 * and finally requests another hand before returning.
	 */
	public void dealHands() {
		
		reset();
		System.out.print("Dealing hands...\n");
		dealToPlayer(player, 2);
		dealToPlayer(dealer, 2);
		System.out.print(determineWinner(player,dealer));
		requestAnotherHand();
		return;		
		
	}
	
	/*
	 * Method determines final winner or tie by calling processHandValue
	 * on each input player and printing out corresponding messages. 
	 */
	public static String determineWinner(Player player, Dealer dealer) {
		int playerHandVal = player.processHandValue();
		int dealerHandVal = dealer.processHandValue();
		
		boolean handsEqual = playerHandVal == dealerHandVal;
		boolean bothBust = playerHandVal > 21 &&
			    		   dealerHandVal > 21;
		boolean playerBust = playerHandVal > 21;
		boolean dealerBust = dealerHandVal > 21;
		boolean playerValGreater = playerHandVal > dealerHandVal;
			    		   
		if(handsEqual || bothBust) {
			return "Tie.\n";
		} else if((!playerBust && dealerBust) ||
				  (!playerBust && !dealerBust && playerValGreater)) {
			return "Player wins!\n";
		} else if((playerBust && !dealerBust) ||
				  (!playerBust && !dealerBust && !playerValGreater)) {
			return "Dealer wins!\n";
		} else {
			return "error";
		}
		

	}
	
	/* 
	 *Method requests another hand from the player, and calls deal
	 *hands if another hand is requested, otherwise it returns. 
	 */
	public void requestAnotherHand(){
		System.out.print("If you would like to play another hand, "
				+ "press 1. If you would like to exit, press 2.\n");
		boolean validChoice = false;
		String choice = "";
		while(!validChoice) {
			choice = scanner.nextLine();
			
			if(choice.matches("1")) {
				validChoice = true;
				dealHands();
			} else if(choice.matches("2")) {
				validChoice = true;
				System.out.print("Goodbye.\n");
			} else {
				System.out.print("Invalid number. Please enter 1 or 2.\n");
			}
			
		}
		
		return;
	}
	
	/*
	 * reset calls the reset method of both the player and the
	 * dealer.
	 */
	public void reset() {
		player.reset();
		dealer.reset();
		return;
	}
	
}
