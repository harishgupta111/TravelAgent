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
import org.hibernate.annotations.Type;

import com.travel.agent.model.enums.RecordCreatorType;

@Entity
@Table(name = "ta_AvailableVehicle")
@Cache(region = "entity.ta_AvailableVehicle", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class AvailableVehicle extends SABaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -949426332114853193L;

	@Id
	@Column(name = "availableVehicleID", insertable = false, updatable = false)
	private String availableVehicleID;

	@Column(name = "dateOfRunning")
	private Date dateOfRunning;

	@Column(name = "availableVehicleCount")
	private Integer availableVehicleCount;
	
	@Column(name = "activeIndicator")
	@Type(type = "yes_no")
	private Boolean activeIndicator;
	
	@Fetch(org.hibernate.annotations.FetchMode.JOIN)
	@JoinColumn(name = "vehicleMasterID", referencedColumnName = "vehicleMasterID")
	@ManyToOne(targetEntity = VehicleMaster.class, fetch = FetchType.LAZY)
	private VehicleMaster vehicleMaster;

	public class AvailableVehicleBuilder {

		private String availableVehicleID;
		private Date dateOfRunning;
		private Integer availableVehicleCount;
		private RecordCreatorType createdBy;
		private RecordCreatorType updatedBy;
		private Date createDate;
		private Boolean activeIndicator;
		private VehicleMaster vehicleMaster;

		public AvailableVehicleBuilder vehicleMaster(VehicleMaster val) {
			this.vehicleMaster = val;
			return this;
		}
		
		public AvailableVehicleBuilder createdBy(RecordCreatorType val) {
			this.createdBy = val;
			return this;
		}

		public AvailableVehicleBuilder updatedBy(RecordCreatorType val) {
			this.updatedBy = val;
			return this;
		}

		public AvailableVehicleBuilder availableVehicleID(String val) {
			this.availableVehicleID = val;
			return this;
		}

		public AvailableVehicleBuilder availableVehicleCount(Integer val) {
			this.availableVehicleCount = val;
			return this;
		}
		
		public AvailableVehicleBuilder activeIndicator(Boolean val) {
			this.activeIndicator = val;
			return this;
		}
		
		public AvailableVehicleBuilder dateOfRunning(Date val) {
			this.dateOfRunning = val;
			return this;
		}

		
		public AvailableVehicle buildNew() {
			return new AvailableVehicle(this);
		}
		
		public AvailableVehicle update() {
			return  updateAvailableVehicle(this);
		}
		
		public AvailableVehicleBuilder(){}
		
		public AvailableVehicleBuilder(AvailableVehicle availableVehicle) {
			this.availableVehicleID = availableVehicle.availableVehicleID;
			this.availableVehicleCount = availableVehicle.availableVehicleCount;
			this.dateOfRunning = availableVehicle.dateOfRunning;
			this.createdBy = availableVehicle.getCreatedBy();
			this.updatedBy = availableVehicle.getUpdatedBy();
			this.createDate = availableVehicle.getCreateDate();
			this.activeIndicator = availableVehicle.activeIndicator;
			this.vehicleMaster = availableVehicle.vehicleMaster;
		}
	}

	public AvailableVehicle() {
		super();
	}

	public AvailableVehicle updateAvailableVehicle(
			AvailableVehicleBuilder availableVehicleBuilder) {
		this.availableVehicleID = availableVehicleBuilder.availableVehicleID;
		this.availableVehicleCount = availableVehicleBuilder.availableVehicleCount;
		this.dateOfRunning = availableVehicleBuilder.dateOfRunning;
		this.activeIndicator = availableVehicleBuilder.activeIndicator;
		this.vehicleMaster = availableVehicleBuilder.vehicleMaster;
		super.setCreatedBy(availableVehicleBuilder.createdBy);
		super.setUpdatedBy(availableVehicleBuilder.updatedBy);
		super.setCreateDate(availableVehicleBuilder.createDate);
		return this;
	}

	public AvailableVehicle(AvailableVehicleBuilder builder) {
		this.availableVehicleID = builder.availableVehicleID;
		this.availableVehicleCount = builder.availableVehicleCount;
		this.dateOfRunning = builder.dateOfRunning;
		this.activeIndicator = builder.activeIndicator;
		this.vehicleMaster = builder.vehicleMaster;
		super.setCreateDate(builder.createDate);
		super.setCreatedBy(builder.createdBy);
		super.setUpdatedBy(builder.updatedBy);
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((activeIndicator == null) ? 0 : activeIndicator.hashCode());
		result = prime
				* result
				+ ((availableVehicleCount == null) ? 0 : availableVehicleCount
						.hashCode());
		result = prime
				* result
				+ ((availableVehicleID == null) ? 0 : availableVehicleID
						.hashCode());
		result = prime * result
				+ ((dateOfRunning == null) ? 0 : dateOfRunning.hashCode());
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
		AvailableVehicle other = (AvailableVehicle) obj;
		if (activeIndicator == null) {
			if (other.activeIndicator != null)
				return false;
		} else if (!activeIndicator.equals(other.activeIndicator))
			return false;
		if (availableVehicleCount == null) {
			if (other.availableVehicleCount != null)
				return false;
		} else if (!availableVehicleCount.equals(other.availableVehicleCount))
			return false;
		if (availableVehicleID == null) {
			if (other.availableVehicleID != null)
				return false;
		} else if (!availableVehicleID.equals(other.availableVehicleID))
			return false;
		if (dateOfRunning == null) {
			if (other.dateOfRunning != null)
				return false;
		} else if (!dateOfRunning.equals(other.dateOfRunning))
			return false;
		return true;
	}

	public Date getDateOfRunning() {
		return dateOfRunning;
	}

	public void setDateOfRunning(Date dateOfRunning) {
		this.dateOfRunning = dateOfRunning;
	}

	public Integer getAvailableVehicleCount() {
		return availableVehicleCount;
	}

	public void setAvailableVehicleCount(Integer availableVehicleCount) {
		this.availableVehicleCount = availableVehicleCount;
	}

	public Boolean getActiveIndicator() {
		return activeIndicator;
	}

	public void setActiveIndicator(Boolean activeIndicator) {
		this.activeIndicator = activeIndicator;
	}

	public VehicleMaster getVehicleMaster() {
		return vehicleMaster;
	}

	public void setVehicleMaster(VehicleMaster vehicleMaster) {
		this.vehicleMaster = vehicleMaster;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAvailableVehicleID() {
		return availableVehicleID;
	}

	public void setAvailableVehicleID(String availableVehicleID) {
		this.availableVehicleID = availableVehicleID;
	}
	
}
