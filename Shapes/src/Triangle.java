import java.awt.Color;
import java.awt.Graphics;

public class Triangle extends Shape{
	private int width;
	private double rotation;
	
	public Triangle(int x, int y, int width, double rotation, Color color) {
		super(x,y,color);
		this.width = width;
		this.rotation = rotation;
	}

	public void draw(Graphics g) {
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		
		for(int i = 0; i < 3; i++) {
			xPoints[i] = (int) (getX()+getWidth()*Math.cos(toRadians(getRotation()+i*120)));
			yPoints[i] = (int) (getY()+getWidth()*Math.sin(toRadians(getRotation()+i*120)));
		}
		g.setColor(getColor());
		g.fillPolygon(xPoints, yPoints, 3);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public double toRadians(double angle){
		return angle * (Math.PI / 180);
	}
}
