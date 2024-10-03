import java.util.Date;
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
    taskList.addTask(task);
    response(
      " Got it. I've added this task:"
      + System.lineSeparator()
      + "  "+ task.toString()
      + System.lineSeparator()
      + " Now you have " + taskList.getTaskListSize() + " tasks in the list"
    );
  }
  public static String trimStringArrayWithStartEnd(String[] s, String start, String end, String dilimeter) {
    String body = "";
    boolean copy = false;
    for (String i : s) {
      if (!end.isEmpty() && i.equals(end))
        break;
      if(copy) {
        body += i + " ";
      }
      if (i.equals(start))
        copy = true;
    }
    return body.trim();
  }
  public static void main(String[] args) {
    String greating = "____________________________________________________________" + System.lineSeparator()
      + " Hello! I'm " + _name + System.lineSeparator()
      + " What can I do for you?";
    System.out.println(greating);
    Scanner in = new Scanner(System.in);
    boolean bye = false;
    while (!bye) {
      String input = in.nextLine();
      String[] token = input.split(" ");
      String cross = " ";
      int i;
      switch (token[0]) {
        case "mark":
          i = Integer.parseInt(token[1])-1;
          taskList.markTask(i);
          response("Nice! I've marked this task as done:"
            + System.lineSeparator()
            + taskList.getTaskById(i).toString()
          );
          break;
        case "unmark":
          i = Integer.parseInt(token[1])-1;
          taskList.unmarkTask(i);
          response("OK, I've marked this task as not done yet:" + System.lineSeparator() + taskList.getTaskById(i).toString());
          break;
        case "bye":
          response("Bye. Hope to see you again soon!");
          bye = true;
          break;
        case "list":
          taskList.printTaskList();
          break;
        case "deadline":
          String d_name = trimStringArrayWithStartEnd(token,"deadline","/by"," ");
          String by = trimStringArrayWithStartEnd(token,"/by",""," ");
          addTask(new Dateline(d_name,by));
          break;
        case "event":
          String e_name = trimStringArrayWithStartEnd(token,"event","/from"," ");
          String from = trimStringArrayWithStartEnd(token,"/from","/to"," ");
          String to = trimStringArrayWithStartEnd(token,"/to",""," ");
          addTask(new Event(e_name,from,to));
          break;
        case "todo":
          String t_name = trimStringArrayWithStartEnd(token,"todo",""," ");
          addTask(new Todo(t_name));
          break;
        default:
          response("added: " + input);
          addTask(new Task(input));
      }
    }
  }
}

