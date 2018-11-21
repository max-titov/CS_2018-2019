
public class Cow {
	private int num;
	private int value;
	
	public Cow(int num, int value) {
		this.num = num;
		this.value = value;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String toString() {
		return "("+getNum()+" "+getValue()+")";
	}
}
