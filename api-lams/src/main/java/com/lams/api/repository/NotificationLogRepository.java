package com.lams.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.NotificationLog;

/**
 * @author Akshay
 *
 */
public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {

}
