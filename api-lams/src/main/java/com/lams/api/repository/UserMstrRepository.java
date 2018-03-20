package com.lams.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lams.api.domain.User;

public interface UserMstrRepository extends JpaRepository<User, Long>{

	@Query("select count(us) from User us where us.email =:email and us.isActive = true")
	public Long checkEmail(@Param("email") String email);
	
	@Query("select count(us) from User us where us.email =:email and us.id !=:id  and us.isActive = true")
	public Long checkEmailById(@Param("email") String email, @Param("id") Long id);
	
	@Query("select count(us) from User us where us.mobile =:mobile and us.isActive = true")
	public Long checkMobile(@Param("mobile") String mobile);
	
	@Query("select count(us) from User us where us.mobile =:mobile and us.id !=:id and us.isActive = true")
	public Long checkMobileById(@Param("mobile") String mobile,@Param("id") Long id);
	
	public List<User> findByUserTypeAndIsActive(Long userType, Boolean isActive);
	
	public List<User> findByUserType(Long userType);
	
	public User findByEmailAndPasswordAndIsActive(String email,String password, Boolean isActive);
	
	public User findByEmailAndIsActive(String email,Boolean isActive);
	
	public List<User> findByIdInAndIsActive(List<Long> userIds,Boolean isActive);
	
	@Query("select employmentType from User us where us.id =:id and us.isActive = true")
	public Long getEmpTypeById(@Param("id") Long id);
}
