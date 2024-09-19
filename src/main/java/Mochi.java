import java.util.Scanner;
public class Mochi {
  final static String _name = "Mochi";
  public static void response(String input) {
    System.out.println("____________________________________________________________");
    System.out.println(input);
    System.out.println("____________________________________________________________");
  }
  public static void main(String[] args) {
    String greating = "____________________________________________________________" + System.lineSeparator()
      + " Hello! I'm " + _name + System.lineSeparator()
      + " What can I do for you?";
    System.out.println(greating);
    TaskList taskList = new TaskList();

    Scanner in = new Scanner(System.in);
    boolean bye = false;
    while (!bye) {
      String input = in.nextLine();
      String[] token = input.split(" ");
      String cross = " ";
      switch (token[0]) {
        case "mark":
          taskList.markTask(Integer.parseInt(token[1])-1);
          if (taskList.getTaskStatusByIndex(Integer.parseInt(token[1])-1))
            cross = "X";
          response("Nice! I've marked this task as done:" + System.lineSeparator() + " [" + cross + "] " + taskList.getTaskNameByIndex(Integer.parseInt(token[1])-1));
          break;
        case "unmark":
          taskList.unmarkTask(Integer.parseInt(token[1])-1);
          if (taskList.getTaskStatusByIndex(Integer.parseInt(token[1])-1))
            cross = "X";
          response("OK, I've marked this task as not done yet:" + System.lineSeparator() + " [ ] " + taskList.getTaskNameByIndex(Integer.parseInt(token[1])-1));
          break;
        case "bye":
          response("Bye. Hope to see you again soon!");
          bye = true;
          break;
        case "list":
          taskList.printTaskList();
          break;
        default:
          response("added: " + input);
          taskList.addTask(input);
      }
    }
  }
}
