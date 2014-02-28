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
		
		print("Fetching........%s%s%s",airline," data from: ", url);
		
	}
	
	public ArrayList<ArrivalDetails> process() throws IOException {
		Document doc = Jsoup.connect(url).get(); 
		Elements tableRow = doc.getElementsByTag("tr");
		Element image = doc.select("img").first();
		
		for (Element tr : tableRow) {
			if (tr.text().contains(airline)) {
				
				String terminal;
			//	String url = image.attr("abs:src");
			//	System.out.println("blah blah " + url);
				
				String scrapedText = tr.text();
				String terminalCatcher = tr.outerHtml();
				if(terminalCatcher.contains("images/t1.jpg")){
					terminal = "T1";
				}
				else if(terminalCatcher.contains("images/t2.jpg")){
					terminal = "T2";
				}
				else terminal = "Error";
				
				String origin = scrapedText.substring(0,
						scrapedText.indexOf(catcherA, 1));
				int charCounter = origin.length();

				String carrier = scrapedText.substring(charCounter,
						(airline.length() + charCounter));
				charCounter += carrier.length();

				String flightNo = scrapedText.substring(charCounter,
						(charCounter + 7));
				charCounter += 7;
				System.out.println(terminal +" Origin: " + origin + origin.length()
						+ "\t\t" + carrier + carrier.length() + "\t" + flightNo
						+ flightNo.length());
			}

		}

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
