package mochi.tasks;

public class Todo extends Task{

  public Todo (String name) {
    super(name,"T");
  }

  @Override
  public String toString() {
    return "[" + this._type + "]" + super.toString();
  }

  @Override
  public String toDBString() {
    return
      super.toDBString();
  }
}
