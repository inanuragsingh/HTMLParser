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

				//List<List> choicesList=new ArrayList(); 
				 List<String> al=choiceElement.select("div.mc-quiz-answer--question-copy--314BC").stream().
						 map(Element :: text).collect(Collectors.toList()); 
				 udemyQuestionBean.setAnswerOptions(al);
				 //choicesList.add(al);
				 //System.out.println("choicesList : "+ choicesList);
				 
				 
				 List<String> aa=choiceElement.select("li > div:first-child").stream().
						 map(s -> { 
							 return s.attr("class"); 
							 }).collect(Collectors.toList());
				 
				 udemyQuestionBean.setCorrectResponse(aa); 
				
			});
			
			
			/*
			 * row.select("ul[aria-labelledby=question-prompt]").forEach(choiceElement -> {
			 * 
			 * //List<List> choiceCorrectnessAttr=new ArrayList(); List
			 * aa=choiceElement.select("li > div:first-child").stream(). map(s -> { return
			 * s.attr("class"); }).collect(Collectors.toList());
			 * //choiceCorrectnessAttr.add(aa);
			 * //System.out.println("choiceCorrectnessAttr : "+ choiceCorrectnessAttr);
			 * 
			 * });
			 */
			
			//row.select("div.mc-quiz-question--explanation--Q5KHQ").forEach(s-> System.out.println("explanation : "+ s.text()));
			
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
	
	/*
	 * public UdemyQuestionBean getUdemyData(String htmlData) {
	 * 
	 * Document doc=getDocument(htmlData);
	 * 
	 * Elements questionsElements =
	 * doc.select("div.mc-quiz-question--header--3fsHJ");
	 * System.out.println("questionsElements size : "+ questionsElements.size());
	 * 
	 * questionsElements.forEach(questionElement -> { String questionIdElement
	 * =questionElement.select("span").first().text();
	 * System.out.println("questionId : "+ questionIdElement);
	 * 
	 * String
	 * questionDesc=questionElement.select("div#question-prompt").first().text();
	 * System.out.println("questionDesc : "+ questionDesc);
	 * 
	 * 
	 * 
	 * 
	 * });
	 * 
	 * 
	 * Elements choiceElements = doc.select("ul[aria-labelledby=question-prompt]");
	 * System.out.println("choiceElements size : "+ choiceElements.size());
	 * //mc-quiz-answer--question-copy--314BC
	 * 
	 * List<List> choicesList=new ArrayList(); List<List> choiceCorrectnessAttr=new
	 * ArrayList(); choiceElements.stream().forEach(choiceElement -> { List
	 * al=choiceElement.select("div.mc-quiz-answer--question-copy--314BC").stream().
	 * map(Element :: text).collect(Collectors.toList()); choicesList.add(al);
	 * 
	 * //choiceElement.select("li > div:first-child").forEach(s->
	 * System.out.println("class : "+ s.attr("class"))); List
	 * aa=choiceElement.select("li > div:first-child").stream().map(s -> { return
	 * s.attr("class"); }).collect(Collectors.toList());
	 * choiceCorrectnessAttr.add(aa);
	 * 
	 * //System.out.println("className : "+ className); //IntStream.range(0,
	 * choiceElement.select("li").size()).filter(i->choiceElement.
	 * select("li > div:first-child").first().attr("class").contains("--correct--"))
	 * .forEach(i->System.out.println(i););;
	 * 
	 * 
	 * });
	 * 
	 * System.out.println("choicesList : "+ choicesList);
	 * System.out.println("choiceCorrectnessAttr : "+ choiceCorrectnessAttr);
	 * 
	 * 
	 * doc.select("div.mc-quiz-question--explanation--Q5KHQ").forEach(s-> s.text());
	 * 
	 * 
	 * 
	 * return null; }
	 */
	
	private Document getDocument(String htmlData) {
		return Jsoup.parse(htmlData);
	}
	
	private String getQuestion() {
		
		return "";
	}

}
