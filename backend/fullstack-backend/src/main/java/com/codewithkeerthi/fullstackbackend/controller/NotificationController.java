package com.codewithkeerthi.fullstackbackend.controller;

import com.codewithkeerthi.fullstackbackend.model.Notification;
import com.codewithkeerthi.fullstackbackend.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("/notifications")
    List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @PostMapping("/notification")
    Notification newNotification(@RequestBody Notification newNotification) {
        newNotification.setCreatedAt(new Date());
        return notificationRepository.save(newNotification);
    }
}