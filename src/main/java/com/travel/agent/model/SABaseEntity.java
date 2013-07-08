package com.travel.agent.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.travel.agent.model.enums.RecordCreatorType;

@MappedSuperclass
public class SABaseEntity implements Serializable{

	/**
	 * 
	 */
	
	public SABaseEntity() {
		super();
		setUpdateDate(new Date());
	}

	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", nullable = false)
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate", nullable = false)
	private Date updateDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "createdBy", nullable = false)
	private RecordCreatorType createdBy;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "updatedBy", nullable = false)
	private RecordCreatorType updatedBy;	

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = new Date();
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = new Date();
	}

	public RecordCreatorType getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(RecordCreatorType createdBy) {
		this.createdBy = createdBy;
	}

	public RecordCreatorType getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(RecordCreatorType updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((updateDate == null) ? 0 : updateDate.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
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
		SABaseEntity other = (SABaseEntity) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (createdBy != other.createdBy)
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
			return false;
		if (updatedBy != other.updatedBy)
			return false;
		return true;
	}	
	
}
