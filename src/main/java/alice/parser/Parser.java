package alice.parser;

import alice.command.*;

import java.time.LocalDate;
import java.util.*;

/**
 * <h1>Parser</h1>
 * The Parser class parses the input given
 * by the user into the program and behaves
 * according to the input.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public class Parser {

    public static Command parse(String fullCommand){
        ArrayList<String> instruction = new ArrayList<>(Arrays.asList(fullCommand.split(" ")));
        String action = instruction.get(0);
        instruction.remove(0);

        switch (action) {
            case "bye":
                return new ExitCommand();
            case "list":
                if (instruction.isEmpty())
                    return new ListCommand();
                else
                    return new ListCommand(String.join(" ", instruction));
            case "find":
                return new FindCommand(action, String.join(" ", instruction));
            case "delete":
                try {
                    return new DeleteCommand(Integer.parseInt(instruction.get(0))-1);
                } catch (ArrayIndexOutOfBoundsException e){
                    return new IncorrectCommand();
                } catch (NumberFormatException e){
                    return new DeleteCommand(action, String.join(" ", instruction));
                }
            case "mark":
                try {
                    return new MarkCommand(Integer.parseInt(instruction.get(0))-1, true);
                } catch (ArrayIndexOutOfBoundsException e){
                    return new IncorrectCommand();
                }
            case "unmark":
                try {
                    return new MarkCommand(Integer.parseInt(instruction.get(0))-1, false);
                } catch (ArrayIndexOutOfBoundsException e){
                    return new IncorrectCommand();
                }
            case "todo", "deadline", "event":
                return new AddCommand(action, String.join(" ", instruction));
            case "help":
                return new HelpCommand();
            default:
                return new UnknownCommand();
        }
    }
}
