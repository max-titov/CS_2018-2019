
public class Rectangle {
	private int x1;
	private int y1;
	private int x2;
	private int y2;

	private int width;
	private int height;

	public Rectangle() {
		this.x1 = 0;
		this.y1 = 0;
		this.x2 = 0;
		this.y2 = 0;
		
		this.width = 0;
		this.height = 0;
	}
	
	public Rectangle(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		
//		if(x1<x2 && y1<y2) {
//			this.x1 = x1;
//			this.y1 = y2;
//			this.x2 = x2;
//			this.y2 = y1;
//		}

		this.width = dif(x2, x1);
		this.height = dif(y2, y1);
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int dif(int a, int b) {
		return Math.abs(a-b);
	}
	
	public int area() {
		return this.getHeight() * this.getWidth();
	}

	public int overlap(Rectangle other) {

		boolean overlapX1 = false;
		boolean overlapX2 = false;
		boolean overlapY1 = false;
		boolean overlapY2 = false;

		if (other.getX1() > this.getX1() && other.getX1() < this.getX2()) {
			overlapX1 = true;
		}
		if (other.getX2() > this.getX1() && other.getX2() < this.getX2()) {
			overlapX2 = true;
		}
		if (other.getY1() > this.getY1() && other.getY1() < this.getY2()) {
			overlapY1 = true;
		}
		if (other.getY2() > this.getY1() && other.getY2() < this.getY2()) {
			overlapY2 = true;
		}
		System.out.println(overlapX1);
		System.out.println(overlapX2);
		System.out.println(overlapY1);
		System.out.println(overlapY2);
		System.out.println();

		if (overlapX1 && overlapX2 && overlapY1 && overlapY2) { // completely overlap
			return other.getHeight() * other.getWidth();
		} else if ((overlapX1 && overlapX2) && overlapY1) { //overlap on right side (overlapX1 && overlapX2) && overlapY1
			System.out.println("right side");
			return other.getHeight() * (other.getWidth() - dif(other.getX2(), this.getX2()));
		} else if ((overlapY1 && overlapY2) && overlapX2) { //overlap on left side (overlapX1 && overlapX2) && overlapY2
			System.out.println("left side");
			return other.getHeight() * (other.getWidth() - dif(this.getX1(), other.getX1()));
		} else if ((overlapY1 && overlapY2) && overlapX1) { //overlap on top side (overlapY1 && overlapY2) && overlapX1
			System.out.println("top side");
			return (other.getHeight() - dif(other.getY1(), this.getY1())) * other.getWidth();
		} else if (false) { //overlap on bottom side (overlapY1 && overlapY2) && overlapX2
			System.out.println("bottom side");
			System.out.println(other.getHeight() +" - " + dif(this.getY2(), other.getY2()) +" * " + other.getWidth());
			return (other.getHeight() - dif(this.getY2(), other.getY2())) * other.getWidth();
		}
		
		else if(overlapX1 && overlapY1) { //overlap on bottom right;
			return dif(this.getX2(), other.getX1()) * dif(this.getY2(), other.getY1());
		} else if(overlapX2 && overlapY2) { //overlap on top left;
			return dif(this.getX1(), other.getX2()) * dif(this.getY1(), other.getY2());
		} else if(overlapX1 && overlapY2) { //overlap on top right;
			return dif(this.getX2(), other.getX1()) * dif(this.getY1(), other.getY2());
		} else if(overlapX1 && overlapY2) { //overlap on bottom left;
			return dif(this.getX1(), other.getX2()) * dif(this.getY2(), other.getY1());
		} else {
			return 0;
		}
	}

	public String toString() {
		return "(" + getX1() + ", " + getY1() + ") (" + getX2() + ", " + getY2() + ")";
	}
}
