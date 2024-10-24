import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern TASK_TODO_DATA_ARGS_FORMAT = Pattern.compile("(?<description>.+)");
    public static final Pattern TASK_DEADLINE_DATA_ARGS_FORMAT = Pattern.compile("(?<description>.+) /by (?<by>[^/]+)");
    public static final Pattern TASK_EVENT_DATA_ARGS_FORMAT = Pattern.compile("(?<description>.+) /from (?<from>[^/]+) /to (?<to>[^/]+)");
    public static final Pattern MARK_TASK_ARGS_FORMAT = Pattern.compile("^(?<taskNumber>\\d.*)$");
    public static final Pattern UNMARK_TASK_ARGS_FORMAT = Pattern.compile("^(?<taskNumber>\\d.*)$");
    public static final Pattern DELETE_TASK_ARGS_FORMAT = Pattern.compile("^(?<taskNumber>\\d.*)$");

    public static Command parseCommand(String userInput) throws CommandFormatException, TaskFormatException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new AssertionError("Basic command scenario is already handled earlier");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments").trim();

        return switch (commandWord) {
            case ListCommand.COMMAND_WORD -> new ListCommand();
            case ExitCommand.COMMAND_WORD -> new ExitCommand();
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

    private static Command prepareAddDeadline(String arguments) throws TaskFormatException {
        final Matcher matcher = TASK_DEADLINE_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new TaskFormatException(
                    Messages.MESSAGE_INVALID_TASK_FORMAT,
                    String.format("TaskArguments='%s'", arguments),
                    AddDeadlineCommand.MESSAGE_USAGE
            );
        }
        return new AddDeadlineCommand(matcher.group("description"), matcher.group("by"));
    }

    private static Command prepareAddEvent(String arguments) throws TaskFormatException {
        final Matcher matcher = TASK_EVENT_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new TaskFormatException(
                    Messages.MESSAGE_INVALID_TASK_FORMAT,
                    String.format("TaskArguments='%s'", arguments),
                    AddEventCommand.MESSAGE_USAGE
            );
        }
        return new AddEventCommand(matcher.group("description"), matcher.group("from"), matcher.group("to"));
    }

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
