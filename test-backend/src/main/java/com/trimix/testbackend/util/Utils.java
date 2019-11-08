package com.trimix.testbackend.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Utils {
	
	private static final Logger LOG = LogManager.getLogger(Utils.class);
	
	public static String getLocalDateString(LocalDate localDate, String formato) {
		String localDateString = "";
		try {
			if (localDate != null) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
				localDateString = localDate.format(formatter);
			}
		} catch (Exception e) {
			LOG.error("Error: "+ localDate + " "+ formato+", " + e.getMessage());
		}
		return localDateString;
	}
	
	public static LocalDate getLocalDate(String localDateString, String formato) {
		LocalDate localDate = null;
		try {
			if (localDateString != null && !localDateString.trim().isEmpty()) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
				localDate = LocalDate.parse(localDateString, formatter);
			}
		} catch (Exception e) {
			LOG.error("Error: " + localDateString + " " + formato + ", "+ e.getMessage());
		}
		return localDate;
	}
	
}
