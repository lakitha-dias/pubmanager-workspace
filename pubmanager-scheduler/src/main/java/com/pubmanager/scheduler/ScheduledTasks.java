package com.pubmanager.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.pubmanager.scheduler.fcm.FCMMessage;
import com.pubmanager.scheduler.fcm.FCMNotificationServer;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    @Autowired
    private FCMNotificationServer fcmNotificationServer;

    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
    	
    	FCMMessage fcmMessage = new FCMMessage();
    	fcmMessage.setTitle("Some long content");
    	fcmMessage.setBody("some subject");
    	fcmMessage.setTarget("category-threshold-exceeded");
    	
    	fcmNotificationServer.sendNotification(fcmMessage);
    	
    	
    	//var userTransactions = (List<UserTransaction>) userTransactionService.findAll();

        //logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) + " | userTransactions : "+userTransactions);
    }

}
