package com.lams.api.service;

import java.util.List;

import com.lams.model.bo.DocumentRequest;
import com.lams.model.bo.DocumentResponse;

public interface DocumentUploadService {

	public DocumentResponse upload(String fileName, byte[] bytes, DocumentRequest documentRequest);
	
	public List<DocumentResponse> getDocumentList(Long applicationId,List<Long> docId);
	
	public boolean inactiveDocument(Long id);
}
