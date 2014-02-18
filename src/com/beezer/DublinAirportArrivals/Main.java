package com.beezer.DublinAirportArrivals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main {
	
	private static String test;
	private static List<String> List;

	public static void main(String args[]) throws IOException {
		
		String file = args[0];
		Properties config = new Properties();		
		config.load(new FileInputStream(file));
		
		System.out.println("====================BEGIN=======================");
		
		ParsingClass parser = new ParsingClass(config);
		
		List = parser.process();
		//debug();
		//testPrint();
		
		
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
