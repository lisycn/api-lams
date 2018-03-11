package com.lams.model.bo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.lams.model.utils.Enums.ContentType;

/**
 * @author Akshay
 *
 */
public class EmailRequest {

	private String to[];
	private String from;
	private ContentType contentType;
	private String subject;
	private Map<String, Object> parameters;
	private String content;
	private String[] cc;
	private byte[] contentInBytes;
	private String fileName;
	private Long fileSize;
	private String maxAllowedFileSize;
	private String templateName;
	private Long userId;
	private String[] bcc;
	public List<ContentAttachmentBO> contentAttachments = null;

	public EmailRequest() {
		contentAttachments = Collections.emptyList();
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getMaxAllowedFileSize() {
		return maxAllowedFileSize;
	}

	public void setMaxAllowedFileSize(String maxAllowedFileSize) {
		this.maxAllowedFileSize = maxAllowedFileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getContentInBytes() {
		return contentInBytes;
	}

	public void setContentInBytes(byte[] contentInBytes) {
		this.contentInBytes = contentInBytes;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public List<ContentAttachmentBO> getContentAttachments() {
		return contentAttachments;
	}

	public void setContentAttachments(List<ContentAttachmentBO> contentAttachments) {
		this.contentAttachments = contentAttachments;
	}

	@Override
	public String toString() {
		return "EmailRequest [to=" + Arrays.toString(to) + ", from=" + from + ", contentType=" + contentType
				+ ", subject=" + subject + ", parameters=" + parameters + ", content=" + content + ", cc="
				+ Arrays.toString(cc) + ", contentInBytes=" + Arrays.toString(contentInBytes) + ", fileName=" + fileName
				+ ", fileSize=" + fileSize + ", maxAllowedFileSize=" + maxAllowedFileSize + ", templateName="
				+ templateName + ", userId=" + userId + ", bcc=" + Arrays.toString(bcc) + ", contentAttachments="
				+ contentAttachments + "]";
	}

}
