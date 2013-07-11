package com.travel.agent.auth;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userAuthenticationDTO")
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserAuthenticationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5860021181666008508L;
	
	private Authentication preAuthenticationToken;

	public Authentication getAuthentication() {
		return preAuthenticationToken;
	}

	public void setAuthentication(Authentication authentication) {
		this.preAuthenticationToken = authentication;
	}
	
	
	

}
