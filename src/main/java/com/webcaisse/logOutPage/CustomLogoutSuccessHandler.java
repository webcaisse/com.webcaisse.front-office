package com.webcaisse.logOutPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.webcaisse.service.CustomUser;
import com.webcaisse.ws.interfaces.SessionManagerService;

public class CustomLogoutSuccessHandler  extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler  {
	
	SessionManagerService sessionManagerService ;
	
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request,HttpServletResponse response, Authentication  authentication) throws IOException,
			ServletException {
		
		CustomUser customUser = (CustomUser) authentication.getPrincipal();

		sessionManagerService.fermerSession(customUser.getSessionId());
		//redirect to login  
        response.sendRedirect(request.getContextPath()+"/login"); 
		
	}

	public void setSessionManagerService(SessionManagerService sessionManagerService) {
		this.sessionManagerService = sessionManagerService;
	}

	
}
