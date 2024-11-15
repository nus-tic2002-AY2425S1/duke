package mochi.commands;

import mochi.common.DialogMessages;
import mochi.tasks.TaskList;
import mochi.ui.Ui;

public class DeleteTaskCommand extends Command {
    private final int index;

    public DeleteTaskCommand(TaskList taskList, String[] token) {
        super(taskList);
        this.index = Integer.parseInt(token[1]) - 1;
    }

    @Override
    public void execute() {
        try {
            String tempTask = taskList.getTaskById(this.index).toString();
            taskList.deleteTask(this.index);
            Ui.response(DialogMessages.DELETE_TASK.getValue() + System.lineSeparator() + "  " + tempTask);
            Ui.response(" Now you have " + taskList.getTaskListSize() + " tasks in the list");
        } catch (Exception e) {
            handleException(e);
        }
    }
}