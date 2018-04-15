package com.lams.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lams.api.service.DocumentUploadService;
import com.lams.model.bo.DocumentRequest;
import com.lams.model.bo.DocumentResponse;
import com.lams.model.bo.LamsResponse;
import com.lams.model.utils.CommonUtils;
import com.lams.model.utils.MultipleJSONObjectHelper;

@RestController
public class DocumentUploadController {

	private static final Logger logger = LoggerFactory.getLogger(DocumentUploadController.class);
	
	@Autowired
	private DocumentUploadService documentUploadService;
	
	@RequestMapping(value = "/upload")
	public ResponseEntity<LamsResponse> upload(@RequestPart("uploadRequest") String documentRequestString, @RequestPart("file") MultipartFile multipartFiles,HttpServletRequest httpServletRequest) {
		
		logger.info("Enter in /upload with {}",documentRequestString);
		DocumentRequest documentRequest = null;
		
		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		if (CommonUtils.isObjectNullOrEmpty(userId)) {
			logger.info("UserId must not be null while Updating  User Details ------------>{}");
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Request, User id is null  or empty"), HttpStatus.OK);
		}
		try {
			documentRequest = MultipleJSONObjectHelper.getObjectFromString(documentRequestString, DocumentRequest.class);
			if ((CommonUtils.isObjectNullOrEmpty(documentRequest.getDocumentId()))) {
                logger.info("Invalid Request {}", documentRequest.toString());
                return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Request"), HttpStatus.OK);
            }
			documentRequest.setUserId(userId);
			DocumentResponse documentResponse = documentUploadService.upload(multipartFiles.getOriginalFilename(), multipartFiles.getBytes(), documentRequest);
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.OK.value(), "Successfully Uploaded File",documentResponse), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Throw Expection while Upload File -------------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong while upload file"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/getUserDocuments/{documentId}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getUserDocuments(@PathVariable("documentId") Long documentId,HttpServletRequest httpServletRequest) {
		logger.info("Enter in user documents------>" + documentId);
		
		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		if (CommonUtils.isObjectNullOrEmpty(userId)) {
			logger.info("UserId must not be null while get User Doc Details ------------>{}");
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Request, User id is null or empty"), HttpStatus.OK);
		}
		
		try {
			DocumentResponse documents = documentUploadService.getUserDocumentByDocId(userId, documentId);
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.OK.value(), "Successfully get documents",documents), HttpStatus.OK);
		} catch(Exception e) {
			logger.info("Throw Expection while get User Doc-------------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong while getting documents"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/getDocuments/{applicationId}",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LamsResponse> getDocuments(@PathVariable("applicationId") Long applicationId,@RequestBody List<Long> documentList) {
		logger.info("Enter in document list ------>" + applicationId);
		if (CommonUtils.isListNullOrEmpty(documentList)) {
			logger.info("Document Id must not be null while getting document Details ------------>{}");
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Request, Document id is null or empty"), HttpStatus.OK);
		}
		
		try {
			List<DocumentResponse> documents = documentUploadService.getDocumentList(applicationId, documentList);
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.OK.value(), "Successfully get documentlist",documents), HttpStatus.OK);
		} catch(Exception e) {
			logger.info("Throw Expection while Upload File -------------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong while getting documents"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/inActiveDocument/{documentMappingId}")
	public ResponseEntity<LamsResponse> inActiveDocument(@PathVariable("documentMappingId") Long documentMappingId) {
		logger.info("Enter in inactive document ------>" + documentMappingId);
		try {
			boolean inactiveDocument = documentUploadService.inactiveDocument(documentMappingId);
			if(inactiveDocument) {
				return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.OK.value(), "Successfully inactive document"), HttpStatus.OK);	
			}
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Document Mapping Id !!"), HttpStatus.OK);
		} catch(Exception e) {
			logger.info("Throw Expection while inactive document -------------------->");
			e.printStackTrace();
			return new ResponseEntity<LamsResponse>(new LamsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong while inactive document"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
 	
}
