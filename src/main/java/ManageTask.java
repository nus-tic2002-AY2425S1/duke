import java.util.ArrayList;
import java.util.stream.Collectors;


public class ManageTask {
    private ArrayList<Task> taskList;
    public ManageTask (ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    // add or update the task
    public void addOrUpdateTask(Task newTask){
        for (int i = 0; i < taskList.size(); i++){
            if (taskList.get(i).taskName.equals(newTask.taskName)){
                taskList.set(i, newTask);
                System.out.println("Got it, I've updated this task:");
                System.out.println(" " + taskList.get(i));
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                SaveAndLoadTask.saveTasks(taskList);
                return;
            }
        }
        addTask(newTask);
    }

    // Add in feature C-DetectDuplicates
    public boolean DuplicateTask(String taskName){
        return taskList.stream().anyMatch(task -> task.taskName.equalsIgnoreCase(taskName));
    }

    // Add task to the task-list
    public void addTask(Task task){
        if (DuplicateTask(task.taskName)){
            System.out.println("This Task already exists: " + task.taskName);
        }
        else{
            taskList.add(task);
            System.out.println("Got it, I've added this task:");
            System.out.println(" " + taskList.get(taskList.size() - 1));
            System.out.println("Now you have " + taskList.size()  + " tasks in the task list.");
            SaveAndLoadTask.saveTasks(taskList);
        }

    }

    // Display the tasks in the task list
    public void displayTaskList() {
        if (taskList.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }

    // Find task with keyword
    public void findTask(String keyword){
        ArrayList<Task> matchTasks = taskList.stream().filter(task -> task.taskName.toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
        if (matchTasks.isEmpty()){
            System.out.println("No task found with the keyword " + keyword);
        }
        else{
            System.out.println("Found " + matchTasks.size() + " tasks with the keyword " + keyword);
            for (int i = 0; i < matchTasks.size(); i++){
                System.out.println((i + 1) + ". " + matchTasks.get(i));
            }
        }
    }

}