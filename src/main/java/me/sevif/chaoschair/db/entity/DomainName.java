package me.sevif.chaoschair.db.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

public class DomainName {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long organizationId;
	private String protocol = "https";
	private Organization organization;
}
