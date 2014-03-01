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
	

	public HtmlParser(Properties config) throws IOException {

		url = config.getProperty("url");
		airline = config.getProperty("airline");
		charCatcher = config.getProperty("stringCatcher");

		print("Fetching........%s%s%s", airline, " data from: ", url);

	}

	public ArrayList<ArrivalDetails> process() throws IOException {
		Document doc = Jsoup.connect(url).get();
		Elements tableRow = doc.getElementsByTag("tr");
		String[] stitcher = new String[6];

		for (Element tr : tableRow) {
			if (tr.text().contains(airline)) {

				String scrapedText = tr.text();
				String terminalCatcher = tr.outerHtml();
				if (terminalCatcher.contains("images/t1.jpg")) {
					stitcher[0] = "T1";
				} else if (terminalCatcher.contains("images/t2.jpg")) {
					stitcher[0] = "T2";
				} else
					stitcher[0] = "Error";

				String origin = scrapedText.substring(0,
						scrapedText.indexOf(charCatcher, 1));
				stitcher[1] = origin;
				scrapedText = trim(scrapedText, origin.length(),
						scrapedText.length());
				

				String carrier = scrapedText.substring(0, airline.length());
				stitcher[2] = carrier;
				scrapedText = trim(scrapedText, carrier.length(),
						scrapedText.length());

				String flightNo = scrapedText.substring(0,
						scrapedText.indexOf(" "));
				stitcher[3] = flightNo;
				scrapedText = trim(scrapedText, flightNo.length(),
						scrapedText.length());

				String arrTime = scrapedText.substring(0, 12);
				stitcher[4] = arrTime;
				scrapedText = trim(scrapedText, arrTime.length(),
						scrapedText.length());
				
				stitcher[5] = scrapedText;
				int i = stitcher[5].length();
				if (stitcher[5].length() == 0){
					stitcher[5] = null;
				}
				
				ArrivalDetails f  = new ArrivalDetails(stitcher);
				
				if((stitcher[5] != null) && (stitcher[5].contains("Arrived"))){
				capture.add(f);
				}
			}
		}

		return capture;
	}

	public static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	private static String trim(String s, int begin, int end) {
		String a = s.substring(begin, end);
		a = a.trim();
		return a;

	}
}
