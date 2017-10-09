package me.sevif.chaoschair.db.entity;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Document(indexName="ticket",createIndex=true)
public class Ticket {

	public static enum Type {
		problem, incident, question, task;
	}
	
	public static enum Priority {
		low, normal, high, urgent;
	}
	
	public static enum Status {
		open, closed, pending, hold, solved;
	}
	
	public static enum ViaProtocol {
		voice, chat, web;
	}
	
	@Id
	@Field(type = FieldType.String, store = true)
	private String id;
	
	private String url;
	
	@JsonProperty("external_id")
	private String externalUuid;
	
	private Type type;
	private Status status;
	private Priority priority;
	
	@JsonProperty("via")
	private ViaProtocol viaProtocol;
	
	@JsonProperty("submitter_id")
	private Long submitterId;
	
	@JsonProperty("assignee_id")
	private Long assigneeId;
	
	@JsonProperty("organization_id")
	private Long organizationId;
	
	private boolean hasIncidents;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss XXX")
	@JsonProperty("due_at")
	private Date dueAt;
	
	private String subject;
	private String description;
	
	@Transient
	private Organization organization;
	
	private List<Tag> tags;
	
	@JsonProperty("id")
	public String getId() {
		return this.id;
	}
	
	@JsonProperty("_id")
	public void setId(String id) {
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
	
	@JsonProperty(value="tags")
	public void setTags(List<Object> tags) {
		Object i = tags.get(0);
		if (i instanceof Tag) {
			this.tags = tags.stream().map(x -> (Tag) x).collect(Collectors.toList());
		} else if (i instanceof java.util.LinkedHashMap) {
			
			this.tags = tags.stream().map((x) -> {
				java.util.LinkedHashMap r = (java.util.LinkedHashMap) x;
				
				return new Tag((String) r.get("tag"));
			}).collect(Collectors.toList());
		} else if (i instanceof String) {
			this.tags = tags.stream().map(x -> new Tag((String) x)).collect(Collectors.toList());
		}
	}
	
	private static ObjectMapper mapper = new ObjectMapper();
	@Transient
	public String asJson() throws JsonProcessingException, JSONException {
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
	}
}
