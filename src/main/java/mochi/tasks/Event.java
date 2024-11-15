package mochi.tasks;

import mochi.common.DateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * The Event class represents a task with a specific start date and end date.
 * It includes methods to compare the deadline to another date, format it for display,
 * and convert it to a database-compatible format.
 */
public class Event extends Task {
  protected LocalDateTime _from;
  protected LocalDateTime _to;
  public Event(String name, String from, String to) {
    super(name,"E");
    this._from = DateTime.parse(from);
    this._to = DateTime.parse(to);
  }
  public String getFrom() {
    return DateTime.toString(this._from);
  }
  public String getTo() {
    return DateTime.toString(this._from);
  }
  @Override
  public String toString() {
    return "[" + _type + "]" + super.toString() + " (from: " + DateTime.toString(_from) + " to: "+ DateTime.toString(_to) +  ")";
  }
  @Override
  public boolean compare(String op, String date) {
    LocalDateTime checkDate = DateTime.parse(date);
    if (op.equalsIgnoreCase("/before")) {
      return this._to.isBefore(checkDate);
    } else if (op.equalsIgnoreCase("/after")) {
      return this._from.isAfter(checkDate);
    }
    return false;
  }
  @Override
  public String toDBString() {
    return _type
      + TaskList._saveDelimiter
      + _status
      + TaskList._saveDelimiter
      + _name
      + TaskList._saveDelimiter
      + DateTime.toDBString(_from)
      + TaskList._saveDelimiter
      + DateTime.toDBString(_to);
  }
  @Override
  public boolean fallsOnDate(LocalDate date) {
    LocalDate fromDate = _from.toLocalDate();
    LocalDate toDate = _to.toLocalDate();
    return (fromDate.isEqual(date) || toDate.isEqual(date) ||
            (fromDate.isBefore(date) && toDate.isAfter(date)));
  }
}
