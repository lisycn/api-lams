package com.lams.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lams.api.domain.Applications;
import com.lams.api.domain.LenderBorrowerConnection;
import com.lams.model.utils.CommonUtils;

public interface LenderBorrowerConnectionRepository extends JpaRepository<LenderBorrowerConnection, Long> {

	@Query("select lb from LenderBorrowerConnection lb where lb.status =:status and lb.isActive = true and lb.application.id =:appId and lb.application.isActive = true")
	public List<LenderBorrowerConnection> getListByApplication(@Param("appId")Long appId,@Param("status")String status);
	
//	 and lbr.isActive = true and lbr.lenderApplicationMapping.isActive = true
	@Query(value = "SELECT distinct(lbr) from LenderBorrowerConnection lbr where lbr.application.id =:applicationId and lbr.lenderApplicationMapping.userId =:lrId and lbr.application.userId =:brId")
	public List<LenderBorrowerConnection> findByApplicationIdAndLenderIdAndBorrowerId(@Param("applicationId")Long applicationId, @Param("lrId")Long lrId, @Param("brId")Long brId);
	
	
	@Query("select lb from LenderBorrowerConnection lb where lb.status =:status and lb.isActive = true and lb.application.applicationTypeId.id =:appTypeId and lb.application.isActive = true")
	public List<LenderBorrowerConnection> findApplicationByAppTypeIdAndStatus(@Param("appTypeId") Long appTypeId,@Param("status")String status);
	
	public LenderBorrowerConnection findByApplicationIdAndLenderApplicationMappingId(Long applicationId,Long id);
	
	@Query("select count(*) from LenderBorrowerConnection lb where lb.application.id =:applicationId and lb.lenderApplicationMapping.userId =:lenderId")
	public Long isActionTakenOnApplicationByLender(@Param("applicationId")Long applicationId, @Param("lenderId")Long lenderId);
	
	@Query("select lb from LenderBorrowerConnection lb where lb.lenderApplicationMapping.userId =:lenderId")
	public List<LenderBorrowerConnection> getAllApplicationByLenderId(@Param("lenderId")Long lenderId);
	
	@Modifying
	@Query("update LenderBorrowerConnection lb set lb.status =:status where lb.application.id =:applicationId and lb.status = 'RESPONDED'")
	public void setRejectStatusAfterAcceptingLender(@Param("applicationId")Long applicationId, @Param("status")String status);
}
