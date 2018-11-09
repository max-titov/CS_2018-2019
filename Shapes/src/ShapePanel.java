import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;

public class ShapePanel extends JPanel implements MouseListener {
	private static final Color BACKGROUND = new Color(0, 0, 0);

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;

	// ** Create variables for shape size and color
	private int shapeSize = 10;
	private Color shapeColor = Color.white;

	// **Create the reference for the array
	// ** the arraySize and the nextIndex

	private ArrayList<Shape> shapes;
	private int arraySize = 10;
	private int nextIndex = 0;

	private int frames;

	private boolean epilepsy;

	Color myColor;
	Timer t;

//************** Constructor  ***********************
	public ShapePanel() {

		myColor = BACKGROUND;
		// *************** Initialize the index and the array
		shapes = new ArrayList<Shape>();

		epilepsy = false;

		// **************************************************
		// timer to repaint every 20 nanoseconds
		// The listener for this is at the bottom
		t = new Timer(20, new Listener());
		t.start();
		addMouseListener(this);
	}

	public void paintComponent(Graphics g) {

		// Overwrite what was on the screen before
		// with a blank background
		if (epilepsy)
			g.setColor(randomColor());
		else
			g.setColor(BACKGROUND);
		g.fillRect(0, 0, getWidth(), getHeight());

		if (epilepsy)
			epilepsy(g);

		// ********** draw the shapes and the lines
		for (int i = 0; i < shapes.size(); i++) {
			for (int k = i + 1; k < shapes.size(); k++) {
				int x1 = shapes.get(i).getX();
				int y1 = shapes.get(i).getY();
				int x2 = shapes.get(k).getX();
				int y2 = shapes.get(k).getY();

				if (epilepsy)
					g.setColor(randomColor());
				else
					setColor(g, x1, y1, x2, y2);

				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(5));
				g2.drawLine(x1, y1, x2, y2);
			}

			if (shapes.get(i) instanceof Triangle)
				((Triangle) shapes.get(i)).setRotation(frames * 5);
			else if (shapes.get(i) instanceof Square)
				((Square) shapes.get(i)).setRotation(frames * 5);
			shapes.get(i).draw(g);
		}
		frames++;

	}

	private void epilepsy(Graphics g) {
		int centerX = ShapeArrayDriver.frame.getWidth() / 2;
		int centerY = ShapeArrayDriver.frame.getHeight() / 2;
		for (int i = 0; i < shapes.size(); i++) {
			double distance = distance(shapes.get(i).getX(), shapes.get(i).getY(), centerX, centerY);
			double difY = shapes.get(i).getY() - centerY;
			double difX = shapes.get(i).getX() - centerX;
			double angle = (Math.atan(difY / difX));
			if (difX < 0) { // atan can only be between -90 and 90, so if x is negative then the angle flips
				angle = (angle - Math.PI);
			}
			angle += 0.15;
			
			shapes.get(i).setX((int) (centerX + distance * Math.cos(angle)));
			shapes.get(i).setY((int) (centerY + distance * Math.sin(angle)));
		}

		for (int i = 0; i < 100; i++) {
			double x = random(0, ShapeArrayDriver.frame.getWidth());
			double y = random(0, ShapeArrayDriver.frame.getHeight());
			double width = random(0, 100);
			double height = random(0, 100);

			g.setColor(randomColor());
			g.fillRect((int) x, (int) y, (int) width, (int) height);
		}
	}

	private double random(double min, double max) {
		return Math.random() * (max - min) + min;
	}
 
	private void setColor(Graphics g, int x1, int y1, int x2, int y2) {
		int colorNum = (int) (distance(x1, y1, x2, y2) / 1000 * 255);
		if (colorNum > 255)
			colorNum = 255;
		g.setColor(new Color(255 - colorNum, 0, colorNum));
	}

	private double distance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	private Color randomColor() {
		return new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
	}

	// *********** Implement the methods for any interfaces

//**  The method below could be useful to test the mouse interaction
	void saySomething(String eventDescription, MouseEvent e) {
		System.out.println(eventDescription + " detected on " + e.getComponent().getClass().getName() + "." + "\n");
	}

	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			repaint();

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		saySomething("clicked", e);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		saySomething("entered", e);
		epilepsy = false;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		saySomething("exited", e);
		// shapes.clear();
		epilepsy = true;

	}

	@Override
	public void mousePressed(MouseEvent e) {
		saySomething("pressed", e);
		int x = e.getX();
		int y = e.getY();
		int random = (int) (Math.random() * 3);
		if (random == 0)
			shapes.add(new Square(x, y, shapeSize, 0, shapeColor));
		else if (random == 1)
			shapes.add(new Circle(x, y, shapeSize * 2, shapeColor));
		else
			shapes.add(new Triangle(x, y, shapeSize, 0, shapeColor));

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		saySomething("clicked", e);

	}

}