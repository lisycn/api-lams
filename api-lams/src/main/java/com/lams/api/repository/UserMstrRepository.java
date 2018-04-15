package com.lams.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lams.api.domain.User;

public interface UserMstrRepository extends JpaRepository<User, Long>{

	@Query("select count(us) from User us where us.email =:email")
	public Long checkEmail(@Param("email") String email);
	
	@Query("select count(us) from User us where us.email =:email and us.id !=:id  and us.isActive = true")
	public Long checkEmailById(@Param("email") String email, @Param("id") Long id);
	
	@Query("select count(us) from User us where us.mobile =:mobile")
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
	
	
	@Query(value = "SELECT count(code) FROM lams.user where code IS NOT NULL ",nativeQuery = true)
	public Long getCodeCount();
	
	
	@Query("select us from User us where us.channelPartnerId.id =:userId and us.isActive = true and us.userType =:userType")
	public List<User> getUserByCpIdAndUserType(@Param("userId")Long cpId,@Param("userType")Long userType);
	
	@Query("select us from User us where us.channelPartnerId.id =:userId and us.isActive = true")
	public List<User> getUserByCpId(@Param("userId")Long cpId);
	
}
