import java.util.Date;

public class Message {

	public static final int PRIORITY_VERBOSE = 1;
	public static final int PRIORITY_HIGH = 2;
	public static final int PRIORITY_CRITICAL = 3;

	private Date date;
	private int priority;
	private String message;

	public Message(Date aDate, int aPriority, String aMessage) {
		date = aDate;
		priority = aPriority;
		message = aMessage;
	}

	public Message(int aPriority, String aMessage) {
		date = new Date();
		priority = aPriority;
		message = aMessage;
	}

	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPriorityString() {
		if (this.priority == 1) {
			return "Verbose";
		} else if (this.priority == 2) {
			return "High";
		} else {
			return "Critical";
		}
	}

	private boolean contains(String keyword) {
		return message.contains(keyword);
	}

	public String toString() {
		String str = "";
		str += String.format("%20s  %8s  %s\n", getDate(), getPriorityString(), getMessage());
		return str;
	}

}
