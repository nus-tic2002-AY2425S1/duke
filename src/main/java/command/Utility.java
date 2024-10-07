package command;

import exception.DukeException;
import tasks.Task;

import java.util.List;

public class Utility {
    public static int parseTaskIndex(String userInput) throws DukeException {
        try {
            String[] parts = userInput.split(" ");
            if (parts.length < 2) {
                throw new DukeException("OOPS!! Please provide a task number!");
            }
            return Integer.parseInt(parts[1]) - 1;
        }  catch (NumberFormatException e) {
            throw new DukeException("OOPS!! The task number must be a valid integer!");
        }
    }

    public static boolean isValidIndex(int taskIndex, List<Task> tasks){
        return taskIndex >= 0 && taskIndex < tasks.size();
    }


}
