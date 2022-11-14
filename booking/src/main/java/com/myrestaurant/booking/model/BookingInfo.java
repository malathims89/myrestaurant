package com.myrestaurant.booking.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * 
 * @author malat singleton to initialize the in memory data
 */
public class BookingInfo {

	private static final String APPLICATION_PROPERTIES = "application.properties";
	private static Properties prop = new Properties();
	private static final String TABLE_NAMES = "name";

	// private field that refers to the object
	public static List<Booking> bookingEntries = new ArrayList<Booking>();

	// These are the tables in my restaurant for which the customer creates a
	// booking
	public static Map myTables = new HashMap<String, Integer>();

	public static Map<String, Integer> tables() {

		try {
			/*
			 * Loading the Map only once
			 */
			if (myTables.isEmpty()) {
				prop.load(ClassLoader.getSystemResourceAsStream(APPLICATION_PROPERTIES));
				List<String> tableNames = Arrays.asList(prop.getProperty(TABLE_NAMES).split(","));
				tableNames.stream().map(e -> myTables.put(e.split("#")[0], Integer.parseInt(e.split("#")[1])))
						.collect(Collectors.toList());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
		return myTables;
	}

}