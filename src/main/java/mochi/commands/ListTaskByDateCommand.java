package mochi.commands;

import mochi.tasks.TaskList;

import java.time.LocalDate;

public class ListTaskByDateCommand extends Command {

  private final LocalDate date;

  public ListTaskByDateCommand(TaskList taskList, LocalDate date) {
    super(taskList);
    this.date = date;
  }

  @Override
  public void execute() {
    taskList.printTaskListByDate(this.date);
  }
}