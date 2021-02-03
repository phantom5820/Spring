
import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class IntervalJob implements Job {
	private static int count = 0;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//주기적으로 자동으로 수행할 일
		count++;
		System.out.println(Calendar.getInstance().getTime() + " IntervalJob - " + count);
	}

}







