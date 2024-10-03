public class Dateline extends Task{
  protected String _by;
  protected String _type;
  public Dateline(String name, String by) {
    super(name);
    this._by = by;
    this._type = "D";
  }

  @Override
  public String toString() {
    return "[" + _type + "]" + super.toString() + " (by " + this._by + ")";
  }
}
