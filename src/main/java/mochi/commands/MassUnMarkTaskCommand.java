package mochi.commands;

import mochi.common.DialogMessages;
import mochi.common.Utils;
import mochi.tasks.TaskList;
import mochi.ui.Ui;

import java.util.Arrays;

public class MassUnMarkTaskCommand extends Command {
    private final int[] idx;

    public MassUnMarkTaskCommand(TaskList taskList, String token) {
        super(taskList);
        this.idx = Utils.splitStringToIntArray(token, ",");
    }

    @Override
    public void execute() {
        try {
            for (int i : idx)
                taskList.unmarkTask(i - 1);
            Ui.response(DialogMessages.MARK_TASK_MASS.getValue() + System.lineSeparator() + Arrays.toString(idx));
        } catch (Exception e) {
            handleException(e);
        }
    }
}