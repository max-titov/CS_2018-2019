import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackGameManager {
	public static final int DEALER_VALUE = 17;
	public static final int BLACKJACK = 21;

	private PanelBlackjack panel;
	private Deck deck;
	private ArrayList<BlackjackPlayer> players;
	private BlackjackDealer dealer;

	private Scanner scan;

	public BlackjackGameManager(int playersNum, PanelBlackjack panel) {
		scan = new Scanner(System.in);
		this.panel = panel;

		deck = new Deck();
		deck.shuffle();
//		ArrayList<Card> deck2 = deck.getDeck();
//		deck2.set(0, new Card(1, 1));
//		deck2.set(4, new Card(11, 2));
//		deck.setDeck(deck2);

		int xDealer = (int) (PanelBlackjack.WIDTH / 2.5);
		int yDealer = 70;
		dealer = new BlackjackDealer("Dealer", 1000, xDealer, yDealer);

		players = new ArrayList<BlackjackPlayer>();

		for (int i = playersNum; i > 0; i--) {
			System.out.println("Player " + (playersNum - i + 1) + ": what is your name?");
			int xPlayer = 50 + ((i - 1) * (1440 / playersNum));
			int yPlayer = 470;
			players.add(new BlackjackPlayer(scan.nextLine(), 100, xPlayer, yPlayer));
		}

	}

	public ArrayList<BlackjackPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<BlackjackPlayer> players) {
		this.players = players;
	}

	public BlackjackDealer getDealer() {
		return dealer;
	}

	public void setDealer(BlackjackDealer dealer) {
		this.dealer = dealer;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public void delay(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public void playRound() {

		System.out.println("Start round?");
		scan.nextLine();
		scan.nextLine();

		resetPlayers();

		placeBets();

		dealStartingHand();

		displayPlayerInfo();

		if (checkForDealerBlackjack() == false) { // if the dealer does not have blackjack
			checkForPlayerBlackjack(); // checks for player blackjacks

			playAllPlayers();
			playDealer();

			calculateTransactions();
		}

		dealer.setTotalMoneyTransaction(0);

		resetDeck();
	}

	private void resetPlayers() {
		// clear all hands
		dealer.clearHand();
		dealer.resetTransactions();
		for (int i = 0; i < players.size(); i++) {
			players.get(i).clearHand();
			players.get(i).resetTransactions();
			players.get(i).setPlayerStatus(BlackjackPlayer.NORMAL);
			players.get(i).setBet(0);
		}
		panel.updateGraphics();
	}

	private void placeBets() {
		for (int i = 0; i < players.size(); i++) {
			System.out.println("Player " + players.get(i).getName() + ": What is your bet (6 - 500)");
			String betString = scan.next();
			int bet;
			try {
				bet = Integer.parseInt(betString);
			} catch (NumberFormatException ex) {
				bet = 5;
			}
			if (bet < 6)
				bet = 6;
			else if (bet > 500)
				bet = 500;

			if (bet % 2 == 1) // making all bets even
				bet--;

			players.get(i).addBet(bet);
			panel.updateGraphics();
		}
	}

	private void dealStartingHand() {

		// draws 2 cards for each player and the dealer
		for (int c = 0; c < 2; c++) {
			dealer.add(deck.drawACard());
			if (c == 0) { // if first card
				// sets the first card to be face down
				dealer.setFaceUp(0, false);
			}
			panel.updateGraphics();
			delay(250);

			for (int i = 0; i < players.size(); i++) {
				players.get(i).add(deck.drawACard());
				panel.updateGraphics();
				delay(250);
			}
		}
	}

	private void displayPlayerInfo() {
		for (int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i));
		}

		System.out.println(dealer.getName() + ": ???, " + dealer.getHand().get(1) + "\n");
	}

	private boolean checkForDealerBlackjack() {
		if (dealer.getHandValue() == BlackjackGameManager.BLACKJACK) { // if the dealer has blackjack
			dealer.setFaceUp(0, true);
			System.out.println("Dealer has blackjack!!");
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getHandValue() != BlackjackGameManager.BLACKJACK) { // if the player does not have
																						// blackjack
					int bet = players.get(i).getBet();
					dealer.addMoney(bet); // dealer gets the bet
					players.get(i).addMoney(-1 * bet);
				}
			}
			panel.updateGraphics();
			return true;
		}
		return false;
	}

	private void checkForPlayerBlackjack() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getHandValue() == BlackjackGameManager.BLACKJACK) { // if the player has blackjack
				int bet = players.get(i).getBet();
				dealer.addMoney((int) (-1.5 * bet)); // dealer loses the bet * 1.5
				players.get(i).addMoney((int) (1.5 * bet)); // player gets the bet *1.5
				players.get(i).setPlayerStatus(BlackjackPlayer.BLACKJACK);
				System.out.println("Player " + players.get(i).getName() + " got blackjack!!");
			}
		}
		panel.updateGraphics();
	}

	private void playAllPlayers() { // FIX THE MEATHOD NAME
		for (int i = 0; i < players.size(); i++) {
			BlackjackPlayer player = players.get(i);
			if (player.getPlayerStatus() != BlackjackPlayer.BLACKJACK) { // if the player has blackjack he has already
																			// been payed
				String question = "Would you like to double down(d), surrender(s), hit(h), or stand(anything)?";
				boolean alreadyHit = false; // player can double down or surrender only if he has not hit already
				boolean playerFinished = false; // whether the player is finished with this round
				while (playerFinished == false) {
					System.out.println(player);

					System.out.println(question);
					String input = scan.next();
					if (input.equals("h")) { // if hitting
						players.get(i).add(deck.drawACard());
						panel.updateGraphics();

						System.out.println(player + "\n");
					} else if (input.equals("d") && !alreadyHit) { // if doubling down
						if (player.getTotalMoney() >= player.getBet() * 2) { // if the player has enough money
							players.get(i).addBet(player.getBet());
							players.get(i).add(deck.drawACard());
							panel.updateGraphics();
							players.get(i).setPlayerStatus(BlackjackPlayer.DOUBLE_DOWN);
							playerFinished = true;

							System.out.println(player + "\n");
						} else {
							System.out.println("You do not have enough money to double down");
						}
					} else if (input.equals("s") && !alreadyHit) { // surrender
						int bet = players.get(i).getBet();
						dealer.addMoney((int) (bet) / 2); // dealer gets half of the bet
						players.get(i).addMoney((int) (-0.5 * bet)); // player loses only half of original bet
						panel.updateGraphics();
						players.get(i).setPlayerStatus(BlackjackPlayer.SURRENDER);
						playerFinished = true;

						System.out.println(player + "\n");
					} else {
						playerFinished = true;
					}
					question = "Would you like to hit(h) or stand(anything)?";
					alreadyHit = true;

					if (player.getHandValue() > BLACKJACK) { // busted
						players.get(i).setPlayerStatus(BlackjackPlayer.BUST);
						System.out.println("Your hand is a bust :(\n");
						playerFinished = true;

						int bet = players.get(i).getBet();
						dealer.addMoney(bet); // dealer gets the bet
						players.get(i).addMoney(-1 * bet);

						panel.updateGraphics();
					}
				}
			}

		}
	}

	private void playDealer() {
		// sets the first card to be face up
		dealer.setFaceUp(0, true);

		panel.updateGraphics();
		delay(350);

		while (dealer.getHandValue() < DEALER_VALUE) {
			dealer.add(deck.drawACard());
			panel.updateGraphics();
			delay(350);
		}
		System.out.println(dealer);
	}

	private void calculateTransactions() {
		for (int i = 0; i < players.size(); i++) {
			int bet = players.get(i).getBet();
			if (players.get(i).getPlayerStatus() != BlackjackPlayer.BLACKJACK && // if the player did not get blackjack,
					players.get(i).getPlayerStatus() != BlackjackPlayer.BUST && // did not bust
					players.get(i).getPlayerStatus() != BlackjackPlayer.SURRENDER) { // and did not surrender

				if (players.get(i).getComparativeHandValue() > dealer.getComparativeHandValue()) { // player won
					dealer.addMoney(-1 * bet); // dealer loses money
					players.get(i).addMoney(bet); // bet added to player
				} else if (players.get(i).getComparativeHandValue() < dealer.getComparativeHandValue()) { // dealer won
					dealer.addMoney(bet); // dealer gets the bet
					players.get(i).addMoney(-1 * bet);
				}
			}
		}

		panel.updateGraphics();
	}

	private void resetDeck() {
		if (deck.getSize() < Deck.SIZE / 2) { // if half the deck is used
			deck = new Deck();
			deck.shuffle();
			System.out.println("Reset deck");
		}
	}

}