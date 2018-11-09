
import java.util.Scanner;


public class RockPaperScissors {

	public static final int HUMAN = 1;
	public static final int COMPUTER = 2;
	public static final int ROCK_LOVER = 3;
	public static final int PAPER_LOVER = 4;
	public static final int SCISSORS_LOVER = 5;

	/** {@value #DEFAULT_WINNING_SCORE} the number of wins to win the game*/
	public static final int DEFAULT_WINNING_SCORE = 2;
	
	private static GamePlayer player1;
	private static  GamePlayer player2;
	
	private static int rounds;

	public static void main(String[] args) {
		
		rounds = 1;

		player1 = choosePlayerType();
		player2 = choosePlayerType();

		//int winningScore = getWinningScore();		// Used in Extension 1

		playRound();
	}
	
	public static void playRound() {
		System.out.println("\nROUND: "+rounds+"\n");
		player1.makeChoice();
		player2.makeChoice();

		showChoices(player1, player2);

		GamePlayer winner = compareChoices(player1, player2);

		printWinner(winner);
		printRecord(player1);
		printRecord(player2);
		
		if(winner.getWins() < getWinningScore()) { //if the player who won the round has not won the game yet
			rounds++;
			playRound(); //play another round  recursion!!!
		}
		else {
			printEndGameMessage(winner);
		}
	}



	/**
	 * Asks for the number of wins to win the game.  If the value is less than or equal
	 * to zero, then, by default, the winning score will be {@value #DEFAULT_WINNING_SCORE}
	 * @return the number of wins to win the game
	 */
	private static int getWinningScore() {

		return DEFAULT_WINNING_SCORE;
	}

	/**
	 * Prints the record of the given <code>Player</code>
	 * @param player the player of the game
	 */
	private static void printRecord(GamePlayer player) {
		System.out.println(player.statistics());

	}

	/**
	 * Prints the winner of the round
	 * @param winner the <code>GamePlayer</code> who is the winner of the round
	 */
	private static void printWinner(GamePlayer winner) {
		if (winner == null) {
			System.out.println("Tie");
		} else {
			System.out.println(winner.getName() + " wins!!");
			winner.win();
		}
		System.out.println();
	}

	/**
	 * Compares and returns who wins the round.  
	 * @param player1 a <code>GamePlayer</code>
	 * @param player2 a <code>GamePlayer</code>
	 * @return returns the <code>GamePlayer</code> who wins the game, <code>null</code> if there is a tie
	 */
	private static GamePlayer compareChoices(GamePlayer player1, GamePlayer player2) {
		int choice1 = player1.getChoice();
		int choice2 = player2.getChoice();
		if(choice1 == 1 && choice2 == 2 || // rock vs paper
				choice1 == 2 && choice2 == 3 || // paper vs scissors
				choice1 == 3 && choice2 == 1) { // scissors vs rock 
			return player2; // player 2 wins
		}else if(choice2 == 1 && choice1 == 2 || // rock vs paper
				choice2 == 2 && choice1 == 3 || // paper vs scissors
				choice2 == 3 && choice1 == 1) { // scissors vs rock
			return player1; // player 1 wins
		}
		return null;
	}

	/**
	 * Prints the choices for both players
	 * @param player1 a player
	 * @param player2 a player
	 */
	private static void showChoices(GamePlayer player1, GamePlayer player2) {

		System.out.println();
		System.out.println(player1.getName() + ": " + player1.getChoiceName() + "   vs.  " + player2.getName() + ": " + player2.getChoiceName());
		System.out.println();
	}

	/**
	 * Prompts the choices and returns the types of players to play the game
	 * @return the type of player to play the game
	 */
	private static GamePlayer choosePlayerType() {
		Scanner input = new Scanner(System.in);
		int type;
		String name = "";
		GamePlayer player;

		System.out.println();
		System.out.println("Choose a type of player:");
		System.out.println("\t1. Human");
		System.out.println("\t2. Computer");
		System.out.println("\t3. Rock Lover");
		System.out.println("\t4. Paper Lover");
		System.out.println("\t5. Scissors Lover");
		System.out.print("Make your choice: ");

		type = input.nextInt();


		if (type == HUMAN) {
			input.nextLine();	// this line of code is needed to go to the carriage return after the integer choice is chosen above
			System.out.print("What is your name: ");
			name = input.nextLine();
			player = new HumanPlayer(name);
		} else if (type == COMPUTER ){
			player = new ComputerPlayer("Skynet");			// You may change this name
		} else if (type == ROCK_LOVER ) {
			player = new RockLoverPlayer("Bart Simpson");	// You may change this name
		} else if (type == PAPER_LOVER ) {
			player = new PaperLoverPlayer("Dog");
		} else {
			player = new ScissorsLoverPlayer("Snip Snip");
		}

		return player;


	}

	/**
	 * Prints a message when the game is over.
	 */
	private static void printEndGameMessage(GamePlayer winner) {
		System.out.print("\nPlayer "+ winner.getName() +" wins the game!");
	}


}