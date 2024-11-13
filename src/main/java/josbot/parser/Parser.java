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
            case "tag":
            case "untag":
                return parseTag(fullCommandType, description);
            case "reminder":
                return new ReminderCommand();
            case "find":
                return new FindCommand(description);
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

    private static Command parseTag(String fullCommandType, String description) throws JosBotException {
        String[] description_input = description.split(" ");
        if(description.equals("") || description == null || description.isEmpty()){
            return new InvalidCommand("invalid_tag");
        }
        else if(fullCommandType.equals("untag") && description_input.length != 1 || fullCommandType.equals("tag") && description_input.length != 2){
            return new InvalidCommand("invalid_tag");
        }
        else
        {
            Command tag = new TagCommands();
            tag.setCommandType(fullCommandType, description);
            return tag;
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
