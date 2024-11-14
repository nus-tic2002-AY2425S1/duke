package starkchatbot.userui;

import starkchatbot.taskmanager.TaskList;

import java.util.Arrays;


/**
 * The InputParser class provides a method for parsing user input commands
 * and performing actions on a TaskList.
 */
public class InputParser {

    /**
     * Parses and processes a user input command, performing the appropriate action
     * on the provided TaskList.
     *
     * @param taskList the {@code TaskList} object on which actions will be performed
     * @param query    the user input string to be parsed and processed
     * @throws StarkException.InvalidCommandException if the input command is invalid
     * @throws NumberFormatException                  if the input does not contain a valid task number
     */
    public static void userInputParser(TaskList taskList, String query) {
        try {
            String[] queryTokens = Tokenize.tokenize(query); // for checking the input are valid

            if (queryTokens[0].equalsIgnoreCase("list")) {
                taskList.printAllTasks();
            } else if (queryTokens[0].equalsIgnoreCase("mark") || queryTokens[0].equalsIgnoreCase("unmark") || queryTokens[0].equalsIgnoreCase("delete")) {
                taskList.editTask(queryTokens[0], Integer.parseInt(queryTokens[1]));
            } else if (queryTokens[0].equalsIgnoreCase("find")) {
                String detail = String.join(" ", Arrays.asList(queryTokens).subList(1, queryTokens.length));
                taskList.findTasks(detail);
            } else if (queryTokens[0].equalsIgnoreCase("tentative")) {
                TentativeEventHandler.tentativeEvent(taskList);
            } else {
                taskList.addTask(queryTokens[0], String.join(" ", query));
            }
        } catch (Exception e) {
            throw new StarkException.InvalidCommandException(e.getMessage());
        }
    }

}
