package com.parser.udemy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.parser.udemy.bean.UdemyQuestionBean;
import com.parser.udemy.service.UdemyService;

@Service("udemyService")
public class UdemyServiceImpl implements UdemyService {
	
	UdemyQuestionBean udemyQuestionBean = new UdemyQuestionBean();
	
	public List<UdemyQuestionBean> getUdemyData(String htmlData) {
		
		Document doc=getDocument(htmlData);
		
		Elements rowElement = doc.select("div.detailed-result-panel--question-container--7NyiS");
		System.out.println("rowElement size : "+ rowElement.size());
		

		
		List<UdemyQuestionBean> udemyQuestionBeanList=new ArrayList();
		
		rowElement.forEach(row -> {
			row.select("div.mc-quiz-question--header--3fsHJ").forEach(questionElement -> {
				String questionIdElement = questionElement.select("span").first().text();
				  System.out.println("questionId : "+ questionIdElement);
				  udemyQuestionBean.setQuestionNo(questionIdElement);
			});
			
			
			row.select("div.mc-quiz-question--header--3fsHJ").forEach(questionElement -> {
				String questionDesc = questionElement.select("div#question-prompt").first().text();
				  System.out.println("questionDesc : "+ questionDesc);
				  udemyQuestionBean.setQuestion(questionDesc);
			});
			
			
			row.select("ul[aria-labelledby=question-prompt]").forEach(choiceElement -> {

				
				 List<String> al=choiceElement.select("div.mc-quiz-answer--question-copy--314BC").stream().
						 map(Element :: text).collect(Collectors.toList()); 
				 udemyQuestionBean.setAnswerOptions(al);
				 List<String> aa=choiceElement.select("li > div:first-child").stream().
						 map(s -> { 
							 return s.attr("class"); 
							 }).collect(Collectors.toList());
				 
				 udemyQuestionBean.setCorrectResponse(aa); 
				
			});
			
			
			
			row.select("div.mc-quiz-question--explanation--Q5KHQ").stream().map(element-> {
				return element.text().replace("Explanation", "");
			}).forEach(s-> {
				udemyQuestionBean.setExplanation(s);
			});;
			
			udemyQuestionBeanList.add(udemyQuestionBean);
			
			udemyQuestionBean=new UdemyQuestionBean();
		});
		
		
		return udemyQuestionBeanList;
	}
	
	
	private Document getDocument(String htmlData) {
		return Jsoup.parse(htmlData);
	}
	
	

}
