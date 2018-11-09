import java.util.Date;

public class LogMessageTester {

	static final long ONE_MIN = 1000 * 60;
	
	public static void main(String[] args) {

		Date now = new Date();
		long lnow = now.getTime();

		Message mrC = new Message(new Date(lnow-ONE_MIN*60), Message.PRIORITY_CRITICAL, "Test Tommorow, Have not studied ;( ");
		System.out.println(mrC);



	}

}