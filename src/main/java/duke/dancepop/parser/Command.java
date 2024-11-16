package duke.dancepop.parser;

import duke.dancepop.Storage;
import duke.dancepop.TaskList;
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

import java.time.LocalDateTime;
import java.util.List;

public abstract class Command {
    public abstract void execute() throws ExitException, InputException;
}

abstract class UnaryCommand extends Command {
}

class ListCommand extends UnaryCommand {
    public void execute() {
        TaskList.print();
    }
}

class ByeCommand extends UnaryCommand {
    public void execute() throws ExitException {
        Log.printMsg("Bye. Hope to see you again soon!");
        Storage.saveToFile();
        throw new ExitException();
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
    LocalDateTime by;

    public DeadlineCommand(String value, String by) throws InputException {
        super(value);
        this.by = DateTimeUtil.userInputToLocalDateTime(by);
    }

    public void execute() {
        Task task = new Deadline(getValue(), by);
        TaskList.add(task);
    }
}

class EventCommand extends BinaryCommand<String> {
    LocalDateTime from;
    LocalDateTime to;

    public EventCommand(String value, String from, String to) throws InputException {
        super(value);
        this.from = DateTimeUtil.userInputToLocalDateTime(from);
        this.to = DateTimeUtil.userInputToLocalDateTime(to);
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

    public void execute() throws InputException {
        try {
            TaskList.markDone(value - 1);
        } catch (IndexOutOfBoundsException ioobe) {
            List<String> errors = new ErrorMessageBuilder(CommandEnum.MARK).indexOutOfBounds().build();
            throw new InputException(errors);
        }
    }
}

class UnmarkCommand extends BinaryCommand<Integer> {

    public UnmarkCommand(int value) {
        super(value);
    }

    public void execute() throws InputException {
        try {
            TaskList.unmarkDone(value - 1);
        } catch (IndexOutOfBoundsException ioobe) {
            List<String> errors = new ErrorMessageBuilder(CommandEnum.UNMARK).indexOutOfBounds().build();
            throw new InputException(errors);
        }
    }
}

class DeleteCommand extends BinaryCommand<Integer> {

    public DeleteCommand(int value) {
        super(value);
    }

    public void execute() throws InputException {
        try {
            TaskList.remove(value - 1);
        } catch (IndexOutOfBoundsException ioobe) {
            List<String> errors = new ErrorMessageBuilder(CommandEnum.DELETE).indexOutOfBounds().build();
            throw new InputException(errors);
        }
    }
}

class ListByDateTimeCommand extends BinaryCommand<LocalDateTime> {

    public ListByDateTimeCommand(String value) throws InputException {
        super(DateTimeUtil.userInputToLocalDateTime(value));
    }

    public void execute() {
        TaskList.print(value);
    }
}

class SaveToFileNameCommand extends BinaryCommand<String> {

    public SaveToFileNameCommand(String value) {
        super(value);
    }

    public void execute() {
        Storage.loadFile(value);
    }
}

class LoadFromFileNameCommand extends BinaryCommand<String> {

    public LoadFromFileNameCommand(String value) {
        super(value);
    }

    public void execute() {
        Storage.loadFile(value);
    }
}