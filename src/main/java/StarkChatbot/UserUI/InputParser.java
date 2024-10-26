package StarkChatbot.UserUI;

import StarkChatbot.TaskManager.TaskList;

public class InputParser {

    public static void userInputParser(TaskList taskList, String query){

        String[] queryTokens = Tokenize.tokenize(query); // for checking the input are valid

        if (queryTokens[0].equalsIgnoreCase("list")) {
            taskList.printAllTasks();
        } else if (queryTokens[0].equalsIgnoreCase("mark")
                || queryTokens[0].equalsIgnoreCase("unmark")
                || queryTokens[0].equalsIgnoreCase("delete")) {
            taskList.statusChange(queryTokens[0], Integer.parseInt(queryTokens[1]));
        } else {
            taskList.addTask(queryTokens[0], String.join(" ", query));
        }
    }

}
