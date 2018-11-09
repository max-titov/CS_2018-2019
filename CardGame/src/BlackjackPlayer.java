
public class BlackjackPlayer extends Player {
	private boolean hitting; //whether the player is still asking for a hit
	
	public BlackjackPlayer(String name) {
		super(name);
		hitting = true;
	}

	public int getHandValue() { // need case when player has 2 Aces
		int sum1 = 0; // sum with Ace equaling 1
		int sum2 = 0; // sum with Ace equaling 11
		for (Card card : this.getHand()) {
			int faceValue = card.getFaceValue();
			if (faceValue == 1) { // Ace
				sum1 += 1;
				sum2 += 11;
			} else if (faceValue >= 11) { // Jack, Queen, King
				sum1 += 10;
				sum2 += 10;
			} else {
				sum1 += faceValue;
				sum2 += faceValue;
			}
		}
		if (sum2 > 21) { // if sum with Ace equaling 11 is a bust, return the other one
			return sum1;
		} else {
			return sum2;
		}
	}
	
	

	public boolean isHitting() {
		return hitting;
	}

	public void setHitting(boolean hitting) {
		this.hitting = hitting;
	}

	@Override
	public String toString() {
		return getName() + ": " + getHandValue() + " | Cards: " + getHand();
	}
}
