package com.myrestaurant.booking.model;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking implements Comparable<Booking>{
	private Integer bookingId;
	
	private String bookingName;
	private Integer bookingSize;
    private Date bookingDate;
	private Time bookingTime;
	@Override
	public int compareTo(Booking o) {
		// TODO Auto-generated method stub
		return this.bookingId-o.bookingId;
	}
}
