package josbot.parser;

import josbot.JosBotException;
import josbot.commands.AddCommand;
import josbot.commands.Command;
import josbot.commands.CommandList;
import josbot.commands.DeleteCommand;
import josbot.commands.ExitCommand;
import josbot.commands.FindCommand;
import josbot.commands.InvalidCommand;
import josbot.commands.ListCommand;
import josbot.commands.MarkCommand;
import josbot.commands.ReminderCommand;
import josbot.commands.TagCommands;

/**
 * Class used to parse Command
 */


public class CommandParser {
    static CommandList cmdList;

    /**
     * Returns command class based on the input command (fullCommand)
     * executed by the user
     *
     * @param fullCommand Command executed by user
     * @return command child class
     * @throws JosBotException
     */

    public static Command parse(String fullCommand) throws JosBotException {
        String fullCommandType = fullCommand.split(" ")[0];
        String description = fullCommand.replace(fullCommandType, "").trim();

        try {
            cmdList = CommandList.valueOf(fullCommandType.toUpperCase());

            switch (cmdList) {
            case TAG:
            case UNTAG:
                if (checkError(description)) {
                    return new TagCommands(fullCommandType, description);
                } else {
                    return new InvalidCommand("invalid_tag");
                }
            case REMINDER:
                return new ReminderCommand(fullCommandType, description);
            case FIND:
                return new FindCommand(fullCommandType, description);
            case DELETE:
                if (checkError(description)) {
                    return new DeleteCommand(fullCommandType, description);
                } else {
                    return new InvalidCommand("missing_mark_number");
                }
            case LIST:
                return new ListCommand(fullCommandType, description);
            case MARK:
            case UNMARK:
                if (checkError(description)) {
                    return new MarkCommand(fullCommandType, description);
                } else {
                    return new InvalidCommand("missing_mark_number");
                }
            case TODO:
            case EVENT:
            case DEADLINE:
                if (checkError(description)) {
                    return new AddCommand(fullCommandType, description);
                } else {
                    return new InvalidCommand("missing_description");
                }
            case BYE:
                return new ExitCommand(fullCommandType, description);
            default:
                return new InvalidCommand("invalid_command");
            }
        } catch (IllegalArgumentException e) {
            return new InvalidCommand("invalid_command");
        }
    }

    private static boolean checkError(String description) {
        return !description.equals("") && description != null && !description.isEmpty();
    }


}
