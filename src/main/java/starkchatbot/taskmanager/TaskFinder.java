package starkchatbot.taskmanager;

import starkchatbot.userui.StarkException;

import java.util.ArrayList;

public class TaskFinder {
    private ArrayList<Task> tasks;

    public TaskFinder(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void findTasks(String detail) {
        try{
            System.out.println("Finding tasks for related \"" + detail + "\" : ");
            int counter = 0;
            for (Task task : tasks) {
                if (task.getDescription().contains(detail)) {
                    counter++;
                    System.out.println(counter + ". " + task.printTask());
                }
            }
            System.out.println(System.lineSeparator() + "Found " + counter + " matching tasks.");
        }catch (Exception e) {
            throw new StarkException.InvalidTaskException("Error during finding tasks");
        }
    }
}
