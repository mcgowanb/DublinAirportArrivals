package com.beezer.DublinAirportArrivals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;


public class SaveOrUpdate {
	private static String logFile;
	private static String saveCheck;

	public SaveOrUpdate(Properties config) throws IOException{
		logFile = config.getProperty("logFile");
		
	}
	
	public static boolean isTweetDuplicate(){
		readFromFile();
		if (saveCheck.equals(Main.lastFlight)){
			return true;
		}
		else return false;
	}

	public static void readFromFile(){
        String line = null;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(logFile);
            
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                saveCheck = line;
            }	

            // Always close files.
            bufferedReader.close();			
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                logFile + "'");				
        }
        catch(IOException ex) {
        	ex.printStackTrace();
        }
	}
	
	public void saveToFile(){
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(logFile);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.newLine();
            bufferedWriter.write(Main.lastFlight);
            
            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
               ex.printStackTrace();
        }
    }
	
}
