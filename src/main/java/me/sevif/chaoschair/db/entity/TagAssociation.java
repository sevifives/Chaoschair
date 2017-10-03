package me.sevif.chaoschair.db.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import me.sevif.chaoschair.db.entity.enums.PrincipalType;

public class TagAssociation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private PrincipalType principalType;
	private Long principalId;
	
}
