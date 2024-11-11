package mochi.commands;

import mochi.tasks.*;
import mochi.ui.*;
import mochi.common.*;
import mochi.common.exception.*;

public class Command {
  private final TaskList taskList;
  public Command(TaskList taskList) {
    this.taskList = taskList;
  }
  public void addTask(Task task) {
    try {
      taskList.addTask(task);
      Ui.response( " "
        + DialogMessages.TASK_ADDED.getValue()
        + System.lineSeparator()
        + "  " + task
      );
      Ui.response(" Now you have " + taskList.getTaskListSize() + " tasks in the list");
    } catch (MochiException e) {
      Ui.response(e.getMessage());
    }
  }
  public void addTask(Task task,boolean state) {
    try {
      taskList.addTask(task);
      if (state)
        markTaskById(taskList.getTaskIdByTask(task));
      Ui.response( " "
        + DialogMessages.TASK_LOADED.getValue()
        + "  " + task.getName()
      );

    } catch (MochiException e) {
      Ui.response(e.getMessage());
    }
  }

  public void listTask() {
    taskList.printTaskList();
  }
  public void markTask(String[] token) {
    try {
      int i = Integer.parseInt(token[1]) - 1;
      taskList.markTask(i);
      Ui.response(DialogMessages.MARK_TASK.getValue()
        + System.lineSeparator()
        + taskList.getTaskById(i).toString()
      );
    } catch (NumberFormatException e) {
      Ui.response(ExceptionMessages.NUMBER_FORMAT_EXCEPTION);
    } catch (IndexOutOfBoundsException e) {
      Ui.response(ExceptionMessages.TASK_ID_NOT_FOUND);
    } catch (MochiException e) {
      Ui.response(e.getMessage());
    }
  }
  public void markTaskById(Integer id) {
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
  public void deleteTask(String[] token) {
    try {
      int i = Integer.parseInt(token[1]) - 1;
      // Task will get deleted, store into var first
      String tempTask = taskList.getTaskById(i).toString();
      taskList.deleteTask(i);
      Ui.response(DialogMessages.DELETE_TASK.getValue()
        + System.lineSeparator()
        + "  " + tempTask
      );
      Ui.response(" Now you have " + taskList.getTaskListSize() + " tasks in the list");
    } catch (IndexOutOfBoundsException e) {
      Ui.response(ExceptionMessages.TASK_ID_NOT_FOUND);
    } catch (MochiException e) {
      Ui.response(e.getMessage());
    }
  }
  public void unMarkTask(String[] token) {
    try {
      int i = Integer.parseInt(token[1]) - 1;
      taskList.unmarkTask(i);
      Ui.response(DialogMessages.UNMARK_TASK.getValue()
        + System.lineSeparator()
        + taskList.getTaskById(i).toString()
      );
    } catch (NumberFormatException e) {
      Ui.response(ExceptionMessages.NUMBER_FORMAT_EXCEPTION);
    } catch (IndexOutOfBoundsException e) {
      Ui.response(ExceptionMessages.TASK_ID_NOT_FOUND);
    } catch (MochiException e) {
      Ui.response(e.getMessage());
    }
  }
}
