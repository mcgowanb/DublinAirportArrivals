package com.beezer.DublinAirportArrivals;

public class ArrivalDetails {
	String terminal;
	String origin;
	String airline;
	String flightNo;
	String arrTime;
	String status;
	

	public ArrivalDetails(String[] singleRowArray) {
		this.terminal = singleRowArray[0];
		this.origin = singleRowArray[1];
		this.airline = singleRowArray[2];
		this.flightNo = singleRowArray[3];
		this.arrTime = singleRowArray[4];
		this.status = singleRowArray[5];
	}

	
	@Override
	public String toString() {
	    return terminal + ": " + airline + " From: " + origin + " Flight No: " + flightNo + " Arrival Time: " + arrTime + " Status: "+ status ;
	}
	
}
