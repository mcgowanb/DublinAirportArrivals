package com.beezer.DublinAirportArrivals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main {
	
	private static String test;

	public static void main(String args[]) throws IOException {
		
		String file = args[0];
		Properties config = new Properties();		
		config.load(new FileInputStream(file));
		
		System.out.println("====================BEGIN=======================");
		
		ParsingClass parser = new ParsingClass(config);
		
		List<String> testList = parser.process();
		
		

	}
}
