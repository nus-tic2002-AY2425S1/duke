// deals with making sense of the user command
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    // Regex below generated with the help of ChatGPT
    public static final String BASIC_COMMAND_REGEX = "^(?<commandWord>\\S+)(?<arguments>.*)";
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile(BASIC_COMMAND_REGEX);

    // Regex used for "mark", "unmark", "delete"
    public static final String TASK_NUMBER_ARGS_REGEX = "^(?<taskNumber>\\d+)$";
    public static final Pattern TASK_NUMBER_ARGS_FORMAT = Pattern.compile(TASK_NUMBER_ARGS_REGEX);
    
    public static final String TODO_COMMAND_ARGS_REGEX = "^(?<description>.+)$";
    public static final Pattern TODO_COMMAND_ARGS_FORMAT = Pattern.compile(TODO_COMMAND_ARGS_REGEX);
    
    public static final String DEADLINE_COMMAND_ARGS_REGEX = "^(?<description>.+) /by (?<due>.+)$";
    public static final Pattern DEADLINE_COMMAND_ARGS_FORMAT = Pattern.compile(DEADLINE_COMMAND_ARGS_REGEX);
    
    public static final String EVENT_COMMAND_ARGS_REGEX = "^(?<description>.+) /from (?<start>.+) /to (?<end>.+)$";
    public static final Pattern EVENT_COMMAND_ARGS_FORMAT = Pattern.compile(EVENT_COMMAND_ARGS_REGEX);
    
    public static Command parse(String userInput) throws CommandException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        
        if (!matcher.matches()) {
            throw new CommandException(Messages.INVALID_COMMAND_FORMAT);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        
        String cleanArgs = arguments.trim();

        switch (commandWord) {
            case ByeCommand.COMMAND_WORD:
                return new ByeCommand();
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case MarkCommand.COMMAND_WORD:
                return prepareMark(cleanArgs);
            case UnmarkCommand.COMMAND_WORD:
                return prepareUnmark(cleanArgs);
            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(cleanArgs);
            case TodoCommand.COMMAND_WORD:
                return prepareTodo(cleanArgs);
            case DeadlineCommand.COMMAND_WORD:
                return prepareDeadline(cleanArgs);
            case EventCommand.COMMAND_WORD:
                return prepareEvent(cleanArgs);
            default:
                throw new CommandException(
                    Messages.INVALID_COMMAND,
                    String.format("Command=%s", commandWord),
                    Messages.VALID_COMMANDS
                );
        }
    }

    private static Command prepareMark(String args) throws CommandException {
        final Matcher matcher = TASK_NUMBER_ARGS_FORMAT.matcher(args);
        
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandException(
                String.format("%s%s", Messages.INVALID_COMMAND_FORMAT, MarkCommand.COMMAND_WORD),
                String.format("Received `%s %s`", MarkCommand.COMMAND_WORD, args),
                String.format("Example usage: %s", MarkCommand.MESSAGE_USAGE)
            );
        }

        int taskNumber = Integer.parseInt(matcher.group("taskNumber"));
        return new MarkCommand(taskNumber);
    }

    private static Command prepareUnmark(String args) throws CommandException {
        final Matcher matcher = TASK_NUMBER_ARGS_FORMAT.matcher(args);
        
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandException(
                String.format("%s%s", Messages.INVALID_COMMAND_FORMAT, UnmarkCommand.COMMAND_WORD),
                String.format("Received `%s %s`", UnmarkCommand.COMMAND_WORD, args),
                String.format("Example usage: %s", UnmarkCommand.MESSAGE_USAGE)
            );
        }

        int taskNumber = Integer.parseInt(matcher.group("taskNumber"));
        return new UnmarkCommand(taskNumber);
    }

    private static Command prepareDelete(String args) throws CommandException {
        final Matcher matcher = TASK_NUMBER_ARGS_FORMAT.matcher(args);
        
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandException(
                String.format("%s%s", Messages.INVALID_COMMAND_FORMAT, DeleteCommand.COMMAND_WORD),
                String.format("Received `%s %s`", DeleteCommand.COMMAND_WORD, args),
                String.format("Example usage: `%s`", DeleteCommand.MESSAGE_USAGE)
            );
        }

        int taskNumber = Integer.parseInt(matcher.group("taskNumber"));
        return new DeleteCommand(taskNumber);
    }

    private static Command prepareTodo(String args) throws CommandException {

        // Check if args (description) is empty
        if (args.isEmpty()) {
            throw new CommandException(
                Messages.INVALID_COMMAND_FORMAT + TodoCommand.COMMAND_WORD + ".",
                Messages.MESSAGE_EMPTY_DESCRIPTION_PRE,
                String.format("Example usage: `%s`", TodoCommand.MESSAGE_USAGE)
            );
        }
        
        final Matcher matcher = TODO_COMMAND_ARGS_FORMAT.matcher(args);
        
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandException(
                Messages.INVALID_COMMAND_FORMAT + TodoCommand.COMMAND_WORD + ".",
                String.format("Received `%s`", args),
                String.format("Example usage: `%s`", TodoCommand.MESSAGE_USAGE)
            );
        }
        
        // System.out.println("this is args " + args);
        String description = matcher.group("description");
        return new TodoCommand(description.trim());
    }

    private static Command prepareDeadline(String args) throws CommandException {

        final String MESSAGE_EMPTY_DUEDATE_PRE = "The task is missing a due date.";
        final String BY_KEYWORD = "/by";

        // Check if args (description) is empty
        if (args.isEmpty()) {
            throw new CommandException(
                Messages.INVALID_COMMAND_FORMAT + DeadlineCommand.COMMAND_WORD + ".",
                Messages.MESSAGE_EMPTY_DESCRIPTION_PRE,
                String.format("Example usage: `%s`", DeadlineCommand.MESSAGE_USAGE)
            );
        }
        
        if (!args.contains(BY_KEYWORD)) {
            throw new CommandException(
                Messages.INVALID_COMMAND_FORMAT + DeadlineCommand.COMMAND_WORD + ".",
                MESSAGE_EMPTY_DUEDATE_PRE,
                String.format("Example usage: `%s`", DeadlineCommand.MESSAGE_USAGE)
            );
        }

        final Matcher matcher = DEADLINE_COMMAND_ARGS_FORMAT.matcher(args);
        
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandException(
                Messages.INVALID_COMMAND_FORMAT + DeadlineCommand.COMMAND_WORD + ".",
                String.format("Received `%s`", DeadlineCommand.COMMAND_WORD + " " + args),
                String.format("Example usage: `%s`", DeadlineCommand.MESSAGE_USAGE)
            );
        }
        
        String description = matcher.group("description");
        String due = matcher.group("due");
        return new DeadlineCommand(description.trim(), due.trim());
    }

    private static Command prepareEvent(String args) throws CommandException {

        final String MESSAGE_EMPTY_STARTDATETIME_PRE = "The task is missing a start date/time.";
        final String MESSAGE_EMPTY_ENDDATETIME_PRE = "The task is missing an end date/time.";
        final String FROM_KEYWORD = "/from";
        final String TO_KEYWORD = "/to";

        // Check if args (description) is empty
        if (args.isEmpty()) {
            throw new CommandException(
                Messages.INVALID_COMMAND_FORMAT + EventCommand.COMMAND_WORD + ".",
                Messages.MESSAGE_EMPTY_DESCRIPTION_PRE,
                String.format("Example usage: `%s`", EventCommand.MESSAGE_USAGE)
            );
        }
        
        if (!args.contains(FROM_KEYWORD)) {
            throw new CommandException(
                Messages.INVALID_COMMAND_FORMAT + EventCommand.COMMAND_WORD + ".",
                MESSAGE_EMPTY_STARTDATETIME_PRE,
                String.format("Example usage: `%s`", EventCommand.MESSAGE_USAGE)
            );
        }
        
        if (!args.contains(TO_KEYWORD)) {
            throw new CommandException(
                Messages.INVALID_COMMAND_FORMAT + EventCommand.COMMAND_WORD + ".",
                MESSAGE_EMPTY_ENDDATETIME_PRE,
                String.format("Example usage: `%s`", EventCommand.MESSAGE_USAGE)
            );
        }

        final Matcher matcher = EVENT_COMMAND_ARGS_FORMAT.matcher(args);
        
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandException(
                Messages.INVALID_COMMAND_FORMAT + EventCommand.COMMAND_WORD + ".",
                String.format("Received `%s`", args),
                String.format("Example usage: `%s`", EventCommand.MESSAGE_USAGE)
            );
        }
        
        // System.out.println("this is args " + args);
        String description = matcher.group("description");
        String startTime = matcher.group("start");
        String endTime = matcher.group("end");
        return new EventCommand(description.trim(), startTime.trim(), endTime.trim());
    }
}
