import java.util.Date;

public class LogTester {

	static final long ONE_MIN = 1000 * 60;

	public static String testRemove(Logger logRef, int n) {
		Message[] msgArray = logRef.remove(n);
		if (msgArray.length == 0)
			return "Log is empty";
		
		String str = "";
		for (int k=0; k<msgArray.length; k++) {
			Message msg = msgArray[k];
			str += String.format("%20s  %8s  %s\n", msg.getDate(), msg.getPriorityString(), msg.getMessage());
		}
		return str;
	}
	public static String testGet(Logger logRef, String keyword) {
		Message[] msgArray = logRef.get(keyword);
		if (msgArray.length == 0)
			return "Log is empty";
		
		String str = "";
		for (int k=0; k<msgArray.length; k++) {
			Message msg = msgArray[k];
			str += String.format("%20s  %8s  %s\n", msg.getDate(), msg.getPriorityString(), msg.getMessage());
		}
		return str;
	}
	
	public static void main(String[] args) {

		Date now = new Date();
		long lnow = now.getTime();

		Logger myLog = new Logger(10);
		myLog.log(new Message(new Date(lnow-ONE_MIN*60), Message.PRIORITY_CRITICAL, "Test Tommorow, Have not studied ;( "));
		System.out.println("My log after 1st entry\n" + myLog);

		myLog.log(new Message(new Date(lnow-ONE_MIN*50), Message.PRIORITY_HIGH, "Need to finish this program ASAP"));
		myLog.log(new Message(new Date(lnow-ONE_MIN*40), Message.PRIORITY_VERBOSE, "Need more allowance"));
		myLog.log(new Message(new Date(lnow-ONE_MIN*40), Message.PRIORITY_VERBOSE, "Practice tonight at 5:30"));
		System.out.println("My log after 4 entries\n" + myLog);
		
		System.out.println("My log: removing these 2 entries\n" + testRemove(myLog, 2));
		System.out.println("My log: after the remove\n" + myLog);
		
		myLog.setPriority(Message.PRIORITY_HIGH);
		myLog.log(new Message(new Date(lnow-ONE_MIN*30), Message.PRIORITY_VERBOSE, "This message should not show up!!!!!"));
		myLog.log(new Message(new Date(lnow-ONE_MIN*30), Message.PRIORITY_HIGH, "Need Food!"));
		myLog.log(new Message(new Date(lnow-ONE_MIN*30), Message.PRIORITY_CRITICAL, "Need Air!"));
		System.out.println("My log: testing priority level, should have discarded VERBOSE msg\n" + myLog);
		
		System.out.println("Testing wrap around, we have 4 msgs, add 7, should loose \"Need more allowance\"");
		for (int k=0; k<7; k++) 
			myLog.log(new Message(Message.PRIORITY_CRITICAL, "Test Msg" + (k+1)));   //note we are calling the 2 argument constructor
		System.out.println("My log:\n" + myLog);
		
		
		System.out.println("Testing the get with keyword, should get the 7 \"Test\" messages\n" + testGet(myLog, "Test"));
		System.out.println("Testing the get with keyword, should get the 1 \"Practice\" message\n" + testGet(myLog, "Practice"));
		
		
		System.out.println("My log: removing all entries\n" + testRemove(myLog, myLog.getCount()));
		System.out.println("My log: after the remove, should be the empty string\n" + myLog);


	}

}
