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
@Table(name = "ta_JourneyMaster")
@Cache(region = "entity.ta_JourneyMaster", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class JourneyMaster extends SABaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8210728579577353961L;

	@Id
	@Column(name = "journeyMasterID", insertable = false, updatable = false)
	private String journeyMasterID;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfJourney")
	private Date dateOfJourney;
	
	@Fetch(org.hibernate.annotations.FetchMode.JOIN)
	@JoinColumn(name = "itineraryMasterID", referencedColumnName = "itineraryMasterID")
	@ManyToOne(targetEntity = ItineraryMaster.class, fetch = FetchType.LAZY)
	private ItineraryMaster itineraryMaster;
	
	@Fetch(org.hibernate.annotations.FetchMode.JOIN)
	@JoinColumn(name = "vehicleMasterID", referencedColumnName = "vehicleMasterID")
	@ManyToOne(targetEntity = VehicleMaster.class, fetch = FetchType.LAZY)
	private VehicleMaster vehicleMaster;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dateOfJourney == null) ? 0 : dateOfJourney.hashCode());
		result = prime * result
				+ ((journeyMasterID == null) ? 0 : journeyMasterID.hashCode());
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
		JourneyMaster other = (JourneyMaster) obj;
		if (dateOfJourney == null) {
			if (other.dateOfJourney != null)
				return false;
		} else if (!dateOfJourney.equals(other.dateOfJourney))
			return false;
		if (journeyMasterID == null) {
			if (other.journeyMasterID != null)
				return false;
		} else if (!journeyMasterID.equals(other.journeyMasterID))
			return false;
		return true;
	}

	public String getJourneyMasterID() {
		return journeyMasterID;
	}

	public void setJourneyMasterID(String journeyMasterID) {
		this.journeyMasterID = journeyMasterID;
	}

	public Date getDateOfJourney() {
		return dateOfJourney;
	}

	public void setDateOfJourney(Date dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}

	public ItineraryMaster getItineraryMaster() {
		return itineraryMaster;
	}

	public void setItineraryMaster(ItineraryMaster itineraryMaster) {
		this.itineraryMaster = itineraryMaster;
	}

	public VehicleMaster getVehicleMaster() {
		return vehicleMaster;
	}

	public void setVehicleMaster(VehicleMaster vehicleMaster) {
		this.vehicleMaster = vehicleMaster;
	}
	
}
