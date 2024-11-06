package wkduke.parser;

import wkduke.command.*;
import wkduke.common.Messages;
import wkduke.exception.CommandFormatException;
import wkduke.exception.TaskFormatException;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user input into command objects for execution.
 * Uses regular expressions to match and extract command keywords and arguments.
 */
public class CommandParser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern TASK_TODO_DATA_ARGS_FORMAT = Pattern.compile("(?<description>.+)");
    public static final Pattern TASK_DEADLINE_DATA_ARGS_FORMAT = Pattern.compile("(?<description>.+) /by (?<by>.+)");
    public static final Pattern TASK_EVENT_DATA_ARGS_FORMAT = Pattern.compile("(?<description>.+) /from (?<from>.+) /to (?<to>.+)");
    public static final Pattern MARK_TASK_ARGS_FORMAT = Pattern.compile("^(?<taskNumber>\\d.*)$");
    public static final Pattern UNMARK_TASK_ARGS_FORMAT = Pattern.compile("^(?<taskNumber>\\d.*)$");
    public static final Pattern DELETE_TASK_ARGS_FORMAT = Pattern.compile("^(?<taskNumber>\\d.*)$");
    public static final Pattern LIST_TASK_ARGS_FORMAT = Pattern.compile("/on (?<on>.+)");

    /**
     * Parses the user input into a command.
     *
     * @param userInput The full user input to be parsed.
     * @return The corresponding {@code Command} based on the user input.
     * @throws CommandFormatException If the command format is invalid.
     * @throws TaskFormatException    If the task format within the command is invalid.
     */
    public static Command parseCommand(String userInput) throws CommandFormatException, TaskFormatException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new AssertionError("Basic command scenario is already handled earlier");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments").trim();

        return switch (commandWord) {
            case ExitCommand.COMMAND_WORD -> new ExitCommand();
            case ListCommand.COMMAND_WORD -> prepareList(arguments);
            case AddCommand.COMMAND_WORD_TODO -> prepareAddToDo(arguments);
            case AddCommand.COMMAND_WORD_DEADLINE -> prepareAddDeadline(arguments);
            case AddCommand.COMMAND_WORD_EVENT -> prepareAddEvent(arguments);
            case MarkCommand.COMMAND_WORD -> prepareMark(arguments);
            case UnmarkCommand.COMMAND_WORD -> prepareUnmark(arguments);
            case DeleteCommand.COMMAND_WORD -> prepareDelete(arguments);
            default -> throw new CommandFormatException(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    String.format("UserInput='%s'", userInput),
                    String.format("AvailableCommand='%s'", Messages.MESSAGE_AVAILABLE_COMMAND)
            );
        };

    }

    /**
     * Prepares an AddDeadline command from the given arguments.
     *
     * @param arguments The arguments provided for the Deadline task.
     * @return A new {@code AddDeadlineCommand} with the specified description and due date.
     * @throws TaskFormatException If the arguments format is invalid.
     */
    private static Command prepareAddDeadline(String arguments) throws TaskFormatException {
        final Matcher matcher = TASK_DEADLINE_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new TaskFormatException(
                    Messages.MESSAGE_INVALID_TASK_FORMAT,
                    String.format("TaskArguments='%s'", arguments),
                    AddDeadlineCommand.MESSAGE_USAGE
            );
        }
        LocalDateTime byDateTime = TimeParser.parseDateTime(matcher.group("by"));
        return new AddDeadlineCommand(matcher.group("description"), byDateTime);
    }

    /**
     * Prepares an AddEvent command from the given arguments.
     *
     * @param arguments The arguments provided for the Event task.
     * @return A new {@code AddEventCommand} with the specified description, start date, and end date.
     * @throws TaskFormatException If the arguments format is invalid.
     */
    private static Command prepareAddEvent(String arguments) throws TaskFormatException {
        final Matcher matcher = TASK_EVENT_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new TaskFormatException(
                    Messages.MESSAGE_INVALID_TASK_FORMAT,
                    String.format("TaskArguments='%s'", arguments),
                    AddEventCommand.MESSAGE_USAGE
            );
        }
        LocalDateTime fromDateTime = TimeParser.parseDateTime(matcher.group("from"));
        LocalDateTime toDateTime = TimeParser.parseDateTime(matcher.group("to"));
        if (fromDateTime.isAfter(toDateTime)) {
            throw new TaskFormatException(
                    Messages.MESSAGE_INVALID_TASK_ARG_FORMAT,
                    String.format("TaskArguments='%s'", arguments),
                    String.format(Messages.MESSAGE_INVALID_DATETIME_RANGE, fromDateTime, toDateTime)
            );
        }
        return new AddEventCommand(matcher.group("description"), fromDateTime, toDateTime);
    }

    /**
     * Prepares an AddToDo command from the given arguments.
     *
     * @param arguments The arguments provided for the ToDo task.
     * @return A new {@code AddToDoCommand} with the specified task description.
     * @throws TaskFormatException If the arguments format is invalid.
     */
    private static Command prepareAddToDo(String arguments) throws TaskFormatException {
        final Matcher matcher = TASK_TODO_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new TaskFormatException(
                    Messages.MESSAGE_INVALID_TASK_FORMAT,
                    String.format("TaskArguments='%s'", arguments),
                    AddToDoCommand.MESSAGE_USAGE
            );
        }
        return new AddToDoCommand(matcher.group("description"));
    }

    /**
     * Prepares a DeleteCommand from the given arguments.
     *
     * @param arguments The arguments provided to specify which task to delete.
     * @return A new {@code DeleteCommand} with the specified task number.
     * @throws CommandFormatException If the arguments format is invalid.
     */
    private static Command prepareDelete(String arguments) throws CommandFormatException {
        final Matcher matcher = DELETE_TASK_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new CommandFormatException(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    String.format("Command='delete', Arguments='%s'", arguments),
                    DeleteCommand.MESSAGE_USAGE
            );
        }
        return new DeleteCommand(Integer.parseInt(matcher.group("taskNumber")));
    }

    /**
     * Prepares a ListCommand or ListOnCommand based on the arguments.
     *
     * @param arguments The arguments specifying a date for filtering, if provided.
     * @return A {@code ListCommand} if no date is provided, or a {@code ListOnCommand} if a date is specified.
     * @throws CommandFormatException If the arguments format is invalid.
     */
    private static Command prepareList(String arguments) throws CommandFormatException {
        if (arguments.isEmpty()) {
            return new ListCommand();
        }

        final Matcher matcher = LIST_TASK_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new CommandFormatException(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    String.format("Command='list', Arguments='%s'", arguments),
                    ListOnCommand.MESSAGE_USAGE
            );
        }
        try {
            LocalDateTime onDateTime = TimeParser.parseDateTime(matcher.group("on"));
            return new ListOnCommand(onDateTime);
        } catch (TaskFormatException e) {
            throw new CommandFormatException(
                    e.getMessage(),
                    e.getDetail(),
                    e.getHelp()
            );
        }
    }

    /**
     * Prepares a MarkCommand from the given arguments.
     *
     * @param arguments The arguments provided to specify which task to mark as done.
     * @return A new {@code MarkCommand} with the specified task number.
     * @throws CommandFormatException If the arguments format is invalid.
     */
    private static Command prepareMark(String arguments) throws CommandFormatException {
        final Matcher matcher = MARK_TASK_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new CommandFormatException(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    String.format("Command='mark', Arguments='%s'", arguments),
                    MarkCommand.MESSAGE_USAGE
            );
        }
        return new MarkCommand(Integer.parseInt(matcher.group("taskNumber")));
    }

    /**
     * Prepares an UnmarkCommand from the given arguments.
     *
     * @param arguments The arguments provided to specify which task to unmark.
     * @return A new {@code UnmarkCommand} with the specified task number.
     * @throws CommandFormatException If the arguments format is invalid.
     */
    private static Command prepareUnmark(String arguments) throws CommandFormatException {
        final Matcher matcher = UNMARK_TASK_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new CommandFormatException(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    String.format("Command='unmark', Arguments='%s'", arguments),
                    UnmarkCommand.MESSAGE_USAGE
            );
        }
        return new UnmarkCommand(Integer.parseInt(matcher.group("taskNumber")));
    }
}
