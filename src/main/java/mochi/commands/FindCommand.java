package mochi.commands;

import mochi.tasks.TaskList;

public class FindCommand extends Command {
    private final String token;

    public FindCommand(TaskList taskList, String token) {
        super(taskList);
        this.token = token;
    }

    @Override
    public void execute() {
        taskList.printTaskListByName(this.token);
    }
}