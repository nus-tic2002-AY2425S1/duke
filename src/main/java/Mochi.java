import java.util.Scanner;
public class Mochi {
  final static String _name = "Mochi";

  public static void main(String[] args) {
    Ui.response(DialogMessages.GREETINGS.getValue());
    TaskList taskList = new TaskList();
    InputProcessor inputProcessor = new InputProcessor(taskList);
    Scanner in = new Scanner(System.in);
    boolean bye = false;
    while (!bye) {
      String input = in.nextLine();
      try {
        inputProcessor.processInput(input);
        if (CommandEnum.getValue(input) == CommandEnum.BYE) {
          bye = true;
        }
      } catch (Exception e) {
        Ui.response(e.getMessage());
      }
    }
  }
}

