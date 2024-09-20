package command;

import output.OutputHandler;
import tasks.Task;

import java.util.List;

public class ListCommandHandler implements CommandHandler{
    @Override
    public void handle(String userInput, List<Task> tasks){
        OutputHandler.printList(tasks);
    }
}
