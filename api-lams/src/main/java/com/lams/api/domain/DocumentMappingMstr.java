package com.lams.api.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lams.api.domain.master.Auditor;

@Entity
@Table(name = "document_mapping_mstr")
public class DocumentMappingMstr  extends Auditor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "document_mstr_id")
	public Long documentMstrId;
	
	@Column(name = "application_id")
	public Long applicationId;
	
	@Column(name = "user_id")
	public Long userId;

	@Column(name = "file_path")
	public String filePath;
	
	@Column(name = "original_name")
	public String originalName;
	
	
	@Column(name = "version")
	public Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDocumentMstrId() {
		return documentMstrId;
	}

	public void setDocumentMstrId(Long documentMstrId) {
		this.documentMstrId = documentMstrId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	
	
	
	
}
