package com.travel.agent.model;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.travel.agent.model.enums.RecordCreatorType;

@Entity
@Table(name = "ta_UserAuthorityMaster")
@Cache(region = "entity.ta_UserAuthorityMaster", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class UserAuthorityMaster extends SABaseEntity implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3944298067197651649L;
	
	@Id
	@Column(name = "userAuthorityMasterId", insertable = false, updatable = false)
	private String userAuthorityMasterId;
	
	@JsonBackReference
	@Fetch(org.hibernate.annotations.FetchMode.JOIN)
	@JoinColumn(name = "userID", referencedColumnName = "userID")
	@ManyToOne(targetEntity = UserMaster.class, fetch = FetchType.LAZY)
	private UserMaster userMaster;
	
	@Column(name = "authority")
	private String authority;

	@Override
	public String getAuthority() {
		return this.authority;
	}
	
	public UserAuthorityMaster() {
		super();
	}



	public class UserAuthorityMasterBuilder
	{
		private String userAuthorityMasterId;
		private UserMaster userMaster;
		private String authority;
		private RecordCreatorType createdBy;
		private RecordCreatorType updatedBy;
		private Date createDate;
		
		public UserAuthorityMasterBuilder createDate(Date val) {
			this.createDate = val;
			return this;
		}

		public UserAuthorityMasterBuilder updatedBy(RecordCreatorType val) {
			this.updatedBy = val;
			return this;
		}

		public UserAuthorityMasterBuilder createdBy(RecordCreatorType val) {
			this.createdBy = val;
			return this;
		}

		public UserAuthorityMasterBuilder authority(String val) {
			this.authority = val;
			return this;
		}

		public UserAuthorityMasterBuilder userMaster(UserMaster val) {
			this.userMaster = val;
			return this;
		}

		public UserAuthorityMasterBuilder userAuthorityMasterId(String val) {
			this.userAuthorityMasterId = val;
			return this;
		}
	
		public UserAuthorityMaster buildNew() {
			return new UserAuthorityMaster(this);
		}

		public UserAuthorityMaster update() {
			return updateUserAuthorityMaster(this);
		}

		public UserAuthorityMasterBuilder() {
		}

		public UserAuthorityMasterBuilder(UserAuthorityMaster userAuthorityMaster) {
			this.authority = userAuthorityMaster.authority;
			this.userAuthorityMasterId = userAuthorityMaster.userAuthorityMasterId;
			this.userMaster = userAuthorityMaster.userMaster;
			this.createdBy = userAuthorityMaster.getCreatedBy();
			this.updatedBy = userAuthorityMaster.getUpdatedBy();
			this.createDate = userAuthorityMaster.getCreateDate();
		}

	}
	
	public UserAuthorityMaster(UserAuthorityMasterBuilder builder) {
		this.userAuthorityMasterId = builder.userAuthorityMasterId;
		this.authority = builder.authority;
		this.userMaster = builder.userMaster;
		super.setCreateDate(builder.createDate);
		super.setCreatedBy(builder.createdBy);
		super.setUpdatedBy(builder.updatedBy);

	}

	public UserAuthorityMaster updateUserAuthorityMaster(UserAuthorityMasterBuilder builder) {
		this.userAuthorityMasterId = builder.userAuthorityMasterId;
		this.authority = builder.authority;
		this.userMaster = builder.userMaster;
		super.setCreatedBy(builder.createdBy);
		super.setUpdatedBy(builder.updatedBy);
		super.setCreateDate(builder.createDate);
		return this;
	}

	
	public String getUserAuthorityMasterId() {
		return userAuthorityMasterId;
	}

	public void setUserAuthorityMasterId(String userAuthorityMasterId) {
		this.userAuthorityMasterId = userAuthorityMasterId;
	}

	public UserMaster getUserMaster() {
		return userMaster;
	}

	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		result = prime
				* result
				+ ((userAuthorityMasterId == null) ? 0 : userAuthorityMasterId
						.hashCode());
		result = prime * result
				+ ((userMaster == null) ? 0 : userMaster.hashCode());
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
		UserAuthorityMaster other = (UserAuthorityMaster) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (userAuthorityMasterId == null) {
			if (other.userAuthorityMasterId != null)
				return false;
		} else if (!userAuthorityMasterId.equals(other.userAuthorityMasterId))
			return false;
		if (userMaster == null) {
			if (other.userMaster != null)
				return false;
		} else if (!userMaster.equals(other.userMaster))
			return false;
		return true;
	}
	
}
