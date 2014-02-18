package com.beezer.DublinAirportArrivals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParsingClass {
	
	public String url;
	
	public ParsingClass(Properties config) throws IOException{
		url = config.getProperty("url");
		print("Fetching.........%s" , url);
	}
	
	public ArrayList<String> process() throws IOException{
		Document doc = Jsoup.connect(url).get();
		System.out.println("Terminal\tAirline\tFlight\tScheduled Arrival\tStatus");
		ArrayList<String> capture = new ArrayList<String>();
		
		Elements tableRows = doc.getElementsByTag("tr");
		Elements tableColumns = doc.getElementsByTag("td");
		
		for(Element tr: tableRows){
			capture.add(tr.text());
			System.out.println(tr.text());
			}
		
		
		
		return capture;
	}
		
		
	
	






	public static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

}
