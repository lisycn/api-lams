package com.lams.model.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DocumentRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long applicationId;
    private Long userId;
    private Long documentId;
    private Long documentMappingId;
    private String originalFileName;
    private String userType;
    private Boolean isUserDocument;
    
    
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
	public Long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	public Long getDocumentMappingId() {
		return documentMappingId;
	}
	public void setDocumentMappingId(Long documentMappingId) {
		this.documentMappingId = documentMappingId;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public Boolean getIsUserDocument() {
		return isUserDocument;
	}
	public void setIsUserDocument(Boolean isUserDocument) {
		this.isUserDocument = isUserDocument;
	}
	@Override
	public String toString() {
		return "DocumentRequest [applicationId=" + applicationId + ", userId=" + userId + ", documentId=" + documentId
				+ ", documentMappingId=" + documentMappingId + ", originalFileName=" + originalFileName + ", userType="
				+ userType + "]";
	}
    
    
}
