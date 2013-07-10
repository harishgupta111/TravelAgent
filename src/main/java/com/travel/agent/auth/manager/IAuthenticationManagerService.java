package com.travel.agent.auth.manager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public interface IAuthenticationManagerService {
	
	public Authentication authenticateToken(UsernamePasswordAuthenticationToken token);

}
