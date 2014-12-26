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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webcaisse.mvc.CsvUtils;
import com.webcaisse.mvc.ObjectCSV;
import com.webcaisse.service.CustomUser;
import com.webcaisse.ws.interfaces.CommandeManagerService;
import com.webcaisse.ws.model.CommandeOut;

@Controller
@RequestMapping("/commandes")
public class CommandePageController {

	@Autowired(required = true)
	CommandeManagerService commandeManagerService;

	@RequestMapping(value = "/enCours", method = RequestMethod.GET)
	public String afficherCommandeEnCours(ModelMap model) {
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		List<CommandeOut> commandes = commandeManagerService
				.rechercherCommande(customUser.getSessionId());

		model.put("commandes", commandes);

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
}
