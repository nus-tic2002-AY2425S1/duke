package duke.dancepop.parser;

import duke.dancepop.entities.Deadline;
import duke.dancepop.entities.Event;
import duke.dancepop.entities.Task;
import duke.dancepop.entities.Todo;
import duke.dancepop.enums.CommandEnum;
import duke.dancepop.exceptions.ErrorMessageBuilder;
import duke.dancepop.exceptions.ExitException;
import duke.dancepop.exceptions.InputException;
import duke.dancepop.utils.DateTimeUtil;
import duke.dancepop.utils.Log;
import duke.dancepop.utils.Storage;
import duke.dancepop.utils.TaskList;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @throws ExitException  If the command signals the program to exit.
     * @throws InputException If the input for the command is invalid.
     */
    public abstract void execute() throws ExitException, InputException;
}

abstract class UnaryCommand extends Command {
}

class ListCommand extends UnaryCommand {

    /**
     * Executes the "list" command.
     * Prints all tasks in the task list.
     */
    public void execute() {
        TaskList.print();
    }
}

class ByeCommand extends UnaryCommand {

    /**
     * Executes the "bye" command.
     * Saves all tasks to a file and exits the program.
     *
     * @throws ExitException Always thrown to signal program exit.
     */
    public void execute() throws ExitException {
        Log.printMsg("Bye. Hope to see you again soon!");
        Storage.saveToFile();
        throw new ExitException();
    }
}

/**
 * Represents a command with a single argument.
 *
 * @param <T> The type of the argument.
 */
abstract class BinaryCommand<T> extends Command {
    protected T value;

    /**
     * Constructs a BinaryCommand with the given value.
     *
     * @param value The argument for the command.
     */
    public BinaryCommand(T value) {
        this.value = value;
    }

    /**
     * Returns the argument of the command.
     *
     * @return The argument.
     */
    public T getValue() {
        return value;
    }
}

/**
 * Represents the "todo" command which adds a new Todo task.
 */
class TodoCommand extends BinaryCommand<String> {

    public TodoCommand(String value) {
        super(value);
    }

    /**
     * Executes the "todo" command.
     * Adds a new Todo task to the task list.
     */
    public void execute() {
        Task task = new Todo(getValue());
        TaskList.addAndPrint(task);
    }
}

/**
 * Represents the "deadline" command which adds a new Deadline task.
 */
class DeadlineCommand extends BinaryCommand<String> {
    LocalDateTime by;

    public DeadlineCommand(String value, String by) throws InputException {
        super(value);
        this.by = DateTimeUtil.userInputToLocalDateTime(by);
    }

    /**
     * Executes the "deadline" command.
     * Adds a new Deadline task to the task list.
     */
    public void execute() {
        Task task = new Deadline(getValue(), by);
        TaskList.addAndPrint(task);
    }
}

/**
 * Represents the "event" command which adds a new Event task.
 */
class EventCommand extends BinaryCommand<String> {
    LocalDateTime from;
    LocalDateTime to;

    public EventCommand(String value, String from, String to) throws InputException {
        super(value);
        this.from = DateTimeUtil.userInputToLocalDateTime(from);
        this.to = DateTimeUtil.userInputToLocalDateTime(to);
    }

    /**
     * Executes the "event" command.
     * Adds a new Event task to the task list.
     */
    public void execute() {
        Task task = new Event(getValue(), from, to);
        TaskList.addAndPrint(task);
    }
}

/**
 * Represents the "mark" command which marks a task as done.
 */
class MarkCommand extends BinaryCommand<Integer> {

    public MarkCommand(int value) {
        super(value);
    }

    /**
     * Executes the "mark" command.
     * Marks the specified task as done.
     *
     * @throws InputException If the task index is out of bounds.
     */
    public void execute() throws InputException {
        try {
            TaskList.markDone(value - 1);
        } catch (IndexOutOfBoundsException ioobe) {
            List<String> errors = new ErrorMessageBuilder(CommandEnum.MARK).indexOutOfBounds().build();
            throw new InputException(errors);
        }
    }
}

/**
 * Represents the "unmark" command which marks a task as not done.
 */
class UnmarkCommand extends BinaryCommand<Integer> {

    public UnmarkCommand(int value) {
        super(value);
    }

    /**
     * Executes the "unmark" command.
     * Marks the specified task as not done.
     *
     * @throws InputException If the task index is out of bounds.
     */
    public void execute() throws InputException {
        try {
            TaskList.unmarkDone(value - 1);
        } catch (IndexOutOfBoundsException ioobe) {
            List<String> errors = new ErrorMessageBuilder(CommandEnum.UNMARK).indexOutOfBounds().build();
            throw new InputException(errors);
        }
    }
}

/**
 * Represents the "delete" command which removes a task from the task list.
 */
class DeleteCommand extends BinaryCommand<Integer> {

    public DeleteCommand(int value) {
        super(value);
    }

    /**
     * Executes the "delete" command.
     * Removes the specified task from the task list.
     *
     * @throws InputException If the task index is out of bounds.
     */
    public void execute() throws InputException {
        try {
            TaskList.remove(value - 1);
        } catch (IndexOutOfBoundsException ioobe) {
            List<String> errors = new ErrorMessageBuilder(CommandEnum.DELETE).indexOutOfBounds().build();
            throw new InputException(errors);
        }
    }
}

/**
 * Represents the "list" command which filters tasks by date.
 */
class ListByDateTimeCommand extends BinaryCommand<LocalDateTime> {

    public ListByDateTimeCommand(String value) throws InputException {
        super(DateTimeUtil.userInputToLocalDateTime(value));
    }

    /**
     * Executes the "list <by date>" command.
     * Prints tasks that occur on the specified date.
     */
    public void execute() {
        TaskList.print(getValue());
    }
}

/**
 * Represents the "save" command which saves tasks to a specified file.
 */
class SaveToFileNameCommand extends BinaryCommand<String> {

    public SaveToFileNameCommand(String value) {
        super(value);
    }

    /**
     * Executes the "save" command.
     * Saves tasks to the specified file.
     */
    public void execute() {
        Storage.saveToFile(getValue());
    }
}

/**
 * Represents the "load" command which loads tasks from a specified file.
 */
class LoadFromFileNameCommand extends BinaryCommand<String> {

    public LoadFromFileNameCommand(String value) {
        super(value);
    }

    /**
     * Executes the "load" command.
     * Loads tasks from the specified file.
     */
    public void execute() {
        Storage.loadFromFile(getValue());
    }
}

/**
 * Represents the "find" command which searches tasks by description.
 */
class FindCommand extends BinaryCommand<String> {

    public FindCommand(String value) {
        super(value);
    }

    /**
     * Executes the "find" command.
     * Prints tasks whose descriptions contain the specified keyword.
     */
    public void execute() {
        TaskList.printByDescriptionContains(getValue());
    }
}