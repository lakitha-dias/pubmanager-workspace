package com.pubmanager.scheduler.fcm;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.api.client.util.Value;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class FCMNotificationServer {

    private FirebaseApp firebaseApp;

    private void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("./firebase-key.json").getInputStream())).build();

            if (FirebaseApp.getApps().isEmpty()) {
                this.firebaseApp = FirebaseApp.initializeApp(options);
            } else {
                this.firebaseApp = FirebaseApp.getInstance();
            }
        } catch (IOException e) {
           // log.error("Create FirebaseApp Error", e);
        }
    }


    public String sendNotification(FCMMessage notificationRequestDto) {
    	
    	 initialize();
    	
    	 Message message = Message.builder()
                 .setTopic(notificationRequestDto.getTarget())
                 .setNotification(new Notification(notificationRequestDto.getTitle(), notificationRequestDto.getBody()))
                 .putData("content", notificationRequestDto.getTitle())
                 .putData("body", notificationRequestDto.getBody())
                 .build();

         String response = null;
         try {
             FirebaseMessaging.getInstance().send(message);
         } catch (FirebaseMessagingException e) {
            // log.error("Fail to send firebase notification", e);
         }

         return response;
    }
    
}
