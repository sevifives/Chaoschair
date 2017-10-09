package me.sevif.chaoschair.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DomainName {
	
	@JsonProperty("domainName")
	private String domainName;
	
	public DomainName(@JsonProperty("domainName") String domainName) {
		this.domainName = domainName;
	}
	
	public String getDomainName() {
		return this.domainName;
	}
	
	
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
}
