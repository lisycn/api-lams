package com.lams.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.Notification;


/**
 * @author Akshay
 *
 */
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	 
}
