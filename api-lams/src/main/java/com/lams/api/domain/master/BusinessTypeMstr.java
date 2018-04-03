package com.lams.api.domain.master;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "business_type_mstr")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class BusinessTypeMstr extends MasterBase implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public BusinessTypeMstr() {
		super();
	}
}
