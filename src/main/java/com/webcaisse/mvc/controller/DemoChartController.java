package com.webcaisse.mvc.controller;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webcaisse.service.CustomUser;
import com.webcaisse.ws.interfaces.CaisseManagerService;
import com.webcaisse.ws.interfaces.ClientManagerService;
import com.webcaisse.ws.model.FamilleOut;

/**
* Example Spring Controller for creating JFreeChart charts
*/
@Controller
public class DemoChartController {
 

	@Autowired
	CaisseManagerService caisseManagerService;
	
  @RequestMapping("/demo-chart.png")
  public void renderChart(String variation, OutputStream stream) throws Exception {
    boolean rotate = "rotate".equals(variation); // add ?variation=rotate to the URL to rotate the chart
    JFreeChart chart = generateChart(rotate);
    ChartUtilities.writeChartAsPNG(stream, chart, 750, 400);
  }
 
  private JFreeChart generateChart(boolean rotate){
    DefaultCategoryDataset data = getDataset();
    return ChartFactory.createBarChart( "example graph", // title
        "x-axis",  // x-axis label
        "y-axis",  // y-axis label
        data,
        rotate ? PlotOrientation.HORIZONTAL : PlotOrientation.VERTICAL,
        true,  // legend displayed
        true,  // tooltips displayed
        false );  // no URLs*/
  }

  private DefaultCategoryDataset getDataset(){
      DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      // Run the SQL query and add the results to the JFreeChart dataset

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

	  List<FamilleOut> familleOuts =  caisseManagerService.getFamillesActivees(customUser.getSocieteId());
      for (FamilleOut familleOut : familleOuts){

//          Number count = (Number) result.get("count");
//          String category = (String) result.get("categoryName");
//          String day = dateFormatter.format((Timestamp) result.get("day"));
          dataset.addValue(2, familleOut.getLibelle(), new Date().getDay()+"");
      }
      return dataset;
  }

}
