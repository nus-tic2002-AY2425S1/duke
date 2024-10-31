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
    
    public static Command parse(String userInput) throws CommandException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        
        if (!matcher.matches()) {
            throw new CommandException(Messages.INVALID_COMMAND_FORMAT);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        
        System.out.println("cmd: " + commandWord);
        System.out.println("args: " + arguments);

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
                Messages.INVALID_COMMAND_FORMAT,
                String.format("Command=%s,Arguments=%s", MarkCommand.COMMAND_WORD, args),
                MarkCommand.MESSAGE_USAGE
            );
        }

        int taskNumber = Integer.parseInt(matcher.group("taskNumber"));
        // int taskNumber = Integer.parseInt(args);
        // System.out.println("taskNumber: " + taskNumber);
        return new MarkCommand(taskNumber);
    }

    private static Command prepareUnmark(String args) throws CommandException {
        final Matcher matcher = TASK_NUMBER_ARGS_FORMAT.matcher(args);
        
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandException(
                Messages.INVALID_COMMAND_FORMAT,
                String.format("Command=%s,Arguments=%s", UnmarkCommand.COMMAND_WORD, args),
                UnmarkCommand.MESSAGE_USAGE
            );
        }

        int taskNumber = Integer.parseInt(matcher.group("taskNumber"));
        // int taskNumber = Integer.parseInt(args);
        // System.out.println("taskNumber: " + taskNumber);
        return new UnmarkCommand(taskNumber);
    }

    private static Command prepareDelete(String args) throws CommandException {
        final Matcher matcher = TASK_NUMBER_ARGS_FORMAT.matcher(args);
        
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandException(
                Messages.INVALID_COMMAND_FORMAT,
                String.format("Command=%s,Arguments=%s", DeleteCommand.COMMAND_WORD, args),
                DeleteCommand.MESSAGE_USAGE
            );
        }

        int taskNumber = Integer.parseInt(matcher.group("taskNumber"));
        // int taskNumber = Integer.parseInt(args);
        System.out.println("taskNumber: " + taskNumber);
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
}
