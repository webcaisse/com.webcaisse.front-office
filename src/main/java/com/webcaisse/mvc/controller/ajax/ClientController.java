package com.webcaisse.mvc.controller.ajax;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webcaisse.mvc.bean.CommandeDump;
import com.webcaisse.mvc.bean.States;
import com.webcaisse.service.CustomUser;
import com.webcaisse.ws.interfaces.ClientManagerService;
import com.webcaisse.ws.model.ClientIn;
import com.webcaisse.ws.model.ClientOut;

@Controller
@RequestMapping("/ajax/client")
public class ClientController {
	
	@Autowired
	private CommandeDump commandeDump;
	
	@Autowired
	private ClientManagerService clientManagerService ;
	
	

	@RequestMapping(value = "/autoCompleteClient", method = RequestMethod.GET)
	@ResponseBody
	public List <ClientOut> autoCompleteClient(@RequestParam("term") String param){
		
		return clientManagerService.autoCompleteClient(param) ;
	}
	
	
	@RequestMapping(value="/selectClient/{idClient}", method= RequestMethod.GET)
	@ResponseBody
	public ClientOut selectClient(@PathVariable("idClient") Long idClient){
		ClientOut clientOut = clientManagerService.loadClientById(idClient);
		if (clientOut!=null){
			updateInMemoryClient(clientOut);
		}
		System.out.println(clientOut.getId());
		System.out.println(clientOut.getNom()) ;
		return clientOut;
		
	
	}

	/**
	 * Mise a jour du client mémoire
	 * @param clientOut
	 */
	private void updateInMemoryClient(ClientOut clientOut) {
		commandeDump.getClient().setIdClient(clientOut.getId());
		commandeDump.getClient().setCodePostale(clientOut.getCodePostale());
		commandeDump.getClient().setEtage(clientOut.getEtage());
		commandeDump.getClient().setImmeuble(clientOut.getImmeuble());
		commandeDump.getClient().setInterphone(clientOut.getInterphone());
		commandeDump.getClient().setNom(clientOut.getNom());
		commandeDump.getClient().setPrenom(clientOut.getPrenom());
		commandeDump.getClient().setNumeroRue(clientOut.getNumeroRue());
		commandeDump.getClient().setNomRue(clientOut.getNomRue());
		commandeDump.getClient().setTelephone(clientOut.getTelephone());
	}

	
	@RequestMapping(value = "/ajouterClient", method = RequestMethod.POST)
	@ResponseBody
	public String ajouterClient(@ModelAttribute("clientIn") ClientIn client, BindingResult result) {
		
		try {
			CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			client.setIdSociete(customUser.getSocieteId());
			clientManagerService.ajouterClient(client);

			return States.SAVE_OK.getCode();
		} catch (Exception e) {
			return States.SAVE_KO.getCode();
		}
	}

	@RequestMapping(value="/afficherListClient", method=RequestMethod.GET)
	public String afficherListClient(ModelMap model){
		
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<ClientOut> clients  = clientManagerService.rechercherClient(customUser.getSocieteId());
		model.put("clients", clients);
		
		return "modules/popup_client";
	}
	
}
