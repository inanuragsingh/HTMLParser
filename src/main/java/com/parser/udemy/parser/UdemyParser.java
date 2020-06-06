package com.parser.udemy.parser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.parser.file.FileReader;
import com.parser.udemy.bean.UdemyQuestionBean;
import com.parser.udemy.service.UdemyService;

public class UdemyParser {
	
	public static final String FILE_PATH="C:\\Users\\Anurag\\Desktop\\temp\\20200531\\AWS Solutions Architect Associate Practice Exams NEW 2019 _ Udemy.html";
	
	@Autowired
	UdemyService udemyService;
	
	@Autowired
	FileReader fileReader;
	
	public void parseHtml() {
		String htmlData=fileReader.getFileString(FILE_PATH);
		List<UdemyQuestionBean> udemyQuestionBeanList=udemyService.getUdemyData(htmlData);
		System.out.println("udemyQuestionBeanList : "+ udemyQuestionBeanList);
	}

}
