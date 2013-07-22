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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;

@Entity
@Table(name = "ta_ItineraryDetail")
@Cache(region = "entity.ta_ItineraryDetail", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class ItineraryDetail  extends SABaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5191730072836458126L;
	
	@Id
	@Column(name = "itineraryDetailID", insertable = false, updatable = false)
	private String itineraryDetailID;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "scheduleStartTime")
	private Date scheduleStartTime;

	@Temporal(TemporalType.TIME)
	@Column(name = "scheduleEndTime")
	private Date scheduleEndTime;
	
	@Column(name = "itinerarySeqId")
	private Integer itinerarySeqId;

	@Column(name = "fromLocationCode")
	private String fromLocationCode;

	@Column(name = "toLocationCode")
	private String toLocationCode;

	@Column(name = "minStopTime")
	private Integer minStopTime;
	
	@Column(name = "minEstimatedTime")
	private Integer minEstimatedTime;
	
	@Fetch(org.hibernate.annotations.FetchMode.JOIN)
	@JoinColumn(name = "itineraryMasterID", referencedColumnName = "itineraryMasterID")
	@ManyToOne(targetEntity = ItineraryMaster.class, fetch = FetchType.LAZY)
	private ItineraryMaster itineraryMaster;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((fromLocationCode == null) ? 0 : fromLocationCode.hashCode());
		result = prime
				* result
				+ ((itineraryDetailID == null) ? 0 : itineraryDetailID
						.hashCode());
		result = prime * result
				+ ((itinerarySeqId == null) ? 0 : itinerarySeqId.hashCode());
		result = prime
				* result
				+ ((minEstimatedTime == null) ? 0 : minEstimatedTime.hashCode());
		result = prime * result
				+ ((minStopTime == null) ? 0 : minStopTime.hashCode());
		result = prime * result
				+ ((scheduleEndTime == null) ? 0 : scheduleEndTime.hashCode());
		result = prime
				* result
				+ ((scheduleStartTime == null) ? 0 : scheduleStartTime
						.hashCode());
		result = prime * result
				+ ((toLocationCode == null) ? 0 : toLocationCode.hashCode());
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
		ItineraryDetail other = (ItineraryDetail) obj;
		if (fromLocationCode == null) {
			if (other.fromLocationCode != null)
				return false;
		} else if (!fromLocationCode.equals(other.fromLocationCode))
			return false;
		if (itineraryDetailID == null) {
			if (other.itineraryDetailID != null)
				return false;
		} else if (!itineraryDetailID.equals(other.itineraryDetailID))
			return false;
		if (itinerarySeqId == null) {
			if (other.itinerarySeqId != null)
				return false;
		} else if (!itinerarySeqId.equals(other.itinerarySeqId))
			return false;
		if (minEstimatedTime == null) {
			if (other.minEstimatedTime != null)
				return false;
		} else if (!minEstimatedTime.equals(other.minEstimatedTime))
			return false;
		if (minStopTime == null) {
			if (other.minStopTime != null)
				return false;
		} else if (!minStopTime.equals(other.minStopTime))
			return false;
		if (scheduleEndTime == null) {
			if (other.scheduleEndTime != null)
				return false;
		} else if (!scheduleEndTime.equals(other.scheduleEndTime))
			return false;
		if (scheduleStartTime == null) {
			if (other.scheduleStartTime != null)
				return false;
		} else if (!scheduleStartTime.equals(other.scheduleStartTime))
			return false;
		if (toLocationCode == null) {
			if (other.toLocationCode != null)
				return false;
		} else if (!toLocationCode.equals(other.toLocationCode))
			return false;
		return true;
	}

	public String getItineraryDetailID() {
		return itineraryDetailID;
	}

	public void setItineraryDetailID(String itineraryDetailID) {
		this.itineraryDetailID = itineraryDetailID;
	}

	public Date getScheduleStartTime() {
		return scheduleStartTime;
	}

	public void setScheduleStartTime(Date scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime;
	}

	public Date getScheduleEndTime() {
		return scheduleEndTime;
	}

	public void setScheduleEndTime(Date scheduleEndTime) {
		this.scheduleEndTime = scheduleEndTime;
	}

	public Integer getItinerarySeqId() {
		return itinerarySeqId;
	}

	public void setItinerarySeqId(Integer itinerarySeqId) {
		this.itinerarySeqId = itinerarySeqId;
	}

	public String getFromLocationCode() {
		return fromLocationCode;
	}

	public void setFromLocationCode(String fromLocationCode) {
		this.fromLocationCode = fromLocationCode;
	}

	public String getToLocationCode() {
		return toLocationCode;
	}

	public void setToLocationCode(String toLocationCode) {
		this.toLocationCode = toLocationCode;
	}

	public Integer getMinStopTime() {
		return minStopTime;
	}

	public void setMinStopTime(Integer minStopTime) {
		this.minStopTime = minStopTime;
	}

	public Integer getMinEstimatedTime() {
		return minEstimatedTime;
	}

	public void setMinEstimatedTime(Integer minEstimatedTime) {
		this.minEstimatedTime = minEstimatedTime;
	}

	public ItineraryMaster getItineraryMaster() {
		return itineraryMaster;
	}

	public void setItineraryMaster(ItineraryMaster itineraryMaster) {
		this.itineraryMaster = itineraryMaster;
	}
	
	

}
