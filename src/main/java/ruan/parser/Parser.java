package ruan.parser;

import ruan.command.*;
import ruan.exception.*;
import ruan.constant.Constants;

/**
 * Represents parser that processes user input and returns the appropriate command
 * Parser interprets the user input and creates the relevant command to execute
 */

public class Parser {
    /**
     * Parses the user input and returns the appropriate command
     * This method interprets the input string to determine which command the user wants to execute
     * @param input The user input as a string
     * @return The command that corresponds to the user's input
     * @throws RuanException If the input does not match any known commands
     */
    public static Command parse(String input) throws RuanException {
        String trimmedInput = input.trim().toLowerCase();

        if (trimmedInput.equals(Constants.BYE_COMMAND)) {
            return new ExitCommand();
        } else if (trimmedInput.startsWith(Constants.TODO_COMMAND)) {
            return new AddCommand(Constants.TODO_COMMAND, trimmedInput.replace(Constants.TODO_COMMAND, "").trim());
        } else if (trimmedInput.startsWith(Constants.DEADLINE_COMMAND)) {
            return new AddCommand(Constants.DEADLINE_COMMAND, trimmedInput.replace(Constants.DEADLINE_COMMAND, "").trim());
        } else if (trimmedInput.startsWith(Constants.EVENT_COMMAND)) {
            return new AddCommand(Constants.EVENT_COMMAND, trimmedInput.replace(Constants.EVENT_COMMAND, "").trim());
        } else if (trimmedInput.startsWith(Constants.DELETE_COMMAND)) {
            String[] data = trimmedInput.split(" ");
            if(data.length != 2){
                throw new RuanException(ErrorType.UNKNOWN_DESCRIPTION);
            }
            return new DeleteCommand(Integer.parseInt(data[1]) - 1);
        } else if (trimmedInput.startsWith(Constants.MARK_COMMAND)) {
            String[] data = trimmedInput.split(" ");
            if(data.length != 2){
                throw new RuanException(ErrorType.UNKNOWN_DESCRIPTION);
            }
            return new SetDoneCommand(Integer.parseInt(data[1]) - 1, true);
        } else if (trimmedInput.startsWith(Constants.UNMARK_COMMAND)) {
            String[] data = trimmedInput.split(" ");
            if(data.length != 2){
                throw new RuanException(ErrorType.UNKNOWN_DESCRIPTION);
            }
            return new SetDoneCommand(Integer.parseInt(data[1]) - 1, false);
        } else if (trimmedInput.startsWith(Constants.FIND_COMMAND)) {
            String keyword = trimmedInput.replaceFirst("(?i)"+Constants.FIND_COMMAND, "").trim();
            return new FindCommand(keyword);
        } else if (trimmedInput.equals(Constants.LIST_COMMAND)) {
            return new ListCommand();
        } else if (trimmedInput.equals(Constants.STATISTICS_COMMAND)) {
            return new StatsCommand();
        } else {
            throw new RuanException(ErrorType.UNKNOWN_DESCRIPTION);
        }
    }
}
