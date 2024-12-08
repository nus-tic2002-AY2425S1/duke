package mochi.commands;

import mochi.common.DialogMessages;
import mochi.tasks.TaskList;
import mochi.ui.Ui;

public class UnMarkTaskCommand extends Command {
    private final int index;

    public UnMarkTaskCommand(TaskList taskList, String[] token) {
        super(taskList);
        this.index = Integer.parseInt(token[1]) - 1;
    }

    @Override
    public void execute() {
        try {
            taskList.unmarkTask(this.index);
            Ui.response(DialogMessages.UNMARK_TASK.getValue() + System.lineSeparator() + taskList.getTaskById(this.index).toString());
        } catch (Exception e) {
            handleException(e);
        }
    }
}