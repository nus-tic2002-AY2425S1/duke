package mochi;

import mochi.commands.*;
import mochi.common.*;
import mochi.parsers.*;
import mochi.tasks.*;
import mochi.ui.*;

import java.util.Scanner;
public class Mochi {
  public final static String _name = "Mochi";

  public static void main(String[] args) {
    Ui.response(DialogMessages.GREETINGS.getValue());
    TaskList taskList = new TaskList();
    InputProcessor inputProcessor = new InputProcessor(taskList);
    Scanner in = new Scanner(System.in);
    boolean shouldExit = false;
    while (!shouldExit) {
      String input = in.nextLine();
      try {
        inputProcessor.processInput(input);
        if (CommandEnum.getValue(input) == CommandEnum.BYE) {
          shouldExit = true;
        }
      } catch (Exception e) {
        Ui.response(e.getMessage());
      }
    }
  }
}

