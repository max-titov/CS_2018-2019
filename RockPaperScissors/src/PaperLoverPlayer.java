public class PaperLoverPlayer extends GamePlayer {

	/**
	 * Constructs a new <code>RockLoverPlayer</code> with a name of <code>playerName</code>
	 * @param playerName the name of the player
	 */
	public PaperLoverPlayer(String playerName) {
		super(playerName);
	}

	@Override
	/**
	 * The player will always choose <code>ROCK</code>
	 */
	public void makeChoice() {
		setChoice(GamePlayer.PAPER);

	}

}
