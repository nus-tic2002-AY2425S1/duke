package mochi.commands;

import mochi.common.DialogMessages;
import mochi.common.Utils;
import mochi.tasks.TaskList;
import mochi.ui.Ui;

import java.util.Arrays;

public class MassDeleteTaskCommand extends Command {
    private final int[] idx;

    public MassDeleteTaskCommand(TaskList taskList, String token) {
        super(taskList);
        this.idx = Utils.splitStringToIntArray(token, ",");
    }

    @Override
    public void execute() {
        try {
            taskList.massDeleteTask(idx);
            Ui.response(DialogMessages.DELETE_TASK_MASS.getValue() + System.lineSeparator() + Arrays.toString(idx));
        } catch (Exception e) {
            handleException(e);
        }
    }
}