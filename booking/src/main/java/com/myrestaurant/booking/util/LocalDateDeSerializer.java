package com.myrestaurant.booking.util;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
/**
 * Created a customer serializer for LocalDate
 * as it is not supported by Jackson
 * @author malat
 *
 */
public class LocalDateDeSerializer extends JsonDeserializer<LocalDate> {

	public LocalDateDeSerializer() {
		super();
	}

	@Override
	public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
		return LocalDate.parse(parser.readValueAs(String.class));
	}
}