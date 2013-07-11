package com.travel.agent.auth.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.travel.agent.auth.manager.IAuthenticationManagerService;

@Component("iAuthenticationManagerService")
public class AuthenticationManagerService implements
		IAuthenticationManagerService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public Authentication authenticateToken(
			UsernamePasswordAuthenticationToken token) {
		return authenticationManager.authenticate(token);
	}
}
