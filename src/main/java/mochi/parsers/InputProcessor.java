package mochi.parsers;

import mochi.commands.*;
import mochi.tasks.*;
import mochi.ui.*;
import mochi.common.*;
import mochi.common.exception.*;

public class InputProcessor {
  private Command cmd;
  public InputProcessor(TaskList taskList) {
    cmd = new Command(taskList);
  }
  public void processInput(String input) throws MochiException {
    processInput(input," ");
  }
  public void processInput(String input,String delimiter) throws MochiException {
    String[] token = input.split(delimiter);
    CommandEnum command = CommandEnum.getValue(token[0]);
    switch (command) {
      case DELETE:
        cmd.deleteTask(token);
        break;
      case MARK:
        cmd.markTask(token);
        break;
      case UNMARK:
        cmd.unMarkTask(token);
        break;
      case BYE:
        Ui.response(DialogMessages.BYE.getValue());
        break;
      case LIST:
        cmd.listTask();
        break;
      case DEADLINE:
        String d_name = Utils.trimStringArrayWithStartEnd(token,"deadline","/by"," ");
        String by = Utils.trimStringArrayWithStartEnd(token,"/by",""," ");
        cmd.addTask(new Deadline(d_name,by));
        break;
      case EVENT:
        String e_name = Utils.trimStringArrayWithStartEnd(token,"event","/from"," ");
        String from = Utils.trimStringArrayWithStartEnd(token,"/from","/to"," ");
        String to = Utils.trimStringArrayWithStartEnd(token,"/to",""," ");
        cmd.addTask(new Event(e_name,from,to));
        break;
      case TODO:
        String t_name = Utils.trimStringArrayWithStartEnd(token,"todo",""," ");
        cmd.addTask(new Todo(t_name));
        break;
      default:
        throw new MochiException(DialogMessages.INPUT_UNKNOWN.getValue());
    }
  }
}
