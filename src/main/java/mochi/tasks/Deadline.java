package mochi.tasks;

import java.time.LocalDateTime;
import mochi.common.*;
/**
 * The Deadline class represents a task with a specific deadline date and time.
 * It includes methods to compare the deadline to another date, format it for display,
 * and convert it to a database-compatible format.
 */
public class Deadline extends Task{
  protected LocalDateTime _by;
  /**
   * Constructs a Deadline task with a specified name and deadline.
   *
   * @param name the name of the task.
   * @param by   the deadline of the task in the "d/M/yyyy HHmm" format.
   */
  public Deadline(String name, String by) {
    super(name,"D");
    this._by = DateTime.parse(by);
  }
  /**
   * Returns the deadline of the task as a formatted string.
   *
   * @return a string representing the deadline in the format "MMM dd yyyy, h:mma".
   */
  public String getBy() {
    return DateTime.toString(this._by);
  }
  @Override
  public String toString() {
    return "[" + _type + "]" + super.toString() + " (by " + DateTime.toString(this._by) + ")";
  }
  @Override
  public boolean compare(String op, String date) {
    LocalDateTime checkDate = DateTime.parse(date);
    if (op.equalsIgnoreCase("/before")) {
      return this._by.isBefore(checkDate);
    } else if (op.equalsIgnoreCase("/after")) {
      return this._by.isAfter(checkDate);
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
      + DateTime.toDBString(_by);
  }
}
