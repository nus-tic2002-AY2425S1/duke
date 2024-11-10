package parser;

// deals with making sense of the user command
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

import commands.ByeCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.ShowCommand;
import commands.UnmarkCommand;
import commands.add.DeadlineCommand;
import commands.add.EventCommand;
import commands.add.FixedDurationCommand;
import commands.add.TodoCommand;
import common.Messages;

import exception.CommandException;

/**
 * Parses user input commands
 * 
 * <p>
 * The Parser class provides methods to interpret user input commands and arguments, validate their formats, and create corresponding command objects. 
 * It uses regular expressions to validate user input strings by matching command patterns and extract arguments. 
 * This ensures that the input adheres to the expected formats for various commands.
 * </p>
 */
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
    
    // public static final String FD_COMMAND_ARGS_REGEX = "^fd\s+([^\s/]+(?:\s+[^\s/]+)*)\s+/duration\s+(\d+(\.\d+)?)\s*$";
    // public static final String FD_COMMAND_ARGS_REGEX = "^fd\\s+(?<taskDescription>.+?)\\s+/duration\\s+(?<duration>\\d+(\\.\\d+)?)\\s*$";
    // public static final String FD_COMMAND_ARGS_REGEX = "^(?<description>.+) /duration (?<duration>.+)$";
    // public static final String FD_COMMAND_ARGS_REGEX = "^(?<description>.+?) /duration (?<duration>\\d+)";
    public static final String FD_COMMAND_ARGS_REGEX = "^(?<description>.+?) /duration (?<duration>\\d+(\\.\\d+)?)";
    public static final Pattern FD_COMMAND_ARGS_FORMAT = Pattern.compile(FD_COMMAND_ARGS_REGEX);

    /**
     * Parses the user input and returns the corresponding Command object. This creates the Command object.
     * 
     * @param userInput represents the input string from the user
     * @return a Command object corresponding to the parsed user input
     * @throws CommandException if the input string is not in the expected format, i.e. invalid input string, or cannot be parsed into a command.
     */
    public static Command parse(String userInput) throws CommandException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        
        if (!matcher.matches()) {
            throw new CommandException(String.format("%s", Messages.ERROR_INVALID_COMMAND_FORMAT));
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
            case ShowCommand.COMMAND_WORD:
                return prepareShow(cleanArgs);
            case FixedDurationCommand.COMMAND_WORD:
                return prepareFixedDuration(cleanArgs);
            default:
                throw new CommandException(
                    Messages.ERROR_INVALID_COMMAND,
                    String.format("Command=%s", commandWord),
                    Messages.VALID_COMMANDS
                );
        }
    }

    /**
     * Prepares the task number for commands that require it, i.e. mark, unmark, and delete.
     * 
     * @param commandWord represents the command word being processed, e.g. mark
     * @param args represents the arguments string containing the task number
     * @param messageUsage represents the usage instructions for the command. It is part of the message displayed when an error occurs
     * @return the parsed task number
     * @throws CommandException if the task number is invalid, i.e. the arguments do not match the expected format
     */
    private static int prepareTaskNumberForCommand(String commandWord, String args, String messageUsage) throws CommandException {
        final Matcher matcher = TASK_NUMBER_ARGS_FORMAT.matcher(args);
        if (!matcher.matches()) {
            throw new CommandException(
                String.format("%s%s", Messages.ERROR_INVALID_COMMAND_FORMAT, commandWord),
                String.format("Received `%s %s`", commandWord, args),
                String.format("Example usage: %s", messageUsage)
            );
        }
        int taskNumber = Integer.parseInt(matcher.group("taskNumber"));
        return taskNumber;
    }

    /**
     * Prepares a MarkCommand based on the provided arguments.
     * 
     * @param args represents the arguments string that contains the task number
     * @return a MarkCommand object
     * @throws CommandException if the task number is invalid, i.e. the arguments do not match the expected format
     */
    private static Command prepareMark(String args) throws CommandException {
        int taskNumber = prepareTaskNumberForCommand(MarkCommand.COMMAND_WORD, args, MarkCommand.MESSAGE_USAGE);
        return new MarkCommand(taskNumber);
    }

    /**
     * Prepares an UnmarkCommand based on the provided arguments.
     * 
     * @param args represents the arguments string that contains the task number
     * @return an UnmarkCommand object
     * @throws CommandException if the task number is invalid, i.e. the arguments do not match the expected format
     */
    private static Command prepareUnmark(String args) throws CommandException {
        int taskNumber = prepareTaskNumberForCommand(UnmarkCommand.COMMAND_WORD, args, UnmarkCommand.MESSAGE_USAGE);
        return new UnmarkCommand(taskNumber);
    }

    /**
     * Prepares a DeleteCommand based on the provided arguments.
     * 
     * @param args represents the arguments string that contains the task number
     * @return a DeleteCommand object
     * @throws CommandException if the task number is invalid, i.e. the arguments do not match the expected format
     */
    private static Command prepareDelete(String args) throws CommandException {
        int taskNumber = prepareTaskNumberForCommand(DeleteCommand.COMMAND_WORD, args, DeleteCommand.MESSAGE_USAGE);
        return new DeleteCommand(taskNumber);
    }

    /**
     * Checks if the description for a task command is empty.
     * 
     * @param args represents the arguments string that contains the description
     * @param commandWord represents the command word that is being processed, e.g. mark
     * @param infoMessage represents the information message to display when an error occurs
     * @param messageUsage represents the usage instructions for the command when an error occurs
     * @throws CommandException if the description is empty
     */
    public static void checkEmptyDescriptionForTaskCommand(String args, String commandWord, String messageUsage) throws CommandException {
        if (args.isEmpty()) {
            throw new CommandException(
                Messages.ERROR_INVALID_COMMAND_FORMAT + commandWord + ".",
                Messages.MESSAGE_EMPTY_DESCRIPTION_PRE,
                String.format("Example usage: `%s`", messageUsage)
            );
        }
    }

    /**
     * Validates the format of the arguments string using regular expression matcher.
     * 
     * @param matcher represents the Matcher object that is used to validate the arguments. It contains the result of matching the command arguments
     * @param commandWord represents the command word being processed
     * @param args represents the arguments string that was provided by the user
     * @param messageUsage represents the usage message to display for the command, providing an example of correct usage. It is shown when an error occurs
     * @throws CommandException if the arguments string do not match the expected format
     */
    private static void validateArgsFormat(Matcher matcher, String commandWord, String args, String messageUsage) throws CommandException {
        if (!matcher.matches()) {
            throw new CommandException(
                Messages.ERROR_INVALID_COMMAND_FORMAT + commandWord + ".",
                String.format("Received `%s %s`", commandWord, args),
                String.format("Example usage: `%s`", messageUsage)
            );
        }
    }

    /**
     * Prepares a TodoCommand object based on the provided arguments.
     * 
     * @param args represents the arguments string that contains the description
     * @return a TodoCommand object
     * @throws CommandException if the arguments string (description) is invalid (empty), i.e. the arguments do not match the expected format
     */
    private static Command prepareTodo(String args) throws CommandException {

        // Check if args (description) is empty
        checkEmptyDescriptionForTaskCommand(args, TodoCommand.COMMAND_WORD, TodoCommand.MESSAGE_USAGE);
        
        final Matcher matcher = TODO_COMMAND_ARGS_FORMAT.matcher(args);
        
        // Validate arg string format
        validateArgsFormat(matcher, TodoCommand.COMMAND_WORD, args, TodoCommand.MESSAGE_USAGE);
        
        String description = matcher.group("description");
        return new TodoCommand(description.trim());
    }

    /**
     * Checks if the arguments contain a specific keyword (e.g. "/by", "/from", "/to") and throws an exception if it does not.
     *
     * @param args represents the arguments string to check.
     * @param keyword represents the keyword to look for in the arguments.
     * @param commandWord represents the command word being processed.
     * @param infoMessage represents the message to display if the keyword is missing.
     * @param messageUsage represents the usage message to display in case of an error.
     * @throws CommandException if the keyword is not found in the arguments.
     */
    private static void checkArgsContainsKeyword(String args, String keyword, String commandWord, String infoMessage, String messageUsage) throws CommandException {
        if (!args.contains(keyword)) {
            throw new CommandException(
                Messages.ERROR_INVALID_COMMAND_FORMAT + commandWord + ".",
                infoMessage,
                String.format("Example usage: `%s`", messageUsage)
            );
        }
    }

    /**
     * Prepares a DeadlineCommand object based on the provided arguments.
     * 
     * @param args represents the arguments string that contains the description and due date of the task
     * @return a DeadlineCommand object
     * @throws CommandException if the arguments string (description and due date received) are invalid / missing, i.e. the arguments do not match the expected format
     */
    private static Command prepareDeadline(String args) throws CommandException {

        final String MESSAGE_EMPTY_DUEDATE_PRE = "The task is missing a due date.";
        final String BY_KEYWORD = "/by";

        // Check if args (description) is empty
        checkEmptyDescriptionForTaskCommand(args, DeadlineCommand.COMMAND_WORD, DeadlineCommand.MESSAGE_USAGE);
        
        checkArgsContainsKeyword(args, BY_KEYWORD, DeadlineCommand.COMMAND_WORD, MESSAGE_EMPTY_DUEDATE_PRE, DeadlineCommand.MESSAGE_USAGE);
        
        final Matcher matcher = DEADLINE_COMMAND_ARGS_FORMAT.matcher(args);
        
        // Validate arg string format
        validateArgsFormat(matcher, DeadlineCommand.COMMAND_WORD, args, DeadlineCommand.MESSAGE_USAGE);
        
        String description = matcher.group("description");

        String dueString = matcher.group("due").trim();

        LocalDateTime due = DateTimeParser.parseInputDeadlineDateTime(dueString);

        return new DeadlineCommand(description.trim(), due);
    }

    /** Prepares an EventCommand object based on the provided arguments.
     * 
     * @param args represents the arguments string that contains the description, start date/time, and end date/time of the event
     * @return an EventCommand object
     * @throws CommandException if the arguments string (description, start date/time, and end date/time) are invalid, i.e. the arguments do not match the expected format, or if the start date/time is after the end date/time
     */
    private static Command prepareEvent(String args) throws CommandException {

        final String MESSAGE_EMPTY_STARTDATETIME_PRE = "The task is missing a start date/time.";
        final String MESSAGE_EMPTY_ENDDATETIME_PRE = "The task is missing an end date/time.";
        final String FROM_KEYWORD = "/from";
        final String TO_KEYWORD = "/to";

        // Check if args (description) is empty
        checkEmptyDescriptionForTaskCommand(args, EventCommand.COMMAND_WORD, EventCommand.MESSAGE_USAGE);

        checkArgsContainsKeyword(args, FROM_KEYWORD, EventCommand.COMMAND_WORD, MESSAGE_EMPTY_STARTDATETIME_PRE, EventCommand.MESSAGE_USAGE);
                
        checkArgsContainsKeyword(args, TO_KEYWORD, EventCommand.COMMAND_WORD, MESSAGE_EMPTY_ENDDATETIME_PRE, EventCommand.MESSAGE_USAGE);

        final Matcher matcher = EVENT_COMMAND_ARGS_FORMAT.matcher(args);
        
        // Validate arg string format
        validateArgsFormat(matcher, EventCommand.COMMAND_WORD, args, EventCommand.MESSAGE_USAGE);

        String description = matcher.group("description");
        String startDateTimeString = matcher.group("start");
        // LocalDateTime startDateTime = LocalDateTime.parse(matcher.group("start").trim());
        LocalDateTime startDateTime = DateTimeParser.parseInputEventDateTime(startDateTimeString);
        
        String endDateTimeString = matcher.group("end");
        // LocalDateTime endDateTime = LocalDateTime.parse(matcher.group("end").trim());
        LocalDateTime endDateTime = DateTimeParser.parseInputEventDateTime(endDateTimeString);
        
        if (endDateTime.isBefore(startDateTime)) {
            throw new CommandException(Messages.ERROR_END_BEFORE_START);
        }
        
        return new EventCommand(description.trim(), startDateTime, endDateTime);
    }

    /**
     * Prepares a ShowCommand object based on the provided arguments.
     * 
     * @param args represents the arguments string that contains the date for which the user wishes to list the deadlines and events
     * @return a ShowCommand object
     * @throws CommandException if the date is invalid, or cannot be parsed
     */
    private static Command prepareShow(String args) throws CommandException {
        LocalDate date = DateTimeParser.parseInputShowDate(args);
        return new ShowCommand(date);
    }

    private static Command prepareFixedDuration(String args) throws CommandException {
        // Check if args (description) is empty
        checkEmptyDescriptionForTaskCommand(args, FixedDurationCommand.COMMAND_WORD, FixedDurationCommand.MESSAGE_USAGE);
        
        final Matcher matcher = FD_COMMAND_ARGS_FORMAT.matcher(args);
        
        // Validate arg string format
        validateArgsFormat(matcher, FixedDurationCommand.COMMAND_WORD, args, FixedDurationCommand.MESSAGE_USAGE);
        
        String description = matcher.group("description");
        // System.out.println("description is " + description);
        String durationString = matcher.group("duration");
        // System.out.println("duration is " + duration);
        
        double duration = Double.parseDouble(durationString);
        // System.out.println("duration is " + duration);

        return new FixedDurationCommand(description, duration);
    }
}
