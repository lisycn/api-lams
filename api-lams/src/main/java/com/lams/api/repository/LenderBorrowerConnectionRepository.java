package com.lams.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.LenderBorrowerConnection;

public interface LenderBorrowerConnectionRepository extends JpaRepository<LenderBorrowerConnection, Long> {

}
