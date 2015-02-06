package com.webcaisse.mvc.controller.ajax;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webcaisse.mvc.bean.CommandeDump;
import com.webcaisse.ws.interfaces.ClientManagerService;
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
	
}
