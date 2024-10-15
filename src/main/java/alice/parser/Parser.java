package alice.parser;

import alice.command.*;

import java.util.*;

public class Parser {

    public static Command parse(String fullCommand){
        ArrayList<String> instruction = new ArrayList<>(Arrays.asList(fullCommand.split(" ")));
        //break the alice.command into args
        String action = instruction.getFirst();
        instruction.removeFirst();

        switch (action) {
            case "bye":
                return new ExitCommand();
            case "list":
                if (instruction.isEmpty())
                    return new ListCommand();
                else
                    return new ListCommand(action, String.join(" ", instruction));
            case "delete":
                try {
                    return new DeleteCommand(Integer.parseInt(instruction.getFirst())-1);
                } catch (ArrayIndexOutOfBoundsException e){
                    return new IncorrectCommand();
                }
            case "mark":
                try {
                    return new MarkCommand(Integer.parseInt(instruction.getFirst())-1, true);
                } catch (ArrayIndexOutOfBoundsException e){
                    return new IncorrectCommand();
                }
            case "unmark":
                try {
                    return new MarkCommand(Integer.parseInt(instruction.getFirst())-1, false);
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
