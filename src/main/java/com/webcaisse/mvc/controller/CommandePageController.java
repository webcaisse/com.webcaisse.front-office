package com.webcaisse.mvc.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webcaisse.beans.csv.ObjectCSV;
import com.webcaisse.enums.parametrage.ParametrageCaisseEnum;
import com.webcaisse.service.CustomUser;
import com.webcaisse.utils.CsvUtils;
import com.webcaisse.ws.interfaces.CommandeManagerService;
import com.webcaisse.ws.interfaces.LivreurManagerService;
import com.webcaisse.ws.interfaces.ParametreManagerService;
import com.webcaisse.ws.model.CommandeOut;
import com.webcaisse.ws.model.LigneCommandeOut;
import com.webcaisse.ws.model.LivreurOut;
import com.webcaisse.ws.model.ParametreOut;

@Controller
@RequestMapping("/commandes")
public class CommandePageController {

	@Autowired(required = true)
	CommandeManagerService commandeManagerService;
	
	@Autowired
	LivreurManagerService livreurManagerService ;
	
	@Autowired
	ParametreManagerService  parametreManagerService ;
	

	@RequestMapping(value = "/enCours", method = RequestMethod.GET)
	public String afficherCommandeEnCours(ModelMap model) {
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<CommandeOut> commandes = commandeManagerService.rechercherCommande(customUser.getSessionId());
        List<LivreurOut> livreurs= livreurManagerService.rechercherLivreur(customUser.getSocieteId()) ;
		model.put("commandes", commandes);
		model.put("livreurs",livreurs) ;

		return "/commandesEnCours";
	}

	@RequestMapping(value = "/rechercherCommande", method = RequestMethod.GET)
	public String rechercherCommandes(
			@RequestParam(value = "dateCommande") String dateCommande,
			ModelMap model) throws ParseException {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		model.addAttribute("dateCommande", dateCommande);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		model.put(
				"commandes",
				commandeManagerService.rechercherCommandeParDate(
						customUser.getSocieteId(),
						simpleDateFormat.parse(dateCommande)));

		return "/commandesEnCours";
	}

	@RequestMapping(value = "/exporterCommande/{dateExport}/", method = RequestMethod.GET)
	public void exporterCommande(HttpServletResponse response,
			@PathVariable String dateExport) throws IOException, ParseException {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		List<CommandeOut> commandes = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

		CsvUtils csvUtils = new CsvUtils();
		List<ObjectCSV> objectCSV = new ArrayList<ObjectCSV>();
		ObjectCSV o = new ObjectCSV();

		if (dateExport != null && !dateExport.isEmpty()
				&& !"DATE_VIDE".equals(dateExport)) {
			commandes = commandeManagerService.rechercherCommandeParDate(
					customUser.getSocieteId(),
					simpleDateFormat.parse(dateExport));
		} else {
			commandes = commandeManagerService.rechercherCommande(customUser
					.getSessionId());
		}

		for (CommandeOut commandeOut : commandes) {

			o.setLibelle(commandeOut.getLibelleProduit());
			o.setDateCommande(commandeOut.getDateCommande());

			objectCSV.add(o);
		}

		csvUtils.exporter("commandes.csv", objectCSV, response);

	}
	
	@RequestMapping("/details/{idCommande}")
	public String afficherDetails(Model model ,@PathVariable("idCommande") Long idCommande) {

		CommandeOut commandeOut = commandeManagerService.loadCommandeById(idCommande);
		List <LigneCommandeOut> ligneComandeOuts= commandeOut.getLigneCommandeOut() ;
	 
		model.addAttribute ("commandeOut",commandeOut) ;
		model.addAttribute ("ligneComandeOuts",ligneComandeOuts) ;

		return "detailsCommande";
	}
	
	@RequestMapping(value = "/rechercherCommandeParLivreur", method = RequestMethod.GET)
	public String rechercherCommandeParLivreur(Model model,@RequestParam(value = "idLivreur") Long idLivreur){
		
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		List<CommandeOut> commandes= commandeManagerService.getCommandesByIdLivreur(idLivreur) ;
		List<LivreurOut> livreurs= livreurManagerService.rechercherLivreur(customUser.getSocieteId()) ;
		model.addAttribute("commandes",commandes) ;
		model.addAttribute("livreurs",livreurs) ;
		
		return "/commandesEnCours";
		
		
	}
	
	@RequestMapping(value="/impressionCommande/{idCommande}",  method = RequestMethod.GET)
	public String impressionCommande(Model model, @PathVariable("idCommande") Long idCommande ){
		
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		CommandeOut commandeOut = commandeManagerService.loadCommandeById(idCommande);
		List <LigneCommandeOut> ligneComandeOuts= commandeOut.getLigneCommandeOut() ;
		//ParametreOut paramatreOut= parametreManagerService.getReferenceByName(ParametrageCaisseEnum.entete1.getValeur()) ;
		
	   List<ParametreOut> referencesHeader=parametreManagerService.getHeaderReferences(customUser.getSocieteId()) ;
	   List<ParametreOut> referencesFooter=parametreManagerService.getFootersReferences(customUser.getSocieteId()) ;
		
	   model.addAttribute ("commandeOut",commandeOut) ;
		model.addAttribute ("ligneComandeOuts",ligneComandeOuts) ;
		model.addAttribute("referencesHeader",referencesHeader) ;
		model.addAttribute("referencesFooter",referencesFooter) ;
		
		
		
		return "/impressionCommande" ;
	}
}
