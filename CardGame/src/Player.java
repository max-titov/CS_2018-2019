import java.util.ArrayList;

public class Player {
	private String playerName;
	private ArrayList<Card> hand;

	public Player(String name) {
		playerName = name;
		hand = new ArrayList<Card>();
	}

	public String getName() {
		return playerName;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public String getHandAsString() {
		String str = "";
		for (Card card : hand)
			str += card + ", ";
		if (str.length() >= 2)
			str.substring(0, str.length() - 2); // removes ", " at the end
		return str;
	}

	public void add(Card card) {
		hand.add(card);
	}

	public boolean contains(Card card) {
		return hand.contains(card);
	}
	
	public void clearHand() {
		hand.clear();
	}

	public void remove(Card card) {
		hand.remove(card);
	}

	@Override
	public String toString() {
		return playerName + ": " + getHandAsString();
	}

}
