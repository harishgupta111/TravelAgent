package com.travel.agent.model;

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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.travel.agent.model.enums.RecordCreatorType;

@Entity
@Table(name = "ta_StateMaster", uniqueConstraints = @UniqueConstraint(columnNames = {
		"stateName", "stateCode" }))
@Cache(region = "entity.ta_StateMaster", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class StateMaster extends SABaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8295791436096988607L;

	@Id
	@Column(name = "stateMasterID", insertable = false, updatable = false)
	private String stateMasterID;

	@NotEmpty(message = "State name cannot be empty")
	@Column(name = "stateName", nullable = false)
	private String stateName;

	@NotEmpty(message = "State code cannot be empty")
	@Column(name = "stateCode", nullable = false)
	private String stateCode;

	@Column(name = "unionTerritory")
	@Type(type = "yes_no")
	private Boolean unionTerritory;

	@JsonManagedReference
	@OneToMany(mappedBy = "stateMaster", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<LocationMaster> locationMasterSet;

	public class StateMasterBuilder {

		private String stateMasterID;
		private String stateName;
		private String stateCode;
		private Boolean unionTerritory;
		private Set<LocationMaster> locationMasterSet;
		private RecordCreatorType createdBy;
		private RecordCreatorType updatedBy;
		private Date createDate;

		public StateMasterBuilder createDate(Date val) {
			this.createDate = val;
			return this;
		}

		public StateMasterBuilder createdBy(RecordCreatorType val) {
			this.createdBy = val;
			return this;
		}

		public StateMasterBuilder updatedBy(RecordCreatorType val) {
			this.updatedBy = val;
			return this;
		}

		public StateMasterBuilder stateMasterId(String val) {
			this.stateMasterID = val;
			return this;
		}

		public StateMasterBuilder stateName(String val) {
			this.stateName = val;
			return this;
		}

		public StateMasterBuilder stateCode(String val) {
			this.stateCode = val;
			return this;
		}

		public StateMasterBuilder unionTerritory(Boolean val) {
			this.unionTerritory = val;
			return this;
		}

		public StateMasterBuilder locationMasterSet(Set<LocationMaster> val) {
			this.locationMasterSet = val;
			return this;
		}

		public StateMaster buildNew() {
			return new StateMaster(this);
		}

		public StateMaster update() {
			return updateStateMaster(this);
		}

		public StateMasterBuilder(StateMaster stateMaster) {
			this.stateMasterID = stateMaster.getStateMasterID();
			this.stateCode = stateMaster.stateCode;
			this.stateName = stateMaster.stateName;
			this.unionTerritory = stateMaster.unionTerritory;
			this.locationMasterSet = stateMaster.locationMasterSet;
			this.createdBy = stateMaster.getCreatedBy();
			this.updatedBy = stateMaster.getUpdatedBy();
			this.createDate = stateMaster.getCreateDate();
		}

		public StateMasterBuilder() {
		}
	}

	public StateMaster() {
		super();
	}

	public StateMaster updateStateMaster(StateMasterBuilder stateMasterBuilder) {
		this.stateMasterID = stateMasterBuilder.stateMasterID;
		this.stateCode = stateMasterBuilder.stateCode;
		this.stateName = stateMasterBuilder.stateName;
		this.unionTerritory = stateMasterBuilder.unionTerritory;
		this.locationMasterSet = stateMasterBuilder.locationMasterSet;
		super.setCreatedBy(stateMasterBuilder.createdBy);
		super.setUpdatedBy(stateMasterBuilder.updatedBy);
		super.setCreateDate(stateMasterBuilder.createDate);
		return this;
	}

	private StateMaster(StateMasterBuilder builder) {
		this.stateMasterID = builder.stateMasterID;
		this.stateCode = builder.stateCode;
		this.stateName = builder.stateName;
		this.unionTerritory = builder.unionTerritory;
		this.locationMasterSet = builder.locationMasterSet;
		super.setCreatedBy(builder.createdBy);
		super.setUpdatedBy(builder.updatedBy);
		super.setCreateDate(builder.createDate);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((stateCode == null) ? 0 : stateCode.hashCode());
		result = prime * result
				+ ((stateMasterID == null) ? 0 : stateMasterID.hashCode());
		result = prime * result
				+ ((stateName == null) ? 0 : stateName.hashCode());
		result = prime * result
				+ ((unionTerritory == null) ? 0 : unionTerritory.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StateMaster other = (StateMaster) obj;
		if (stateCode == null) {
			if (other.stateCode != null)
				return false;
		} else if (!stateCode.equals(other.stateCode))
			return false;
		if (stateMasterID == null) {
			if (other.stateMasterID != null)
				return false;
		} else if (!stateMasterID.equals(other.stateMasterID))
			return false;
		if (stateName == null) {
			if (other.stateName != null)
				return false;
		} else if (!stateName.equals(other.stateName))
			return false;
		if (unionTerritory == null) {
			if (other.unionTerritory != null)
				return false;
		} else if (!unionTerritory.equals(other.unionTerritory))
			return false;
		return true;
	}

	public String getStateMasterID() {
		return stateMasterID;
	}

	public void setStateMasterID(String stateMasterID) {
		this.stateMasterID = stateMasterID;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public Boolean getUnionTerritory() {
		return unionTerritory;
	}

	public void setUnionTerritory(Boolean unionTerritory) {
		this.unionTerritory = unionTerritory;
	}

	public Set<LocationMaster> getLocationMasterSet() {
		return locationMasterSet;
	}

	public void setLocationMasterSet(Set<LocationMaster> locationMasterSet) {
		this.locationMasterSet = locationMasterSet;
	}

}
