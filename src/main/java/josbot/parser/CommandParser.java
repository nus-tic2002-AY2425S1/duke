package josbot.parser;

import josbot.JosBotException;
import josbot.commands.*;

public class CommandParser {

    public static Command parse(String fullCommand) throws JosBotException {
        String fullCommandType = fullCommand.split(" ")[0];
        String description = fullCommand.replace(fullCommandType, "").trim();
        fullCommandType = fullCommandType.toLowerCase();


        switch(fullCommandType){
            case "tag":
            case "untag":
                return parseTag(fullCommandType, description);
            case "reminder":
                return new ReminderCommand(fullCommandType, description);
            case "find":
                return new FindCommand(fullCommandType, description);
            case "delete":
                return parseDelete(fullCommandType, description);
            case "list":
                return new ListCommand(fullCommandType, description);
            case "mark":
            case "unmark":
                return parseMark(fullCommandType, description);
            case "todo":
            case "event":
            case "deadline":
                return parseAdd(fullCommandType, description);
            case "bye":
                return new ExitCommand(fullCommandType, description);
            default:
                return new InvalidCommand("invalid_command");
        }
    }

    private static Command parseAdd(String fullCommandType, String description) throws JosBotException {

        if(description.equals("") || description == null || description.isEmpty()){
            return new InvalidCommand("missing_description");
        }else
        {
            Command add = new AddCommand(fullCommandType, description);
            //add.setCommandType(fullCommandType, description);
            return add;
        }
    }
    private static Command parseMark(String fullCommandType, String description) throws JosBotException {
        if(description.equals("") || description == null || description.isEmpty()){
            return new InvalidCommand("missing_mark_number");
        }else
        {
            return new MarkCommand(fullCommandType, description);
        }
    }

    private static Command parseTag(String fullCommandType, String description) throws JosBotException {
        String[] description_input = description.split(" ");
        if(description.equals("") || description == null || description.isEmpty() || (fullCommandType.equals("untag") && description_input.length != 1) || (fullCommandType.equals("tag") && description_input.length != 2)){
            return new InvalidCommand("invalid_tag");
        }
        else
        {
            return new TagCommands(fullCommandType, description);
        }
    }

    private static Command parseDelete(String fullCommandType, String description) throws JosBotException {
        if(description.equals("") || description == null || description.isEmpty()){
            return new InvalidCommand("missing_mark_number");
        }else
        {
            return new DeleteCommand(fullCommandType, description);
        }
    }

    private boolean checkError(String description){
        if(description.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }



}
