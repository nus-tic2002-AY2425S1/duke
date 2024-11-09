package snitch;

import snitch.command.*;
import snitch.SnitchException;

public class Parser {
    public static Command parse(String input) throws SnitchException {
        if (input.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return new AddCommand(input);
        } else if (input.startsWith("delete")) {
            try {
                return new DeleteCommand(Integer.parseInt(input.substring(7).trim()));
            } catch (NumberFormatException e) {
                throw new SnitchException("Invalid task number format. Please enter a valid number.");
            }
        } else {
            throw new SnitchException("Come on man!!! I don't know what that means.");
        }
    }
}
