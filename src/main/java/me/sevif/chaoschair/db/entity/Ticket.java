package me.sevif.chaoschair.db.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

public class Ticket {

	public static enum Type {
		PROBLEM, INCIDENT, QUESTION, TASK;
	}
	
	public static enum Priority {
		LOW, NORMAL, HIGH, URGENT;
	}
	
	public static enum Status {
		OPEN, CLOSED, PENDING, HOLD, SOLVED;
	}
	
	public static enum ViaProtocol {
		VOICE, CHAT, WEB;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String url;
	private String externalUuid;
	
	private Type type;
	private Status status;
	private Priority priority;
	private ViaProtocol viaProtocol;
	
	private Long submitterId;
	private Long assigneeId;
	private Long organizationId;
	
	private boolean hasIncidents;
	private Date dueAt;
	
	private String subject;
	private String description;
	
	// transient
	@Transient
	private Organization organization;
	
	@Transient
	private List<Tag> tags;
}
