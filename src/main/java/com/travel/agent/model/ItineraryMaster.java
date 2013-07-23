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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.travel.agent.model.enums.RecordCreatorType;

@Entity
@Table(name = "ta_ItineraryMaster")
@Cache(region = "entity.ta_ItineraryMaster", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class ItineraryMaster extends SABaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5521478831401054188L;

	@Id
	@Column(name = "itineraryMasterID", insertable = false, updatable = false)
	private String itineraryMasterID;

	@Column(name = "originLocationCode")
	private String originLocationCode;

	@Column(name = "destinationLocationCode")
	private String destinationLocationCode;

	@Column(name = "nonStopStatus")
	@Type(type = "yes_no")
	private Boolean nonStopStatus;

	@Column(name = "dayOfWeek")
	private String dayOfWeek;

	@Column(name = "weekOfMonth")
	private String weekOfMonth;
	
	@OneToMany(mappedBy = "itineraryMaster", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<ItineraryDetail> itineraryDetailSet;
	
	@OneToMany(mappedBy = "itineraryMaster", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<JourneyMaster> journeyMasterSet;

	public class ItineraryMasterBuilder {

		private String itineraryMasterID;
		private String originLocationCode;
		private String destinationLocationCode;
		private Boolean nonStopStatus;
		private String dayOfWeek;
		private String weekOfMonth;
		private RecordCreatorType createdBy;
		private RecordCreatorType updatedBy;
		private Date createDate;

		public ItineraryMasterBuilder weekOfMonth(String val) {
			this.weekOfMonth = val;
			return this;
		}

		public ItineraryMasterBuilder dayOfWeek(String val) {
			this.dayOfWeek = val;
			return this;
		}
				
		public ItineraryMasterBuilder createDate(Date val) {
			this.createDate = val;
			return this;
		}

		public ItineraryMasterBuilder createdBy(RecordCreatorType val) {
			this.createdBy = val;
			return this;
		}

		public ItineraryMasterBuilder updatedBy(RecordCreatorType val) {
			this.updatedBy = val;
			return this;
		}

		public ItineraryMasterBuilder itineraryMasterID(String val) {
			this.itineraryMasterID = val;
			return this;
		}

		public ItineraryMasterBuilder originLocationCode(String val) {
			this.originLocationCode = val;
			return this;
		}

		public ItineraryMasterBuilder destinationLocationCode(String val) {
			this.destinationLocationCode = val;
			return this;
		}

		public ItineraryMasterBuilder nonStopStatus(Boolean val) {
			this.nonStopStatus = val;
			return this;
		}

		public ItineraryMaster buildNew() {
			return new ItineraryMaster(this);
		}
		
		public ItineraryMaster update() {
			return updateItineraryMaster(this);
		}

		public ItineraryMasterBuilder() {}
		
		public ItineraryMasterBuilder(ItineraryMaster itineraryMaster) {

			this.itineraryMasterID = itineraryMaster.itineraryMasterID;
			this.originLocationCode = itineraryMaster.originLocationCode;
			this.destinationLocationCode = itineraryMaster.destinationLocationCode;
			this.nonStopStatus = itineraryMaster.nonStopStatus;
			this.dayOfWeek = itineraryMaster.dayOfWeek;
			this.weekOfMonth = itineraryMaster.weekOfMonth;
			this.createdBy = itineraryMaster.getCreatedBy();
			this.updatedBy = itineraryMaster.getUpdatedBy();
			this.createDate = itineraryMaster.getCreateDate();
		}

	}

	public ItineraryMaster() {
		super();
	}

	public ItineraryMaster updateItineraryMaster(
			ItineraryMasterBuilder itineraryMasterBuilder) {
		this.itineraryMasterID = itineraryMasterBuilder.itineraryMasterID;
		this.originLocationCode = itineraryMasterBuilder.originLocationCode;
		this.destinationLocationCode = itineraryMasterBuilder.destinationLocationCode;
		this.nonStopStatus = itineraryMasterBuilder.nonStopStatus;
		this.dayOfWeek = itineraryMasterBuilder.dayOfWeek;
		this.weekOfMonth = itineraryMasterBuilder.weekOfMonth;
		super.setCreatedBy(itineraryMasterBuilder.createdBy);
		super.setUpdatedBy(itineraryMasterBuilder.updatedBy);
		super.setCreateDate(itineraryMasterBuilder.createDate);

		return this;
	}

	private ItineraryMaster(ItineraryMasterBuilder builder) {

		this.itineraryMasterID = builder.itineraryMasterID;
		this.originLocationCode = builder.originLocationCode;
		this.destinationLocationCode = builder.destinationLocationCode;
		this.nonStopStatus = builder.nonStopStatus;
		this.dayOfWeek = builder.dayOfWeek;
		this.weekOfMonth = builder.weekOfMonth;
		super.setCreatedBy(builder.createdBy);
		super.setUpdatedBy(builder.updatedBy);
		super.setCreateDate(builder.createDate);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dayOfWeek == null) ? 0 : dayOfWeek.hashCode());
		result = prime
				* result
				+ ((destinationLocationCode == null) ? 0
						: destinationLocationCode.hashCode());
		result = prime
				* result
				+ ((itineraryMasterID == null) ? 0 : itineraryMasterID
						.hashCode());
		result = prime * result
				+ ((nonStopStatus == null) ? 0 : nonStopStatus.hashCode());
		result = prime
				* result
				+ ((originLocationCode == null) ? 0 : originLocationCode
						.hashCode());
		result = prime * result
				+ ((weekOfMonth == null) ? 0 : weekOfMonth.hashCode());
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
		ItineraryMaster other = (ItineraryMaster) obj;
		if (dayOfWeek == null) {
			if (other.dayOfWeek != null)
				return false;
		} else if (!dayOfWeek.equals(other.dayOfWeek))
			return false;
		if (destinationLocationCode == null) {
			if (other.destinationLocationCode != null)
				return false;
		} else if (!destinationLocationCode
				.equals(other.destinationLocationCode))
			return false;
		if (itineraryMasterID == null) {
			if (other.itineraryMasterID != null)
				return false;
		} else if (!itineraryMasterID.equals(other.itineraryMasterID))
			return false;
		if (nonStopStatus == null) {
			if (other.nonStopStatus != null)
				return false;
		} else if (!nonStopStatus.equals(other.nonStopStatus))
			return false;
		if (originLocationCode == null) {
			if (other.originLocationCode != null)
				return false;
		} else if (!originLocationCode.equals(other.originLocationCode))
			return false;
		if (weekOfMonth == null) {
			if (other.weekOfMonth != null)
				return false;
		} else if (!weekOfMonth.equals(other.weekOfMonth))
			return false;
		return true;
	}

	public String getItineraryMasterID() {
		return itineraryMasterID;
	}

	public void setItineraryMasterID(String itineraryMasterID) {
		this.itineraryMasterID = itineraryMasterID;
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

	public Boolean getNonStopStatus() {
		return nonStopStatus;
	}

	public void setNonStopStatus(Boolean nonStopStatus) {
		this.nonStopStatus = nonStopStatus;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getWeekOfMonth() {
		return weekOfMonth;
	}

	public void setWeekOfMonth(String weekOfMonth) {
		this.weekOfMonth = weekOfMonth;
	}
	
	

}
