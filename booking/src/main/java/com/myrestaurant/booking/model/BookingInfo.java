package com.myrestaurant.booking.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author malat
 * singleton to initialize the in memory data
 */
public class BookingInfo {

	   // private field that refers to the object
	   public static List<Booking> bookingEntries = new ArrayList<Booking>();
	                     
	}