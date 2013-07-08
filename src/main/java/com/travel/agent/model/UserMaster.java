package com.travel.agent.model;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.security.core.userdetails.UserDetails;

import com.travel.agent.model.enums.RecordCreatorType;

@Entity
@Table(name = "ta_UserMaster")
@Cache(region = "entity.ta_UserMaster", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class UserMaster extends SABaseEntity implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4153994237761419631L;

	public UserMaster() {
		super();
	}

	@Id
	@Column(name = "userId", insertable = false, updatable = false)
	private String userId;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	@Type(type = "yes_no")
	private Boolean enabled;

	@Column(name = "credentialsNonExpired")
	@Type(type = "yes_no")
	private Boolean credentialsNonExpired;

	@Column(name = "accountNonLocked")
	@Type(type = "yes_no")
	private Boolean accountNonLocked;

	@Column(name = "accountNonExpired")
	@Type(type = "yes_no")
	private Boolean accountNonExpired;

	@Column(name = "email")
	private String email;

	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "userMaster", fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
	private Set<UserAuthorityMaster> userAuthorityMasterSet;

	public class UserMasterBuilder {

		private String userId;
		private String username;
		private String password;
		private String email;
		private String name;
		private RecordCreatorType createdBy;
		private RecordCreatorType updatedBy;
		private Date createDate;
		private Boolean enabled;
		private Boolean credentialsNonExpired;
		private Boolean accountNonLocked;
		private Boolean accountNonExpired;
		private Set<UserAuthorityMaster> userAuthorityMasterSet;
		
		public UserMasterBuilder userAuthorityMasterSet(Set<UserAuthorityMaster> val) {
			this.userAuthorityMasterSet = val;
			return this;
		}

		public UserMasterBuilder enabled(Boolean val) {
			this.enabled = val;
			return this;
		}

		public UserMasterBuilder credentialsNonExpired(Boolean val) {
			this.credentialsNonExpired = val;
			return this;
		}

		public UserMasterBuilder accountNonLocked(Boolean val) {
			this.accountNonLocked = val;
			return this;
		}

		public UserMasterBuilder accountNonExpired(Boolean val) {
			this.accountNonExpired = val;
			return this;
		}

		public UserMasterBuilder createDate(Date val) {
			this.createDate = val;
			return this;
		}

		public UserMasterBuilder createdBy(RecordCreatorType val) {
			this.createdBy = val;
			return this;
		}

		public UserMasterBuilder updatedBy(RecordCreatorType val) {
			this.updatedBy = val;
			return this;
		}

		public UserMasterBuilder userId(String val) {
			this.userId = val;
			return this;
		}

		public UserMasterBuilder username(String val) {
			this.username = val;
			return this;
		}

		public UserMasterBuilder password(String val) {
			this.password = val;
			return this;
		}

		public UserMasterBuilder email(String val) {
			this.email = val;
			return this;
		}

		public UserMaster buildNew() {
			return new UserMaster(this);
		}

		public UserMaster update() {
			return updateUserMaster(this);
		}

		public UserMasterBuilder() {
		}

		public UserMasterBuilder(UserMaster userMaster) {
			this.userId = userMaster.userId;
			this.username = userMaster.username;
			this.password = userMaster.password;
			this.email = userMaster.email;
			this.name = userMaster.name;
			this.createdBy = userMaster.getCreatedBy();
			this.updatedBy = userMaster.getUpdatedBy();
			this.createDate = userMaster.getCreateDate();
			this.enabled = userMaster.enabled;
			this.credentialsNonExpired = userMaster.credentialsNonExpired;
			this.accountNonLocked = userMaster.accountNonLocked;
			this.accountNonExpired = userMaster.accountNonExpired;
			this.userAuthorityMasterSet = userMaster.userAuthorityMasterSet;

		}
	}

	public UserMaster(UserMasterBuilder userMasterBuilder) {
		this.userId = userMasterBuilder.userId;
		this.username = userMasterBuilder.username;
		this.password = userMasterBuilder.password;
		this.email = userMasterBuilder.email;
		this.name = userMasterBuilder.name;
		super.setCreateDate(userMasterBuilder.createDate);
		super.setCreatedBy(userMasterBuilder.createdBy);
		super.setUpdatedBy(userMasterBuilder.updatedBy);
		this.enabled = userMasterBuilder.enabled;
		this.credentialsNonExpired = userMasterBuilder.credentialsNonExpired;
		this.accountNonLocked = userMasterBuilder.accountNonLocked;
		this.accountNonExpired = userMasterBuilder.accountNonExpired;
		this.userAuthorityMasterSet = userMasterBuilder.userAuthorityMasterSet;

	}

	public UserMaster updateUserMaster(UserMasterBuilder userMasterBuilder) {
		this.userId = userMasterBuilder.userId;
		this.username = userMasterBuilder.username;
		this.password = userMasterBuilder.password;
		this.email = userMasterBuilder.email;
		this.name = userMasterBuilder.name;
		super.setCreatedBy(userMasterBuilder.createdBy);
		super.setUpdatedBy(userMasterBuilder.updatedBy);
		super.setCreateDate(userMasterBuilder.createDate);
		this.credentialsNonExpired = userMasterBuilder.credentialsNonExpired;
		this.accountNonLocked = userMasterBuilder.accountNonLocked;
		this.accountNonExpired = userMasterBuilder.accountNonExpired;
		this.userAuthorityMasterSet = userMasterBuilder.userAuthorityMasterSet;
		return this;
	}
	
	@Override
	public Collection<UserAuthorityMaster> getAuthorities() {
		return this.userAuthorityMasterSet;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.getAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.getAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.getCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return this.getEnabled();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	
	public Set<UserAuthorityMaster> getUserAuthorityMasterSet() {
		return userAuthorityMasterSet;
	}

	public void setUserAuthorityMasterSet(
			Set<UserAuthorityMaster> userAuthorityMasterSet) {
		this.userAuthorityMasterSet = userAuthorityMasterSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((accountNonExpired == null) ? 0 : accountNonExpired
						.hashCode());
		result = prime
				* result
				+ ((accountNonLocked == null) ? 0 : accountNonLocked.hashCode());
		result = prime
				* result
				+ ((credentialsNonExpired == null) ? 0 : credentialsNonExpired
						.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserMaster other = (UserMaster) obj;
		if (accountNonExpired == null) {
			if (other.accountNonExpired != null)
				return false;
		} else if (!accountNonExpired.equals(other.accountNonExpired))
			return false;
		if (accountNonLocked == null) {
			if (other.accountNonLocked != null)
				return false;
		} else if (!accountNonLocked.equals(other.accountNonLocked))
			return false;
		if (credentialsNonExpired == null) {
			if (other.credentialsNonExpired != null)
				return false;
		} else if (!credentialsNonExpired.equals(other.credentialsNonExpired))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}
