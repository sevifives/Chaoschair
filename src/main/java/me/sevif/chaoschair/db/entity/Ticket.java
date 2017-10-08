package me.sevif.chaoschair.db.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName="ticket")
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
	@JsonProperty("_id")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExternalUuid() {
		return externalUuid;
	}

	public void setExternalUuid(String externalUuid) {
		this.externalUuid = externalUuid;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public ViaProtocol getViaProtocol() {
		return viaProtocol;
	}

	public void setViaProtocol(ViaProtocol viaProtocol) {
		this.viaProtocol = viaProtocol;
	}

	public Long getSubmitterId() {
		return submitterId;
	}

	public void setSubmitterId(Long submitterId) {
		this.submitterId = submitterId;
	}

	public Long getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public boolean isHasIncidents() {
		return hasIncidents;
	}

	public void setHasIncidents(boolean hasIncidents) {
		this.hasIncidents = hasIncidents;
	}

	public Date getDueAt() {
		return dueAt;
	}

	public void setDueAt(Date dueAt) {
		this.dueAt = dueAt;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Organization getOrganization() {
		return organization;
	}

	public List<Tag> getTags() {
		return tags;
	}
}
