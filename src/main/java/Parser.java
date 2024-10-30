// deals with making sense of the user command
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    // "^(?<commandWord>\\S+)(?:\\s+(?<remainingWords>.*))?$";
    // ^(?<commandWord>\S+)(?<arguments>.*)
    // public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    
    // Regex generated with the help of ChatGPT
    public static final String BASIC_COMMAND_REGEX = "^(?<commandWord>\\S+)(?<arguments>.*)";
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile(BASIC_COMMAND_REGEX);

    public static Command parse(String userInput) throws CommandException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            // return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
            throw new CommandException("Invalid command");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        
        // System.out.println("cmd: " + commandWord);
        // System.out.println("args: " + arguments);

        switch (commandWord) {
            case ByeCommand.COMMAND_WORD:
                return new ByeCommand();
            default:
                throw new CommandException("Unknown command: " + commandWord);
        }
    }
}
