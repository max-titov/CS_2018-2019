import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape{
	private int diameter;
	
	public Circle(int x, int y, int diameter, Color color) {
		super(x,y,color);
		this.diameter = diameter;
	}

	public void draw(Graphics g) {
		g.setColor(getColor());
		g.fillOval(getX()-diameter/2, getY()-diameter/2, diameter, diameter);
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}
	
	
}
