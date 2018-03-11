package com.lams.model.bo;

import java.util.Map;

import com.lams.model.utils.Enums.ContentType;

/**
 * @author Akshay
 *
 */
public class SystemNotifyRequest {
	
	private String message;
	private String[] profileId;
	private Long userId;
	private String fromName;
	
	private Map<String, Object> parameters;
	private Long templateId;
	private ContentType template;
	private String content;
	private String fromId;
	private String templateName;
	
	private Long applicationId;
	private Long productId;
	
	
	
	
	
	
	
	
	
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
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	public ContentType getTemplate() {
		return template;
	}
	public void setTemplate(ContentType template) {
		this.template = template;
	}
	public Map<String, Object> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String[] getProfileId() {
		return profileId;
	}
	public void setProfileId(String[] profileId) {
		this.profileId = profileId;
	}
	
	
	
}
