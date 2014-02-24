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

public class HtmlParser {
	
	public String url;
	public String airline;
	public static String lastFlight;
	static ArrayList<String> capture = new ArrayList<String>();

		
	
	public HtmlParser(Properties config) throws IOException{
	
		url = config.getProperty("url");
		airline = config.getProperty("airline");
		
		print("Fetching.........%s" , url);
		
	}
	
	public ArrayList<String> process() throws IOException{
		Document doc = Jsoup.connect(url).get();
		
		Elements tableRow = doc.getElementsByTag("tr");
		Elements tableCol = doc.getElementsByTag("td");
		int rowCount=0;
		int colCount=0;
		
		
		for(Element tr : tableRow){
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
		String countArray[] = lastFlight.split(delims);
		
		for(int i=0; i < countArray.length;i++){
			System.out.println(countArray[i]);
		} 
	}


	public static void print(String msg, Object... args) {
		System.out.println("=====================TEST======================");
		System.out.println(String.format(msg, args));
		System.out.println("=====================END TEST===================");
	}
	
	/*public ArrayList<String> process() throws IOException{
		Document doc = Jsoup.connect(url).get();
		
		Elements tableRow = doc.getElementsByTag("tr");
		Elements tableCol = doc.getElementsByTag("td");
		int rowCount=0;
		int colCount=0;
		
		
		for(Element tr : tableRow){
			if(tr.text().contains(airline)){
				capture.add(tr.text());
				testCount++;
			}
		}
		return capture;
	}*/

}
