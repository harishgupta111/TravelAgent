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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.travel.agent.model.enums.RecordCreatorType;

@Entity
@Table(name = "ta_LocationMaster", 
uniqueConstraints = @UniqueConstraint(columnNames = {"locationName", "locationCode", "locationPin"}))
@Cache(region="entity.ta_LocationMaster", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class LocationMaster extends SABaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6386674469300627522L;
	
	@Id
	@Column(name = "locationMasterID", insertable = false, updatable = false)
	private String locationMasterID;
	
	@Column(name = "locationName", insertable = false, updatable = false)
	private String locationName;
	
	@Column(name = "locationCode", insertable = false, updatable = false)
	private String locationCode;
	
	@Column(name = "locationPin", insertable = false, updatable = false)
	private String locationPin;
	
	@JsonBackReference
	@Fetch(org.hibernate.annotations.FetchMode.JOIN)
	@JoinColumn(name = "stateMasterID", referencedColumnName = "stateMasterID")
	@ManyToOne(targetEntity = StateMaster.class, fetch = FetchType.LAZY)
	private StateMaster stateMaster;
	
	public class LocationMasterBuilder {
		
		private String locationMasterID;
		private String locationName;
		private String locationCode;
		private String locationPin;
		private StateMaster stateMaster;
		private RecordCreatorType createdBy;
		private RecordCreatorType updatedBy;
		private Date createDate;

		public LocationMasterBuilder locationMasterId(String val) {
			this.locationMasterID = val;
			return this;
		}

		
		public LocationMasterBuilder createDate(Date val) {
			this.createDate = val;
			return this;
		}

		public LocationMasterBuilder createdBy(RecordCreatorType val) {
			this.createdBy = val;
			return this;
		}

		public LocationMasterBuilder updatedBy(RecordCreatorType val) {
			this.updatedBy = val;
			return this;
		}

		public LocationMasterBuilder locationName(String val) {
			this.locationName = val;
			return this;
		}

		public LocationMasterBuilder locationCode(String val) {
			this.locationCode = val;
			return this;
		}

		public LocationMasterBuilder locationPin(String val) {
			this.locationPin = val;
			return this;
		}

		public LocationMasterBuilder stateMaster(StateMaster val)
		{
			this.stateMaster = val;
			return this;
		}
		
		public LocationMaster buildNew() {
			return new LocationMaster(this);
		}
		
		public LocationMaster update() {
			return updateLocationMaster(this);
		}

		
		public LocationMasterBuilder(){}
		
		public LocationMasterBuilder(LocationMaster locationMaster){
			
			this.locationMasterID = locationMaster.locationMasterID;
			this.locationName = locationMaster.locationName;
			this.locationCode = locationMaster.locationCode;
			this.locationPin = locationMaster.locationPin;
			this.stateMaster = locationMaster.stateMaster;
			this.createdBy = locationMaster.getCreatedBy();
			this.updatedBy = locationMaster.getUpdatedBy();
			this.createDate = locationMaster.getCreateDate();
		}
	}

	public LocationMaster() {
		super();
	}

	public LocationMaster updateLocationMaster(
			LocationMasterBuilder locationMasterBuilder) {
		super.setCreateDate(locationMasterBuilder.createDate);
		this.locationCode = locationMasterBuilder.locationCode;
		this.locationMasterID = locationMasterBuilder.locationMasterID;
		this.locationName  = locationMasterBuilder.locationName;
		this.locationPin  = locationMasterBuilder.locationPin;
		this.stateMaster = locationMasterBuilder.stateMaster;
		super.setCreatedBy(locationMasterBuilder.createdBy);
		super.setUpdatedBy(locationMasterBuilder.updatedBy);
		return this;
	}

	private LocationMaster(LocationMasterBuilder builder) {
		super.setCreateDate(builder.createDate);
		this.locationCode = builder.locationCode;
		this.locationMasterID = builder.locationMasterID;
		this.locationName  = builder.locationName;
		this.locationPin  = builder.locationPin;
		this.stateMaster = builder.stateMaster;
		super.setCreatedBy(builder.createdBy);
		super.setUpdatedBy(builder.updatedBy);
	}


	public String getLocationMasterID() {
		return locationMasterID;
	}

	public void setLocationMasterID(String locationMasterID) {
		this.locationMasterID = locationMasterID;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationPin() {
		return locationPin;
	}

	public void setLocationPin(String locationPin) {
		this.locationPin = locationPin;
	}
	
	public StateMaster getStateMaster() {
		return stateMaster;
	}

	public void setStateMaster(StateMaster stateMaster) {
		this.stateMaster = stateMaster;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((locationCode == null) ? 0 : locationCode.hashCode());
		result = prime * result
				+ ((locationMasterID == null) ? 0 : locationMasterID.hashCode());
		result = prime * result
				+ ((locationName == null) ? 0 : locationName.hashCode());
		result = prime * result
				+ ((locationPin == null) ? 0 : locationPin.hashCode());
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
		LocationMaster other = (LocationMaster) obj;
		if (locationCode == null) {
			if (other.locationCode != null)
				return false;
		} else if (!locationCode.equals(other.locationCode))
			return false;
		if (locationMasterID == null) {
			if (other.locationMasterID != null)
				return false;
		} else if (!locationMasterID.equals(other.locationMasterID))
			return false;
		if (locationName == null) {
			if (other.locationName != null)
				return false;
		} else if (!locationName.equals(other.locationName))
			return false;
		if (locationPin == null) {
			if (other.locationPin != null)
				return false;
		} else if (!locationPin.equals(other.locationPin))
			return false;
		return true;
	}
}
