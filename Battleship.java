import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Battleship {
	private final static int ARRAY_SIZE = 50;
	private final static int maxGuesses = 25;
	private static int numOfGuesses = 25;
	private static ArrayList<Integer> battleField = new ArrayList<Integer>();
	static Scanner scnr = new Scanner(System.in);
	static Random rand = new Random();
	static Integer userGuess;
	static boolean shipOneSunk;
	static boolean shipTwoSunk;
	static boolean shipThreeSunk;
	
	public static void main(String[] args) { // Calls initial set of methods to start the game, then enters an infinite loop to continue until the user decides to quit.
		welcomeMessage();

		placeShips();
		
		gameStart(battleField);
		
		while (endingMessage() > 0) {
			
			resetBattleField();
			
			setFirstShip(battleField);
			
			setSecondShip(battleField);
			
			setThirdShip(battleField);
			
			System.out.print("\n");
			
			gameStart(battleField); }
	}

		
	public static void placeShips() { // Fills the "battlefield" with zeros and then calls individual methods to place the ships.
		
		for (int i = 0; i < ARRAY_SIZE-13; i++) { 
			battleField.add(0);
		}
				
		setFirstShip(battleField);
		
		setSecondShip(battleField);
		
		setThirdShip(battleField);
	}
	

	
	public static ArrayList<Integer> setFirstShip(ArrayList<Integer> emptyField) { // Places the first ship inside the battlefield
		
		int shipOneStart = rand.nextInt(16);
		battleField.add(shipOneStart, 1);
		battleField.add(shipOneStart + 1, 1);
		
		return emptyField;
	}
	
	
	public static ArrayList<Integer> setSecondShip(ArrayList<Integer> emptyField) { // Places the second ship inside the battlefield
		
		int shipTwoStart = rand.nextInt(32);
		// int shipOneReference = emptyField.indexOf(1); Irrelevant code
		
		for (int i = 0; i < 5; i++) {
			battleField.add(shipTwoStart + i, 2);
		}
		return emptyField;
	}
	
	public static ArrayList<Integer> setThirdShip(ArrayList<Integer> emptyField) { // Places the third ship inside the battlefield
		
		int shipThreeStart = rand.nextInt(31);
		
		
		for (int i = 0; i < 6; i++) {
			battleField.add(shipThreeStart + i, 3);
		}
		
		return emptyField;
	}
	
	
	public static void gameStart(ArrayList<Integer> filledBattlefield) {
		
		while (numOfGuesses > 0) {
		
			System.out.print("Guess a location: ");
			userGuess = scnr.nextInt();
			userGuess = checkBounds(userGuess);
			
			
				if (battleField.get(userGuess.intValue()) == 1) {
					System.out.println("Hit!");
					battleField.set(userGuess.intValue(), 0); 
				
					if (!battleField.contains(1)) {
						System.out.println("Congrats you sunk the length 2 ship!");
						shipOneSunk = true;
					}
				}
			
				else if (battleField.get(userGuess.intValue()) == 2) {
					System.out.println("Hit!");
					battleField.set(userGuess.intValue(), 0);
				
					if (!battleField.contains(2)) {
						System.out.println("Congrats you sunk the length 5 ship!");
						shipTwoSunk = true;
					}
				}
			
				else if (battleField.get(userGuess.intValue()) == 3) {
					System.out.println("Hit!");
					battleField.set(userGuess.intValue(), 0);
				
					if (!battleField.contains(3)) {
						System.out.println("Congrats you sunk the length 6 ship!");
						shipThreeSunk = true;
					}
				}
			
				else if (battleField.get(userGuess.intValue()) == 0) {
					System.out.println("Miss!"); }
			
				numOfGuesses--; 
				
				if (winCondition()) {
					System.out.println("\nCongrats you win!");
					break;
					}
			
		}
		
		if (!winCondition()) {
			System.out.println("\nYou ran out of guesses, try again!");
		}
		
		
		}
	
	
	public static void resetBattleField() { // Resets the battlefield so that the ships can be placed again.
		
		battleField.clear();
		
		for (int i = 0; i < ARRAY_SIZE; i++) { // Fills the "field" with Integers so that the ships can be placed
			battleField.add(0);
		}
		
		numOfGuesses = maxGuesses;
	}

	
	
	public static int checkBounds(int currentGuess) { // Checks the bounds for the user input and ensures that they're within the correct range.
		
		while ((currentGuess < -1 || currentGuess > 49)) {
			System.out.println("Please stay within the bounds of the game");
			System.out.println("Guess a location: ");
			currentGuess = scnr.nextInt();
		}
		return currentGuess;
	}

	
	public static boolean winCondition() { // Method checks to see if all the ships are sunk, if true then the game ends with a victory or with a lose.
		if (shipOneSunk & shipTwoSunk & shipThreeSunk) {
			return true;
		}
		else 
			return false;
	}
	
	
	public static void welcomeMessage() { // Displays a welcome message and explains the rules to the user.
		
		System.out.println("Welcome to Gus Franzen's Battleship game!");
		System.out.println("These are the rules: \n\n1. The battlefield has a length of 0-49, so choose target locations in this range.\n"
				+ "2. There are three ships, with a length of 2, 5 & 6.\n3. You have 25 guesses until you lose.");
		System.out.println("Good luck and have fun!\n\n");
	}


	public static int endingMessage() { // Method asks the player if they want to play again, returns an int value that checks whether to end the program or not.
		
		System.out.println("Would you like to play again? Type any number to continue (-1 to quit): ");
		int userInput = scnr.nextInt();
		
		if (userInput < 0) {
			System.out.println("Thank you for playing!"); }
		
		return userInput;
	}
	

}



