package ruan.parser;

import ruan.command.*;
import ruan.exception.*;

public class Parser {

    public static Command parse(String input) throws RuanException {
        String trimmedInput = input.trim().toLowerCase();

        if (trimmedInput.equals("bye")) {
            return new ExitCommand();
        } else if (trimmedInput.startsWith("todo")) {
            return new AddCommand("todo", trimmedInput.replace("todo", "").trim());
        } else if (trimmedInput.startsWith("deadline")) {
            return new AddCommand("deadline", trimmedInput.replace("deadline", "").trim());
        } else if (trimmedInput.startsWith("event")) {
            return new AddCommand("event", trimmedInput.replace("event", "").trim());
        } else if (trimmedInput.startsWith("delete")) {
            return new DeleteCommand(Integer.parseInt(trimmedInput.split(" ")[1]) - 1);
        } else if (trimmedInput.startsWith("mark")) {
            return new SetDoneCommand(Integer.parseInt(trimmedInput.split(" ")[1]) - 1, true);
        } else if (trimmedInput.startsWith("unmark")) {
            return new SetDoneCommand(Integer.parseInt(trimmedInput.split(" ")[1]) - 1, false);
        } else if (trimmedInput.equals("list")) {
            return new ListCommand();
        } else {
            throw new RuanException(ErrorType.UNKNOWN_DESCRIPTION);
        }
    }
}
