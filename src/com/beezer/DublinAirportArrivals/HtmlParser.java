package com.beezer.DublinAirportArrivals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {
	
	private String url;
	private String airline;
	public static String lastFlight;
	private String catcherA;
	static ArrayList<ArrivalDetails> capture = new ArrayList<ArrivalDetails>();

		
	
	public HtmlParser(Properties config) throws IOException{
	
		url = config.getProperty("url");
		airline = config.getProperty("airline");
		catcherA = config.getProperty("stringCatcher");
		
		print("Fetching.........%s" , url);
		
	}
	
	public ArrayList<ArrivalDetails> process() throws IOException{
		Document doc = Jsoup.connect(url).get();
		
		Elements tableRow = doc.getElementsByTag("tr");
		
		for(Element tr : tableRow){
			if(tr.text().contains(airline)){
				if(tr.text().contains("Aer")){
					String parsedString = tr.text();
					
					String origin = parsedString.substring(0, parsedString.indexOf(catcherA,1));
					int charCounter = origin.length();
					
					String carrier = parsedString.substring(charCounter,(airline.length()+charCounter));
					charCounter += carrier.length();
					
					String flightNo = parsedString.substring(charCounter,(charCounter+7));
					charCounter += 7;
					int parsedStringCount = parsedString.length();
					int carrierLenght = carrier.length();
					int flightLength = flightNo.length();
					
					
					System.out.println("Origin: "+ origin+origin.length() + "\t\t" + carrier+carrier.length()+"\t"+flightNo+flightNo.length());
					//System.out.println("Airline test is "+ carrier + " & Length is: "+airline.length());
					//System.out.println("FlightNo: test is "+ flightNo + " & Length is: "+flightNo.length());
					
					//String delims = "[ ]+";
					//String[] singleRowArray = tr.text().split(delims);
					//ArrivalDetails temp = new ArrivalDetails(singleRowArray);
					//capture.add(temp);
					
				}
			}
				
		}
	//	testPrint();
		return capture;
	}
	
	public static void testPrint(){
		System.out.println("====================TEST=======================");
		System.out.println(capture.get(capture.size()-8));
		System.out.println("================END TEST=======================");
	}

	public static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}
}
