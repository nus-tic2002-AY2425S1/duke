package commands;

import root.*;
import tasks.Task;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //ui.showLine(); 
        System.out.println("\tHere are the tasks in your list:"); 

        int index = 1;
        for (Task task : tasks.getTasks()) {
            System.out.println('\t');
            System.out.println(index + ". " + task); 
            index++;
        }

        ui.showLine(); 
    }
}
