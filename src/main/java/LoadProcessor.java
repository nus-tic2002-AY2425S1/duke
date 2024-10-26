public class LoadProcessor {
  private final TaskList taskList;
  public LoadProcessor(TaskList t) {
    this.taskList = t;
  }
  public void addTask(Task task,boolean state) {
    try {
      taskList.addTask(task);
      if (state)
        markTaskById(taskList.getTaskIdByTask(task));
      Conversation.response( " "
        + DialogMessages.TASK_LOADED.getValue()
        + "  " + task.getName()
      );

    }
    catch (MochiException e) {
      Conversation.response(e.getMessage());
    }
    finally {
    }
  }
  public void markTaskById(Integer id) {
    try {
      taskList.markTask(id);
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
        addTask(new Todo(t_name),Boolean.parseBoolean(token[1]));
        break;
      case D:
        String d_name = token[2];
        String by = token[3];
        addTask(new Deadline(d_name,by),Boolean.parseBoolean(token[1]));
        break;
      case E:
        String e_name = token[2];
        String from = token[3];
        String to = token[4];
        addTask(new Event(e_name,from,to),Boolean.parseBoolean(token[1]));
        break;
      default:
        throw new MochiException(DialogMessages.INPUT_UNKNOWN.getValue());
    }
  }
}
