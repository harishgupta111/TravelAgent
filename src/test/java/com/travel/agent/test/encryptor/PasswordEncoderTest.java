package com.travel.agent.test.encryptor;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class PasswordEncoderTest {
	
	private static Logger logger = Logger.getLogger(PasswordEncoderTest.class);
	@Autowired
	private ShaPasswordEncoder shaPasswordEncoder;
	
	@Test
	public void shouldEncrypt()
	{
		String encrypted = shaPasswordEncoder.encodePassword("password", "testUsername1");
		logger.info(encrypted);
		Assert.assertNotSame("password", encrypted);
	}

}
