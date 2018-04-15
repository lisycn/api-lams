package com.lams.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.lams.api.domain.DocumentUserMappingMstr;

public interface DocumentUserMappingMstrRepository extends JpaRepository<DocumentUserMappingMstr, Long>{

	@Modifying
	@Query("update DocumentUserMappingMstr dm set dm.isActive = false where dm.documentMstrId =:documentMstrId and dm.userId =:userId and dm.isActive = true")
	public int inactiveByUserId(@Param("userId") Long userId,@Param("documentMstrId") Long documentMstrId);
	
	@Query("from DocumentUserMappingMstr dm where dm.documentMstrId =:documentMstrId and dm.userId =:userId and dm.isActive = true")
	public DocumentUserMappingMstr getByUserIdAndDocId(@Param("userId") Long userId,@Param("documentMstrId") Long documentMstrId);
	
	
}
