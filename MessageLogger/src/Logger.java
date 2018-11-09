
public class Logger {

	private Message[] logArray;
	private int next;
	private int currentPriority = Message.PRIORITY_VERBOSE;
	private int msgCount;

	public Logger(int length) {
		logArray = new Message[length];
		next = 0;
		msgCount = 0;
	}

	public int getPriority() {
		return currentPriority;
	}

	public void setPriority(int priority) {
		this.currentPriority = priority;
	}

	public int getCount() {
		return msgCount;
	}

	public void log(Message msg) {
		if (msg.getPriority() >= currentPriority) {
			logArray[next] = msg;
			next = ++next % logArray.length;

			msgCount++;
			if(msgCount > logArray.length)
				msgCount = logArray.length;
		}
	}

	public Message[] get(int num) {
		Message[] messages = new Message[num];
		int messageNum = next - 1;
		for(int i = 0; i < num; i++) {
			if(messageNum - i < 0) {
				messageNum = logArray.length-1+i;
			}
			messages[i] = logArray[messageNum - i];
		}
		return messages;
	}

	public Message[] get(String keyword) {
		Message[] messages = new Message[msgCount];
		int count = 0;
		for(int i = 0; i < logArray.length; i++) {
			if(logArray[i].getMessage().contains(keyword)) {
				messages[count] = logArray[i];
				count++;
			}
		}
		Message[] newMessages = new Message[count];
		for(int i = 0; i < count; i++) {
			newMessages[i] = messages[i];
		}
		return newMessages;
	}
	
	public Message[] remove (int num) {
		return new Message[0];
	}
	
	public String toString() {
		Message[] msgArray = get(msgCount);
		String str = "";
		for(int k = 0; k <msgArray.length; k++) {
			Message msg = msgArray[k];
			str += String.format("%20s %8s %s\n", msg.getDate(),msg.getPriorityString(), msg.getMessage());
		}
		return str;
	}

}
