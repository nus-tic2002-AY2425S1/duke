package mochi.commands;

import mochi.common.DialogMessages;
import mochi.tasks.TaskList;
import mochi.ui.Ui;

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