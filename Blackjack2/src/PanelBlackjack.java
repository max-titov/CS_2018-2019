
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PanelBlackjack extends JPanel{

	public static final int WIDTH = 1536; //1536
	public static final int HEIGHT = 864; //864
	public static final Color BACKGROUND = new Color(0, 128, 43);

	private final int delayLength = 50;

	private BufferedImage image;

	private BlackjackGameManager manager;

	private int numberOfPlayers;

	private JPanel bottomPanel;
	private JLabel label;
	private JTextField input;
	private JButton enterButton, hitButton, standButton, doubleDownButton, surrenderButton;

	public PanelBlackjack() {
//		addKeyListener(new Key());
//		setFocusable(true);
		
		setLayout(new BorderLayout());

		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		label = new JLabel("Player");

		enterButton = new JButton(" ENTER ");
		enterButton.addActionListener(new EnterListener());

		input = new JTextField("", 10);

		hitButton = new JButton("    Hit    ");
		hitButton.addActionListener(new HitListener());

		standButton = new JButton("   Stand   ");
		standButton.addActionListener(new StandListener());

		doubleDownButton = new JButton("Double Down");
		doubleDownButton.addActionListener(new DoubleDownListener());

		surrenderButton = new JButton(" Surrender ");
		surrenderButton.addActionListener(new SurrenderListener());

		bottomPanel.add(label);
		bottomPanel.add(input);
		bottomPanel.add(enterButton);
		bottomPanel.add(Box.createRigidArea(new Dimension(40, 0)));
		bottomPanel.add(hitButton);
		bottomPanel.add(standButton);
		bottomPanel.add(doubleDownButton);
		bottomPanel.add(surrenderButton);

		add(bottomPanel, BorderLayout.SOUTH);

		numberOfPlayers = 2;
		// initialize manager
		manager = new BlackjackGameManager(numberOfPlayers, this);

		// set up graphics
		getPanelGraphics();

		// starting game thread
		Thread gameThread = new Thread(new GameRunnable());
		gameThread.start();
	}

	public void setPlayerLabel(String name) {
		label.setText("Player: " + name);
	}
	
//	private class Key extends KeyAdapter {
//		public void keyTyped(KeyEvent e) {
//			System.out.println(e.getKeyCode());
//		}
//		public void keyPressed(KeyEvent e) {
//			System.out.println(e.getKeyCode());
//			if (e.getKeyCode() == 10) {
//				System.out.println("Enter");
//				manager.setEnterPressed(true);
//				manager.setInputString(input.getText());
//				input.setText("");
//			}
//		}
//		public void keyReleased(KeyEvent e) {
//
//		}
//	}

	private class EnterListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Enter");
			manager.setEnterPressed(true);
			manager.setInputString(input.getText());
			input.setText("");
		}
	}

	private class HitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Hit");
			manager.setCurrentAction(BlackjackGameManager.HIT);
			manager.setNewAction(true);
		}
	}

	private class StandListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Stand");
			manager.setCurrentAction(BlackjackGameManager.STAND);
			manager.setNewAction(true);
		}
	}

	private class DoubleDownListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Double Down");
			manager.setCurrentAction(BlackjackGameManager.DOUBLE_DOWN);
			manager.setNewAction(true);
		}
	}

	private class SurrenderListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Surrender");
			manager.setCurrentAction(BlackjackGameManager.SURRENDER);
			manager.setNewAction(true);
		}
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	private Graphics getPanelGraphics() {

		if (null == image) {
			image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		}
		Graphics graphics = image.getGraphics();
		graphics.setColor(BACKGROUND);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		return graphics;

	}

	public class GameRunnable implements Runnable {
		
		public void run() {
			manager.createPlayers();
			this.gameLoop();
		}
		public void gameLoop() {
			manager.playRound();
			try {
				Thread.sleep(10);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			this.gameLoop();
		}
	}

	public void updateGraphics() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					draw();
					try {
						Thread.sleep(delayLength);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw() {
		Graphics g = getPanelGraphics();
		try {
			BlackjackDealer dealer = manager.getDealer();
			dealer.setCardPositions();
			dealer.display(g);
		} catch (NullPointerException e) {
			
		}
		
		try {
			ArrayList<BlackjackPlayer> players = manager.getPlayers();
			for (int i = 0; i < players.size(); i++) { // iterates through all players
				players.get(i).setCardPositions();
				players.get(i).display(g);
			}
		} catch (NullPointerException e) {
			
		}

		repaint();
	}

}
