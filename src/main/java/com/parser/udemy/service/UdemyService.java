package com.parser.udemy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parser.udemy.bean.UdemyQuestionBean;


public interface UdemyService {

	public List<UdemyQuestionBean> getUdemyData(String htmlData);
	
	
	
}
