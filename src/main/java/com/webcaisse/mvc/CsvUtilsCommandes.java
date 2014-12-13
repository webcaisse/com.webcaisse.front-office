package com.webcaisse.mvc;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.webcaisse.ws.model.ClientOut;
import com.webcaisse.ws.model.CommandeOut;

public class CsvUtilsCommandes {
	
	public String fileName ;
    public  List<CommandeOut> commandes ;
   
	
	public CsvUtilsCommandes() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void writeCommande( String fileName, List<CommandeOut> commandes, HttpServletResponse response ) throws IOException {
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(response.getWriter());
		} catch (IOException e) {
			e.printStackTrace();
		}
         try {
             response.setHeader("Content-Disposition","attachment; filename=\""+fileName+"\"");
             for (CommandeOut commande : commandes) {
                 writer.write(commande.getLibelleProduit());
                 writer.write(",");
                 writer.write(commande.getDateCommande().toString());
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
