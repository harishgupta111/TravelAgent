package com.travel.agent.auth.manager;

import org.springframework.security.core.Authentication;

import com.travel.agent.auth.UserAuthenticationDTO;

public interface IUserAuthenticationDTOService {
	
	public void setAuthenticate(Authentication authentication);
	
	public UserAuthenticationDTO getAuthenticationDTO();
	
}
