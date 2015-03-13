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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	

	@RequestMapping("/")
	public String afficherStatistique(ModelMap model, @ModelAttribute("rechercheStatsIn") RechercheStatsIn in) {
		// startDate par defaut
		Calendar calendar1= Calendar.getInstance();
		calendar1.setTime(new Date());
		calendar1.add(Calendar.DATE, -1);
		Calendar calendar2= Calendar.getInstance();
		calendar2.setTime(new Date());
		calendar2.add(Calendar.DATE, +1);
		
		
		model.put("dateDebut", sdf.format(calendar1.getTime()));
		model.put("dateFin", sdf.format(calendar2.getTime()));
		return "statistique";
	}
	
	@RequestMapping(value="/chercherStatistique")
	public String changeDate(Model model, @ModelAttribute("rechercheStatsIn") RechercheStatsIn in) {
		model.addAttribute("dateDebut", sdf.format(in.getStartDate()));
		model.addAttribute("dateFin", sdf.format(in.getEndDate()));
		return "statistique";
	}

	@RequestMapping("/modePaiement/{dateDebut}/{dateFin}")
	public void renderChart(OutputStream stream,@PathVariable("dateDebut") @DateTimeFormat(pattern="yyyyMMdd") Date dateDebut,
			@PathVariable("dateFin") @DateTimeFormat(pattern="yyyyMMdd") Date dateFin) throws Exception {
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		 
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		DetailsModePaiementIn in = new DetailsModePaiementIn();
		in.setIdSociete(customUser.getSocieteId());
		in.setDateDebutStats(dateDebut);
		in.setDateFinStats(dateFin);
		List<DetailsModePaiementOut> detailsModePaiementOuts =  caisseManagerService.afficherDetailesModePaiement(in);
	    for (DetailsModePaiementOut detailsModePaiementOut : detailsModePaiementOuts) {
	    	 dataset.setValue(detailsModePaiementOut.getLibelleModePaiement(), detailsModePaiementOut.getMontantModePaiement());
		}
	    JFreeChart chart =  ChartFactory.createPieChart3D("Détails mode paiement", dataset, true, false, false);
		ChartUtilities.writeChartAsPNG(stream, chart, 750, 400);
		
	}
	
	

}
