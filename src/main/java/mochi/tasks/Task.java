package mochi.tasks;

public class Task {
  protected String _name;
  protected boolean _status;
  protected String _type;

  public Task(String name, String type) {
    _name = name;
    _type = type;
    _status = false;
  }
  /**
   * Compares the task's deadline with a specified date using an operator.
   *
   * @param op   the operator to use for comparison ("/before" or "/after").
   * @param date the date to compare with the task's deadline in the "d/M/yyyy HHmm" format.
   * @return true if the deadline meets the comparison condition, false otherwise.
   */
  public boolean compare(String op, String date) {
    return false;
  }
  /**
   * Convert the date time to friendly format
   *
   * @return a string representing the deadline in the format "MMM dd yyyy, h:mma".
   */
  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + _name;
  }
  public String getType() {
    return _type;
  }
  /**
   * Converts the Deadline task to a string formatted for database storage.
   *
   * @return a database-compatible string representation of the task in the format
   *         "type|status|name|date|date" where "date" is in the "d/M/yyyy HHmm" format.
   */
  public String toDBString() {
    return _type
      + TaskList._saveDelimiter
      + _status
      + TaskList._saveDelimiter
      + _name;
  }

  public String getStatusIcon() {
    return ( _status ? "X" : " ");
  }
  public String getName() { return _name; }
  public void markTask() {
    _status = true;
  }
  public void unmarkTask() {
    _status = false;
  }
}
