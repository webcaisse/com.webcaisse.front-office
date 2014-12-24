package com.webcaisse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.webcaisse.ws.interfaces.AuthentificationService;
import com.webcaisse.ws.interfaces.SessionManagerService;
import com.webcaisse.ws.model.UserOut;

public class CustomUserDetailsService implements UserDetailsService {

	AuthentificationService authentificationService;
	
	SessionManagerService sessionManagerService;
	
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
	

		UserOut userOut = null;

		userOut = authentificationService.findByUserName(userName);

		if (userOut != null) {
			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
			final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
			
			CustomUser customUser =  new CustomUser(userOut.getUsername(), userOut.getPassword(),
					enabled, accountNonExpired, credentialsNonExpired,
					accountNonLocked, authorities, userOut.getId());
			
			customUser.setSessionId(sessionManagerService.ouvrirSession(userOut.getId()));
			return customUser;
		}

		throw new UsernameNotFoundException("User $username not found");

	}

	public void setAuthentificationService(
			AuthentificationService authentificationService) {
		this.authentificationService = authentificationService;
	}

	public void setSessionManagerService(SessionManagerService sessionManagerService) {
		this.sessionManagerService = sessionManagerService;
	}
	
}
