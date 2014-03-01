package com.beezer.DublinAirportArrivals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
	
	private static ArrayList<ArrivalDetails> list;
	private static String lastFlight;

	public static void main(String args[]) throws IOException {
		
		String file = args[0];
		Properties config = new Properties();		
		config.load(new FileInputStream(file));
		
		System.out.println("====================BEGIN=======================");
		
		HtmlParser parser = new HtmlParser(config);
		
		list = parser.process();
	//	debug();
	    testPrint();
		System.out.println("=====================END========================");
	}
	
	
	public static void debug(){
		System.out.println("====================DEBUG=======================");
		for (ArrivalDetails x : list){
			System.out.println(x);
			
		}
		System.out.println("================END DEBUG=======================");
	}
	
	public static void testPrint(){
		System.out.println("====================TEST=======================");
		System.out.println(list.get(list.size()-1));
		System.out.println("================END TEST=======================");
	}
	
}
