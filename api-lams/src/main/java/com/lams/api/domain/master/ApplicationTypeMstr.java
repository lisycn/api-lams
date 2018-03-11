package com.lams.api.domain.master;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "application_type_mstr")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class ApplicationTypeMstr  extends MasterBase implements Serializable {
	
	private static final long serialVersionUID = -4805059037944649444L;
	
	public ApplicationTypeMstr() {
		super();
	}
	

}
