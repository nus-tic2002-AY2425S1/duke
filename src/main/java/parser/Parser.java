package parser;
// deals with making sense of the user command
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.ShowCommand;
import commands.TodoCommand;
import commands.UnmarkCommand;
import common.Messages;

import exception.CommandException;

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
            default:
                throw new CommandException(
                    Messages.ERROR_INVALID_COMMAND,
                    String.format("Command=%s", commandWord),
                    Messages.VALID_COMMANDS
                );
        }
    }

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

    private static Command prepareMark(String args) throws CommandException {
        int taskNumber = prepareTaskNumberForCommand(MarkCommand.COMMAND_WORD, args, MarkCommand.COMMAND_WORD);
        return new MarkCommand(taskNumber);
    }

    private static Command prepareUnmark(String args) throws CommandException {
        int taskNumber = prepareTaskNumberForCommand(UnmarkCommand.COMMAND_WORD, args, UnmarkCommand.MESSAGE_USAGE);
        return new UnmarkCommand(taskNumber);
    }

    private static Command prepareDelete(String args) throws CommandException {
        int taskNumber = prepareTaskNumberForCommand(DeleteCommand.COMMAND_WORD, args, DeleteCommand.MESSAGE_USAGE);
        return new DeleteCommand(taskNumber);
    }

    public static void checkEmptyDescriptionForTaskCommand(String args, String commandWord, String infoMessage, String messageUsage) throws CommandException {
        if (args.isEmpty()) {
            throw new CommandException(
                Messages.ERROR_INVALID_COMMAND_FORMAT + commandWord + ".",
                infoMessage,
                String.format("Example usage: `%s`", messageUsage)
            );
        }
    }

    public static void validateArgsFormat(Matcher matcher, String commandWord, String args, String messageUsage) throws CommandException {
        if (!matcher.matches()) {
            throw new CommandException(
                Messages.ERROR_INVALID_COMMAND_FORMAT + commandWord + ".",
                String.format("Received `%s %s`", commandWord, args),
                String.format("Example usage: `%s`", messageUsage)
            );
        }
    }

    private static Command prepareTodo(String args) throws CommandException {

        // Check if args (description) is empty
        checkEmptyDescriptionForTaskCommand(args, TodoCommand.COMMAND_WORD, Messages.MESSAGE_EMPTY_DESCRIPTION_PRE, TodoCommand.MESSAGE_USAGE);
        
        final Matcher matcher = TODO_COMMAND_ARGS_FORMAT.matcher(args);
        
        // Validate arg string format
        validateArgsFormat(matcher, TodoCommand.COMMAND_WORD, args, TodoCommand.MESSAGE_USAGE);
        
        String description = matcher.group("description");
        return new TodoCommand(description.trim());
    }

    private static void checkArgsContainsKeyword(String args, String keyword, String commandWord, String infoMessage, String messageUsage) throws CommandException {
        if (!args.contains(keyword)) {
            throw new CommandException(
                Messages.ERROR_INVALID_COMMAND_FORMAT + commandWord + ".",
                infoMessage,
                String.format("Example usage: `%s`", messageUsage)
            );
        }
    }

    private static Command prepareDeadline(String args) throws CommandException {

        final String MESSAGE_EMPTY_DUEDATE_PRE = "The task is missing a due date.";
        final String BY_KEYWORD = "/by";

        // Check if args (description) is empty
        checkEmptyDescriptionForTaskCommand(args, DeadlineCommand.COMMAND_WORD, Messages.MESSAGE_EMPTY_DESCRIPTION_PRE, DeadlineCommand.MESSAGE_USAGE);
        
        checkArgsContainsKeyword(args, BY_KEYWORD, DeadlineCommand.COMMAND_WORD, MESSAGE_EMPTY_DUEDATE_PRE, DeadlineCommand.MESSAGE_USAGE);
        
        final Matcher matcher = DEADLINE_COMMAND_ARGS_FORMAT.matcher(args);
        
        // Validate arg string format
        validateArgsFormat(matcher, DeadlineCommand.COMMAND_WORD, args, DeadlineCommand.MESSAGE_USAGE);
        
        String description = matcher.group("description");

        /*
         * Teach the chatbot how to understand dates and times. For example, if the command is 
         * deadline return book /by 2/12/2019 1800, 
         * the chatbot should understand 2/12/2019 1800 as 2nd of December 2019, 6pm, 
         * instead of treating it as just a String.
         * 
         * Store deadline dates as a java.time.LocalDate (or java.time.LocalDateTime) in your 
         * task objects. Accept dates in a format such as yyyy-mm-dd format (e.g., 2019-10-15) 
         * and print in a different format such as MMM dd yyyy e.g., (Oct 15 2019).
         */

        // When add deadline task, user enters the date in yyyy-mm-dd HHmm format (e.g., 2019-10-15 1800)
        // parser validates the date & format, then converts the date to MMM dd yyyy HHmm format (e.g., Oct 15 2019 1800) then saves to taskList and tasks.txt
        // TODO: Restrictions: Deadline must have date, but time can be optional (or if no time given, assume 0000 hours). Event must have both date and time 
        // deadline return book /by 2019-10-15 1800

        String dueString = matcher.group("due").trim();
        // System.out.println("localdatetime " + DateTimeParser.parseDateTime(dueString));
        // System.out.println("LocalDateTime due " + dueString);
        LocalDateTime due = DateTimeParser.parseInputDeadlineDateTime(dueString);
        // System.out.println("LocalDateTime due " + due);
        // LocalDateTime due = LocalDateTime.parse(matcher.group("due").trim());

        // check if dates in a format such as yyyy-mm-dd format (e.g., 2019-10-15)

        return new DeadlineCommand(description.trim(), due);
    }

    private static Command prepareEvent(String args) throws CommandException {

        final String MESSAGE_EMPTY_STARTDATETIME_PRE = "The task is missing a start date/time.";
        final String MESSAGE_EMPTY_ENDDATETIME_PRE = "The task is missing an end date/time.";
        final String FROM_KEYWORD = "/from";
        final String TO_KEYWORD = "/to";

        // Check if args (description) is empty
        checkEmptyDescriptionForTaskCommand(args, EventCommand.COMMAND_WORD, Messages.MESSAGE_EMPTY_DESCRIPTION_PRE, EventCommand.MESSAGE_USAGE);

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

    private static Command prepareShow(String args) throws CommandException {
        LocalDate date = DateTimeParser.parseInputShowDate(args);
        return new ShowCommand(date);
    }
}
