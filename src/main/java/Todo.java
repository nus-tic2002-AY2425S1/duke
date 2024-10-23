public class Todo extends Task{
  protected String _type;
  public Todo (String name) {
    super(name);
    _type = "T";
  }

  @Override
  public String toString() {
    return "[" + this._type + "]" + super.toString();
  }

  @Override
  public String toDBString() { return Command.TODO.toString() + TaskList._saveDelimiter + super.getName(); }
}
