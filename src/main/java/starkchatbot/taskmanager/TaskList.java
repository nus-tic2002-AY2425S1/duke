package starkchatbot.taskmanager;

import starkchatbot.userui.StarkException;

import java.util.ArrayList;


/**
 * Represents a list of tasks, providing functionality to add, edit, and print tasks.
 * This class manages a collection of {@link Task} objects, allowing for various operations
 * such as adding new tasks, marking them as done, unmarking, deleting tasks, and printing
 * all tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int totalTasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.totalTasks = tasks.size();
    }


    /**
     * Adds a new task to the TaskList based on the specified type.
     *
     * @param type The type of task to be added (e.g., "Todo", "Deadline", "Event").
     * @param task The description of the task.
     * @throws StarkException.InvalidIndexException If the task format is invalid.
     */
    public void addTask(String type, String task) throws StarkException.InvalidIndexException {
        task = task.replace(type +" ","");       //remove the type of new task from input

        if(type.equalsIgnoreCase("Todo")){
            tasks.add(new Todo(task));
            totalTasks++;
        }else if(type.equalsIgnoreCase("Deadline")){
            String[] str = task.split(" /by ");
            try{
                tasks.add(new Deadline(str[0],str[1]));
            }catch (StarkException.InvalidIndexException e){
                throw new StarkException.InvalidIndexException(" OOPS!!! \"DEADLINE\" tasks need to be done before a specific date/time " +
                                                                System.lineSeparator() +"\t\t \"eg: deadline return book /by 2011-03-11 1700\" ");
            }
            totalTasks++;
        }else if (type.equalsIgnoreCase("Event")){
            String[] str = task.split(" /from ");
            try {
                String action = str[0];
                str = str[1].split(" /to ");
                String startTime = str[0];
                String endTime = str[1];
                tasks.add(new Event(action,startTime,endTime));
            }catch (StarkException.InvalidIndexException e){
                throw new StarkException.InvalidIndexException(" OOPS!!! \"EVENT\" tasks need to start and ends at a specific date/time " +
                                                                System.lineSeparator() + "\t\t \"eg: event project meeting /from 2011-03-11 1700 /to 2011-03-11 1900\" ");
            }


            totalTasks++;
        }
        System.out.println("Got it. I've added this task:\n  " + tasks.get(totalTasks-1).printTask());
        System.out.println("Now you have " + totalTasks + " tasks in the list.");

    }

    /**
     * Prints all tasks in the TaskList to the console, displaying their current status.
     */
    public void printAllTasks() {
        int counter = 0;
        for(Task task : tasks){
            counter++;
            System.out.println(counter + ". " + task.printTask());
        }
    }


    /**
     * Edits a task in the TaskList based on the specified action and task number.
     * The action can be "mark", "unmark", or "delete".
     *
     * @param done   The action to perform on the task (e.g., "mark", "unmark", "delete").
     * @param number The index of the task to edit.
     * @throws StarkException.InvalidIndexException If the task number is invalid or if the list is empty.
     */
    public void editTask(String done, int number) {
        if(tasks.size() >= number && number > 0) {
            Task requiredTask = tasks.get(number - 1);

            if (done.equalsIgnoreCase("mark")) {
                requiredTask.setStatus(done);
                System.out.println("Nice! I've marked this task as done:" + System.lineSeparator()
                                    + "  " + requiredTask.printTask());

            } else if (done.equalsIgnoreCase("unmark")) {
                requiredTask.setStatus(done);
                System.out.println("OK, I've marked this task as not done yet:"+ System.lineSeparator()
                                    + "  " + requiredTask.printTask());

            } else if (done.equalsIgnoreCase("delete")) {
                tasks.remove(number - 1);
                totalTasks--;
                System.out.println("Noted. I've removed this task:" + System.lineSeparator()
                                    + "  " + requiredTask.printTask() + System.lineSeparator()
                                    + "Now you have "+ totalTasks +" tasks in the list");
            }
        }else{
            if(tasks.isEmpty()){
                throw new StarkException.InvalidIndexException("OOPS! Task list is empty!"
                                                                + System.lineSeparator() + " \t\t please add tasks");
            }else {
                throw new StarkException.InvalidIndexException("OOPS! input task number is invalid "
                                                                + System.lineSeparator() +" Valid range is 1 to " + totalTasks);
            }
        }
    }


    /**
     * Returns the list of tasks managed by this TaskList.
     *
     * @return An ArrayList containing all tasks in the TaskList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }


}
