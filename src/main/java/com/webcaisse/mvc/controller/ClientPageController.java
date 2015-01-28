package com.webcaisse.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webcaisse.mvc.CsvUtils;
import com.webcaisse.mvc.ObjectCSV;
import com.webcaisse.mvc.bean.Client;
import com.webcaisse.service.CustomUser;
import com.webcaisse.validator.ClientValidator;
import com.webcaisse.ws.interfaces.ClientManagerService;
import com.webcaisse.ws.model.ClientIn;
import com.webcaisse.ws.model.ClientOut;

@Controller
@RequestMapping("/clients")
public class ClientPageController {

	@Autowired(required = true)
	ClientManagerService clientManagerService;

	@Autowired
	ClientValidator clientValidator;
	
	@Autowired
	Client client;

	@RequestMapping("/afficher")
	public String afficherClients(ModelMap model) {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		List<ClientOut> clients = clientManagerService
				.rechercherClient(customUser.getSocieteId());

		model.put("clients", clients);

		return "clients";
	}

	@RequestMapping("/exporterClients")
	public void exporterCommande(HttpServletResponse response)
			throws IOException {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		CsvUtils csvUtils = new CsvUtils();
		List<ObjectCSV> objectCSV = new ArrayList<ObjectCSV>();

		List<ClientOut> clients = clientManagerService
				.rechercherClient(customUser.getSocieteId());

		for (ClientOut clientOut : clients) {

			ObjectCSV o = new ObjectCSV();
			o.setLibelle(clientOut.getNom());
			o.setPrenom(clientOut.getPrenom());
			o.setTelephone(clientOut.getTelephone());
			o.setEmail(clientOut.getEmail());

			objectCSV.add(o);
		}

		csvUtils.exporter("clients.csv", objectCSV, response);
	}

	@RequestMapping(value = "/afficherFormulaireClient", method = RequestMethod.GET)
	public String afficherFomulaire(Model model) {
		ClientIn in = new ClientIn();
		model.addAttribute("clientIn", in);

		return "ajouterClient";
	}

	@RequestMapping(value = "/ajouterClient", method = RequestMethod.POST)
	public String ajouterClient(@ModelAttribute("clientIn") ClientIn client,
			BindingResult result) {
		clientValidator.validate(client, result);

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		client.setIdSociete(customUser.getSocieteId());

		if (result.hasErrors()) {

			return "ajouterClient";
		} else {
			clientManagerService.ajouterClient(client);

			return "redirect:/clients/afficher";
		}
	}

	@RequestMapping("/supprimerClient/{idClient}")
	public String supprimerFamille(@PathVariable("idClient") Long idClient) {

		clientManagerService.supprimerClient(idClient);

		return "redirect:/clients/afficher";
	}

	@RequestMapping(value = "/afficherUpdateClient/{idClient}", method = RequestMethod.GET)
	public String afficherUpadateClient(Model model,
			@PathVariable("idClient") Long idClient) {
		ClientOut clientOut = clientManagerService.loadClientById(idClient);
		
		ClientIn in = new ClientIn();

		in.setNom(clientOut.getNom());
		in.setPrenom(clientOut.getPrenom());
		in.setEmail(clientOut.getEmail());
		in.setTelephone(clientOut.getTelephone());
		in.setCode1(clientOut.getCode1());
		in.setCode2(clientOut.getCode2());
		in.setCode3(clientOut.getCode3());
		in.setCodePostale(clientOut.getCodePostale());
		in.setEtage(clientOut.getEtage());
		in.setImmeuble(clientOut.getImmeuble());
		in.setInterphone(clientOut.getInterphone());
		in.setNumeroRue(clientOut.getNumeroRue());
		in.setNomRue(clientOut.getNomRue());
		in.setVille(clientOut.getVille());

		in.setId(idClient);

		model.addAttribute("clientIn", in);
		return "MajClient";
	}

	@RequestMapping(value = "/saveUpdateClient", method = RequestMethod.POST)
	public String saveUpdate(@ModelAttribute("clientIn") ClientIn client) {

		clientManagerService.updateClient(client);
		return "redirect:/clients/afficher";
	}
	
	@RequestMapping(value = "/ajouterClientMemoire")
	public String ajouterClient(@ModelAttribute("clientIn") ClientIn clientIn) {
		
		client.setNom(clientIn.getNom());
		client.setPrenom(clientIn.getPrenom());
		client.setTelephone(clientIn.getTelephone());
		client.setCodePostale(clientIn.getCodePostale());
		client.setInterphone(clientIn.getInterphone());
		client.setNomRue(clientIn.getNomRue());
		client.setNumeroRue(clientIn.getNumeroRue());
		client.setImmeuble(clientIn.getImmeuble());
		client.setEtage(clientIn.getEtage());
		
		
		return "redirect:/loginSuccess" ;
}

}
