package com.webcaisse.mvc;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.webcaisse.ws.model.ClientOut;

public class CsvUtilsClients {
	
	public String fileName ;
    public  List<ClientOut> clients;
   
	
	public CsvUtilsClients() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void writeClient ( String fileName, List<ClientOut> clients, HttpServletResponse response ) throws IOException {
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(response.getWriter());
		   } catch (IOException e) {
			e.printStackTrace();
		}
         try {
             response.setHeader("Content-Disposition","attachment; filename=\""+fileName+"\"");
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
		
		
	}}

