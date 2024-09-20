package command;

import exception.DukeException;
import tasks.Task;

import java.util.List;

public interface CommandHandler {
    void handle(String userInput, List<Task> tasks) throws DukeException;
}
