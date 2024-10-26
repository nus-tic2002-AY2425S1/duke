public class InputProcessor {
  private final TaskList taskList;
  public InputProcessor(TaskList t) {
    this.taskList = t;
  }
  public void addTask(Task task) {
    try {
      taskList.addTask(task);
      Conversation.response( " "
              + DialogMessages.TASK_ADDED.getValue()
              + System.lineSeparator()
              + "  " + task.toString()
      );
      Conversation.response(" Now you have " + taskList.getTaskListSize() + " tasks in the list");
    }
    catch (MochiException e) {
      Conversation.response(e.getMessage());
    }
    finally {
    }
  }
  public void markTask(String[] token) {
    try {
      int i = Integer.parseInt(token[1]) - 1;
      taskList.markTask(i);
      Conversation.response(DialogMessages.MARK_TASK.getValue()
              + System.lineSeparator()
              + taskList.getTaskById(i).toString()
      );
    } catch (NumberFormatException e) {
      Conversation.response(ExceptionMessages.NUMBER_FORMAT_EXCEPTION);
    }
    catch (IndexOutOfBoundsException e) {
      Conversation.response(ExceptionMessages.TASK_ID_NOT_FOUND);
    }
    catch (MochiException e) {
      Conversation.response(e.getMessage());
    }
  }
  public void deleteTask(String[] token) {
    try {
      int i = Integer.parseInt(token[1]) - 1;
      // Task will get deleted, store into var first
      String tempTask = taskList.getTaskById(i).toString();
      taskList.deleteTask(i);
      Conversation.response(DialogMessages.DELETE_TASK.getValue()
              + System.lineSeparator()
              + "  " + tempTask
      );
      Conversation.response(" Now you have " + taskList.getTaskListSize() + " tasks in the list");
    }
    catch (IndexOutOfBoundsException e) {
      Conversation.response(ExceptionMessages.TASK_ID_NOT_FOUND);
    }
    catch (MochiException e) {
      Conversation.response(e.getMessage());
    }
  }
  public void unMarkTask(String[] token) {
    try {
      int i = Integer.parseInt(token[1]) - 1;
      taskList.unmarkTask(i);
      Conversation.response(DialogMessages.UNMARK_TASK.getValue()
              + System.lineSeparator()
              + taskList.getTaskById(i).toString()
      );
    } catch (NumberFormatException e) {
      Conversation.response(ExceptionMessages.NUMBER_FORMAT_EXCEPTION);
    }
    catch (IndexOutOfBoundsException e) {
      Conversation.response(ExceptionMessages.TASK_ID_NOT_FOUND);
    }
    catch (MochiException e) {
      Conversation.response(e.getMessage());
    }
  }
  public static String trimStringArrayWithStartEnd(String[] s, String start, String end, String delimiter) {
    String body = "";
    boolean copy = false;
    for (String i : s) {
      if (!end.isEmpty() && i.equals(end))
        break;
      if(copy) {
        body += i + delimiter;
      }
      if (i.equalsIgnoreCase(start))
        copy = true;
    }
    return body.trim();
  }
  public void processInput(String input) throws MochiException {
    processInput(input," ");
  }
  public void processInput(String input,String delimiter) throws MochiException {
    String[] token = input.split(delimiter);
    Command command = Command.getValue(token[0]);
    switch (command) {
      case DELETE:
        deleteTask(token);
        break;
      case MARK:
        markTask(token);
        break;
      case UNMARK:
        unMarkTask(token);
        break;
      case BYE:
        Conversation.response(DialogMessages.BYE.getValue());
        break;
      case LIST:
        taskList.printTaskList();
        break;
      case DEADLINE:
        String d_name = trimStringArrayWithStartEnd(token,"deadline","/by"," ");
        String by = trimStringArrayWithStartEnd(token,"/by",""," ");
        addTask(new Deadline(d_name,by));
        break;
      case EVENT:
        String e_name = trimStringArrayWithStartEnd(token,"event","/from"," ");
        String from = trimStringArrayWithStartEnd(token,"/from","/to"," ");
        String to = trimStringArrayWithStartEnd(token,"/to",""," ");
        addTask(new Event(e_name,from,to));
        break;
      case TODO:
        String t_name = trimStringArrayWithStartEnd(token,"todo",""," ");
        addTask(new Todo(t_name));
        break;
      default:
        throw new MochiException(DialogMessages.INPUT_UNKNOWN.getValue());
    }
  }
}
