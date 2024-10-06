public class Deadline extends Task{
  protected String _by;
  protected String _type;
  public Deadline(String name, String by) {
    super(name);
    this._by = by;
    this._type = "D";
  }
  public String getBy() {
    return this._by;
  }
  @Override
  public String toString() {
    return "[" + _type + "]" + super.toString() + " (by " + this._by + ")";
  }
}
