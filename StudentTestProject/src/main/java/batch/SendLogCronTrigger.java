package batch;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SendLogCronTrigger {
	private String timer; //크론식 저장할 부분
	private Class<? extends Job> job; //job을 상속받는 애들
	private Scheduler scheduler; // 작업할것들을 등록한다.
	
	
	public SendLogCronTrigger(String timer, Class<? extends Job> job) {
		super();
		this.timer = timer;
		this.job = job;
	}
	
	//스케줄러에 등록
	public void triggerJob() {
		SchedulerFactory factory;
		
		
		JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(job.getName(), "group").build();
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger","group").
				withSchedule(CronScheduleBuilder.cronSchedule(timer)).build();
	
		factory = new StdSchedulerFactory();
		try {
			scheduler = factory.getScheduler();
			scheduler.start();
			scheduler.scheduleJob(jobDetail, cronTrigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	
	}
}
