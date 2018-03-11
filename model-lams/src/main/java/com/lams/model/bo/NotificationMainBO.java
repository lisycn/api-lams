package com.lams.model.bo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.lams.model.utils.Enums.AckType;
import com.lams.model.utils.Enums.ContentType;
import com.lams.model.utils.Enums.NotificationType;

/**
 * @author Akshay
 *
 */
public class NotificationMainBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9151646917155081519L;
	protected NotificationType type;
	protected String[] to;
	protected String[] cc;
	protected String from;
	protected ContentType contentType;
	protected String content;
	protected String templateName;
	protected String title;
	protected String subject;
	protected Map<String, Object> parameters = null;
	protected AckType ackType;
	protected Integer ackTimeout;
	protected Integer retryCount;
	protected Integer order;
	protected Boolean secure;
	protected String attachmentIds;
	protected String fileName;
	protected String fileFormat;
	protected byte[] contentInBytes;
	protected Long readSysNotifId;
	protected Boolean readStatusSysNotif;
	protected Long applicationId;
	protected Long productId;
	protected List<ContentAttachmentBO> contentAttachments = null;

	public NotificationMainBO() {
		contentAttachments = Collections.emptyList();
		parameters = Collections.emptyMap();
	}

	protected String[] bcc;

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public List<ContentAttachmentBO> getContentAttachments() {
		return contentAttachments;
	}

	public void setContentAttachments(List<ContentAttachmentBO> contentAttachments) {
		this.contentAttachments = contentAttachments;
	}

	public void addAttachment(ContentAttachmentBO attachments) {
		getContentAttachments().add(attachments);
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Boolean getReadStatusSysNotif() {
		return readStatusSysNotif;
	}

	public void setReadStatusSysNotif(Boolean readStatusSysNotif) {
		this.readStatusSysNotif = readStatusSysNotif;
	}

	public Long getReadSysNotifId() {
		return readSysNotifId;
	}

	public void setReadSysNotifId(Long readSysNotifId) {
		this.readSysNotifId = readSysNotifId;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public AckType getAckType() {
		return ackType;
	}

	public void setAckType(AckType ackType) {
		this.ackType = ackType;
	}

	public Integer getAckTimeout() {
		return ackTimeout;
	}

	public void setAckTimeout(Integer ackTimeout) {
		this.ackTimeout = ackTimeout;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Boolean getSecure() {
		return secure;
	}

	public void setSecure(Boolean secure) {
		this.secure = secure;
	}

	public String getAttachmentIds() {
		return attachmentIds;
	}

	public void setAttachmentIds(String attachmentIds) {
		this.attachmentIds = attachmentIds;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public byte[] getContentInBytes() {
		return contentInBytes;
	}

	public void setContentInBytes(byte[] contentInBytes) {
		this.contentInBytes = contentInBytes;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Override
	public String toString() {
		return "NotificationMainBO [type=" + type + ", to=" + Arrays.toString(to) + ", cc=" + Arrays.toString(cc)
				+ ", from=" + from + ", contentType=" + contentType + ", content=" + content + ", templateName="
				+ templateName + ", title=" + title + ", subject=" + subject + ", parameters=" + parameters
				+ ", ackType=" + ackType + ", ackTimeout=" + ackTimeout + ", retryCount=" + retryCount + ", order="
				+ order + ", secure=" + secure + ", attachmentIds=" + attachmentIds + ", fileName=" + fileName
				+ ", fileFormat=" + fileFormat + ", contentInBytes=" + Arrays.toString(contentInBytes)
				+ ", readSysNotifId=" + readSysNotifId + ", readStatusSysNotif=" + readStatusSysNotif
				+ ", applicationId=" + applicationId + ", productId=" + productId + ", contentAttachments="
				+ contentAttachments + ", bcc=" + Arrays.toString(bcc) + "]";
	}
	
}
