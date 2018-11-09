import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Card> hand;

	// graphics

	private int x;
	private int y;

	public Player(String name, int x, int y) {
		this.name = name;
		this.hand = new ArrayList<Card>();

		// graphics
		this.x = x;
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
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
		return name + ": " + getHandAsString();
	}

	// graphics

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}