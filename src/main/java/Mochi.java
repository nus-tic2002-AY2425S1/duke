import java.util.Scanner;
public class Mochi {
  final static String _name = "Mochi";
  final static TaskList taskList = new TaskList();

  public static void response(String input) {
    System.out.println("____________________________________________________________");
    System.out.println(input);
    System.out.println("____________________________________________________________");
  }
  public static void addTask(Task task) {
    try {
      taskList.addTask(task);
      response( " "
          + DialogMessages.TASK_ADDED.getValue()
          + System.lineSeparator()
          + "  " + task.toString()
      );
      response(" Now you have " + taskList.getTaskListSize() + " tasks in the list");
    }
    catch (MochiException e) {
      response(e.getMessage());
    }
    finally {
    }
  }
  public static void markTask(String[] token) {
    try {
      int i = Integer.parseInt(token[1]) - 1;
      taskList.markTask(i);
      response(DialogMessages.MARK_TASK.getValue()
        + System.lineSeparator()
        + taskList.getTaskById(i).toString()
      );
    } catch (NumberFormatException e) {
      response(ExceptionMessages.NUMBER_FORMAT_EXCEPTION);
    }
      catch (IndexOutOfBoundsException e) {
      response(ExceptionMessages.TASK_ID_NOT_FOUND);
    }
  }
  public static void deleteTask(String[] token) {
    try {
      int i = Integer.parseInt(token[1]) - 1;
      // Task will get deleted, store into var first
      String tempTask = taskList.getTaskById(i).toString();
      taskList.deleteTask(i);
      response(DialogMessages.DELETE_TASK.getValue()
        + System.lineSeparator()
        + "  " + tempTask
      );
      response(" Now you have " + taskList.getTaskListSize() + " tasks in the list");
    }
    catch (IndexOutOfBoundsException e) {
      response(ExceptionMessages.TASK_ID_NOT_FOUND);
    }
  }
  public static void unMarkTask(String[] token) {
    try {
      int i = Integer.parseInt(token[1]) - 1;
      taskList.unmarkTask(i);
      response(DialogMessages.UNMARK_TASK.getValue()
        + System.lineSeparator()
        + taskList.getTaskById(i).toString()
      );
    } catch (NumberFormatException e) {
      response(ExceptionMessages.NUMBER_FORMAT_EXCEPTION);
    }
    catch (IndexOutOfBoundsException e) {
      response(ExceptionMessages.TASK_ID_NOT_FOUND);
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
      if (i.equals(start))
        copy = true;
    }
    return body.trim();
  }
  public static void main(String[] args) {
    response(DialogMessages.GREETINGS.getValue());
    Scanner in = new Scanner(System.in);
    boolean bye = false;
    while (!bye) {
      String input = in.nextLine();
      String[] token = input.split(" ");
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
          response(DialogMessages.BYE.getValue());
          bye = true;
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
          response(DialogMessages.INPUT_UNKNOWN.getValue());
      }
    }
  }
}

