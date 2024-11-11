package josbot.parser;

import josbot.JosBotException;
import josbot.commands.*;

public class Parser {


    public static Command parse(String fullCommand) throws JosBotException {
        //Command command = new Command();
        String fullCommandType = fullCommand.split(" ")[0];
        String description = fullCommand.replace(fullCommandType, "").trim();
        fullCommandType = fullCommandType.toLowerCase();


        switch(fullCommandType){
            case "delete":
                return parseDelete(fullCommandType, description);
            case "list":
                return new ListCommand();
            case "mark":
            case "unmark":
                return parseMark(fullCommandType, description);
            case "todo":
            case "event":
            case "deadline":
                return parseAdd(fullCommandType, description);
            case "bye":
                return new ExitCommand();
            default:
                return new InvalidCommand("invalid_command");
        }
    }

    private static Command parseAdd(String fullCommandType, String description) throws JosBotException {
        if(description.equals("") || description == null || description.isEmpty()){
            return new InvalidCommand("missing_description");
        }else
        {
            Command add = new AddCommand();
            add.setCommandType(fullCommandType, description);
            return add;
        }
    }
    private static Command parseMark(String fullCommandType, String description) throws JosBotException {
        if(description.equals("") || description == null || description.isEmpty()){
            return new InvalidCommand("missing_mark_number");
        }else
        {
            Command mark = new MarkCommand();
            mark.setCommandType(fullCommandType, description);
            return mark;
        }
    }

    private static Command parseDelete(String fullCommandType, String description) throws JosBotException {
        //potential enhancement to allow multiple delete
        if(description.equals("") || description == null || description.isEmpty()){
            return new InvalidCommand("missing_mark_number");
        }else
        {
            Command delete = new DeleteCommand();
            delete.setCommandType(fullCommandType, description);
            return delete;
        }
    }
}
