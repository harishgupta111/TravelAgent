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

import com.travel.agent.model.enums.RecordCreatorType;

@Entity
@Table(name = "ta_PassengerDetail")
@Cache(region = "entity.ta_passengerDetail", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class PassengerDetail extends SABaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5475661224118597822L;

	@Id
	@Column(name = "passengerDetailID", insertable = false, updatable = false)
	private String passengerDetailID;

	@Column(name = "passengerName")
	private String passengerName;

	@Column(name = "passengerAge")
	private Integer passengerAge;

	@Column(name = "amount")
	private Double amount;

	@Fetch(org.hibernate.annotations.FetchMode.JOIN)
	@JoinColumn(name = "bookingID", referencedColumnName = "bookingID")
	@ManyToOne(targetEntity = Booking.class, fetch = FetchType.LAZY)
	private Booking booking;

	public class PassengerDetailBuilder {

		private String passengerDetailID;
		private String passengerName;
		private Integer passengerAge;
		private Double amount;
		private Booking booking;
		private RecordCreatorType createdBy;
		private RecordCreatorType updatedBy;
		private Date createDate;

		public PassengerDetailBuilder createDate(Date val) {
			this.createDate = val;
			return this;
		}

		public PassengerDetailBuilder createdBy(RecordCreatorType val) {
			this.createdBy = val;
			return this;
		}

		public PassengerDetailBuilder updatedBy(RecordCreatorType val) {
			this.updatedBy = val;
			return this;
		}

		public PassengerDetailBuilder passengerDetailID(String val) {
			this.passengerDetailID = val;
			return this;
		}

		public PassengerDetailBuilder passengerName(String val) {
			this.passengerName = val;
			return this;
		}

		public PassengerDetailBuilder passengerAge(Integer val) {
			this.passengerAge = val;
			return this;
		}

		public PassengerDetailBuilder amount(Double val) {
			this.amount = val;
			return this;
		}

		public PassengerDetailBuilder booking(Booking val) {
			this.booking = val;
			return this;
		}

		public PassengerDetail buildNew() {
			return new PassengerDetail(this);
		}

		public PassengerDetail update() {
			return updatePassengerDetail(this);
		}

		public PassengerDetailBuilder() {
		}

		public PassengerDetailBuilder(PassengerDetail passengerDetail) {

			this.passengerDetailID = passengerDetail.passengerDetailID;
			this.passengerName = passengerDetail.passengerName;
			this.passengerAge = passengerDetail.passengerAge;
			this.amount = passengerDetail.amount;
			this.booking = passengerDetail.booking;
			this.createDate = passengerDetail.getCreateDate();
			this.createdBy = passengerDetail.getCreatedBy();
			this.updatedBy = passengerDetail.getUpdatedBy();
		}
	}

	public PassengerDetail() {
		super();
	}

	public PassengerDetail updatePassengerDetail(
			PassengerDetailBuilder passengerDetailBuilder) {
		this.passengerDetailID = passengerDetailBuilder.passengerDetailID;
		this.passengerName = passengerDetailBuilder.passengerName;
		this.passengerAge = passengerDetailBuilder.passengerAge;
		this.amount = passengerDetailBuilder.amount;
		this.booking = passengerDetailBuilder.booking;
		super.setCreateDate(passengerDetailBuilder.createDate);
		super.setCreatedBy(passengerDetailBuilder.createdBy);
		super.setUpdatedBy(passengerDetailBuilder.updatedBy);
		return this;
	}

	private PassengerDetail(PassengerDetailBuilder builder) {
		this.passengerDetailID = builder.passengerDetailID;
		this.amount = builder.amount;
		this.booking = builder.booking;
		this.passengerAge = builder.passengerAge;
		this.passengerDetailID = builder.passengerDetailID;
		this.passengerName = builder.passengerName;
		super.setCreatedBy(builder.createdBy);
		super.setUpdatedBy(builder.updatedBy);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((passengerName == null) ? 0 : passengerName.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((passengerAge == null) ? 0 : passengerAge.hashCode());
		result = prime
				* result
				+ ((passengerDetailID == null) ? 0 : passengerDetailID
						.hashCode());
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
		PassengerDetail other = (PassengerDetail) obj;
		if (passengerName == null) {
			if (other.passengerName != null)
				return false;
		} else if (!passengerName.equals(other.passengerName))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (passengerAge == null) {
			if (other.passengerAge != null)
				return false;
		} else if (!passengerAge.equals(other.passengerAge))
			return false;
		if (passengerDetailID == null) {
			if (other.passengerDetailID != null)
				return false;
		} else if (!passengerDetailID.equals(other.passengerDetailID))
			return false;
		return true;
	}

	public String getPassengerDetailID() {
		return passengerDetailID;
	}

	public void setPassengerDetailID(String passengerDetailID) {
		this.passengerDetailID = passengerDetailID;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public Integer getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(Integer passengerAge) {
		this.passengerAge = passengerAge;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
