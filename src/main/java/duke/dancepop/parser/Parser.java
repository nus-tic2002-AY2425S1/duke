package duke.dancepop.parser;

import duke.dancepop.enums.CommandEnum;
import duke.dancepop.enums.RegexEnum;
import duke.dancepop.exceptions.ErrorMessageBuilder;
import duke.dancepop.exceptions.ExceptionConsts;
import duke.dancepop.exceptions.InputException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     * Parses the user input string and returns the corresponding Command object.
     *
     * @param input The user input string.
     * @return The Command object associated with the input.
     * @throws InputException If the input is invalid or the command is unrecognized.
     */
    public static Command parse(String input) throws InputException {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase().trim();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        return switch (command) {
            case "todo" -> parseTodo(arguments);
            case "deadline" -> parseDeadline(arguments);
            case "event" -> parseEvent(arguments);
            case "mark" -> parseMark(arguments);
            case "unmark" -> parseUnmark(arguments);
            case "delete" -> parseDelete(arguments);
            case "list" -> parseList(arguments);
            case "bye" -> parseBye(arguments);
            case "save" -> parseSave(arguments);
            case "load" -> parseLoad(arguments);
            case "find" -> parseFind(arguments);
            default -> throw new InputException(ExceptionConsts.UNKNOWN_COMMAND_ERROR);
        };
    }

    private static Command parseTodo(String arguments) throws InputException {
        if (arguments.isBlank()) {
            List<String> errors = new ErrorMessageBuilder(CommandEnum.TODO).missingDescription().build();
            throw new InputException(errors);
        }
        return new TodoCommand(arguments);
    }

    private static Command parseDeadline(String arguments) throws InputException {
        Pattern deadlinePattern = Pattern.compile(RegexEnum.DEADLINE.getValue());
        Matcher deadlineMatcher = deadlinePattern.matcher(arguments);

        ErrorMessageBuilder errorBuilder = new ErrorMessageBuilder(CommandEnum.DEADLINE);

        if (!deadlineMatcher.matches()) {
            errorBuilder.unknownArguments();
            throw new InputException(errorBuilder.build());
        }

        String description = deadlineMatcher.group("description").trim();
        String deadline = deadlineMatcher.group("deadline").trim();

        if (description.isEmpty()) {
            errorBuilder.missingDescription();
        }

        if (deadline.isEmpty()) {
            errorBuilder.missingBy();
        }

        List<String> errors = errorBuilder.build();
        if (!errors.isEmpty())
            throw new InputException(errors);

        return new DeadlineCommand(description, deadline);
    }

    private static Command parseEvent(String arguments) throws InputException {
        Pattern eventPattern = Pattern.compile(RegexEnum.EVENT.getValue());
        Matcher eventMatcher = eventPattern.matcher(arguments);

        ErrorMessageBuilder errorBuilder = new ErrorMessageBuilder(CommandEnum.EVENT);

        if (!eventMatcher.matches()) {
            errorBuilder.unknownArguments();
            throw new InputException(errorBuilder.build());
        }

        String description = eventMatcher.group("description").trim();
        String from = eventMatcher.group("start").trim();
        String to = eventMatcher.group("end").trim();

        if (description.isEmpty()) {
            errorBuilder.missingDescription();
        }

        if (from.isEmpty()) {
            errorBuilder.missingFrom();
        }

        if (to.isEmpty()) {
            errorBuilder.missingTo();
        }

        List<String> errors = errorBuilder.build();
        if (!errors.isEmpty())
            throw new InputException(errors);

        return new EventCommand(description, from, to);
    }

    private static Command parseMark(String arguments) throws InputException {
        if (arguments.isBlank()) {
            throw new InputException(new ErrorMessageBuilder(CommandEnum.MARK).missingIndex().build());
        }

        int value = parseInt(CommandEnum.MARK, arguments);
        return new MarkCommand(value);
    }

    private static Command parseUnmark(String arguments) throws InputException {
        if (arguments.isBlank()) {
            throw new InputException(new ErrorMessageBuilder(CommandEnum.UNMARK).missingIndex().build());
        }

        int value = parseInt(CommandEnum.UNMARK, arguments);
        return new UnmarkCommand(value);
    }

    private static Command parseDelete(String arguments) throws InputException {
        if (arguments.isBlank()) {
            throw new InputException(new ErrorMessageBuilder(CommandEnum.DELETE).missingIndex().build());
        }

        int value = parseInt(CommandEnum.DELETE, arguments);
        return new DeleteCommand(value);
    }

    private static Command parseList(String arguments) throws InputException {
        if (arguments.isBlank()) {
            return new ListCommand();
        }
        try {
            return new ListByDateTimeCommand(arguments);
        } catch (InputException e) {
            List<String> errors = new ErrorMessageBuilder(CommandEnum.LIST).unknownArguments().build();
            errors.addAll(e.getMessages());
            throw new InputException(errors);
        }
    }

    private static Command parseBye(String arguments) throws InputException {
        if (!arguments.isBlank()) {
            List<String> errors = new ErrorMessageBuilder(CommandEnum.BYE).additionalArguments().build();
            throw new InputException(errors);
        }
        return new ByeCommand();
    }

    private static int parseInt(CommandEnum command, String value) throws InputException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            List<String> errors = new ErrorMessageBuilder(command).integerParse().build();
            throw new InputException(errors);
        }
    }

    private static Command parseSave(String value) throws InputException {
        validateFileName(value);
        return new SaveToFileNameCommand(value);
    }

    private static Command parseLoad(String value) throws InputException {
        validateFileName(value);
        return new LoadFromFileNameCommand(value);
    }

    private static void validateFileName(String value) throws InputException {
        boolean isFileNameValid = value.toLowerCase().endsWith(".csv");
        if (!isFileNameValid) {
            List<String> errors = new ErrorMessageBuilder(CommandEnum.LOAD).unknownArguments().build();
            throw new InputException(errors);
        }
    }

    private static Command parseFind(String value) {
        return new FindCommand(value);
    }
}
