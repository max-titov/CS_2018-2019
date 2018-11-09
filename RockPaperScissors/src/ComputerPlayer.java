import java.util.Scanner;

public class ComputerPlayer extends GamePlayer{
	
	public ComputerPlayer(String playerName) {
		super(playerName);
	}
	
	@Override
	public void makeChoice() {
		int choice = (int)(Math.random()*3 + 1);
		this.setChoice(choice);
		
	}
}
