package com.lams.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lams.api.domain.Applications;

public interface ApplicationsRepository  extends JpaRepository<Applications, Long>{

	public List<Applications> findByUserIdAndIsActive(Long userId, Boolean isActive);
	
	public Applications findByIdAndIsActive(Long id, Boolean isActive);
	
	@Query(value = "SELECT lead_reference_no FROM lams.applications where application_type_id =:applicationType and is_from_cp is not true order by id desc limit 1",nativeQuery = true)
	public String getLastLeadReferenceNo(@Param("applicationType") Long applicationType);
	
	@Query(value = "SELECT lead_reference_no FROM lams.applications where application_type_id =:applicationType and is_from_cp = true and lead_reference_no like %:cpCode% order by id desc limit 1",nativeQuery = true)
	public String getLastLeadReferenceNoForCP(@Param("applicationType") Long applicationType,@Param("cpCode")String cpCode);
	
	@Query(value = "SELECT distinct(app.userId) from Applications app where app.applicationTypeId.id in (:id) and app.isActive = true")
	public List<Long> getUserIdByApplicationTypeId(@Param("id")List<Long> appTypeId);
	
	@Query(value = "SELECT distinct(app.userId) from Applications app where app.applicationTypeId.id =:id and app.isActive = true")
	public List<Long> getUserIdById(@Param("id")Long appId);
	
	public List<Applications> findByUserIdAndIsActiveAndApplicationTypeIdIdIn(Long userId,Boolean isActive,List<Long> applicationTypeIds);
	
	@Query(value = "SELECT app.userId from Applications app where app.id =:id and app.isActive = true")
	public Long getUserIdByAppId(@Param("id")Long appId);
	
	public List<Applications> findByApplicationTypeIdIdAndIsActiveAndStatus(Long appTypeId,Boolean isActive,String status);

	@Query(value="select app from Applications app , User usr where app.isActive = true and app.userId = usr.id and usr.channelPartnerId.id =:cpId and app.userId =:userId and usr.channelPartnerId.isActive = true and app.isFromCP = true")
	public List<Applications> getAllAppByUserIdAndCpId(@Param("userId")Long userId, @Param("cpId")Long cpId);
	
	@Modifying
	@Query(value = "update Applications app set app.isActive = false where app.userId =:userId and app.isActive = true and (app.isLoanDetailsLock = false or app.isLoanDetailsLock IS NULL)")
	public int inActiveByUserId(@Param("userId")Long userId);
	
	@Modifying
	@Query(value = "update Applications app set app.isActive = false where app.id =:applicationId and app.userId =:userId and app.isActive = true")
	public int inActiveByApplicationIdAndUserId(@Param("applicationId")Long applicationId,@Param("userId")Long userId);
	
	@Modifying
	@Query(value = "update Applications app set app.isActive = false where app.id =:applicationId and app.isActive = true")
	public int inActiveByApplicationId(@Param("applicationId")Long applicationId);
}
