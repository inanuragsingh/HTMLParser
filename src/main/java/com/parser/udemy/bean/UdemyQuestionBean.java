package com.parser.udemy.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UdemyQuestionBean {

	public enum UDEMY_QUESTION_TYPE{
		MULTI_SELECT("multi-select"),
		MULTIPLE_CHOICE("multiple-choice");
		
		private String questionType;
		
		UDEMY_QUESTION_TYPE(String questionType){
			this.questionType=questionType;
		}
		
		public String getQuestionType() {
			return this.questionType;
		}
		
	};
	
	private String questionNo;
	private String question;
	private UDEMY_QUESTION_TYPE questionType;
	private String answerOption1;
	private String answerOption2;
	private String answerOption3;
	private String answerOption4;
	private String answerOption5;
	private String answerOption6;
	private String correctResponse;
	private String explanation;
	private String knowledgeArea;
	
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswerOption1() {
		return answerOption1;
	}
	public void setAnswerOption1(String answerOption1) {
		this.answerOption1 = answerOption1;
	}
	public String getAnswerOption2() {
		return answerOption2;
	}
	public void setAnswerOption2(String answerOption2) {
		this.answerOption2 = answerOption2;
	}
	public String getAnswerOption3() {
		return answerOption3;
	}
	public void setAnswerOption3(String answerOption3) {
		this.answerOption3 = answerOption3;
	}
	public String getAnswerOption4() {
		return answerOption4;
	}
	public void setAnswerOption4(String answerOption4) {
		this.answerOption4 = answerOption4;
	}
	public String getAnswerOption5() {
		return answerOption5;
	}
	public void setAnswerOption5(String answerOption5) {
		this.answerOption5 = answerOption5;
	}
	public String getAnswerOption6() {
		return answerOption6;
	}
	public void setAnswerOption6(String answerOption6) {
		this.answerOption6 = answerOption6;
	}
	public String getCorrectResponse() {
		return correctResponse;
	}
	public void setCorrectResponse(String correctResponse) {
		this.correctResponse = correctResponse;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getKnowledgeArea() {
		return knowledgeArea;
	}
	public void setKnowledgeArea(String knowledgeArea) {
		this.knowledgeArea = knowledgeArea;
	}
	public UDEMY_QUESTION_TYPE getQuestionType() {
		return questionType;
	}
	public void setQuestionType(UDEMY_QUESTION_TYPE questionType) {
		this.questionType = questionType;
	}
	public String getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}
	
	public void setAnswerOptions(List<String> al) {
		
		IntStream.range(0,al.size()).forEach(i ->{
			if(i==0) {
				this.answerOption1=al.get(i);
			}else if(i==1) {
				this.answerOption2=al.get(i);
			}else if(i==2) {
				this.answerOption3=al.get(i);
			}else if(i==3) {
				this.answerOption4=al.get(i);
			}else if(i==4) {
				this.answerOption5=al.get(i);
			}else if(i==5) {
				this.answerOption6=al.get(i);
			}
		});
		
	}
	
	public void setCorrectResponse(List<String> al) {
		List<String> intList=new ArrayList();
		IntStream.range(0, al.size()).forEach(i-> {
			if(al.get(i).contains("--correct--")) {
				intList.add(i+1+"");
			}else if(al.get(i).contains("radio boxed disabled")) {
				this.questionType=UDEMY_QUESTION_TYPE.MULTIPLE_CHOICE;
			}else if(al.get(i).contains("checkbox boxed disabled")) {
				this.questionType=UDEMY_QUESTION_TYPE.MULTI_SELECT;
			}
		});
		
		String correctAns=intList.stream().collect(Collectors.joining(","));
		this.correctResponse=correctAns;
	}
	
	
	@Override
	public String toString() {
		return "UdemyQuestionBean [questionNo=" + questionNo + ", question=" + question + ", questionType="
				+ questionType.questionType + ", answerOption1=" + answerOption1 + ", answerOption2=" + answerOption2
				+ ", answerOption3=" + answerOption3 + ", answerOption4=" + answerOption4 + ", answerOption5="
				+ answerOption5 + ", answerOption6=" + answerOption6 + ", correctResponse=" + correctResponse
				+ ", explanation=" + explanation + ", knowledgeArea=" + knowledgeArea + "]";
	}
	
	public String[] convertValuestoList(){
		
		
		String[] header = {question, questionType.questionType,answerOption1,answerOption2,answerOption3,answerOption4,answerOption5,answerOption6,correctResponse,explanation,knowledgeArea};
		
		return header;
		 
	}
	

}
