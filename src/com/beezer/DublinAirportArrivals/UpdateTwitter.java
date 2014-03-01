package com.beezer.DublinAirportArrivals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class UpdateTwitter {
	private String status;
	private TwitterFactory twitterFactory;
	
	public UpdateTwitter(String status) {
		this.status = status;
	}
	
	public void initialize(Properties config) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(config.getProperty("oauth.consumerKey"))
		  .setOAuthConsumerSecret(config.getProperty("oauth.consumerSecret"))
		  .setOAuthAccessToken(config.getProperty("oauth.accessToken"))
		  .setOAuthAccessTokenSecret(config.getProperty("oauth.accessTokenSecret"));
		this.twitterFactory = new TwitterFactory(cb.build());
	}
	
	public void publish() throws FileNotFoundException, IOException {
		
		Twitter twitter = twitterFactory.getInstance();
		Status result = null;
		try {
			result = twitter.updateStatus(status);
			System.out.println("Successfully updated the status to [" + result.getText() + "].");
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws FileNotFoundException, IOException {
		
		String latestStatus = "154.9:Red Gate Stores Strandhill\n"
				+ "155.9:Sligo Fuels Finisklin\n"
				+ "156.9:Mace Tubbercurry\n"
				+ "156.9:Test2\n"
				+ "http://www.pumps.ie";
		
		Properties config = new Properties();
		config.load(new FileInputStream("etc/sligo.petrol.properties"));
		System.out.println(config.toString());
		
		UpdateTwitter u = new UpdateTwitter(latestStatus);
		u.initialize(config);
		u.publish();
	}
}
