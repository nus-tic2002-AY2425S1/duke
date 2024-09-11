import java.util.ArrayList;
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
    ArrayList<String> list = new ArrayList<>();
    Scanner in = new Scanner(System.in);
    String line = "";
    while (!line.equals("bye")) {
      line = in.nextLine();
      switch (line) {
        case "bye":
          response("Bye. Hope to see you again soon!");
          break;
        case "list":
          String r = "";
          int index = 1;
          for (String s : list) {
            if (list.indexOf(s) == list.size()-1)
              r +=  index + ": " + s;
            else
              r +=  index + ": " + s + System.lineSeparator();
            index++;
          }
          response(r);
          break;
        default :
          response("added: " + line);
          list.add(line);
      }
    }
  }
}
