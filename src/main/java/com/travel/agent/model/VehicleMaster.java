package com.travel.agent.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import com.travel.agent.model.enums.RecordCreatorType;
import com.travel.agent.model.enums.VehicleType;

@Entity
@Table(name = "ta_VehicleMaster", uniqueConstraints = @UniqueConstraint(columnNames = {"plateNumber"}))
@Cache(region = "entity.ta_vehicleMaster", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class VehicleMaster extends SABaseEntity {

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

	@Column(name = "make")
	private String make;

	@Column(name = "modelName")
	private String modelName;
	
	@Column(name = "modelYear")
	private Integer modelYear;

	@Min(value=3,message = "Number of seats should greater be than 3")
	@NotNull(message="Number of seats cannot be null")
	@Column(name = "noOfSeats")
	private Integer noOfSeats;
	
	@NotEmpty(message = "PlateNumber cannot be empty")
	@Column(name = "plateNumber")
	private String plateNumber;
	
	@NotNull(message="Vehicle Count cannot be null")
	@Column(name = "vehicleCount")
	private Integer vehicleCount;
	
	@OneToMany(mappedBy = "vehicleMaster", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<AvailableVehicle> availableVehicleSet;
	
	@OneToMany(mappedBy = "vehicleMaster", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<JourneyMaster> journeyMasterSet;

	public class VehicleMasterBuilder {

		private String vehicleMasterID;
		private VehicleType vehicleType;
		private String make;
		private String modelName;
		private Integer modelYear;
		private Integer noOfSeats;
		private String plateNumber;
		private Integer vehicleCount;
		private Set<AvailableVehicle> availableVehicleSet;
		private RecordCreatorType createdBy;
		private RecordCreatorType updatedBy;
		private Date createDate;
		
		public VehicleMasterBuilder availableVehicleSet(Set<AvailableVehicle> val) {
			this.availableVehicleSet = val;
			return this;
		}
		
		public VehicleMasterBuilder createDate(Date val) {
			this.createDate = val;
			return this;
		}
		

		public VehicleMasterBuilder createdBy(RecordCreatorType val) {
			this.createdBy = val;
			return this;
		}

		public VehicleMasterBuilder updatedBy(RecordCreatorType val) {
			this.updatedBy = val;
			return this;
		}

		public VehicleMasterBuilder vehicleMasterID(String val) {
			this.vehicleMasterID = val;
			return this;
		}

		public VehicleMasterBuilder vehicleType(VehicleType val) {
			this.vehicleType = val;
			return this;
		}

		public VehicleMasterBuilder make(String val) {
			this.make = val;
			return this;
		}

		public VehicleMasterBuilder modelName(String val) {
			this.modelName = val;
			return this;
		}

		public VehicleMasterBuilder modelYear(Integer val) {
			this.modelYear = val;
			return this;
		}

		public VehicleMasterBuilder noOfSeats(Integer val) {
			this.noOfSeats = val;
			return this;
		}

		public VehicleMasterBuilder plateNumber(String val) {
			this.plateNumber = val;
			return this;
		}

		public VehicleMasterBuilder vehicleCount(Integer val) {
			this.vehicleCount = val;
			return this;
		}

		public VehicleMaster buildNew() {
			return new VehicleMaster(this);
		}
		
		public VehicleMaster update() {
			return  updateVehicleMaster(this);
		}
		
		public VehicleMasterBuilder(){}
		
		public VehicleMasterBuilder(VehicleMaster vehicleMaster) {
			this.vehicleMasterID = vehicleMaster.vehicleMasterID;
			this.vehicleType = vehicleMaster.vehicleType;
			this.make = vehicleMaster.make;
			this.modelName = vehicleMaster.modelName;
			this.modelYear = vehicleMaster.modelYear;
			this.noOfSeats = vehicleMaster.noOfSeats;
			this.plateNumber = vehicleMaster.plateNumber;
			this.vehicleCount = vehicleMaster.vehicleCount;
			this.availableVehicleSet = vehicleMaster.availableVehicleSet;
			this.createdBy = vehicleMaster.getCreatedBy();
			this.updatedBy = vehicleMaster.getUpdatedBy();
			this.createDate = vehicleMaster.getCreateDate();
			
		}
	}

	public VehicleMaster() {
		super();
	}

	public VehicleMaster updateVehicleMaster(
			VehicleMasterBuilder vehicleMasterBuilder) {
		this.vehicleMasterID = vehicleMasterBuilder.vehicleMasterID;
		this.vehicleType = vehicleMasterBuilder.vehicleType;
		this.make = vehicleMasterBuilder.make;
		this.modelName = vehicleMasterBuilder.modelName;
		this.modelYear = vehicleMasterBuilder.modelYear;
		this.noOfSeats = vehicleMasterBuilder.noOfSeats;
		this.plateNumber = vehicleMasterBuilder.plateNumber;
		this.vehicleCount = vehicleMasterBuilder.vehicleCount;
		this.availableVehicleSet = vehicleMasterBuilder.availableVehicleSet;
		super.setCreatedBy(vehicleMasterBuilder.createdBy);
		super.setUpdatedBy(vehicleMasterBuilder.updatedBy);
		super.setCreateDate(vehicleMasterBuilder.createDate);
		return this;
	}

	public VehicleMaster(VehicleMasterBuilder builder) {
		this.vehicleMasterID = builder.vehicleMasterID;
		this.vehicleType = builder.vehicleType;
		this.make = builder.make;
		this.modelName = builder.modelName;
		this.modelYear = builder.modelYear;
		this.noOfSeats = builder.noOfSeats;
		this.plateNumber = builder.plateNumber;
		this.vehicleCount = builder.vehicleCount;
		this.availableVehicleSet = builder.availableVehicleSet;
		super.setCreateDate(builder.createDate);
		super.setCreatedBy(builder.createdBy);
		super.setUpdatedBy(builder.updatedBy);
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result
				+ ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result
				+ ((modelYear == null) ? 0 : modelYear.hashCode());
		result = prime * result
				+ ((noOfSeats == null) ? 0 : noOfSeats.hashCode());
		result = prime * result
				+ ((plateNumber == null) ? 0 : plateNumber.hashCode());
		result = prime * result
				+ ((vehicleCount == null) ? 0 : vehicleCount.hashCode());
		result = prime * result
				+ ((vehicleMasterID == null) ? 0 : vehicleMasterID.hashCode());
		result = prime * result
				+ ((vehicleType == null) ? 0 : vehicleType.hashCode());
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
		VehicleMaster other = (VehicleMaster) obj;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		if (modelYear == null) {
			if (other.modelYear != null)
				return false;
		} else if (!modelYear.equals(other.modelYear))
			return false;
		if (noOfSeats == null) {
			if (other.noOfSeats != null)
				return false;
		} else if (!noOfSeats.equals(other.noOfSeats))
			return false;
		if (plateNumber == null) {
			if (other.plateNumber != null)
				return false;
		} else if (!plateNumber.equals(other.plateNumber))
			return false;
		if (vehicleCount == null) {
			if (other.vehicleCount != null)
				return false;
		} else if (!vehicleCount.equals(other.vehicleCount))
			return false;
		if (vehicleMasterID == null) {
			if (other.vehicleMasterID != null)
				return false;
		} else if (!vehicleMasterID.equals(other.vehicleMasterID))
			return false;
		if (vehicleType != other.vehicleType)
			return false;
		return true;
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

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Integer getModelYear() {
		return modelYear;
	}

	public void setModelYear(Integer modelYear) {
		this.modelYear = modelYear;
	}

	public Integer getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public Integer getVehicleCount() {
		return vehicleCount;
	}

	public void setVehicleCount(Integer vehicleCount) {
		this.vehicleCount = vehicleCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<AvailableVehicle> getAvailableVehicleSet() {
		return availableVehicleSet;
	}

	public void setAvailableVehicleSet(Set<AvailableVehicle> availableVehicleSet) {
		this.availableVehicleSet = availableVehicleSet;
	}

	public Set<JourneyMaster> getJourneyMasterSet() {
		return journeyMasterSet;
	}

	public void setJourneyMasterSet(Set<JourneyMaster> journeyMasterSet) {
		this.journeyMasterSet = journeyMasterSet;
	}
	
}
