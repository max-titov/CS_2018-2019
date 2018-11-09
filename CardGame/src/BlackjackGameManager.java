import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackGameManager {
	public final int DEALER_VALUE = 17;
	public final int BLACKJACK = 21;
	
	private Deck deck;
	private ArrayList<BlackjackPlayer> players;
	private BlackjackPlayer dealer;

	public BlackjackGameManager(int playersNum) {
		deck = new Deck();
		deck.shuffle();
		
		dealer = new BlackjackPlayer("Dealer");

		players = new ArrayList<BlackjackPlayer>();
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < playersNum; i++) {
			System.out.println("Player " + (i + 1) + ": what is your name?");
			players.add(new BlackjackPlayer(scan.next()));
		}
	}

	

	public ArrayList<BlackjackPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<BlackjackPlayer> players) {
		this.players = players;
	}

	public BlackjackPlayer getDealer() {
		return dealer;
	}

	public void setDealer(BlackjackPlayer dealer) {
		this.dealer = dealer;
	}
	
	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}



	public void dealStartingHand() {
		dealer.clearHand();
		dealer.add(deck.draw());
		dealer.add(deck.draw());
		
		for (int i = 0; i < players.size(); i++) {
			players.get(i).clearHand();
			players.get(i).add(deck.draw());
			players.get(i).add(deck.draw());
			System.out.println(players.get(i));
		}
	}

	public void playRound() {
		dealStartingHand();
		
		for(int i = 0; i < players.size(); i++) {
			players.get(i).setHitting(true);
		}
		
		System.out.println(dealer.getName()+": "+dealer.getHand().get(0)+", ???\n");
		
		Scanner scan = new Scanner(System.in);

		while (playersStillHitting()) {
			for (int i = 0; i < players.size(); i++) {
				BlackjackPlayer player = players.get(i);
				if (player.isHitting()) {
					System.out.println(player);
					System.out.println("Would you like to hit or stand? (h/s)");
					if (scan.next().equals("h")) { //if hitting
						players.get(i).add(deck.draw());
						PanelBlackjack.draw();
						System.out.println(player + "\n");
					} else {
						players.get(i).setHitting(false);
					}
					
					if(player.getHandValue() > BLACKJACK) {
						System.out.println("Your hand is a bust :(\n");
						players.get(i).setHitting(false);
					}else if(player.getHandValue() == BLACKJACK) {
						//blackjack
					}
				}

			}
		}
		
		while(dealer.getHandValue()<DEALER_VALUE) {
			dealer.add(deck.draw());
		}
		System.out.println(dealer);
	}

	public boolean playersStillHitting() {
		boolean stillHitting = false;
		for (BlackjackPlayer player : players)
			stillHitting = stillHitting || player.isHitting();
		return stillHitting;
	}


}
