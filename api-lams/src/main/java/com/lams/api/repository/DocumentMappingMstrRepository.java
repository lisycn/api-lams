package com.lams.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lams.api.domain.DocumentMappingMstr;

public interface DocumentMappingMstrRepository extends JpaRepository<DocumentMappingMstr, Long>{

	@Query(value = "SELECT version FROM lams.document_mapping_mstr where document_mstr_id =:docId and application_id =:appId and is_active = 1 order by id desc limit 1",nativeQuery = true)
	public Integer getLastVersion(@Param("docId") Long docId,@Param("appId") Long appId);
	
	@Query("from DocumentMappingMstr where applicationId=:applicationId and documentMstrId IN :docId and isActive = true order by createdDate asc")
	public List<DocumentMappingMstr> getDocumentList(@Param("applicationId") Long applicationId,@Param("docId") List<Long> docId);
	
	@Query("from DocumentMappingMstr where applicationId=:applicationId and documentMstrId =:docId and isActive = true order by createdDate asc")
	public List<DocumentMappingMstr> getDocumentByAppIdAndDocMstId(@Param("applicationId") Long applicationId,@Param("docId") Long docId);
	
	@Modifying
	@Query("update DocumentMappingMstr dm set isActive = false where id=:id and isActive = true")
	public int inActiveDocument(@Param("id") Long id);
}
