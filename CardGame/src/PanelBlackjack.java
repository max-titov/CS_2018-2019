

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelBlackjack extends JPanel {

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	public static final Color BACKGROUND = new Color(0, 128, 43);

	public static final int CARDWIDTH = 500;
	public static final int CARDHEIGHT = 726;

	private BufferedImage image;
	private static Graphics buffer;

	private static BlackjackGameManager manager;
	
	private int numberOfPlayers;

	public PanelBlackjack() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		buffer = image.getGraphics();
		numberOfPlayers = 2;
		manager = new BlackjackGameManager(numberOfPlayers);

		drawBackground();
		manager.playRound();
	}

	public int random(int min, int max) { //inclusive
		return (int) (Math.random() * (max+1 - min) + min);
	}

	public static void draw() {
		drawBackground();
		ArrayList<BlackjackPlayer> players = manager.getPlayers();
		BlackjackPlayer dealer = manager.getDealer();
		
		for(int i = 0; i < players.size(); i++) { // iterates through all players
			for(int c = 0; c < players.get(i).getHand().size(); c++) {// iterates through all cards 
				drawCard(players.get(i).getHand().get(c),50+i*200+c*25,500+c*25, 0.2);
			}
		}
	}
	
	public static void drawCard(Card card, int x, int y, double size) {
		try {
			BufferedImage cardImage = ImageIO.read(new File("src/card_images/" + card.getSimple() + ".png"));
			double scalar = 1 / size;
			buffer.drawImage(cardImage, x, y, (int) (CARDWIDTH / scalar), (int) (CARDHEIGHT / scalar), null);
		} catch (IOException ex) {
			System.out.println("could not find image: " + card.getSimple() + ".png");
		}
	}


	public static void drawBackground() {
		buffer.setColor(BACKGROUND);
		buffer.fillRect(0, 0, WIDTH, HEIGHT);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
}
