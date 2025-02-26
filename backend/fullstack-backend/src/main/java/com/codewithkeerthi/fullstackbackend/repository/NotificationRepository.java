package com.codewithkeerthi.fullstackbackend.repository;

import com.codewithkeerthi.fullstackbackend.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}