package wkduke.command;

import wkduke.parser.TimeParser;
import wkduke.storage.Storage;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ListOnCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " /on {dateTime}";
    public static final String MESSAGE_SUCCESS = "Here are the tasks in your list on '%s':";
    public static final String MESSAGE_FAILED = "Your task list is currently empty on '%s'.";
    protected LocalDateTime on;

    public ListOnCommand(LocalDateTime on) {
        this.on = on;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getAllTaskOnDate(on);
        if (tasks.isEmpty()) {
            ui.printMessages(
                    String.format(MESSAGE_FAILED, on.format(TimeParser.CLI_FORMATTER))
            );
            return;
        }

        List<String> messages = new ArrayList<>();
        messages.add(
                String.format(MESSAGE_SUCCESS, on.format(TimeParser.CLI_FORMATTER))
        );
        for (Task task : tasks) {
            messages.add(String.format("%d.%s", taskList.getTaskIndex(task) + 1, task));
        }
        ui.printMessages(messages.toArray(new String[0]));
    }
}
