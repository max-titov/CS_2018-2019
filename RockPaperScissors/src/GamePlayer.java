
public abstract class GamePlayer {
	
	public static final int ROCK = 1;
	public static final int PAPER = 2;
	public static final int SCISSORS = 3;
	
	private String name;
	private int choice;
	private int wins;
	
	public GamePlayer(String playerName) {
		name = playerName;
		wins = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getChoice() {
		return choice;
	}
	
	public String getChoiceName() {
		if(choice == 1)
			return "Rock";
		else if(choice == 2)
			return "Paper";
		else if(choice == 3)
			return "Scissors";
		else	
			return "No Choice Selected";
	}
	
	public void setChoice(int playerChoice) {
		choice = playerChoice;
	}
	
	public abstract void makeChoice();
	
	public void win() {
		wins++;
	}
	
	public int getWins() {
		return wins;
	}
	
	public String statistics() {
		return "Player "+getName()+" has " + getWins() + " wins.";
	}
	
}
