
public class Log {
	private int day;
	private int cow;
	private int change;
	
	public Log(int day, int cow, int change) {
		this.day = day;
		this.cow = cow;
		this.change = change;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getCow() {
		return cow;
	}

	public void setCow(int cow) {
		this.cow = cow;
	}

	public int getChange() {
		return change;
	}

	public void setChange(int change) {
		this.change = change;
	}
	
	public int compareTo(Log other) {
		if(this.getDay() < other.getDay()) return -1;
		else if(this.getDay() > other.getDay()) return 1;
		else return 0;
	}
	
	@Override
	public String toString() {
		return getDay()+" "+getCow()+" "+getChange();
	}
	
}
	