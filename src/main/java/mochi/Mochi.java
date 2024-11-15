package mochi;

import mochi.commands.CommandEnum;
import mochi.common.DialogMessages;
import mochi.parsers.InputProcessor;
import mochi.tasks.TaskList;
import mochi.ui.Ui;

import java.util.Scanner;

public class Mochi {
    public static final String NAME = "Mochi";

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

