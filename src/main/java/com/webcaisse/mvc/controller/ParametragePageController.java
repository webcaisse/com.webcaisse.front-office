package com.webcaisse.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webcaisse.mvc.bean.Parametres;
import com.webcaisse.service.CustomUser;
import com.webcaisse.ws.interfaces.ParametreManagerService;
import com.webcaisse.ws.model.ClientIn;
import com.webcaisse.ws.model.ParametreIn;


@Controller
@RequestMapping("/parametrage")
public class ParametragePageController {

    @Autowired
	ParametreManagerService parametreManagerService ;

	@RequestMapping("/afficher")
	public String afficher(){
		
		return "parametrage";
	}
	
	@RequestMapping(value="/ajax/caisse", method = RequestMethod.GET)
	public String afficherParametrageCaisse(ModelMap model){
		//List<ParametreIn> parametres = new  ArrayList<ParametreIn>() ;

        Parametres parametres = new Parametres () ;
		model.addAttribute("parametres", parametres) ;
		return "modules/parametrage/caisse";
	}
	
	@RequestMapping(value="/sauvegarderParametres",method = RequestMethod.POST)
	public void sauvegarderParametres (@ModelAttribute("parametres") Parametres parametres) 
	{
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
		for (ParametreIn parametreIn : parametres.getParametres()) {
			
			parametreIn.setIdSociete(customUser.getSocieteId());
			parametreManagerService.sauvegarderParametre(parametreIn);
		}
		
		
		
		
		
	}
	
}
