package com.webcaisse.mvc.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webcaisse.ws.interfaces.ClientManagerService;
import com.webcaisse.ws.interfaces.CommandeManagerService;
import com.webcaisse.ws.model.ClientOut;
import com.webcaisse.ws.model.CommandeOut;


@Controller
@RequestMapping("/clients")
public class ClientPageController {

	@Autowired (required=true)
	ClientManagerService clientManagerService ;
	final static Long ID_SOCIETE= 1L ;
	
	
	@RequestMapping("/afficher/{idSociete}")
	public String afficherClients (ModelMap model ,@PathVariable Long idSociete){
		
	List<ClientOut> clients = clientManagerService.rechercherClient(idSociete) ;
		
		model.put("clients", clients) ;
		
		return "clients";
	}
	
	@RequestMapping("/exporterClients")
	public void exporterCommande(HttpServletResponse response) throws IOException{
		BufferedWriter writer = null;
		
		List<ClientOut> clients = clientManagerService.rechercherClient(ID_SOCIETE) ;
		
		try {
			writer = new BufferedWriter(response.getWriter());
		} catch (IOException e) {
			e.printStackTrace();
		}
         try {
             response.setHeader("Content-Disposition","attachment; filename=\"clients.csv\"");
             for (ClientOut client : clients) {
                 writer.write(client.getNom());
                 writer.write(",");
                 writer.write(client.getPrenom());
                 writer.write(",");
                 writer.write(client.getEmail()) ;
                 writer.write(",");
                 writer.write(client.getTelephone());
                 writer.write("\n");
                
             }
             writer.newLine();
         } catch (IOException ex) {
         } finally {
             try {
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
             writer.close();
         }
		
		
	}
}
