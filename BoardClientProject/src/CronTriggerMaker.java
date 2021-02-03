

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronTriggerMaker {
	private String timer;//크론(Cron) 표현식
	private Class<? extends Job> job;
	
	public CronTriggerMaker(String timer, Class<? extends Job> job) {
		super();
		this.timer = timer;
		this.job = job;
	}
	
	public void createTrigger() {
		SchedulerFactory factory;
		Scheduler scheduler;
		
		//Job 생성
		JobDetail jobDetail =JobBuilder.newJob(job).withIdentity(job.getName(),"group").build();
		
		//CronTrigger 생성
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger", "group").
				withSchedule(CronScheduleBuilder.cronSchedule(timer)).build();
		
		factory = new StdSchedulerFactory();
		try {
			scheduler = factory.getScheduler();
			scheduler.start();
			
			//JobDetail, CronTrigger를 스케줄러에 등록
			scheduler.scheduleJob(jobDetail,cronTrigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		CronTriggerMaker js = new CronTriggerMaker("* * * * * ?", IntervalJob.class);
		js.createTrigger();
	}
	
	//http://www.cronmaker.com/
}









