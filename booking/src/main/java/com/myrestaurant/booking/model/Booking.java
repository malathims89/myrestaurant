package com.myrestaurant.booking.model;


import java.sql.Time;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.myrestaurant.booking.util.LocalDateDeSerializer;
import com.myrestaurant.booking.util.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking implements Comparable<Booking> {
	private Integer bookingId;

	private String bookingName;
	private Integer bookingSize;

	
	@JsonDeserialize(using = LocalDateDeSerializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)  
	private LocalDate bookingDate;
	private Time bookingTime;

	/*
	 * I'm implementing a default sorting here for the ids (to simulate a db
	 * increment pattern
	 */
	@Override
	public int compareTo(Booking o) {
		// TODO Auto-generated method stub
		return this.bookingId - o.bookingId;
	}
}
