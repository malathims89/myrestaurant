package com.myrestaurant.booking.handler;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.myrestaurant.booking.model.Booking;
import com.myrestaurant.booking.model.BookingInfo;

import lombok.NoArgsConstructor;

@Path("/booking")
@NoArgsConstructor
public class BookingHandler {

	@GET
	@Path("/listAllBookings")
	@Produces("application/json")
	public List<Booking> listAllBookings() {
		return BookingInfo.bookingEntries;
	}

	@POST
	@Path("/createBooking")
	@Consumes("application/json")
	@Produces("text/plain")
	public String createBooking(Booking booking) {
		List<Integer> ids = BookingInfo.bookingEntries.stream().sorted().map(b -> b.getBookingId())
				.collect(Collectors.toList());
		/**
		 * id being incremented customer logic
		 **/
		if (!ids.isEmpty()) {
			booking.setBookingId(ids.get(ids.size()-1) + 1);
		} else {
			booking.setBookingId(1);
		}
		BookingInfo.bookingEntries.add(booking);
		return "Added";

	}

}
