package com.travel.agent.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import com.travel.agent.model.enums.RecordCreatorType;

public class ItineraryMaster extends SABaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5521478831401054188L;

	@Id
	@Column(name = "itineraryRecID", insertable = false, updatable = false)
	private String itineraryRecID;

	@Id
	@Column(name = "itineraryMasterID", insertable = false, updatable = false)
	private String itineraryMasterID;

	@Column(name = "originLocationCode")
	private String originLocationCode;

	@Column(name = "destinationLocationCode")
	private String destinationLocationCode;

	@Column(name = "timeAtOrigin")
	private Date timeAtOrigin;

	@Column(name = "timeAtDestination")
	private Date timeAtDestination;

	@Column(name = "itinerarySeqId")
	private Integer itinerarySeqId;

	@Column(name = "cancelStatus")
	@Type(type = "yes_no")
	private Boolean cancelStatus;

	@Column(name = "nonStopStatus")
	@Type(type = "yes_no")
	private Boolean nonStopStatus;

	public class ItineraryMasterBuilder {

		private String itineraryRecID;
		private String itineraryMasterID;
		private String originLocationCode;
		private String destinationLocationCode;
		private Date timeAtOrigin;
		private Date timeAtDestination;
		private Integer itinerarySeqId;
		private Boolean cancelStatus;
		private Boolean nonStopStatus;
		private RecordCreatorType createdBy;
		private RecordCreatorType updatedBy;
		private Date createDate;

		public ItineraryMasterBuilder itineraryRecID(String val) {
			this.itineraryRecID = val;
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

		public ItineraryMasterBuilder timeAtOrigin(Date val) {
			this.timeAtOrigin = val;
			return this;
		}

		public ItineraryMasterBuilder timeAtDestination(Date val) {
			this.timeAtOrigin = val;
			return this;
		}

		public ItineraryMasterBuilder itinerarySeqId(Integer val) {
			this.itinerarySeqId = val;
			return this;
		}

		public ItineraryMasterBuilder cancelStatus(Boolean val) {
			this.cancelStatus = val;
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
			this.timeAtOrigin = itineraryMaster.timeAtOrigin;
			this.timeAtDestination = itineraryMaster.timeAtDestination;
			this.itinerarySeqId = itineraryMaster.itinerarySeqId;
			this.cancelStatus = itineraryMaster.cancelStatus;
			this.nonStopStatus = itineraryMaster.nonStopStatus;
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
		this.itineraryRecID = itineraryMasterBuilder.itineraryRecID;
		this.itineraryMasterID = itineraryMasterBuilder.itineraryMasterID;
		this.originLocationCode = itineraryMasterBuilder.originLocationCode;
		this.destinationLocationCode = itineraryMasterBuilder.destinationLocationCode;
		this.timeAtOrigin = itineraryMasterBuilder.timeAtOrigin;
		this.timeAtDestination = itineraryMasterBuilder.timeAtDestination;
		this.itinerarySeqId = itineraryMasterBuilder.itinerarySeqId;
		this.cancelStatus = itineraryMasterBuilder.cancelStatus;
		this.nonStopStatus = itineraryMasterBuilder.nonStopStatus;
		super.setCreatedBy(itineraryMasterBuilder.createdBy);
		super.setUpdatedBy(itineraryMasterBuilder.updatedBy);
		super.setCreateDate(itineraryMasterBuilder.createDate);

		return this;
	}

	private ItineraryMaster(ItineraryMasterBuilder builder) {
		this.itineraryRecID = builder.itineraryRecID;
		this.itineraryMasterID = builder.itineraryMasterID;
		this.originLocationCode = builder.originLocationCode;
		this.destinationLocationCode = builder.destinationLocationCode;
		this.timeAtOrigin = builder.timeAtOrigin;
		this.timeAtDestination = builder.timeAtDestination;
		this.itinerarySeqId = builder.itinerarySeqId;
		this.cancelStatus = builder.cancelStatus;
		this.nonStopStatus = builder.nonStopStatus;
		super.setCreatedBy(builder.createdBy);
		super.setUpdatedBy(builder.updatedBy);
		super.setCreateDate(builder.createDate);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((cancelStatus == null) ? 0 : cancelStatus.hashCode());
		result = prime
				* result
				+ ((destinationLocationCode == null) ? 0
						: destinationLocationCode.hashCode());
		result = prime
				* result
				+ ((itineraryMasterID == null) ? 0 : itineraryMasterID
						.hashCode());
		result = prime * result
				+ ((itineraryRecID == null) ? 0 : itineraryRecID.hashCode());
		result = prime * result
				+ ((itinerarySeqId == null) ? 0 : itinerarySeqId.hashCode());
		result = prime * result
				+ ((nonStopStatus == null) ? 0 : nonStopStatus.hashCode());
		result = prime
				* result
				+ ((originLocationCode == null) ? 0 : originLocationCode
						.hashCode());
		result = prime
				* result
				+ ((timeAtDestination == null) ? 0 : timeAtDestination
						.hashCode());
		result = prime * result
				+ ((timeAtOrigin == null) ? 0 : timeAtOrigin.hashCode());
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
		if (cancelStatus == null) {
			if (other.cancelStatus != null)
				return false;
		} else if (!cancelStatus.equals(other.cancelStatus))
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
		if (itineraryRecID == null) {
			if (other.itineraryRecID != null)
				return false;
		} else if (!itineraryRecID.equals(other.itineraryRecID))
			return false;
		if (itinerarySeqId == null) {
			if (other.itinerarySeqId != null)
				return false;
		} else if (!itinerarySeqId.equals(other.itinerarySeqId))
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
		if (timeAtDestination == null) {
			if (other.timeAtDestination != null)
				return false;
		} else if (!timeAtDestination.equals(other.timeAtDestination))
			return false;
		if (timeAtOrigin == null) {
			if (other.timeAtOrigin != null)
				return false;
		} else if (!timeAtOrigin.equals(other.timeAtOrigin))
			return false;
		return true;
	}

	public String getItineraryRecID() {
		return itineraryRecID;
	}

	public void setItineraryRecID(String itineraryRecID) {
		this.itineraryRecID = itineraryRecID;
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

	public Date getTimeAtOrigin() {
		return timeAtOrigin;
	}

	public void setTimeAtOrigin(Date timeAtOrigin) {
		this.timeAtOrigin = timeAtOrigin;
	}

	public Date getTimeAtDestination() {
		return timeAtDestination;
	}

	public void setTimeAtDestination(Date timeAtDestination) {
		this.timeAtDestination = timeAtDestination;
	}

	public Integer getItinerarySeqId() {
		return itinerarySeqId;
	}

	public void setItinerarySeqId(Integer itinerarySeqId) {
		this.itinerarySeqId = itinerarySeqId;
	}

	public Boolean getCancelStatus() {
		return cancelStatus;
	}

	public void setCancelStatus(Boolean cancelStatus) {
		this.cancelStatus = cancelStatus;
	}

	public Boolean getNonStopStatus() {
		return nonStopStatus;
	}

	public void setNonStopStatus(Boolean nonStopStatus) {
		this.nonStopStatus = nonStopStatus;
	}
	
}
