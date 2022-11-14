package com.myrestaurant.booking.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Created a separate Serializer for localDate as it is not supported by Jackson
 * @author malat
 *
 */
public class LocalDateSerializer extends JsonSerializer<LocalDate> {

	public LocalDateSerializer() {
		super();
	}

	@Override
	public void serialize(LocalDate value, JsonGenerator generator, SerializerProvider provider) throws IOException {
		generator.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE));
	}
}