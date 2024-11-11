package mochi.parsers;

import mochi.commands.*;
import mochi.tasks.*;
import mochi.ui.*;
import mochi.common.*;
import mochi.common.exception.*;

import java.time.LocalDateTime;
import java.util.Objects;

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
        if (token.length == 1) {
          cmd.listTask();
        }
        else {
          String tmpDate = "";
          if (Objects.equals(token[2], "/before")) {
            tmpDate = Utils.trimStringArrayWithStartEnd(token,"/before",""," ");
          }
          if (Objects.equals(token[2], "/after")) {
            tmpDate = Utils.trimStringArrayWithStartEnd(token,"/after",""," ");
          }
          LocalDateTime isDate = DateTime.parse(tmpDate);
          if (isDate != null) {
            cmd.listTask(token[1],token[2],tmpDate);
          }
          else {
            Ui.response(tmpDate + " " + DialogMessages.INVALID_TASK.getValue());
          }
        }

        break;
      case DEADLINE:
        String d_name = Utils.trimStringArrayWithStartEnd(token,"deadline","/by"," ");
        String by = Utils.trimStringArrayWithStartEnd(token,"/by",""," ");
        LocalDateTime isDate = DateTime.parse(by);
        if (isDate != null) {
          cmd.addTask(new Deadline(d_name,by));
        } else {
          Ui.response("/by " + DialogMessages.INVALID_TASK.getValue());
        }
        break;
      case EVENT:
        String e_name = Utils.trimStringArrayWithStartEnd(token,"event","/from"," ");
        String from = Utils.trimStringArrayWithStartEnd(token,"/from","/to"," ");
        String to = Utils.trimStringArrayWithStartEnd(token,"/to",""," ");

        LocalDateTime isFromDate = DateTime.parse(from);
        LocalDateTime isToDate = DateTime.parse(to);
        if ((isFromDate != null) && (isToDate != null)) {
          cmd.addTask(new Event(e_name,from,to));
        } else if (isFromDate == null) {
          Ui.response("/from " + DialogMessages.INVALID_TASK.getValue());
        } else {
          Ui.response("/to " + DialogMessages.INVALID_TASK.getValue());
        }
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
