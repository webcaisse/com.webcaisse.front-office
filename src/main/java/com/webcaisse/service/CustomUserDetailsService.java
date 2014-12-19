package com.webcaisse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webcaisse.ws.interfaces.AuthentificationService;
import com.webcaisse.ws.model.UserOut;


public class CustomUserDetailsService implements UserDetailsService{

	@Autowired(required=true)
    AuthentificationService authentificationService ;
	
	
	
	public UserDetails loadUserByUsername(String userName)throws UsernameNotFoundException {
	 
		UserOut userOut = null ;
		
		
		try{
		userOut = authentificationService.findByUserName(userName);
		
		
		System.out.print(userOut.getNom());
		System.out.print(userOut.getPrenom());
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		return new User(userOut.getUsername(),userOut.getPassword(),enabled,accountNonExpired, credentialsNonExpired,accountNonLocked, null)  ;
				
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	}

	
	

