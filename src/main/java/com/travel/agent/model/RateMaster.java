package com.travel.agent.model;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travel.agent.model.enums.RateType;
import com.travel.agent.model.enums.RecordCreatorType;

@Entity
@Table(name = "ta_RateMaster", uniqueConstraints = @UniqueConstraint(columnNames = {
		"rateType", "originLocationCode", "destinationLocationCode",
		"effectiveStartDate", "rate", "activeIndicator" }))
@Cache(region = "entity.ta_rateMaster", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class RateMaster extends SABaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3280572790370276831L;

	@Id
	@Column(name = "rateMasterID")
	private String rateMasterID;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "rateType")
	private RateType rateType;

	@Column(name = "originLocationCode")
	private String originLocationCode;

	@Column(name = "destinationLocationCode")
	private String destinationLocationCode;

	@Column(name = "rate")
	private Double rate;

	@Column(name = "effectiveStartDate")
	private Date effectiveStartDate;
	
	@Column(name = "effectiveEndDate", nullable = true)
	private Date effectiveEndDate;
	
	@Column(name = "activeIndicator")
	@Type(type = "yes_no")
	private Boolean activeIndicator;
	
	@JsonIgnore
	@Transient
	private RateMasterBuilder rateMasterBuilder;

	public class RateMasterBuilder {

		private String rateMasterID;
		private RateType rateType;
		private String originLocationCode;
		private String destinationLocationCode;
		private Double rate;
		private Date effectiveStartDate;
		private Date effectiveEndDate;
		private Boolean activeIndicator;
		private RecordCreatorType createdBy;
		private RecordCreatorType updatedBy;
		private Date createDate;
		
		public RateMasterBuilder rateMasterID(String val) {
			this.rateMasterID = val;
			return this;
		}	

		public RateMasterBuilder createDate(Date val) {
			this.createDate = val;
			return this;
		}
		
		public RateMasterBuilder createdBy(RecordCreatorType val) {
			this.createdBy = val;
			return this;
		}

		public RateMasterBuilder updatedBy(RecordCreatorType val) {
			this.updatedBy = val;
			return this;
		}

		public RateMasterBuilder rateType(RateType val) {
			this.rateType = val;
			return this;
		}

		public RateMasterBuilder originLocationCode(String val) {
			this.originLocationCode = val;
			return this;
		}

		public RateMasterBuilder destinationLocationCode(String val) {
			this.destinationLocationCode = val;
			return this;
		}

		public RateMasterBuilder rate(Double val) {
			this.rate = val;
			return this;
		}

		public RateMasterBuilder effectiveStartDate(Date val) {
			this.effectiveStartDate = val;
			return this;
		}
		
		public RateMasterBuilder effectiveEndDate(Date val) {
			this.effectiveEndDate = val;
			return this;
		}
		
		public RateMasterBuilder activeIndicator(Boolean val) {
			this.activeIndicator = val;
			return this;
		}

		public RateMaster buildNew() {
			return new RateMaster(this);
		}
		
		public RateMaster update() {
			return updateRateMaster(this);
		}
		
		public RateMasterBuilder(){}
		
		public RateMasterBuilder(RateMaster rateMaster) {
			this.rateMasterID = rateMaster.rateMasterID;
			this.rateType = rateMaster.rateType;
			this.originLocationCode = rateMaster.originLocationCode;
			this.destinationLocationCode = rateMaster.destinationLocationCode;
			this.rate = rateMaster.rate;
			this.effectiveStartDate = rateMaster.effectiveStartDate;
			this.effectiveEndDate = rateMaster.effectiveEndDate;
			this.activeIndicator = rateMaster.getActiveIndicator();
			this.createdBy = rateMaster.getCreatedBy();
			this.updatedBy = rateMaster.getUpdatedBy();
		}
	}

	public RateMaster() {
		super();
	}
	
	/*
	 * method to update an old object
	 */
	private RateMaster updateRateMaster(RateMasterBuilder builder)
	{
		this.rateMasterID = builder.rateMasterID;
		this.rateType = builder.rateType;
		this.originLocationCode = builder.originLocationCode;
		this.destinationLocationCode = builder.destinationLocationCode;
		this.rate = builder.rate;
		this.effectiveStartDate = builder.effectiveStartDate;
		this.effectiveEndDate = builder.effectiveEndDate;
		this.activeIndicator = builder.activeIndicator;
		super.setCreatedBy(builder.createdBy);
		super.setUpdatedBy(builder.updatedBy);
		super.setCreateDate(builder.createDate);
		return this;
	}
 
	/*
	 * Private Constructor to build a new object
	 */
	private RateMaster(RateMasterBuilder builder) {

		this.rateMasterID = builder.rateMasterID;
		this.rateType = builder.rateType;
		this.originLocationCode = builder.originLocationCode;
		this.destinationLocationCode = builder.destinationLocationCode;
		this.rate = builder.rate;
		this.effectiveStartDate = builder.effectiveStartDate;
		this.effectiveEndDate = builder.effectiveEndDate;
		this.activeIndicator = builder.activeIndicator;
		super.setCreatedBy(builder.createdBy);
		super.setUpdatedBy(builder.updatedBy);
		super.setCreateDate(builder.createDate);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activeIndicator == null) ? 0 : activeIndicator.hashCode());
		result = prime
				* result
				+ ((destinationLocationCode == null) ? 0
						: destinationLocationCode.hashCode());
		result = prime
				* result
				+ ((effectiveEndDate == null) ? 0 : effectiveEndDate.hashCode());
		result = prime
				* result
				+ ((effectiveStartDate == null) ? 0 : effectiveStartDate
						.hashCode());
		result = prime
				* result
				+ ((originLocationCode == null) ? 0 : originLocationCode
						.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		result = prime * result
				+ ((rateMasterID == null) ? 0 : rateMasterID.hashCode());
		result = prime * result
				+ ((rateType == null) ? 0 : rateType.hashCode());
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
		RateMaster other = (RateMaster) obj;
		if (activeIndicator == null) {
			if (other.activeIndicator != null)
				return false;
		} else if (!activeIndicator.equals(other.activeIndicator))
			return false;
		if (destinationLocationCode == null) {
			if (other.destinationLocationCode != null)
				return false;
		} else if (!destinationLocationCode
				.equals(other.destinationLocationCode))
			return false;
		if (effectiveEndDate == null) {
			if (other.effectiveEndDate != null)
				return false;
		} else if (!effectiveEndDate.equals(other.effectiveEndDate))
			return false;
		if (effectiveStartDate == null) {
			if (other.effectiveStartDate != null)
				return false;
		} else if (!effectiveStartDate.equals(other.effectiveStartDate))
			return false;
		if (originLocationCode == null) {
			if (other.originLocationCode != null)
				return false;
		} else if (!originLocationCode.equals(other.originLocationCode))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		if (rateMasterID == null) {
			if (other.rateMasterID != null)
				return false;
		} else if (!rateMasterID.equals(other.rateMasterID))
			return false;
		if (rateType != other.rateType)
			return false;
		return true;
	}

	public String getRateMasterID() {
		return rateMasterID;
	}

	public void setRateMasterID(String rateMasterID) {
		this.rateMasterID = rateMasterID;
	}

	public RateType getRateType() {
		return rateType;
	}

	public void setRateType(RateType rateType) {
		this.rateType = rateType;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Boolean getActiveIndicator() {
		return activeIndicator;
	}

	public void setActiveIndicator(Boolean activeIndicator) {
		this.activeIndicator = activeIndicator;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public RateMasterBuilder getRateMasterBuilder() {
		return rateMasterBuilder;
	}

	public void setRateMasterBuilder(RateMasterBuilder rateMasterBuilder) {
		this.rateMasterBuilder = rateMasterBuilder;
	}
	
}
