package com.lams.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lams.api.domain.User;

public interface UserMstrRepository extends JpaRepository<User, Long>{

}
