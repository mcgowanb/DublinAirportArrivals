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
	private String charCatcher;
	static ArrayList<ArrivalDetails> capture = new ArrayList<ArrivalDetails>();

		
	
	public HtmlParser(Properties config) throws IOException{
	
		url = config.getProperty("url");
		airline = config.getProperty("airline");
		charCatcher = config.getProperty("stringCatcher");
		
		print("Fetching........%s%s%s",airline," data from: ", url);
		
	}
	
	public ArrayList<ArrivalDetails> process() throws IOException {
		Document doc = Jsoup.connect(url).get(); 
		Elements tableRow = doc.getElementsByTag("tr");
		String[] stitcher = new String[10];
		
		for (Element tr : tableRow) {
			if (tr.text().contains(airline)) {
				
				String scrapedText = tr.text();
				String terminalCatcher = tr.outerHtml();
				if(terminalCatcher.contains("images/t1.jpg")){
					stitcher[0] = "T1";
				}
				else if(terminalCatcher.contains("images/t2.jpg")){
					stitcher[0] = "T2";
				}
				else stitcher[0] = "Error";
			
				
				String origin = scrapedText.substring(0,scrapedText.indexOf(charCatcher, 1));
				origin = origin.trim();
				int charCounter = origin.length()+1;
				stitcher[1] = origin;
				int w = origin.length();
			//	print(" %s %s", origin, origin.length());
				
				scrapedText = trim(scrapedText, origin.length(),scrapedText.length());
				

				String carrier = scrapedText.substring(charCounter,(airline.length() + charCounter));
				carrier = carrier.trim();
				charCounter += carrier.length()+1;
				stitcher[2] = carrier;
				int x = carrier.length();
			//	print(" %s %s", carrier, carrier.length());

				String flightNo = scrapedText.substring(charCounter,(charCounter + 7));
				flightNo = flightNo.trim();
				charCounter += flightNo.length()+1;
				stitcher[3] = flightNo;
				int y = flightNo.length();
			//	print(" %s %s", flightNo, flightNo.length());
				
				String schedTime = scrapedText.substring(charCounter,charCounter + 12);
				schedTime = schedTime.trim();
				charCounter += schedTime.length()+1;
				stitcher[4] = schedTime;
				int z = schedTime.length();
			//	print(" %s %s", schedTime, schedTime.length());
				
				String status = scrapedText.substring(charCounter);
				stitcher[5] = status;
			//	print(" %s %s", status, status.length());
				
				System.out.println(stitcher[0] +" Origin: " + origin + "\t\t" + carrier + "\t" + flightNo + "\t" + schedTime + "  " + "\t" + status);
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
	
	private static String trim(String s, int begin, int end) {
			String a;
			a = s.trim();
			return a.substring(begin, end);

	}
}
