package mochi.tasks;
/**
 * The Todo class represents a task with a basic message
 * It includes methods to convert it to a database-compatible format.
 */
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
