package mochi.commands;

import mochi.common.DialogMessages;
import mochi.common.Utils;
import mochi.tasks.TaskList;
import mochi.ui.Ui;
import java.util.Arrays;

public class MassMarkTaskCommand extends Command {
  private final int[] idx;
  public MassMarkTaskCommand(TaskList taskList, String token) {
    super(taskList);
    this.idx = Utils.splitStringToIntArray(token,",");
  }
  @Override
  public void execute() {
    try {
      for (int i : idx)
        taskList.markTask(i-1);
      Ui.response(DialogMessages.UNMARK_TASK_MASS.getValue() + System.lineSeparator() + Arrays.toString(idx));
    } catch (Exception e) {
      handleException(e);
    }
  }
}