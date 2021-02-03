package batch;

public class SendLogMain {

	public static void main(String[] args) {
			SendLogCronTrigger trigger = new SendLogCronTrigger("0 0 0 1/1 * ? *", SendLogJob.class);
			trigger.triggerJob();

	}

	

	//http://www.cronmaker.com/
}
