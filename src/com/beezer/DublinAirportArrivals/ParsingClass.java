package com.beezer.DublinAirportArrivals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParsingClass {
	
	public String url;
	public String airline;
	public static String testArray[];
	public static String lastFlight;
	static ArrayList<String> capture = new ArrayList<String>();
		
	
	public ParsingClass(Properties config) throws IOException{
	
		url = config.getProperty("url");
		airline = config.getProperty("airline");
		
		print("Fetching.........%s" , url);
		
	}
	
	public ArrayList<String> process() throws IOException{
		Document doc = Jsoup.connect(url).get();
		
		Elements tableRows = doc.getElementsByTag("tr");
		//Elements tableCol = doc.getElementsByTag("td");
		
		for(Element tr: tableRows){
			if(tr.text().contains(airline)){
				capture.add(tr.text());
				}
			}
		return capture;
	}
	
	public static void lastFlight() throws IOException{ 
		
		lastFlight = (capture.get(capture.size()-1));
		print (lastFlight);
		

	}
	
	public static void splitStringToArray(){
		String delims = "[ ]+";
		testArray = lastFlight.split(delims);
		
		for(int i=0; i < testArray.length;i++){
			System.out.println(testArray[i]);
		}
		
		
		 
	}


	public static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

}
