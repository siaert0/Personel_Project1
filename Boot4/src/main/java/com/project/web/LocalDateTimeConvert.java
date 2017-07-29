package com.project.web;

import java.sql.*;
import java.time.*;

import javax.persistence.*;


@Converter(autoApply = true)
public class LocalDateTimeConvert implements AttributeConverter<LocalDateTime, Timestamp> {
	  @Override
	  public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
	     return localDateTime != null ? Timestamp.valueOf(localDateTime) : null;
	   }

	  @Override
	  public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
	     return timestamp != null ? timestamp.toLocalDateTime() : null;
	   }
}
