package mochi.parsers;

import mochi.commands.*;
import mochi.common.*;
import mochi.common.exception.*;
import mochi.tasks.*;

public class LoadProcessor {
  private final Command cmd;
  public LoadProcessor(TaskList taskList) {
    cmd = new Command(taskList);
  }
  public void processInput(String input) throws MochiException {
    processInput(input," ");
  }
  public void processInput(String input,String delimiter) throws MochiException {
    if (delimiter.equals("||"))
      delimiter = "\\|\\|";
    String[] token = input.split(delimiter);
    TaskType taskTypes = TaskType.getValue(token[0]);
    switch (taskTypes) {
      case T:
        String t_name = token[2];
        cmd.addTask(new Todo(t_name),Boolean.parseBoolean(token[1]));
        break;
      case D:
        String d_name = token[2];
        String by = token[3];
        cmd.addTask(new Deadline(d_name,by),Boolean.parseBoolean(token[1]));
        break;
      case E:
        String e_name = token[2];
        String from = token[3];
        String to = token[4];
        cmd.addTask(new Event(e_name,from,to),Boolean.parseBoolean(token[1]));
        break;
      default:
        throw new MochiException(DialogMessages.INPUT_UNKNOWN.getValue());
    }
  }
}
