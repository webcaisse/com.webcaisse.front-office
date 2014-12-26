package com.webcaisse.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webcaisse.mvc.CsvUtils;
import com.webcaisse.mvc.ObjectCSV;
import com.webcaisse.service.CustomUser;
import com.webcaisse.ws.interfaces.ClientManagerService;
import com.webcaisse.ws.model.ClientOut;

@Controller
@RequestMapping("/clients")
public class ClientPageController {

	@Autowired(required = true)
	ClientManagerService clientManagerService;

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
		ObjectCSV o = new ObjectCSV();
		List<ClientOut> clients = clientManagerService
				.rechercherClient(customUser.getSocieteId());

		for (ClientOut clientOut : clients) {

			o.setLibelle(clientOut.getNom());
			o.setPrenom(clientOut.getPrenom());
			o.setTelephone(clientOut.getTelephone());
			o.setEmail(clientOut.getEmail());

			objectCSV.add(o);
		}

		csvUtils.exporter("clients.csv", objectCSV, response);
	}
}
