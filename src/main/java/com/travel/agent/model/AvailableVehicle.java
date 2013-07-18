package com.travel.agent.model;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Type;

import com.travel.agent.model.enums.RecordCreatorType;
import com.travel.agent.model.enums.VehicleType;

@Entity
@Table(name = "ta_AvailableVehicle", uniqueConstraints = @UniqueConstraint(columnNames = {"plateNumber"}))
@Cache(region = "entity.ta_AvailableVehicle", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class AvailableVehicle extends SABaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -949426332114853193L;

	@Id
	@Column(name = "vehicleMasterID", insertable = false, updatable = false)
	private String vehicleMasterID;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "vehicleType")
	private VehicleType vehicleType;

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

		private String vehicleMasterID;
		private VehicleType vehicleType;
		private Date dateOfRunning;
		private Integer availableVehicleCount;
		private RecordCreatorType createdBy;
		private RecordCreatorType updatedBy;
		private Date createDate;
		private Boolean activeIndicator;
		private VehicleMaster vehicleMaster;
		
		
		public AvailableVehicleBuilder createdBy(RecordCreatorType val) {
			this.createdBy = val;
			return this;
		}

		public AvailableVehicleBuilder updatedBy(RecordCreatorType val) {
			this.updatedBy = val;
			return this;
		}

		public AvailableVehicleBuilder vehicleMasterID(String val) {
			this.vehicleMasterID = val;
			return this;
		}

		public AvailableVehicleBuilder vehicleType(VehicleType val) {
			this.vehicleType = val;
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
			this.vehicleMasterID = availableVehicle.vehicleMasterID;
			this.vehicleType = availableVehicle.vehicleType;
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
		this.vehicleMasterID = availableVehicleBuilder.vehicleMasterID;
		this.vehicleType = availableVehicleBuilder.vehicleType;
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
		this.vehicleMasterID = builder.vehicleMasterID;
		this.vehicleType = builder.vehicleType;
		this.availableVehicleCount = builder.availableVehicleCount;
		this.dateOfRunning = builder.dateOfRunning;
		this.activeIndicator = builder.activeIndicator;
		this.vehicleMaster = builder.vehicleMaster;
		super.setCreateDate(builder.createDate);
		super.setCreatedBy(builder.createdBy);
		super.setUpdatedBy(builder.updatedBy);
		
	}

	public String getVehicleMasterID() {
		return vehicleMasterID;
	}

	public void setVehicleMasterID(String vehicleMasterID) {
		this.vehicleMasterID = vehicleMasterID;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
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

}
