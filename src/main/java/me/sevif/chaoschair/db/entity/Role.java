package me.sevif.chaoschair.db.entity;

public class Role {
	
	public Role(String name) {
		this.name = name;
	}
	
	private String name;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
