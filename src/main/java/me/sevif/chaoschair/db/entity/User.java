package me.sevif.chaoschair.db.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String url;
	private String externalId;
	private String name;
	private String alias;

	private boolean active;
	private boolean verified;
	private boolean shared;
	private boolean suspended;
	
	private String email;
	private String phone;
	private String signature;
	
	private String locale;
	private String timezone;
	
	private Date lastLoginAt;
	private Date createdAt;
	
	// actual associations
	private Long organizationId;
	
	// transient associations
	@Transient
	private List<Tag> tags;
	
	@Transient
	private Role role;
	
}
