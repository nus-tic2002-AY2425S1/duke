package mochi.tasks;

import java.time.LocalDateTime;
import mochi.common.*;

public class Deadline extends Task{
  protected LocalDateTime _by;
  public Deadline(String name, String by) {
    super(name,"D");
    this._by = DateTime.parse(by);
  }
  public String getBy() {
    return DateTime.toString(this._by);
  }
  @Override
  public String toString() {
    return "[" + _type + "]" + super.toString() + " (by " + DateTime.toString(this._by) + ")";
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
