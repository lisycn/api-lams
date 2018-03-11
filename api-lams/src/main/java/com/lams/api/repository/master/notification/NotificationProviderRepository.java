package com.lams.api.repository.master.notification;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.master.notification.NotificationProvider;

/**
 * @author Akshay
 *
 */
public interface NotificationProviderRepository extends JpaRepository<NotificationProvider, Long> {

	public NotificationProvider findByNotificationTypeAndIsActive(String notificationType,Boolean isActive);

}
