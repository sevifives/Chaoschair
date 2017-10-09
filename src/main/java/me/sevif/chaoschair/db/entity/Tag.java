package me.sevif.chaoschair.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag {
	
	@JsonProperty("tag")
	private String tag;
	
	public Tag(@JsonProperty("tag") String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
}
