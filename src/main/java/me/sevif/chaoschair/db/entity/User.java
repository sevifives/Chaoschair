
package me.sevif.chaoschair.db.entity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Document(indexName="user_")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Field(type = FieldType.String, store = true)
	private Long id;
	
	private String url;
	
	@JsonProperty("external_id")
	private String externalUuid;
	
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
	
	@JsonProperty("last_login_at")
	@Temporal(value=TemporalType.TIMESTAMP)
	//"2013-08-04T01:03:27 -10:00"
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss XXX")
	private Date lastLoginAt;
	
	@JsonProperty("created_at")
	@Temporal(value=TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss XXX")
	private Date createdAt;
	
	// actual associations
	@JsonProperty("organization_id")
	private Long organizationId;
	
	private List<Tag> tags;
	
	private Role role;

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

	public void setExternalId(String externalUuid) {
		this.externalUuid = externalUuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Date getLastLoginAt() {
		return lastLoginAt;
	}

	public void setLastLoginAt(Date lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Tag> getTags() {
		return tags;
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	@JsonProperty(value="tags", access=Access.WRITE_ONLY)
	public void setRawTags(List<String> tags) {
		this.tags = tags.stream().map(x -> new Tag(x)).collect(Collectors.toList());
	}
	
}
