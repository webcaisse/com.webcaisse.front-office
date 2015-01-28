package com.webcaisse.mvc.controller.ajax;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webcaisse.mvc.bean.Client;
import com.webcaisse.ws.interfaces.ClientManagerService;
import com.webcaisse.ws.model.ClientIn;
import com.webcaisse.ws.model.ClientOut;

@Controller
@RequestMapping("/ajax/client")
public class ClientController {
	
	@Autowired
	Client client;
	
	@Autowired
	ClientManagerService clientManagerService ;
	
	

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
		return clientOut;
	}

	/**
	 * Mise a jour du client mémoire
	 * @param clientOut
	 */
	private void updateInMemoryClient(ClientOut clientOut) {
		client.setIdClient(clientOut.getId());
		client.setCodePostale(clientOut.getCodePostale());
		client.setEtage(client.getEtage());
		client.setImmeuble(clientOut.getImmeuble());
		client.setInterphone(clientOut.getInterphone());
		client.setNom(clientOut.getNom());
		client.setPrenom(clientOut.getPrenom());
		client.setNumeroRue(clientOut.getNumeroRue());
		client.setNomRue(clientOut.getNomRue());
		client.setTelephone(clientOut.getTelephone());
	}
	
}
