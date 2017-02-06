package com.nviz.report.util;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.DefaultFontMapper;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.nviz.vo.Record;

public class ReportGeneratorUtils {
	
	public static Map<String, Integer> getConsolidatedMap(Map<String, List<Integer>> map) {
		Map<String,Integer> cMap = new HashMap<>();
		for(Entry<String, List<Integer>> entry: map.entrySet()){
			String quality = entry.getKey();
			List<Integer> inputs = entry.getValue();
			int sum=0;
			for(Integer inp : inputs){
				sum+=inp;
			}
			int avg = sum/inputs.size();
			cMap.put(quality,avg);
		}
		return cMap;
	}

	public static Map generateMap(List<Record> records) {
		Map<String,List<Integer>> map = new HashMap<>();
		for(Record record : records){
			if(map.get(record.getQuality()) == null){
				List<Integer> inputs = new ArrayList<>();
				inputs.add(record.getInput());
				map.put(record.getQuality().getTitle(),inputs);
			}
			else{
				List<Integer> inputs = (List<Integer>) map.get(record.getQuality());
				inputs.add(record.getInput());
				map.put(record.getQuality().getTitle(),inputs);
			}
		}
		return getConsolidatedMap(map);
	}
	
	public static void writeChartToPDF(Map map, int width, int height, String fileName) throws Exception {
		PdfWriter writer = null;
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		JFreeChart chart = generateBarChart(map,dataSet);
		Document document = new Document();
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(
					fileName));
			 document.open();
			PdfContentByte contentByte = writer.getDirectContent();
			PdfTemplate template = contentByte.createTemplate(width, height);
			Graphics2D graphics2d = template.createGraphics(width, height,
					new DefaultFontMapper());
			Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,
					height);
			chart.draw(graphics2d, rectangle2d);
			graphics2d.dispose();
			contentByte.addTemplate(template, 0, 0);
		} catch (Exception e) {
			throw new Exception(e);
		}
		( document).close();
	}

	public static JFreeChart generateBarChart(Map<String, Integer> cMap, DefaultCategoryDataset dataSet) {
		for(Entry<String,Integer> entry : cMap.entrySet()){
			dataSet.setValue(entry.getValue(), "Survey", entry.getKey());
		}

		JFreeChart chart = ChartFactory.createBarChart(
				"Nvizion 360 Feedback survey report", "Quality", "Rating",
				dataSet, PlotOrientation.VERTICAL, false, true, false);

		return chart;
	}
}
