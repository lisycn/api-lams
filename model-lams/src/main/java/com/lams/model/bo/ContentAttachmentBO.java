package com.lams.model.bo;

import java.io.Serializable;

/**
 * @author Akshay
 *
 */
public class ContentAttachmentBO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2195059990781395464L;

	private String fileName;
	
	private byte[] contentInByte;

	public ContentAttachmentBO() {
		super();
	}

	public ContentAttachmentBO(String fileName, byte[] contentInByte) {
		super();
		this.fileName = fileName;
		this.contentInByte = contentInByte;
	}

	public String getFileName() {
		return fileName;
	}

	public byte[] getContentInByte() {
		return contentInByte;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setContentInByte(byte[] contentInByte) {
		this.contentInByte = contentInByte;
	}
	
	
	

}
