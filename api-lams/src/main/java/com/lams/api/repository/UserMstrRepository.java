package com.lams.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lams.api.domain.User;

public interface UserMstrRepository extends JpaRepository<User, Long>{

	@Query("select count(us) from User us where us.email =:email")
	public Long checkEmail(@Param("email") String email);
	
	@Query("select count(us) from User us where us.mobile =:mobile")
	public Long checkMobile(@Param("mobile") String mobile);
	
	public List<User> findByUserTypeAndIsActive(Long userType, Boolean isActive);
	
	public User findByEmailAndPasswordAndIsActive(String email,String password, Boolean isActive);
}
