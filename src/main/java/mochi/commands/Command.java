package mochi.commands;

import mochi.common.exception.ExceptionMessages;
import mochi.common.exception.MochiException;
import mochi.tasks.TaskList;
import mochi.ui.Ui;

public abstract class Command {
  protected final TaskList taskList;
  public Command(TaskList taskList) {
    this.taskList = taskList;
  }
  // Abstract execute method to be implemented by subclasses
  public abstract void execute();
  // Common exception handling
  protected void handleException(Exception e) {
    if (e instanceof MochiException) {
      Ui.response(e.getMessage());
    } else if (e instanceof NumberFormatException) {
      Ui.response(ExceptionMessages.NUMBER_FORMAT_EXCEPTION);
    } else if (e instanceof IndexOutOfBoundsException) {
      Ui.response(ExceptionMessages.TASK_ID_NOT_FOUND);
    }
  }
}