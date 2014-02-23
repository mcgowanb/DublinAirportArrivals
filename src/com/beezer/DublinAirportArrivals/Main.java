package com.beezer.DublinAirportArrivals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main {
	
	private static List<String> List;
	private static String lastFlight;

	public static void main(String args[]) throws IOException {
		
		String file = args[0];
		Properties config = new Properties();		
		config.load(new FileInputStream(file));
		
		System.out.println("====================BEGIN=======================");
		
		HtmlParser parser = new HtmlParser(config);
		
		List = parser.process();
		HtmlParser.lastFlight();
		//HtmlParser.splitStringToArray();
		
		//Ok. Parser works but it takes the last flight, which can be a future date. Needs to have data i the last row
		

		debug();
	//	testPrint();
	
		
		System.out.println("=====================END========================");
	}
	
	
	public static void debug(){
		System.out.println("====================DEBUG=======================");
		for (String x : List){
			System.out.println(x);
			
		}
		System.out.println("================END DEBUG=======================");
	}
	
	public static void testPrint(){
		System.out.println("====================TEST=======================");
		System.out.println(List.get(List.size()-1));
		System.out.println("================END TEST=======================");
	}
	
}
