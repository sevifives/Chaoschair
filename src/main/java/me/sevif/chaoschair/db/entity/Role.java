package me.sevif.chaoschair.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Role {
	
	
	public Role(@JsonProperty("name") String name) {
		this.name = name;
	}
	
	@JsonProperty("name")
	private String name;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
