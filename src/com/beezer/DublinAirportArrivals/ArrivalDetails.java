package com.beezer.DublinAirportArrivals;

public class ArrivalDetails {
	String origin;
	String airline1;
	String airline2;
	String flightNo;
	String date;
	String month;
	String arrTime;
	String status;
	

	public ArrivalDetails(String[] singleRowArray) {
		this.origin = singleRowArray[0];
		this.airline1 = singleRowArray[1];
		this.airline2 = singleRowArray[2];
		this.flightNo = singleRowArray[3];
		this.date = singleRowArray[4];
		this.month = singleRowArray[5];
		this.arrTime = singleRowArray[6];
		this.status = singleRowArray[7];
		
	}

	
	@Override
	public String toString() {
		String airline = airline1.concat(airline2);
	    return airline1 + airline2 + " From: " + origin + " " + " No: " + flightNo + " Time: "+date+" "+month+" "+arrTime +" Status: "+ status ;
	}
	
}
