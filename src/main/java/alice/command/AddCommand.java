package alice.command;

import alice.exception.NoArgsException;
import alice.storage.Storage;
import alice.task.*;
import alice.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AddCommand extends Command {

    public AddCommand(String action, String instruction) {
        super(action, instruction);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, NoArgsException {

        if (getInstruction().isEmpty())
            throw new NoArgsException("arguments");

        switch (getAction()) {
            case "todo":
                tasks.add(new Todo(getInstruction()));
                break;
            case "deadline":
                if (!getInstruction().contains(" /by "))
                    throw new NoArgsException("/by");

                ArrayList<String> deadlineInstruction = new ArrayList<>(Arrays.asList(getInstruction().split(" /by ")));
                tasks.add(new Deadline(deadlineInstruction.getFirst(), deadlineInstruction.getLast()));
                break;
            case "event":
                if (!getInstruction().contains(" /from "))
                    throw new NoArgsException("/from");
                else if (!getInstruction().contains(" /to "))
                    throw new NoArgsException("/to");

                ArrayList<String> eventInstruction = new ArrayList<>(Arrays.asList(getInstruction().split(" /from ")));
                ArrayList<String> eventInstruction2 = new ArrayList<>(Arrays.asList(eventInstruction.getLast().split(" /to ")));

                tasks.add(new Event(eventInstruction.getFirst(), eventInstruction2.getFirst(), eventInstruction2.getLast()));
                break;
        }
        System.out.println("Got it, I've added this task: \n" + tasks.getLast().print());
        ui.showSize(tasks.size());

    }
}