package mochi.commands;

import mochi.common.DialogMessages;
import mochi.common.exception.ExceptionMessages;
import mochi.common.exception.MochiException;
import mochi.tasks.Task;
import mochi.tasks.TaskList;
import mochi.ui.Ui;

public class AddTaskCommand extends Command {
    private final Task task;
    private boolean state;
    private boolean isLoading = false;

    public AddTaskCommand(TaskList taskList, Task task) {
        super(taskList);
        this.task = task;
    }

    public AddTaskCommand(TaskList taskList, Task task, boolean state) {
        super(taskList);
        this.task = task;
        this.state = state;
        this.isLoading = true;
    }

    @Override
    public void execute() {
        if (isLoading) {
            try {
                taskList.addTask(task);
                if (state) {
                    markTaskById(taskList.getTaskIdByTask(task));
                }
                Ui.response(" "
                        + DialogMessages.TASK_LOADED.getValue()
                        + "  " + task.getName()
                );
            } catch (MochiException e) {
                Ui.response(e.getMessage());
            }
        } else {
            try {
                taskList.addTask(this.task);
                Ui.response(" " + DialogMessages.TASK_ADDED.getValue() + System.lineSeparator() + "  " + task);
                Ui.response(" Now you have " + taskList.getTaskListSize() + " tasks in the list");
            } catch (MochiException e) {
                handleException(e);
            }
        }
    }

    private void markTaskById(Integer id) {
        try {
            taskList.markTask(id);
        } catch (NumberFormatException e) {
            Ui.response(ExceptionMessages.NUMBER_FORMAT_EXCEPTION);
        } catch (IndexOutOfBoundsException e) {
            Ui.response(ExceptionMessages.TASK_ID_NOT_FOUND);
        } catch (MochiException e) {
            Ui.response(e.getMessage());
        }
    }
}