package com.beezer.DublinAirportArrivals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;


public class Main {
	
	private static ArrayList<ArrivalDetails> list;
	public static String lastFlight;
	private static String tweetFlightHashTag;

	public static void main(String args[]) throws IOException {
		
		String file = args[0];
		Properties config = new Properties();		
		config.load(new FileInputStream(file));
		
		System.out.println("====================BEGIN=======================");
		
		HtmlParser parser = new HtmlParser(config);
		SaveOrUpdate saveOrUpdate = new SaveOrUpdate(config);
		
		list = parser.process();
		
		debug();
	 
		if(list.size() <1){
			print("No Flights currenty landed");
		}
		else{
		lastFlight = (list.get(list.size()-1)).toString();
		print("Most recent flight to land from %s is:",parser.airline);
		print(lastFlight);
		tweetFlightHashTag = lastFlight.concat("  #DAA");
		print(tweetFlightHashTag);
		
		
		if(SaveOrUpdate.isTweetDuplicate() == false){
			UpdateTwitter publisher = new UpdateTwitter(tweetFlightHashTag);
			publisher.initialize(config);
			publisher.publish();	
			saveOrUpdate.saveToFile();
		}
		else print("Most recent flight already posted to twitter, NFA");
		}
		System.out.println("=====================END========================");
	}
	
	
	public static void debug(){
		System.out.println("====================DEBUG=======================");
		for (ArrivalDetails x : list){
			System.out.println(x);
			
		}
		System.out.println("====================DEBUG=======================");
	}
	
	public static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}
	
}
