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

  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + _name;
  }

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
  public boolean getStatus() { return _status; }
  public String getName() { return _name; }
  public void markTask() {
    _status = true;
  }
  public void unmarkTask() {
    _status = false;
  }
}
