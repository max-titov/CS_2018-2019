
public class Haybale {
	private int flavor;
	private int spice;
	
	public Haybale(int f, int s) {
		flavor = f;
		spice = s;
	}

	public int getFlavor() {
		return flavor;
	}

	public void setFlavor(int flavor) {
		this.flavor = flavor;
	}

	public int getSpice() {
		return spice;
	}

	public void setSpice(int spice) {
		this.spice = spice;
	}
	
	public String toString() {
		return "("+getFlavor()+", "+getSpice()+")";
	}
}
	