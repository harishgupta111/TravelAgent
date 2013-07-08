package com.travel.agent.model;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.travel.agent.model.enums.ContactUsMessageType;
import com.travel.agent.model.enums.RecordCreatorType;

@Entity
@Table(name = "ta_ContactUs")
@Cache(region = "entity.contactUs", usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class ContactUs extends SABaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3704648176042926764L;

	@Id
	@Column(name = "contactUsId", insertable = false, updatable = false)
	private String contactUsId;

	@NotEmpty(message = "First name cannot be empty")
	@Column(name = "firstName")
	private String firstName;

	@NotEmpty(message = "Last name cannot be empty")
	@Column(name = "lastName")
	private String lastName;

	@NotEmpty(message = "E-mail name cannot be empty")
	@Email(message = "E-mail is not valid")
	@Column(name = "senderEmail")
	private String senderEmail;

	@NotNull(message="Please select message type")
	@Column(name = "messageType")
	@Enumerated(EnumType.ORDINAL)
	private ContactUsMessageType contactUsMessageType;

	@NotEmpty(message = "Message name cannot be empty")
	@Column(name = "userMessage")
	private String userMessage;

	public class ContactUsBuilder {
		// Optional parameters - initialized to default values
		private String contactUsId;
		private String firstName;
		private String lastName;
		private String senderEmail;
		private ContactUsMessageType contactUsMessageType;
		private String userMessage;
		private RecordCreatorType createdBy;
		private RecordCreatorType updatedBy;
		private Date createDate;
		
		public ContactUsBuilder contactUsId(String val) {
			this.contactUsId = val;
			return this;
		}
		
		public ContactUsBuilder createDate(Date val) {
			this.createDate = val;
			return this;
		}
		
		public ContactUsBuilder createdBy(RecordCreatorType val) {
			this.createdBy = val;
			return this;
		}

		public ContactUsBuilder updatedBy(RecordCreatorType val) {
			this.updatedBy = val;
			return this;
		}

		public ContactUsBuilder firstName(String val) {
			this.firstName = val;
			return this;
		}

		public ContactUsBuilder lastName(String val) {
			this.lastName = val;
			return this;
		}

		public ContactUsBuilder senderEmail(String val) {
			this.senderEmail = val;
			return this;
		}

		public ContactUsBuilder contactUsMessageType(ContactUsMessageType val) {
			this.contactUsMessageType = val;
			return this;
		}

		public ContactUsBuilder userMessage(String val) {
			this.userMessage = val;
			return this;
		}

		public ContactUs buildNew() {
			return new ContactUs(this);
		}		
		
		public ContactUs update() {
			return updateContactUs(this);
		}
		
		public ContactUsBuilder(){}
		
		public ContactUsBuilder(ContactUs contactUs) {
			this.contactUsId = contactUs.contactUsId;
			this.firstName = contactUs.firstName;
			this.lastName = contactUs.lastName;
			this.senderEmail = contactUs.senderEmail;
			this.contactUsMessageType = contactUs.contactUsMessageType;
			this.userMessage = contactUs.userMessage;
			this.createDate = contactUs.getCreateDate();
			this.createdBy = contactUs.getCreatedBy();
			this.updatedBy = contactUs.getUpdatedBy();
		}

	}

	public ContactUs() {
		super();
	}
	
	public ContactUs updateContactUs(ContactUsBuilder builder) {
		this.contactUsId = builder.contactUsId;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.senderEmail = builder.senderEmail;
		this.contactUsMessageType = builder.contactUsMessageType;
		this.userMessage = builder.userMessage;
		super.setCreateDate(builder.createDate);
		super.setCreatedBy(builder.createdBy);
		super.setUpdatedBy(builder.updatedBy);
		return this;
	}

	private ContactUs(ContactUsBuilder builder) {
		this.contactUsId = builder.contactUsId;
		this.contactUsMessageType = builder.contactUsMessageType;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.senderEmail = builder.senderEmail;
		this.userMessage = builder.userMessage;
		super.setCreatedBy(builder.createdBy);
		super.setUpdatedBy(builder.updatedBy);
		super.setCreateDate(this.getCreateDate());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contactUsId == null) ? 0 : contactUsId.hashCode());
		result = prime
				* result
				+ ((contactUsMessageType == null) ? 0 : contactUsMessageType
						.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((senderEmail == null) ? 0 : senderEmail.hashCode());
		result = prime * result
				+ ((userMessage == null) ? 0 : userMessage.hashCode());
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
		ContactUs other = (ContactUs) obj;
		if (contactUsId == null) {
			if (other.contactUsId != null)
				return false;
		} else if (!contactUsId.equals(other.contactUsId))
			return false;
		if (contactUsMessageType != other.contactUsMessageType)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (senderEmail == null) {
			if (other.senderEmail != null)
				return false;
		} else if (!senderEmail.equals(other.senderEmail))
			return false;
		if (userMessage == null) {
			if (other.userMessage != null)
				return false;
		} else if (!userMessage.equals(other.userMessage))
			return false;
		return true;
	}

	public String getContactUsId() {
		return contactUsId;
	}

	public void setContactUsId(String contactUsId) {
		this.contactUsId = contactUsId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public ContactUsMessageType getContactUsMessageType() {
		return contactUsMessageType;
	}

	public void setContactUsMessageType(
			ContactUsMessageType contactUsMessageType) {
		this.contactUsMessageType = contactUsMessageType;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

}
