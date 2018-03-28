package com.lams.api.domain.payment;

import javax.persistence.*;

@Entity
@Table(name = "service_provider")
public class ServiceProvider {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "provider_name")
	private String providerName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
	
	
	
	
}
