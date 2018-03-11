package com.lams.api.repository.master.notification;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.master.notification.NotificationTemplate;

/**
 * @author Akshay
 *
 */
public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate, Long> {

	public NotificationTemplate findByNameAndNotificationType(String name, String notificationType);

}
