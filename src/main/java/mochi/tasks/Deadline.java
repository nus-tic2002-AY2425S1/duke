package mochi.tasks;

public class Deadline extends Task{
  protected String _by;
  public Deadline(String name, String by) {
    super(name,"D");
    this._by = by;
  }
  public String getBy() {
    return this._by;
  }
  @Override
  public String toString() {
    return "[" + _type + "]" + super.toString() + " (by " + this._by + ")";
  }
  @Override
  public String toDBString() {
    return _type
      + TaskList._saveDelimiter
      + _status
      + TaskList._saveDelimiter
      + _name
      + TaskList._saveDelimiter
      + _by;
  }
}
