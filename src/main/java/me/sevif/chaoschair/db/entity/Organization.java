package me.sevif.chaoschair.db.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String url;
	private String externalUuid;
	private String name;
	private String details;
	
	private Date createdAt;
	
	// transient associations
	@Transient
	private List<DomainName> domainNames;
	
	@Transient
	private List<Tag> tags;
}
