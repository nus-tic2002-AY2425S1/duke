package parser;

import tasks.Task;
import java.util.List;
import static output.OutputHandler.printList;


public class ListCommandHandler implements CommandHandler{
    @Override
    public void handle(String userInput, List<Task> tasks){
        printList(tasks);
    }
}
