import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public TaskList() {
    }
    public void addTask(String task) {
        tasks.add(new Task(task));
        System.out.println("added: " + task);
    }
    public void printAllTasks() {
        int counter = 0;
        for(Task task : tasks){
            counter++;
            System.out.println(counter + ". " + task.printTask());
        }
    }

    public void statusChange(String done, int number) {
        if(tasks.size() >= number) {
            Task requiredTask = tasks.get(number - 1);
            requiredTask.setStatus(done);
            if (done.equalsIgnoreCase("mark")) {
                System.out.println("Nice! I've marked this task as done:\n" + "  " + requiredTask.printTask());
            } else if (done.equalsIgnoreCase("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:\n" + "  " + requiredTask.printTask());
            }
        }else{
            System.out.println("No tasks found");
        }
    }


}
