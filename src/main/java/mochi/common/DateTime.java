package mochi.common;
import mochi.common.exception.ExceptionMessages;
import mochi.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
  private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
  private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

  public static LocalDateTime parse(String dateTime) {
    try {
      return LocalDateTime.parse(dateTime, INPUT_FORMATTER);
    } catch (DateTimeParseException e) {
      Ui.response(ExceptionMessages.DATE_FORMAT_INVALID);
      return null;
    }
  }
  public static String toString(LocalDateTime dateTime) {
    return dateTime.format(DISPLAY_FORMATTER);
  }

  public static String toDBString(LocalDateTime dateTime) {
    return dateTime.format(INPUT_FORMATTER);
  }
}
