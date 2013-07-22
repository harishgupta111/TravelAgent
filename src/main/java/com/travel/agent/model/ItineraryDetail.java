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

import com.travel.agent.model.enums.RecordCreatorType;

@Entity
@Table(name = "ta_ItineraryDetail")
@Cache(region = "entity.ta_ItineraryDetail", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class ItineraryDetail extends SABaseEntity {

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

	public class ItineraryDetailBuilder {
		private String itineraryDetailID;
		private Date scheduleStartTime;
		private Date scheduleEndTime;
		private Integer itinerarySeqId;
		private String fromLocationCode;
		private String toLocationCode;
		private Integer minStopTime;
		private Integer minEstimatedTime;
		private ItineraryMaster itineraryMaster;
		private RecordCreatorType createdBy;
		private RecordCreatorType updatedBy;
		private Date createDate;

		public ItineraryDetailBuilder itineraryDetailID(String val) {
			this.itineraryDetailID = val;
			return this;
		}

		public ItineraryDetailBuilder scheduleStartTime(Date val) {
			this.scheduleStartTime = val;
			return this;
		}

		public ItineraryDetailBuilder scheduleEndTime(Date val) {
			this.scheduleEndTime = val;
			return this;
		}

		public ItineraryDetailBuilder itinerarySeqId(Integer val) {
			this.itinerarySeqId = val;
			return this;
		}

		public ItineraryDetailBuilder fromLocationCode(String val) {
			this.fromLocationCode = val;
			return this;
		}

		public ItineraryDetailBuilder toLocationCode(String val) {
			this.toLocationCode = val;
			return this;
		}

		public ItineraryDetailBuilder minStopTime(Integer val) {
			this.minStopTime = val;
			return this;
		}

		public ItineraryDetailBuilder minEstimatedTime(Integer val) {
			this.minEstimatedTime = val;
			return this;
		}

		public ItineraryDetailBuilder itineraryMaster(ItineraryMaster val) {
			this.itineraryMaster = val;
			return this;
		}

		public ItineraryDetailBuilder createDate(Date val) {
			this.createDate = val;
			return this;
		}

		public ItineraryDetailBuilder createdBy(RecordCreatorType val) {
			this.createdBy = val;
			return this;
		}

		public ItineraryDetailBuilder updatedBy(RecordCreatorType val) {
			this.updatedBy = val;
			return this;
		}

		public ItineraryDetail buildNew() {
			return new ItineraryDetail(this);
		}

		public ItineraryDetail update() {
			return updateItineraryDetail(this);
		}

		public ItineraryDetailBuilder(ItineraryDetail itineraryDetail) {
			this.fromLocationCode = itineraryDetail.fromLocationCode;
			this.itineraryDetailID = itineraryDetail.itineraryDetailID;
			this.itineraryMaster = itineraryDetail.itineraryMaster;
			this.itinerarySeqId = itineraryDetail.itinerarySeqId;
			this.minEstimatedTime = itineraryDetail.minEstimatedTime;
			this.minStopTime = itineraryDetail.minStopTime;
			this.scheduleEndTime = itineraryDetail.scheduleEndTime;
			this.scheduleStartTime = itineraryDetail.scheduleStartTime;
			this.createdBy = itineraryDetail.getCreatedBy();
			this.updatedBy = itineraryDetail.getUpdatedBy();
			this.createDate = itineraryDetail.getCreateDate();
			this.toLocationCode = itineraryDetail.toLocationCode;
		}

		public ItineraryDetailBuilder() {
		}

	}

	public ItineraryDetail() {
		super();
	}

	
	public ItineraryDetail(ItineraryDetailBuilder itineraryDetailBuilder) {
		this.fromLocationCode = itineraryDetailBuilder.fromLocationCode;
		this.itineraryDetailID = itineraryDetailBuilder.itineraryDetailID;
		this.itineraryMaster = itineraryDetailBuilder.itineraryMaster;
		this.itinerarySeqId = itineraryDetailBuilder.itinerarySeqId;
		this.minEstimatedTime = itineraryDetailBuilder.minEstimatedTime;
		this.minStopTime = itineraryDetailBuilder.minStopTime;
		this.scheduleEndTime = itineraryDetailBuilder.scheduleEndTime;
		this.scheduleStartTime = itineraryDetailBuilder.scheduleStartTime;
		this.toLocationCode = itineraryDetailBuilder.toLocationCode;
		super.setCreatedBy(itineraryDetailBuilder.createdBy);
		super.setUpdatedBy(itineraryDetailBuilder.updatedBy);
		super.setCreateDate(itineraryDetailBuilder.createDate);
		
	}

	public ItineraryDetail updateItineraryDetail(
			ItineraryDetailBuilder itineraryDetailBuilder) {
		this.fromLocationCode = itineraryDetailBuilder.fromLocationCode;
		this.itineraryDetailID = itineraryDetailBuilder.itineraryDetailID;
		this.itineraryMaster = itineraryDetailBuilder.itineraryMaster;
		this.itinerarySeqId = itineraryDetailBuilder.itinerarySeqId;
		this.minEstimatedTime = itineraryDetailBuilder.minEstimatedTime;
		this.minStopTime = itineraryDetailBuilder.minStopTime;
		this.scheduleEndTime = itineraryDetailBuilder.scheduleEndTime;
		this.scheduleStartTime = itineraryDetailBuilder.scheduleStartTime;
		this.toLocationCode = itineraryDetailBuilder.toLocationCode;
		super.setCreatedBy(itineraryDetailBuilder.createdBy);
		super.setUpdatedBy(itineraryDetailBuilder.updatedBy);
		super.setCreateDate(itineraryDetailBuilder.createDate);
		return this;
	}

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
