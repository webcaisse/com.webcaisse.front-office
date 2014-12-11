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
import org.springframework.web.bind.annotation.RequestParam;


import com.webcaisse.ws.interfaces.CommandeManagerService;
import com.webcaisse.ws.model.CommandeOut;

@Controller
@RequestMapping("/commandes")
public class CommandePageController {

	@Autowired (required=true)
	CommandeManagerService commandeManagerService ;
	
	private static final Long ID_SOCIETE = 1L;
	
	@RequestMapping(value = "/enCours/{idSession}", method=RequestMethod.GET)
	public String afficherCommandeEnCours(ModelMap model,@PathVariable  Long idSession){
		
		List<CommandeOut> commandes = commandeManagerService.rechercherCommande(idSession) ;
		
		model.put("commandes", commandes) ;
		
		return "/commandesEnCours";
	}
	
	@RequestMapping(value = "/rechercherCommande",  method=RequestMethod.GET)
	public String rechercherCommandes(@RequestParam(value="dateCommande") String  dateCommande,ModelMap model) throws ParseException{
		
		//List<CommandeOut> commandes = commandeManagerService.rechercherCommandeParDate(ID_SOCIETE, dateCommande) ;
		model.addAttribute("dateCommande", dateCommande) ;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		model.put("commandes", commandeManagerService.rechercherCommandeParDate(ID_SOCIETE,simpleDateFormat.parse(dateCommande)));
		
		return "/commandesEnCours";
	}
	
	@RequestMapping(value="/exporterCommande/{idSession}",method=RequestMethod.GET)
	public void exporterCommande(HttpServletResponse response,@PathVariable  Long idSession) throws IOException{
		BufferedWriter writer = null;
		
		List<CommandeOut> commandes= commandeManagerService.rechercherCommande(idSession) ;
		
		 
		try {
			writer = new BufferedWriter(response.getWriter());
		} catch (IOException e) {
			e.printStackTrace();
		}
         try {
             response.setHeader("Content-Disposition","attachment; filename=\"commandes.csv\"");
             for (CommandeOut commande : commandes) {
                 writer.write(commande.getLibelleProduit());
                 writer.write(",");
                 writer.write(commande.getDateCommande().toString());
                 writer.write("\n");
                 //System.out.println(writer) ;
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
