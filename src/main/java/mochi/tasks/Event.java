package mochi.tasks;

public class Event extends Task {
  protected String _from;
  protected String _to;
  public Event(String name, String from, String to) {
    super(name,"E");
    this._from = from;
    this._to = to;
  }
  public String getFrom() {
    return this._from;
  }
  public String getTo() {
    return this._to;
  }
  @Override
  public String toString() {
    return "[" + _type + "]" + super.toString() + " (from: " + _from + " to: "+ _to +  ")";
  }

  @Override
  public String toDBString() {
    return _type
      + TaskList._saveDelimiter
      + _status
      + TaskList._saveDelimiter
      + _name
      + TaskList._saveDelimiter
      + _from
      + TaskList._saveDelimiter
      + _to;
  }
}
