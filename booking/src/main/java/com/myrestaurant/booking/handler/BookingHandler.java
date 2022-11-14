package com.myrestaurant.booking.handler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.myrestaurant.booking.model.Booking;
import com.myrestaurant.booking.model.BookingInfo;

import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.NoArgsConstructor;

@Path("/booking")
@NoArgsConstructor
public class BookingHandler<E> {
	/**
	 * Returns a list of all the bookings
	 * <p>
	 * 
	 * @param
	 * @return A list containing all Booking Information
	 */
	@GET
	@Path("/listAllBookings/{date}")
	@Produces("application/json")
	public List<Booking> listAllBookings(@PathParam("date") String date) {
		List<Booking> bookingList = new ArrayList<Booking>();
		try {
			bookingList = BookingInfo.bookingEntries.stream()
					.filter(e -> e.getBookingDate().equals(LocalDate.parse(date))).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookingList;
	}

	/**
	 * Creates a booking for a user
	 * <p>
	 * 
	 * @param booking
	 * @return A list containing all Booking Information
	 */
	@POST
	@Path("/createBooking")
	@Consumes("application/json")
	@Produces("text/plain")
	public String createBooking(Booking booking) {
		String response = "Added " + HttpResponseStatus.OK;
		try {
			response = book(booking, response);
		} catch (Exception e) {
			response = BookingInfo.INTERNAL_ERROR_TEXT + HttpResponseStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return response;

	}

	/**
	 * Booking Id needs to be unique.
	 * 
	 * In order to simulate a DB incrementing the booking id by 1 after each
	 * insertion
	 * 
	 * @param booking
	 */

	private void generateBookingId(Booking booking) {
		List<Integer> ids = BookingInfo.bookingEntries.stream().sorted().map(b -> b.getBookingId())
				.collect(Collectors.toList());
		/**
		 * id being incremented customer logic
		 **/
		if (!ids.isEmpty()) {
			booking.setBookingId(ids.get(ids.size() - 1) + 1);
		} else {
			booking.setBookingId(1);
		}
	}

	/**
	 * Core logic ------------- 1. Assigns a table for the booking size 2. Check
	 * slot conflicts and insert data
	 * 
	 * @param booking
	 * @param response
	 * @return
	 */
	private String book(Booking booking, String response) {
		Integer tableSize = booking.getBookingSize();

		/**
		 * This method will tell me whether the window for the same table booking is
		 * before and after two hours
		 * 
		 * b-> each booking iterated for the day booking -> current booking
		 */
		// 2 - 10
		if (tableSize == 3) {
			response = handleBooking(booking, BookingInfo.SMALL, response);
		} else if (tableSize == 20) {
			response = handleBooking(booking, BookingInfo.FAMILY, response);
		} else if (tableSize == 8) {
			response = handleBooking(booking, BookingInfo.MEDIUM, response);
		} else if (tableSize == 10) {
			response = handleBooking(booking, BookingInfo.LARGE, response);
		} else {
			response = BookingInfo.NO_TABLES_AVAILABLE;
		}
		return response;
	}

	/**
	 * Resolves time conflict and creates a booking
	 * 
	 * @param booking
	 * @param tableSize
	 * @param response
	 * @return
	 */
	private String handleBooking(Booking booking, String tableSize, String response) {
		/*
		 * if (BookingInfo.tables().get(tableSize).equals(0)) {
		 *//**
			 * Beta - this functionality can be commented out. Just to simulate availability
			 * of tables
			 * 
			 * would be helpful if there is an actual databse
			 *//*
				 * response = "Sorry " + tableSize + " tables are all booked for the day!"; }
				 */
		// else {
		if (reslolveTimeConflict(booking).isEmpty()) {
			BookingInfo.tables().put(tableSize, Integer.valueOf(BookingInfo.tables().get(tableSize) - 1));
			generateBookingId(booking);
			BookingInfo.bookingEntries.add(booking);
		} else {
			response = "Please choose another timeslot!";
		}
		// }
		return response;
	}

	/**
	 * This will compare my current booking with the list of bookings
	 * 
	 * compares with table size and 2 hour window
	 * 
	 * @param booking
	 * @return
	 */
	private List reslolveTimeConflict(Booking booking) {
		List bookingConflict = BookingInfo.bookingEntries.stream().filter(b -> (b.getBookingSize()
				.equals(booking.getBookingSize()) && booking.getBookingDate().equals(b.getBookingDate()))
				&& !((((booking.getBookingTime().toLocalTime().isAfter(b.getBookingTime().toLocalTime().plusHours(2))))
						|| (booking.getBookingTime().toLocalTime()
								.isBefore(b.getBookingTime().toLocalTime().minusHours(2))))))
				.collect(Collectors.toList());
		return bookingConflict;
	}

}
