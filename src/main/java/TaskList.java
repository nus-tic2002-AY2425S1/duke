import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private int totalTasks = 0;
    public TaskList() {
    }

    public void addTask(String type, String task) {
        task = task.replace(type +" ","");       //remove the type of new task from input

        if(type.equalsIgnoreCase("Todo")){
            tasks.add(new Todo(task));
        }else if(type.equalsIgnoreCase("Deadline")){
            String[] str = task.split(" /by ");
            tasks.add(new Deadline(str[0],str[1]));
        }else if (type.equalsIgnoreCase("Event")){
            String[] str = task.split(" /from ");
            tasks.add(new Event(str[0],str[1]));
        }else{
            tasks.add(new Task(type + task));
        }

        totalTasks++;
        System.out.println("Got it. I've added this task:\n  " + tasks.get(totalTasks-1).printTask());
        System.out.println("Now you have " + totalTasks + " tasks in the list.");

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
