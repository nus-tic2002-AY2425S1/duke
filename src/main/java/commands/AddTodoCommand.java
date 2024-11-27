package commands;

import root.*; 
import java.io.IOException; 
import tasks.*; 


public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n\tCorrect usage: todo <description>");
        }
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        storage.saveTasks(tasks.getTasks());
        //ui.showLine();
        System.out.println("\tGot it. I've added this task:"); 
        System.out.println("\t [T][ ] " + description); 
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list."); 
        ui.showLine();
    }
}


