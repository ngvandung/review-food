///**
// *
// */
//package com.review.scheduler;
//
//import java.util.Date;
//
//import com.review.service.FoodService;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.review.configuration.SchedulerConfiguration;
//import com.review.util.DateFormat;
//
///**
// * @author ddung
// *
// */
//public class PriorityFoodScheduler {
//	private static final Logger _log = Logger.getLogger(PriorityFoodScheduler.class);
//
//	public PriorityFoodScheduler() {
//		SchedulerConfiguration.getInstance().getTaskScheduler().scheduleAtFixedRate(new Runnable() {
//			@Override
//			public void run() {
//				Date now = new Date();
//				_log.info("Priority Food Queue Scheduler: " + DateFormat.formatDateToString_ddMMyyyy_HHmmss(now));
//
//
//			}
//		}, 3000);
//	}
//}
