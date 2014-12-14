package com.webcaisse.mvc;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.webcaisse.ws.model.ClientOut;

public class CsvUtils {
	
	public String fileName ;
    public List<ObjectCSV> objCsv ;
   
	
	public CsvUtils() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void exporter ( String fileName, List<ObjectCSV> objCsv, HttpServletResponse response ) throws IOException {
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(response.getWriter());
		   } catch (IOException e) {
			e.printStackTrace();
		}
         try {
             response.setHeader("Content-Disposition","attachment; filename=\""+fileName+"\"");
             
             for (ObjectCSV objectCSV : objCsv) {
				
                 writer.write(objectCSV.getLibelle());
                 writer.write(",");
                 writer.write(objectCSV.getPrenom());
                 writer.write(",");
                 writer.write(objectCSV.getTelephone());
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

