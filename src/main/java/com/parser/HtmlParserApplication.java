package com.parser;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.parser.udemy.bean.UdemyQuestionBean;
import com.parser.udemy.parser.UdemyParser;

@SpringBootApplication(scanBasePackages = {"com.parser"})
public class HtmlParserApplication implements CommandLineRunner {

	@Bean
	public UdemyParser getUdemyParser(){
		return  new UdemyParser();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(HtmlParserApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		 getUdemyParser().parseHtmlAndCreateCSV();
	}

}
