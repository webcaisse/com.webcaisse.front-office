package com.webcaisse.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webcaisse.beans.parametrage.ParametreCaisseIn;
import com.webcaisse.beans.parametrage.ParametreTVAIn;
import com.webcaisse.enums.commande.ParametrageTVAEnum;
import com.webcaisse.enums.parametrage.ParametrageCaisseEnum;
import com.webcaisse.service.CustomUser;
import com.webcaisse.ws.interfaces.LivreurManagerService;
import com.webcaisse.ws.interfaces.ParametreManagerService;
import com.webcaisse.ws.interfaces.UserManagerService;
import com.webcaisse.ws.model.LivreurIn;
import com.webcaisse.ws.model.LivreurOut;
import com.webcaisse.ws.model.ParametreIn;
import com.webcaisse.ws.model.ProfilVOut;
import com.webcaisse.ws.model.UserIn;
import com.webcaisse.ws.model.UserOut;

@Controller
@RequestMapping("/parametrage")
public class ParametragePageController {

	@Autowired
	ParametreManagerService parametreManagerService;
	
	@Autowired
	UserManagerService userManagerService;

	@Autowired
	LivreurManagerService livreurManagerService ;
	
	
	@RequestMapping("/afficher")
	public String afficher(ModelMap model) {
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<UserOut> users = parametreManagerService.rechercherUser(customUser.getSocieteId()) ;
		List<ProfilVOut> profils = userManagerService.getProfils();
		List<LivreurOut> livreurs = livreurManagerService.rechercherLivreur(customUser.getSocieteId()) ;
		model.put("users", users);
		model.put("profils", profils);
		model.put("livreurs", livreurs) ;

		ParametreCaisseIn caisseIn = new ParametreCaisseIn();
		caisseIn.setEntete1("dmldfmdkfmd");
		model.put("parametreCaisseIn", caisseIn);
		return "parametrage";
	}

	@RequestMapping(value = "/ajax/caisse", method = RequestMethod.GET)
	public String afficherParametrageCaisse(ModelMap model) {

		return "modules/parametrage/caisse";
	}

	@RequestMapping(value = "/sauvegarderParametresCaisse", method = RequestMethod.POST)
	public String sauvegarderParametres(ModelMap model,@ModelAttribute("parametreCaisseIn") ParametreCaisseIn parametreCaisseIn) {
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		ParametreIn parametreIn = new ParametreIn();
		parametreIn.setIdSociete(customUser.getSocieteId());

		sauvegarderParam(ParametrageCaisseEnum.entete1.getValeur(), parametreCaisseIn.getEntete1(), parametreIn);
		sauvegarderParam(ParametrageCaisseEnum.entete2.getValeur(),parametreCaisseIn.getEntete2(), parametreIn);
		sauvegarderParam(ParametrageCaisseEnum.entete3.getValeur(),parametreCaisseIn.getEntete3(), parametreIn);
		sauvegarderParam(ParametrageCaisseEnum.entete4.getValeur(),parametreCaisseIn.getEntete4(), parametreIn);

		sauvegarderParam(ParametrageCaisseEnum.pied1.getValeur(), parametreCaisseIn.getPied1(), parametreIn);
		sauvegarderParam(ParametrageCaisseEnum.pied2.getValeur(),parametreCaisseIn.getPied2(), parametreIn);
		sauvegarderParam(ParametrageCaisseEnum.pied3.getValeur(),parametreCaisseIn.getPied3(), parametreIn);
		sauvegarderParam(ParametrageCaisseEnum.pied4.getValeur(),parametreCaisseIn.getPied4(), parametreIn);


		return afficher(model);
	}


	@RequestMapping(value = "/sauvegarderParametresTVA", method = RequestMethod.POST)
	public String sauvegarderParametresTVA(ModelMap model,@ModelAttribute("parametreTVAIn") ParametreTVAIn parametreTVAIn) {
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		ParametreIn parametreIn = new ParametreIn();
		parametreIn.setIdSociete(customUser.getSocieteId());

		sauvegarderParam(ParametrageTVAEnum.tvaSurPlace.getValeur(), parametreTVAIn.getTvaSurPlace(), parametreIn);
		sauvegarderParam(ParametrageTVAEnum.tvaEmporter.getValeur(),parametreTVAIn.getTvaEmporter(),parametreIn) ;
		sauvegarderParam(ParametrageTVAEnum.tvaLivraison.getValeur(),parametreTVAIn.getTvaLivraison(),parametreIn) ;
		

		return afficher(model);
	}
	
	@RequestMapping( value="/sauvegarderParametresUtilisateur",method=RequestMethod.POST)
	public String sauvgarderParametresUtilisateurs(ModelMap model,@ModelAttribute("parametreUtilisateurIn") UserIn parametreUtilisateurIn ){
		
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		parametreUtilisateurIn.setSocieteId(customUser.getSocieteId());
		parametreUtilisateurIn.setEnabled(Boolean.TRUE);
		parametreManagerService.sauvegarderUser(parametreUtilisateurIn);
		return afficher(model); 	
	}
	
	
	@RequestMapping( value="/sauvegarderParametresLivreur",method=RequestMethod.POST)
	public String sauvgarderParametresLivreur(ModelMap model,@ModelAttribute("parametreLivreurIn") LivreurIn parametreLivreurIn ){
		
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		parametreLivreurIn.setSocieteId(customUser.getSocieteId());
		livreurManagerService.sauvegarderLivreur(parametreLivreurIn);
		return afficher(model); 	
	}
	
	
	
	
	
	/**
	 * Sauvegarder un parametre dans la BDD
	 * 
	 * @param paramName
	 * @param paramValue
	 * @param parametreIn
	 */
	private void sauvegarderParam(String paramName, String paramValue,ParametreIn parametreIn) {
		if (!StringUtils.isEmpty(paramValue)) {
			parametreIn.setNomParametre(paramName);
			parametreIn.setValeur(paramValue);
			parametreManagerService.sauvegarderParametre(parametreIn);
		}
	}

}
