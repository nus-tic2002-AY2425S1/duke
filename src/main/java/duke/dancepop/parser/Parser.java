package duke.dancepop.parser;

import duke.dancepop.enums.CommandEnum;
import duke.dancepop.enums.RegexEnum;
import duke.dancepop.exceptions.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
  public static Command parse(String input) throws InputException {
    String[] parts = input.split(" ", 2);
    String command = parts[0].toLowerCase().trim();
    String arguments = parts.length > 1 ? parts[1].trim() : "";

    switch (command) {
      case "todo" -> {
        return parseTodo(arguments);
      }
      case "deadline" -> {
        return parseDeadline(arguments);
      }
      case "event" -> {
        return parseEvent(arguments);
      }
      case "mark" -> {
        return parseMark(arguments);
      }
      case "unmark" -> {
        return parseUnmark(arguments);
      }
      case "delete" -> {
        return parseDelete(arguments);
      }
      case "list" -> {
        return parseList(arguments);
      }
      case "bye" -> {
        return parseBye(arguments);
      }
      default -> throw new InputException(ExceptionConsts.UNKNOWN_COMMAND_ERROR);
    }
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
      errorBuilder.unknownCommand();
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
      errorBuilder.unknownCommand();
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
      int value = parseInt(CommandEnum.MARK, arguments);
      return new MarkCommand(value);
  }

  private static Command parseUnmark(String arguments) throws InputException {
    int value = parseInt(CommandEnum.UNMARK, arguments);
    return new UnmarkCommand(value);
  }

  private static Command parseDelete(String arguments) throws InputException {
    int value = parseInt(CommandEnum.DELETE, arguments);
    return new DeleteCommand(value);
  }

  private static Command parseList(String arguments) throws InputException {
    if (!arguments.isBlank()) {
      List<String> errors = new ErrorMessageBuilder(CommandEnum.LIST).additionalArguments().build();
      throw new InputException(errors);
    }
    return new ListCommand();
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
}
