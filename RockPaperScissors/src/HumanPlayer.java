import java.util.Scanner;

public class HumanPlayer extends GamePlayer{
	
	public HumanPlayer(String playerName) {
		super(playerName);
	}
	
	@Override
	public void makeChoice() {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		boolean madeChoice = false;
		while(madeChoice == false) {
			System.out.println("Player "+getName()+", make your choice:\n1) Rock\n2) Paper\n3) Scissors");
			choice = input.nextInt();
			if(choice == 1 || choice == 2 || choice == 3) {
				madeChoice = true;
			}else {
				System.out.println("The choice you made is not one of the options, please try again");
			}
		}
		this.setChoice(choice);
		
	}
}
