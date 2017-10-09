package me.sevif.chaoschair.db.entity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Document(indexName="organization")
@JsonIgnoreProperties(value={"facets"}, ignoreUnknown=true)
public class Organization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Field(type = FieldType.String, store = true)
	private Long id;
	
	private String url;
	
	@JsonProperty("external_id")
	private String externalUuid;
	
	private String name;
	private String details;
	
	private Date createdAt;
	
	@JsonProperty("domain_names")
	private List<DomainName> domainNames;
	
	@JsonProperty("shared_tickets")
	private boolean sharedTickets;
	
	
	@JsonProperty("tags")
	private List<Tag> tags;

	
	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	@JsonProperty("_id")
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@JsonProperty("domain_names")
	public List<DomainName> getDomainNames() {
		return this.domainNames;
	}

	public List<Tag> getTags() {
		return tags;
	}

	@JsonProperty(value="domain_names", access=Access.WRITE_ONLY)
	public void setRawDomainNames(List<String> domainNames) {
		this.domainNames = domainNames.stream().map(x -> new DomainName(x)).collect(Collectors.toList());
	}
	
	public void setDomainNames(List<DomainName> domainNames) {
		this.domainNames = domainNames;
	}

	@JsonProperty(value="tags", access=Access.WRITE_ONLY)
	public void setRawTags(List<String> tags) {
		this.tags = tags.stream().map(x -> new Tag(x)).collect(Collectors.toList());
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public boolean isSharedTickets() {
		return sharedTickets;
	}

	public void setSharedTickets(boolean sharedTickets) {
		this.sharedTickets = sharedTickets;
	}
}
