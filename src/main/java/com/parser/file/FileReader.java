package com.parser.file;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class FileReader {

	
	
	static Function<String, String> fileReaderFunction= s -> {
		
		String fileString="";
		try {
			System.out.println("ClassLoader.getSystemResource(s).toURI() : "+ ClassLoader.getSystemResource(s));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			fileString=Files.lines(Paths.get(ClassLoader.getSystemResource(s).toURI()), StandardCharsets.UTF_8).collect(Collectors.joining("\n"));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileString;
	};
	
	public String getFileString(String fileString) {
		return fileReaderFunction.apply(fileString);
	}
	
	/*
	 * public static void main(String args[]) { String
	 * fileString=fileReaderFunction.
	 * apply("C:\\Users\\Anurag\\Desktop\\temp\\20200531\\AWS Solutions Architect Associate Practice Exams NEW 2019 _ Udemy.html"
	 * ); System.out.println("fileString : "+ fileString); }
	 */
	
}
