package com.travel.agent.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;

import com.travel.agent.model.enums.RecordCreatorType;

@Entity
@Table(name = "ta_Booking")
@Cache(region = "entity.ta_Booking", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class Booking extends SABaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4186274679681720713L;

	@Id
	@Column(name = "bookingID", insertable = false, updatable = false)
	private String bookingID;

	@Column(name = "originLocationCode")
	private String originLocationCode;

	@Column(name = "destinationLocationCode")
	private String destinationLocationCode;

	@Column(name = "dateOfJourney")
	private Date dateOfJourney;

	@Column(name = "noOfPassenger")
	private Integer noOfPassenger;

	@Column(name = "totalCollectable")
	private Double totalCollectable;

	@Column(name = "dateOfBooking")
	private Date dateOfBooking;

	@Fetch(org.hibernate.annotations.FetchMode.JOIN)
	@JoinColumn(name = "vehicleMasterID", referencedColumnName = "vehicleMasterID")
	@ManyToOne(targetEntity = VehicleMaster.class, fetch = FetchType.LAZY)
	private VehicleMaster vehicleMaster;

	@OneToMany(mappedBy = "booking", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<PassengerDetail> passengerDetailSet;

	public class BookingBuilder {
 
		private String bookingID;
		private String origin;
		private String destination;
		private Date dateOfJourney;
		private Integer noOfPassenger;
		private Double totalCollectable;
		private Date dateOfBooking;
		private VehicleMaster vehicleMaster;
		private Set<PassengerDetail> passengerDetailSet;
		private RecordCreatorType createdBy;
		private RecordCreatorType updatedBy;
		private Date createDate;

		public BookingBuilder bookingID(String val) {
			this.bookingID = val;
			return this;
		}
		
		public BookingBuilder createDate(Date val) {
			this.createDate = val;
			return this;
		}

		public BookingBuilder createdBy(RecordCreatorType val) {
			this.createdBy = val;
			return this;
		}

		public BookingBuilder updatedBy(RecordCreatorType val) {
			this.updatedBy = val;
			return this;
		}

		public BookingBuilder origin(String val) {
			this.origin = val;
			return this;
		}

		public BookingBuilder destination(String val) {
			this.destination = val;
			return this;
		}

		public BookingBuilder dateOfJourney(Date val) {
			this.dateOfJourney = val;
			return this;
		}

		public BookingBuilder noOfPassenger(Integer val) {
			this.noOfPassenger = val;
			return this;
		}

		public BookingBuilder totalCollectable(Double val) {
			this.totalCollectable = val;
			return this;
		}

		public BookingBuilder dateOfBooking(Date val) {
			this.dateOfBooking = val;
			return this;
		}

		public BookingBuilder vehicleMaster(VehicleMaster val) {
			this.vehicleMaster = val;
			return this;
		}

		public BookingBuilder passengerDetailSet(Set<PassengerDetail> val) {
			this.passengerDetailSet = val;
			return this;
		}

		public Booking buildNew() {
			return new Booking(this);
		}

		public Booking update() {
			return updateBooking(this);
		}

		public BookingBuilder() {
		}

		public BookingBuilder(Booking booking) {
			this.bookingID = booking.bookingID;
			this.origin = booking.originLocationCode;
			this.destination = booking.destinationLocationCode;
			this.dateOfJourney = booking.dateOfJourney;
			this.noOfPassenger = booking.noOfPassenger;
			this.totalCollectable = booking.totalCollectable;
			this.dateOfBooking = booking.dateOfBooking;
			this.vehicleMaster = booking.vehicleMaster;
			this.passengerDetailSet = booking.passengerDetailSet;
			this.createDate = booking.getCreateDate();
			this.createdBy = booking.getCreatedBy();
			this.updatedBy = booking.getUpdatedBy();
		}

	}

	public Booking() {
		super();
	}

	public Booking updateBooking(BookingBuilder bookingBuilder) {
		this.bookingID = bookingBuilder.bookingID;
		this.originLocationCode = bookingBuilder.origin;
		this.destinationLocationCode = bookingBuilder.destination;
		this.dateOfJourney = bookingBuilder.dateOfJourney;
		this.noOfPassenger = bookingBuilder.noOfPassenger;
		this.totalCollectable = bookingBuilder.totalCollectable;
		this.dateOfBooking = bookingBuilder.dateOfBooking;
		this.vehicleMaster = bookingBuilder.vehicleMaster;
		this.passengerDetailSet = bookingBuilder.passengerDetailSet;
		super.setCreatedBy(bookingBuilder.createdBy);
		super.setUpdatedBy(bookingBuilder.updatedBy);
		super.setCreateDate(bookingBuilder.createDate);
		return this;
	}

	private Booking(BookingBuilder builder) {
		this.bookingID = builder.bookingID;
		this.originLocationCode = builder.origin;
		this.destinationLocationCode = builder.destination;
		this.dateOfBooking = builder.dateOfBooking;
		this.dateOfJourney = builder.dateOfJourney;
		this.noOfPassenger = builder.noOfPassenger;
		this.passengerDetailSet = builder.passengerDetailSet;
		this.totalCollectable = builder.totalCollectable;
		this.vehicleMaster = builder.vehicleMaster;
		super.setCreatedBy(builder.createdBy);
		super.setUpdatedBy(builder.updatedBy);
		super.setCreateDate(builder.createDate);
	}

	@Override
	public int hashCode() {
		//comment
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((bookingID == null) ? 0 : bookingID.hashCode());
		result = prime * result
				+ ((dateOfBooking == null) ? 0 : dateOfBooking.hashCode());
		result = prime * result
				+ ((dateOfJourney == null) ? 0 : dateOfJourney.hashCode());
		result = prime
				* result
				+ ((destinationLocationCode == null) ? 0
						: destinationLocationCode.hashCode());
		result = prime * result
				+ ((noOfPassenger == null) ? 0 : noOfPassenger.hashCode());
		result = prime
				* result
				+ ((originLocationCode == null) ? 0 : originLocationCode
						.hashCode());
		result = prime
				* result
				+ ((totalCollectable == null) ? 0 : totalCollectable.hashCode());
		result = prime * result
				+ ((vehicleMaster == null) ? 0 : vehicleMaster.hashCode());
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
		Booking other = (Booking) obj;
		if (bookingID == null) {
			if (other.bookingID != null)
				return false;
		} else if (!bookingID.equals(other.bookingID))
			return false;
		if (dateOfBooking == null) {
			if (other.dateOfBooking != null)
				return false;
		} else if (!dateOfBooking.equals(other.dateOfBooking))
			return false;
		if (dateOfJourney == null) {
			if (other.dateOfJourney != null)
				return false;
		} else if (!dateOfJourney.equals(other.dateOfJourney))
			return false;
		if (destinationLocationCode == null) {
			if (other.destinationLocationCode != null)
				return false;
		} else if (!destinationLocationCode
				.equals(other.destinationLocationCode))
			return false;
		if (noOfPassenger == null) {
			if (other.noOfPassenger != null)
				return false;
		} else if (!noOfPassenger.equals(other.noOfPassenger))
			return false;
		if (originLocationCode == null) {
			if (other.originLocationCode != null)
				return false;
		} else if (!originLocationCode.equals(other.originLocationCode))
			return false;
		if (totalCollectable == null) {
			if (other.totalCollectable != null)
				return false;
		} else if (!totalCollectable.equals(other.totalCollectable))
			return false;
		if (vehicleMaster == null) {
			if (other.vehicleMaster != null)
				return false;
		} else if (!vehicleMaster.equals(other.vehicleMaster))
			return false;
		return true;
	}

	public String getBookingID() {
		return bookingID;
	}

	public void setBookingID(String bookingID) {
		this.bookingID = bookingID;
	}

	public Date getDateOfJourney() {
		return dateOfJourney;
	}

	public void setDateOfJourney(Date dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}

	public Integer getNoOfPassenger() {
		return noOfPassenger;
	}

	public void setNoOfPassenger(Integer noOfPassenger) {
		this.noOfPassenger = noOfPassenger;
	}

	public Double getTotalCollectable() {
		return totalCollectable;
	}

	public void setTotalCollectable(Double totalCollectable) {
		this.totalCollectable = totalCollectable;
	}

	public Date getDateOfBooking() {
		return dateOfBooking;
	}

	public void setDateOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}

	public VehicleMaster getVehicleMaster() {
		return vehicleMaster;
	}

	public void setVehicleMaster(VehicleMaster vehicleMaster) {
		this.vehicleMaster = vehicleMaster;
	}

	public Set<PassengerDetail> getPassengerDetailSet() {
		return passengerDetailSet;
	}

	public void setPassengerDetailSet(Set<PassengerDetail> passengerDetailSet) {
		this.passengerDetailSet = passengerDetailSet;
	}

	public String getOriginLocationCode() {
		return originLocationCode;
	}

	public void setOriginLocationCode(String originLocationCode) {
		this.originLocationCode = originLocationCode;
	}

	public String getDestinationLocationCode() {
		return destinationLocationCode;
	}

	public void setDestinationLocationCode(String destinationLocationCode) {
		this.destinationLocationCode = destinationLocationCode;
	}

}
