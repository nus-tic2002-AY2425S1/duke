package duke.dancepop.parser;


import duke.dancepop.Log;
import duke.dancepop.TaskList;
import duke.dancepop.entities.Deadline;
import duke.dancepop.entities.Event;
import duke.dancepop.entities.Task;
import duke.dancepop.entities.Todo;

public abstract class Command {
  public abstract void execute();
}

abstract class UnaryCommand extends Command {}

class ListCommand extends UnaryCommand {
  public void execute() {
    TaskList.print();
  }
}

class ByeCommand extends UnaryCommand {
  public void execute() {
    Log.printMsg("Bye. Hope to see you again soon!");
    System.exit(0);
  }
}

abstract class BinaryCommand<T> extends Command {
  protected T value;

  public BinaryCommand(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }
}


class TodoCommand extends BinaryCommand<String> {

  public TodoCommand(String value) {
    super(value);
  }

  public void execute() {
    Task task = new Todo(getValue());
    TaskList.add(task);
  }
}

class DeadlineCommand extends BinaryCommand<String> {
  String by;

  public DeadlineCommand(String value, String by) {
    super(value);
    this.by = by;
  }

  public void execute() {
    Task task = new Deadline(getValue(), by);
    TaskList.add(task);
  }
}

class EventCommand extends BinaryCommand<String> {
  String from;
  String to;

  public EventCommand(String value, String from, String to) {
    super(value);
    this.from = from;
    this.to = to;
  }

  public void execute() {
    Task task = new Event(getValue(), from, to);
    TaskList.add(task);
  }
}

class MarkCommand extends BinaryCommand<Integer> {

  public MarkCommand(int value) {
    super(value);
  }

  public void execute() {
    TaskList.markDone(value-1);
  }
}

class UnmarkCommand extends BinaryCommand<Integer> {

  public UnmarkCommand(int value) {
    super(value);
  }

  public void execute() {
    TaskList.unmarkDone(value-1);
  }
}

class DeleteCommand extends BinaryCommand<Integer> {

  public DeleteCommand(int value) {
    super(value);
  }

  public void execute() {
    TaskList.remove(value-1);
  }
}
