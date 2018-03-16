package com.lams.model.loan.bo;

import java.io.Serializable;

import com.lams.model.bo.ApplicationsBO;

public class PersonalLoanDetailsBO extends ApplicationsBO implements Serializable {

	private static final long serialVersionUID = -4805059037944649444L;

	public PersonalLoanDetailsBO() {
		super();
	}
	
	private String reasonForPl;

	public String getReasonForPl() {
		return reasonForPl;
	}


	public void setReasonForPl(String reasonForPl) {
		this.reasonForPl = reasonForPl;
	}

	
	
	

}
