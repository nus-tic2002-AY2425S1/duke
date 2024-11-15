package mochi.commands;

import mochi.common.*;
import mochi.ui.*;
import mochi.tasks.*;

import java.util.Arrays;

public class MassUnMarkTaskCommand extends Command {

  private final int[] idx;

  public MassUnMarkTaskCommand(TaskList taskList, String token) {
    super(taskList);
    this.idx = Utils.splitStringToIntArray(token,",");
  }

  @Override
  public void execute() {
    try {
      for (int i : idx)
        taskList.unmarkTask(i-1);
      Ui.response(DialogMessages.MARK_TASK_MASS.getValue() + System.lineSeparator() + Arrays.toString(idx));
    } catch (Exception e) {
      handleException(e);
    }
  }
}