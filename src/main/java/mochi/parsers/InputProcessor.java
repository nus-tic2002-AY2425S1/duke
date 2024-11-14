package mochi.parsers;

import mochi.commands.*;
import mochi.tasks.*;
import mochi.ui.*;
import mochi.common.*;
import mochi.common.exception.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputProcessor {
  private final TaskList taskList;
  public InputProcessor(TaskList taskList) {
    this.taskList = taskList;
  }
  public void processInput(String input) throws MochiException {
    processInput(input, " ");
  }
  public void processInput(String input,String delimiter) throws MochiException {
    String[] token = input.split(delimiter);
    CommandEnum cmdType = CommandEnum.getValue(token[0]);
    Command cmd = null;
    switch (cmdType) {
      case DELETE:
      if ((token.length > 1) && token[1].startsWith("[")) {
        String regex = "^\\[\\d+(,\\d+)*\\]$";
        if (token[1].matches(regex)) {
          cmd = new MassDeleteTaskCommand(taskList, token[1].replaceAll("[\\[\\]]", ""));
        } else {
          Ui.response(DialogMessages.INVALID_MULTI_PATTERN.getValue());
        }
      }
      else {
        cmd = new DeleteTaskCommand(taskList, token);
      }
      break;
      case MARK:
        if ((token.length > 1) && token[1].startsWith("[")) {
          String regex = "^\\[\\d+(,\\d+)*\\]$";
          if (token[1].matches(regex)) {
            cmd = new MassMarkTaskCommand(taskList, token[1].replaceAll("[\\[\\]]", ""));
          } else {
            Ui.response(DialogMessages.INVALID_MULTI_PATTERN.getValue());
          }
        }
        else {
          cmd = new MarkTaskCommand(taskList, token);
        }
        break;
      case UNMARK:
        if ((token.length > 1) && token[1].startsWith("[")) {
          String regex = "^\\[\\d+(,\\d+)*\\]$";
          if (token[1].matches(regex)) {
            cmd = new MassUnMarkTaskCommand(taskList, token[1].replaceAll("[\\[\\]]", ""));
          } else {
            Ui.response(DialogMessages.INVALID_MULTI_PATTERN.getValue());
          }
        }
        else {
          cmd = new UnMarkTaskCommand(taskList, token);
        }
        break;
      case BYE:
        Ui.response(DialogMessages.BYE.getValue());
        break;
      case LIST:
        if (token.length < 3) {
          cmd = new ListTaskCommand(taskList,token);
        }
        else {
          String tmpDate = "";
          if (Objects.equals(token[2], "/before")) {
            tmpDate = Utils.trimStringArrayWithStartEnd(token, "/before", "", " ");
          }
          if (Objects.equals(token[2], "/after")) {
            tmpDate = Utils.trimStringArrayWithStartEnd(token, "/after", "", " ");
          }
          LocalDateTime isDate = DateTime.parse(tmpDate);
          if (isDate != null) {
            cmd = new ListTaskCommand(taskList, token);
          }
        }

        break;
      case DEADLINE:
        String d_name = Utils.trimStringArrayWithStartEnd(token,"deadline","/by"," ");
        String by = Utils.trimStringArrayWithStartEnd(token,"/by",""," ");
        LocalDateTime isDate = DateTime.parse(by);
        if (isDate != null) {
          cmd = new AddTaskCommand(this.taskList,new Deadline(d_name,by));
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
          cmd = new AddTaskCommand(this.taskList,new Event(e_name,from,to));
        } else if (isFromDate == null) {
          Ui.response("/from " + DialogMessages.INVALID_TASK.getValue());
        } else {
          Ui.response("/to " + DialogMessages.INVALID_TASK.getValue());
        }
        break;
      case TODO:
        String t_name = Utils.trimStringArrayWithStartEnd(token,"todo",""," ");
        cmd = new AddTaskCommand(this.taskList,new Todo(t_name));
        break;
      default:
        throw new MochiException(DialogMessages.INPUT_UNKNOWN.getValue());
    }
    if(cmd != null) {
      cmd.execute();
    }
  }
}
