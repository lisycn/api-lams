package com.lams.api.domain.loan;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.lams.api.domain.Applications;

@Entity
@Table(name = "personal_loan_details")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class PersonalLoanDetails extends Applications implements Serializable {

	private static final long serialVersionUID = -4805059037944649444L;

	public PersonalLoanDetails() {
		super();
	}
	
	@Column(name="reason_for_pl")
	private String reasonForPl;

	public String getReasonForPl() {
		return reasonForPl;
	}


	public void setReasonForPl(String reasonForPl) {
		this.reasonForPl = reasonForPl;
	}

	
	
	

}
