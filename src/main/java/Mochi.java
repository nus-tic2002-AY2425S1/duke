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
    Scanner in = new Scanner(System.in);
    String line = "";
    while (!line.equals("bye")) {
      line = in.nextLine();
      if (!line.equals("bye")) {
        response(line);
      }
      else {
        response("Bye. Hope to see you again soon!");
      }
    }
  }
}
