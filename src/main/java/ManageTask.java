import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class ManageTask {
    private ArrayList<Task> taskList;
    public ManageTask (ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    // add or update the task
    public void addOrUpdateTask(Task newTask){
        assert newTask != null : "newTask cannot be null";
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

    /**
     * Checks if a task with the given name is already exists in the current task list
     * @param taskName the name of the task to check for duplicates
     * @return the first task object which match the task which we plan to add in the list
     *         or return null if no duplicate task is found
     */
    public Task isDuplicate(String taskName) {
        return taskList.stream()
                .filter(task -> task.taskName.equalsIgnoreCase(taskName))
                .findFirst()
                .orElse(null);
    }

    // Method to get the number of tasks completed in the past week
    public ArrayList<Task> getTasksCompletedInPastWeek() {
        ArrayList<Task> completedTasks = new ArrayList<>();
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);

        for (Task task : taskList) {
            if (task.isDone && task.getCompletedDate() != null &&
                    task.getCompletedDate().isAfter(oneWeekAgo)) {
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

    // Add task to the task-list
    public void addTask(Task task){

        assert task != null : "Task can't be null";

        Task duplicateTask = isDuplicate(task.taskName);
        if (duplicateTask != null) {
            // Display the details of the duplicate task
            System.out.println("OPS!!!! This task already exists:");
            System.out.println(" " + duplicateTask);
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

    /**
     * Search for the tasks that contain some specified words within the tasks and
     * display all those task that contain that name. if no task match, a message will be display
     * @param keyword the keyword to search for in a task
     */
    public void findTask(String keyword){

        assert keyword != null : "keyword cannot be null";
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