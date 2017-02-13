package blackjack;
import java.util.Scanner;

public class BlackJack {
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Game game = new Game();
		boolean selectionMade = false;
		String choice = "";
		
		System.out.print("Welcome to BlackJack. Press 1 to start a new game. Press 2 to exit.\n");
		
		while(!selectionMade){
			
			choice = scanner.nextLine();
			
			if((choice).matches("1") ) {
				
				game.startGame(scanner);
				selectionMade = true;
				scanner.close();
	
			} else if((choice).matches("2")) {
				
				System.out.print("Goodbye.\n");
				selectionMade = true;
				scanner.close();
		
			} else {
				System.out.print("Invalid selection, please choose 1 or 2.\n");
			}
			
		}
		
		scanner.close();
		return;
		
	}
	
	

}
