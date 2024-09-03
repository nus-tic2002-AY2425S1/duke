package duke.dancepop.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
  public static Command parse(String input) throws IllegalArgumentException {
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
      // TODO: Handle invalid input flow
      default -> { return null; }
    }
  }

  private static Command parseTodo(String arguments) {
    if (arguments.isBlank()) {
      // TODO: Handle error flow
    }
    return new TodoCommand(arguments);
  }

  private static Command parseDeadline(String arguments) {
    Pattern deadlinePattern = Pattern.compile("(.+) /by (.+)");
    Matcher deadlineMatcher = deadlinePattern.matcher(arguments);
    if (!deadlineMatcher.matches()) {
      // TODO: Handle error flow
    }
    return new DeadlineCommand(deadlineMatcher.group(1).trim(), deadlineMatcher.group(2).trim());
  }

  private static Command parseEvent(String arguments) {
    Pattern eventPattern = Pattern.compile("(.+) /from (.+) /to (.+)");
    Matcher eventMatcher = eventPattern.matcher(arguments);
    if (!eventMatcher.matches()) {
      // TODO: Handle error flow
    }

    return new EventCommand(eventMatcher.group(1).trim(), eventMatcher.group(2).trim(), eventMatcher.group(3).trim());
  }

  private static Command parseMark(String arguments) {
    // TODO: Handle NumberFormatException?
    int markValue = Integer.parseInt(arguments);
    return new MarkCommand(markValue);
  }

  private static Command parseUnmark(String arguments) {
    // TODO: Handle NumberFormatException?
    int markValue = Integer.parseInt(arguments);
    return new UnmarkCommand(markValue);
  }

  private static Command parseDelete(String arguments) {
    // TODO: Handle NumberFormatException?
    int deleteValue = Integer.parseInt(arguments);
    return new DeleteCommand(deleteValue);
  }

  private static Command parseList(String arguments) {
    if (!arguments.isBlank()) {
      // TODO: Handle error flow
    }
    return new ListCommand();
  }

  private static Command parseBye(String arguments) {
    if (!arguments.isBlank()) {
      // TODO: Handle error flow
    }
    return new ByeCommand();
  }
}
