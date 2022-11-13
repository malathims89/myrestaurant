package com.myrestaurant.booking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.myrestaurant.booking.handler.BookingHandler;

import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import io.muserver.rest.RestHandlerBuilder;
/**
 * A simple Restaurant Booking Application
 * using muserver
 *@author malathi
 */
public class BookingApplication {
	private static final Logger logger = LogManager.getLogger(BookingApplication.class);

	 public static void main(String[] args) {
	        BookingHandler bookingHandler = new BookingHandler();
	        MuServer server = MuServerBuilder.httpServer()
	            .addHandler(
	                RestHandlerBuilder.restHandler(bookingHandler)
	                    .addCustomWriter(new JacksonJaxbJsonProvider())
	                    .addCustomReader(new JacksonJaxbJsonProvider())
	            )
	            .start();
	        logger.info("API example: " + server.uri().resolve("/users"));
	    }

    
}
