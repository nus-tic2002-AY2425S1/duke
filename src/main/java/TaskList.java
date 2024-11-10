import java.util.ArrayList;

/**
 * Class to handle list of task
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

   
    public void addTodo(String description) throws RuanException {

        if (description.isBlank()) {
            throw new RuanException(ErrorType.EMPTY_DESCRIPTION);
        }

        //add todo task
        tasks.add(new Todo(description));

        //send task string to be formatted & be printed
        printTaskAdded(tasks.get(tasks.size() - 1));

    }

    public void addDeadline(String description, String by) throws RuanException {

        if (description.isBlank()) {
            throw new RuanException(ErrorType.EMPTY_DESCRIPTION);
        }

        if (by.isBlank()) {
            throw new RuanException(ErrorType.MISSING_DEADLINE);
        }

        //add deadline task
        tasks.add(new Deadline(description, by));

        //send task string to be formatted and be printed
        printTaskAdded(tasks.get(tasks.size() - 1));
        
    }

    public void addEvent(String description, String from, String to) throws RuanException {
        
        if (description.isBlank()) {
            throw new RuanException(ErrorType.EMPTY_DESCRIPTION);
        }

        if (from.isBlank() || to.isBlank()) {
            throw new RuanException(ErrorType.MISSING_EVENT_TIMES);
        }

        //add event task
        tasks.add(new Event(description, from, to));

        //send task string to be formatted and be printed
        printTaskAdded(tasks.get(tasks.size() - 1));

    }


    
    public void addTask(String description){
        
        tasks.add(new Task(description));

        String[] lineOut = new String[100];
        lineOut[0] = "added: " + description;
        //send string array to be printed
        print(lineOut);

    }

    
    public void markTask(int index) throws RuanException {

        if (index < 0 || index >= tasks.size()) {
            throw new RuanException(ErrorType.INVALID_TASK_NUMBER);
        }

        tasks.get(index).markDone();

        String[] lineOut = new String[100];
        lineOut[0] = "Nice! I've marked this task as done:";
        lineOut[1] = "  " + tasks.get(index);
        //send string array to be printed
        print(lineOut);

    }

    
    public void unmarkTask(int index) throws RuanException {

        if (index < 0 || index >= tasks.size()) {
            throw new RuanException(ErrorType.INVALID_TASK_NUMBER);
        }

        tasks.get(index).unmarkDone();

        String[] lineOut = new String[100];
        lineOut[0] = "OK, I've marked this task as not done yet:";
        lineOut[1] = "  " + tasks.get(index);
        //send string array to be printed
        print(lineOut);

    }


    public void deleteTask(int index) throws RuanException {

        if (index < 0 || index >= tasks.size()) {
            throw new RuanException(ErrorType.INVALID_TASK_NUMBER);
        }

        Task removedTask = tasks.remove(index);

        String[] lineOut = new String[100];
        lineOut[0] = "Noted. I've removed this task:";
        lineOut[1] = "  " + removedTask;
        lineOut[2] = "Now you have " + tasks.size() + " tasks in the list.";
        //send string array to be printed
        print(lineOut);

    }

    
    /**
     * Function to print whole task list
     */
    public void printList() {

        if (tasks.isEmpty()) {
            String[] lineOut = new String[100];
            lineOut[0] = "Your task list is currently empty.";
            //send string array to be printed
            print(lineOut);
            return;
        }

        String[] lineOut = new String[100];
        lineOut[0] = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            lineOut[i + 1] = (i + 1) + ". " + tasks.get(i);
        }
        //send string array to be printed
        print(lineOut);

    }

    /**
     * Function to format & print todo, deadline & event tasks
     */
    private void printTaskAdded(Task task) {

        String[] lineOut = new String[100];
        lineOut[0] = "Got it. I've added this task:";
        lineOut[1] = "  " + task;
        lineOut[2] = "Now you have " + tasks.size() + " tasks in the list.";
        //send string array to be printed
        print(lineOut);

    }

    /**
     * Function to print string array with line at top & bottom
     */
    public void print(String[] lineOut){

        //to print the header line
        System.out.println("  -----------------------------------");

        for(String line: lineOut){
            if(line == null){
                //exit this loop when line is null
                break;
            }
            //print each line string in a new line
            System.out.println("  "+line);
        }

        //to print the footer line
        System.out.println("  -----------------------------------");

    }
 
}
