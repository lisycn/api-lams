package com.lams.model.bo;

import java.util.ArrayList;
import java.util.List;

public class DocumentResponse {

	public Long id;
	public String filePath;
	public Long documentMappingId;
	public Long applicationId;
	public Boolean isFileUpload;
	public String documentMstrName;
	public Long documentMstrId;
	public String originalName;
	public List<DocumentResponse> documentResponseList = new ArrayList<>(); 
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Long getDocumentMappingId() {
		return documentMappingId;
	}
	public void setDocumentMappingId(Long documentMappingId) {
		this.documentMappingId = documentMappingId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Boolean getIsFileUpload() {
		return isFileUpload;
	}
	public void setIsFileUpload(Boolean isFileUpload) {
		this.isFileUpload = isFileUpload;
	}
	public String getDocumentMstrName() {
		return documentMstrName;
	}
	public void setDocumentMstrName(String documentMstrName) {
		this.documentMstrName = documentMstrName;
	}
	public Long getDocumentMstrId() {
		return documentMstrId;
	}
	public void setDocumentMstrId(Long documentMstrId) {
		this.documentMstrId = documentMstrId;
	}
	public List<DocumentResponse> getDocumentResponseList() {
		return documentResponseList;
	}
	public void setDocumentResponseList(List<DocumentResponse> documentResponseList) {
		this.documentResponseList = documentResponseList;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	@Override
	public String toString() {
		return "DocumentResponse [id=" + id + ", filePath=" + filePath + ", documentMappingId=" + documentMappingId
				+ ", applicationId=" + applicationId + ", isFileUpload=" + isFileUpload + ", documentMstrName="
				+ documentMstrName + ", documentMstrId=" + documentMstrId + "]";
	}
	
	
	
	
}
