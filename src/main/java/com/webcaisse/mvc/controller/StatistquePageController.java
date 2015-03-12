package com.webcaisse.mvc.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webcaisse.beans.statistique.RechercheStatsIn;
import com.webcaisse.service.CustomUser;
import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.model.DetailsModePaiementIn;
import com.webcaisse.ws.model.DetailsModePaiementOut;

@Controller
@RequestMapping("/statistique")
public class StatistquePageController {

	@Autowired
	CaisseManagerService caisseManagerService;

	@RequestMapping("/")
	public String afficherStatistique(ModelMap model, @ModelAttribute("rechercheStatsIn") RechercheStatsIn in) {
		return "statistique";
	}

	@RequestMapping("/modePaiement")
	public void renderChart(OutputStream stream,@RequestParam(value = "dateDebut") String dateDebut, @RequestParam(value = "dateFin") String dateFin,ModelMap model) throws Exception {
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		 
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		
		model.addAttribute("dateDebut",dateDebut);
		model.addAttribute("dateFin",dateFin);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

		Calendar calendar1= Calendar.getInstance();
		calendar1.setTime(simpleDateFormat.parse(dateDebut));
		calendar1.add(Calendar.DATE, 100);
		Calendar calendar2= Calendar.getInstance();
		calendar2.setTime(simpleDateFormat.parse(dateFin));
		calendar2.add(Calendar.DATE, -10);
		  
		DetailsModePaiementIn in = new DetailsModePaiementIn();
		in.setIdSociete(customUser.getSocieteId());
		in.setDateDebutStats(calendar1.getTime());
		in.setDateFinStats(calendar2.getTime());
		List<DetailsModePaiementOut> detailsModePaiementOuts =  caisseManagerService.afficherDetailesModePaiement(in);
	    for (DetailsModePaiementOut detailsModePaiementOut : detailsModePaiementOuts) {
	    	 dataset.setValue(detailsModePaiementOut.getLibelleModePaiement(), detailsModePaiementOut.getMontantModePaiement());
		}
	    JFreeChart chart =  ChartFactory.createPieChart3D("Détails mode paiement", dataset, true, false, false);
		ChartUtilities.writeChartAsPNG(stream, chart, 750, 400);
		
	}
	
	

}
