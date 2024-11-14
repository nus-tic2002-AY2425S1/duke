package mochi.commands;

import mochi.ui.*;
import mochi.tasks.*;
import mochi.common.*;
public class ListTaskCommand extends Command {

  private final String[] token;

  public ListTaskCommand(TaskList taskList, String[] token) {
    super(taskList);
    this.token = token;
  }

  @Override
  public void execute() {
    if (token.length == 1) {
      taskList.printTaskList();

    } else if (token.length == 2) {
      if ("event".equals(token[1])) {
        taskList.printTaskList("E", "", "");
      } else if ("deadline".equals(token[1])) {
        taskList.printTaskList("D", "", "");
      } else if ("todo".equals(token[1])) {
        taskList.printTaskList("T", "", "");
      }
    }
    else {
      String type = token[1];
      String op = token[2];
      String date = token[3];
      if ("event".equals(type)) {
        taskList.printTaskList("E", op, date);
      } else if ("deadline".equals(type)) {
        taskList.printTaskList("D", op, date);
      } else {
        Ui.response(DialogMessages.LIST_TASK_EMPTY.getValue());
      }
    }
  }
}