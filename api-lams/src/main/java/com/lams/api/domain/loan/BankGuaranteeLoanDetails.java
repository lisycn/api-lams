package com.lams.api.domain.loan;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.lams.api.domain.Applications;

@Entity
@Table(name = "bank_guarantee_details")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class BankGuaranteeLoanDetails extends Applications implements Serializable {

	private static final long serialVersionUID = -4805059037944649444L;

	public BankGuaranteeLoanDetails() {
		super();
	}

}
