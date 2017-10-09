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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Document(indexName="organization")
@JsonIgnoreProperties(value={"facets"}, ignoreUnknown=true)
public class Organization {
	
	@Id
	@Field(type = FieldType.String, store = true)
	private Long id;
	
	private String url;
	
	@JsonProperty("external_id")
	private String externalUuid;
	
	private String name;
	private String details;
	
	private Date createdAt;
	
	@JsonProperty("domainNames")
	private List<DomainName> domainNames;
	
	@JsonProperty("shared_tickets")
	private boolean sharedTickets;
	
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

	public List<DomainName> getDomainNames() {
		return this.domainNames;
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

	
	public List<Tag> getTags() {
		return tags;
	}

	@JsonProperty(value="domain_names", access=Access.WRITE_ONLY)
	public void setRawDomainNames(List<String> domainNames) {
		this.domainNames = domainNames.stream().map(x -> new DomainName(x)).collect(Collectors.toList());
	}
	
	@JsonProperty(value="domainNames")
	public void setDomainNames(List<DomainName> domainNames) {
		this.domainNames = domainNames;
	}
	
	public boolean isSharedTickets() {
		return sharedTickets;
	}

	public void setSharedTickets(boolean sharedTickets) {
		this.sharedTickets = sharedTickets;
	}
	
	private static ObjectMapper mapper = new ObjectMapper();
	@Transient
	public String asJson() throws JsonProcessingException, JSONException {
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
	}
}
