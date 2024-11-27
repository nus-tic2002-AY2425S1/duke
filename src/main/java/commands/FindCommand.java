package commands;

import root.*;
import tasks.Task;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();  
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Collect matching tasks
        int index = 1;
        boolean found = false;
        ui.showLine();
        System.out.println("\tHere are the matching tasks in your list:");
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword)) {  
                System.out.println(index + ". " + task);
                found = true;
                index++;
            }
        }
        if (!found) {
            System.out.println("\tNo matching tasks found.");
        }
        ui.showLine();
    }
}

