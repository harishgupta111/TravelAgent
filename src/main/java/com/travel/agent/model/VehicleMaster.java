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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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

	@Column(name = "noOfSeats")
	private Integer noOfSeats;

	@Column(name = "plateNumber")
	private String plateNumber;

	@Column(name = "vehicleCount")
	private Integer vehicleCount;

	@Column(name = "availableVehicleCount")
	private Integer availableVehicleCount;

	@OneToMany(mappedBy = "vehicleMaster", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<Booking> bookingSet;

	public class VehicleMasterBuilder {

		private String vehicleMasterID;
		private VehicleType vehicleType;
		private String make;
		private String modelName;
		private Integer modelYear;
		private Integer noOfSeats;
		private String plateNumber;
		private Integer vehicleCount;
		private Integer availableVehicleCount;
		private Set<Booking> bookingSet;
		private RecordCreatorType createdBy;
		private RecordCreatorType updatedBy;
		private Date createDate;
		
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

		public VehicleMasterBuilder availableVehicleCount(Integer val) {
			this.availableVehicleCount = val;
			return this;
		}

		public VehicleMasterBuilder bookingSet(Set<Booking> val) {
			this.bookingSet = val;
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
			this.availableVehicleCount = vehicleMaster.availableVehicleCount;
			this.bookingSet = vehicleMaster.bookingSet;
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
		this.availableVehicleCount = vehicleMasterBuilder.availableVehicleCount;
		this.bookingSet = vehicleMasterBuilder.bookingSet;
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
		this.availableVehicleCount = builder.availableVehicleCount;
		this.bookingSet = builder.bookingSet;
		super.setCreateDate(builder.createDate);
		super.setCreatedBy(builder.createdBy);
		super.setUpdatedBy(builder.updatedBy);
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((availableVehicleCount == null) ? 0 : availableVehicleCount
						.hashCode());
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
		if (availableVehicleCount == null) {
			if (other.availableVehicleCount != null)
				return false;
		} else if (!availableVehicleCount.equals(other.availableVehicleCount))
			return false;
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

	public Integer getAvailableVehicleCount() {
		return availableVehicleCount;
	}

	public void setAvailableVehicleCount(Integer availableVehicleCount) {
		this.availableVehicleCount = availableVehicleCount;
	}

	public Set<Booking> getBookingSet() {
		return bookingSet;
	}

	public void setBookingSet(Set<Booking> bookingSet) {
		this.bookingSet = bookingSet;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
