package com.parser.udemy.parser;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.opencsv.CSVWriter;
import com.parser.file.FileReader;
import com.parser.udemy.bean.UdemyQuestionBean;
import com.parser.udemy.service.UdemyService;

public class UdemyParser {
	
	//public static final String FILE_PATH="C:\\Users\\Anurag\\Desktop\\temp\\20200531\\AWS Solutions Architect Associate Practice Exams NEW 2019 _ Udemy.html";
	
	@Value("${udemyFile.inputPath}")
	private String inputPath;

	@Value("${udemyFile.outputPath}")
	private String outputPath;
	
	public static final String HTML_APPENDOR=".html";
	public static final String CSV_APPENDOR=".csv";	
	@Autowired
	UdemyService udemyService;
	
	@Autowired
	FileReader fileReader;
	
	public List<UdemyQuestionBean> parseHtml() throws Exception {
		String htmlData=fileReader.getFileString(inputPath+ HTML_APPENDOR);
		return udemyService.getUdemyData(htmlData);
		//System.out.println("udemyQuestionBeanList : "+ udemyQuestionBeanList);
		//createCSV(udemyQuestionBeanList);
	}
	
	public void createCSV(List<UdemyQuestionBean> udemyQuestionBeanList) throws Exception {
		String header[] = {"Question", "Question Type (multiple-choice or multi-select)", "Answer Option 1", "Answer Option 2", "Answer Option 3","Answer Option 4","Answer Option 5","Answer Option 6","Correct Response","Explanation","Knowledge Area"};
		List<String[]> list = new ArrayList();
		list.add(header);
		
		udemyQuestionBeanList.forEach(udemyQuestionBean -> {
			list.add(udemyQuestionBean.convertValuestoList());
		});
		
		//Instantiating the CSVWriter class
	      CSVWriter writer = new CSVWriter(new FileWriter(outputPath+ "output"+ CSV_APPENDOR));
	      
	      writer.writeAll(list);
	      writer.flush();
	      System.out.println("Data entered");
	}
	
	
	  public void parseHtmlAndCreateCSV() throws Exception { 
		   
		  createCSV(parseHtml()); 
	}
	  
	 

}
