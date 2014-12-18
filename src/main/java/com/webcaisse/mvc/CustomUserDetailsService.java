package com.webcaisse.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webcaisse.ws.interfaces.AuthentificationService;
import com.webcaisse.ws.model.UserOut;




@Transactional
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	AuthentificationService authentificationService ;
	
	
	
	public UserDetails loadUserByUsername(String userName)throws UsernameNotFoundException {
	 
		
		try{
		UserOut userOut = authentificationService.findByUserName(userName);
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		return new User(userOut.getNom(),userOut.getPrenom(),enabled,accountNonExpired, credentialsNonExpired,accountNonLocked, null)  ;
				
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	}

	
	

