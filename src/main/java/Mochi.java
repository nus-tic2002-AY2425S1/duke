import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Mochi {
  final static String _name = "Mochi";

  public static void main(String[] args) {
    Conversation.response(DialogMessages.GREETINGS.getValue());
    TaskList taskList = new TaskList();
    InputProcessor inputProcessor = new InputProcessor(taskList);
    Scanner in = new Scanner(System.in);
    boolean bye = false;
    while (!bye) {
      String input = in.nextLine();
      try {
        inputProcessor.processInput(input);
        if (Command.getValue(input) == Command.BYE) {
          bye = true;
        }
      } catch (Exception e) {
        Conversation.response(e.getMessage());
      }
    }
  }
}

