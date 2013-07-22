package com.travel.agent.test.service;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.service.IContactUsDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.ContactUs;
import com.travel.agent.model.ContactUs.ContactUsBuilder;
import com.travel.agent.model.enums.ContactUsMessageType;
import com.travel.agent.model.enums.RecordCreatorType;

@Transactional(readOnly = true)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class ContactUsDaoServiceTest {

	@Autowired
	private IContactUsDaoService iContactUsDaoService;

	@Test
	@Rollback(value=true)
	@Transactional(propagation = Propagation.REQUIRED)
	public void shouldCreate() throws TASystemException {
		ContactUs c = new ContactUs();
		ContactUsBuilder cb = c.new ContactUsBuilder();
		c = cb.firstName("First Name").lastName("Last Name").senderEmail("abc@abc.com")
				.contactUsMessageType(ContactUsMessageType.FEEDBACK)
				.userMessage("New User Feedback").createdBy(RecordCreatorType.TEST)
				.updatedBy(RecordCreatorType.TEST).buildNew();
		ContactUs created = iContactUsDaoService.create(c);
		Assert.assertEquals(created, c);
	}

	@Test
	public void getAll() throws TASystemException {
		Set<ContactUs> set = this.iContactUsDaoService.getAll();
		Assert.assertEquals(1, set.size());
	}

}
