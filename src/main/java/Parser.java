// deals with making sense of the user command
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    // Regex generated with the help of ChatGPT
    public static final String BASIC_COMMAND_REGEX = "^(?<commandWord>\\S+)(?<arguments>.*)";
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile(BASIC_COMMAND_REGEX);

    // Regex used for "mark", "unmark", "delete"
    public static final String TASK_NUMBER_REGEX = "^(?<taskNumber>\\d+)$";         // "^mark (?<taskNumber>\\S+)$"
    // public static final String MARK_COMMAND_REGEX = "^mark\\s+(?<taskNumber>\\d+)$";         // "^mark (?<taskNumber>\\S+)$"
    // public static final String MARK_COMMAND_REGEX = "(?<commanWord>mark) (?<taskNumber>\\d+)";         // "^mark (?<taskNumber>\\S+)$"
    // public static final String MARK_COMMAND_REGEX = "^mark (?<taskNumber>\\d+)$";            // "(?<command>mark) (?<taskNumber>\\d+)";         // "^mark (?<taskNumber>\\S+)$"
    public static final Pattern TASK_NUMBER_FORMAT = Pattern.compile(TASK_NUMBER_REGEX);

    public static Command parse(String userInput) throws CommandException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        
        if (!matcher.matches()) {
            throw new CommandException(Messages.INVALID_COMMAND_FORMAT);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        
        // System.out.println("cmd: " + commandWord);
        // System.out.println("args: " + arguments);

        switch (commandWord) {
            case ByeCommand.COMMAND_WORD:
                return new ByeCommand();
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case MarkCommand.COMMAND_WORD:
                return prepareMark(arguments.trim());
            case UnmarkCommand.COMMAND_WORD:
                return prepareUnmark(arguments.trim());
            default:
                throw new CommandException(
                    Messages.INVALID_COMMAND,
                    String.format("Command=%s", commandWord),
                    Messages.VALID_COMMANDS
                );
        }
    }

    private static Command prepareMark(String args) throws CommandException {
        final Matcher matcher = TASK_NUMBER_FORMAT.matcher(args);
        
        // System.out.println("matching? " + matcher.matches());
        // System.out.println("args is " + args);
        
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandException(
                Messages.INVALID_COMMAND_FORMAT,
                String.format("Command=%s,Arguments=%s", MarkCommand.COMMAND_WORD, args),
                MarkCommand.MESSAGE_USAGE
            );
        }

        int taskNumber = Integer.parseInt(args);
        // System.out.println("taskNumber: " + taskNumber);
        return new MarkCommand(taskNumber);
    }

    private static Command prepareUnmark(String args) throws CommandException {
        final Matcher matcher = TASK_NUMBER_FORMAT.matcher(args);
        
        // System.out.println("matching? " + matcher.matches());
        // System.out.println("args is " + args);
        
        // Validate arg string format
        if (!matcher.matches()) {
            throw new CommandException(
                Messages.INVALID_COMMAND_FORMAT,
                String.format("Command=%s,Arguments=%s", UnmarkCommand.COMMAND_WORD, args),
                UnmarkCommand.MESSAGE_USAGE
            );
        }

        int taskNumber = Integer.parseInt(args);
        // System.out.println("taskNumber: " + taskNumber);
        return new UnmarkCommand(taskNumber);
    }
}
