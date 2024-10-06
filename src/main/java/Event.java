public class Event extends Task {
  protected String _from;
  protected String _to;
  protected String _type;
  public Event(String name, String from, String to) {
    super(name);
    this._from = from;
    this._to = to;
    this._type = "E";
  }
  public String getFrom() {
    return this._from;
  }
  public String getTo() {
    return this._to;
  }
  @Override
  public String toString() {
    return "[" + _type + "]" + super.toString() + " (from: " + this._from + " to: "+ this._to +  ")";
  }
}
