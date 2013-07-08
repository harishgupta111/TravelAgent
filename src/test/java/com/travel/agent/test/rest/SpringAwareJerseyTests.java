package com.travel.agent.test.rest;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import com.riffpie.common.testing.AbstractSpringAwareJerseyTest;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;
import com.sun.jersey.test.framework.spi.container.grizzly.web.GrizzlyWebTestContainerFactory;

public class SpringAwareJerseyTests extends AbstractSpringAwareJerseyTest {
	
	public static final String PACKAGE_NAME = "com.travel.agent.restful.service";

	public SpringAwareJerseyTests() {
		super(new WebAppDescriptor.Builder(PACKAGE_NAME)
				.contextPath("/")
				.contextParam("contextConfigLocation",
						"/applicationContext-test.xml")
				.servletClass(SpringServlet.class)
				.contextListenerClass(ContextLoaderListener.class)
				.requestListenerClass(RequestContextListener.class).build());
	}
	
	@Override
	protected TestContainerFactory getTestContainerFactory() {
		return new GrizzlyWebTestContainerFactory();

	}

}
