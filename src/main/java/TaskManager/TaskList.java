package TaskManager;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private int totalTasks = 0;
    public TaskList() {
    }

    public void addTask(String type, String task) throws StarkException.InvalidIndexException {
        task = task.replace(type +" ","");       //remove the type of new task from input

        if(type.equalsIgnoreCase("Tasks.Todo")){
            tasks.add(new Todo(task));
            totalTasks++;
        }else if(type.equalsIgnoreCase("Tasks.Deadline")){
            String[] str = task.split(" /by ");
            try{
                tasks.add(new Deadline(str[0],str[1]));
            }catch (StarkException.InvalidIndexException e){
                throw new StarkException.InvalidIndexException(" OOPS!!! \"DEADLINE\" tasks need to be done before a specific date/time " +
                        System.lineSeparator() +"\t\t \"eg: deadline return book /by Sunday\" ");
            }
            totalTasks++;
        }else if (type.equalsIgnoreCase("Tasks.Event")){
            String[] str = task.split(" /from ");
            try {
                String action = str[0];
                str = str[1].split(" /to ");
                String startTime = str[0];
                String endTime = str[1];
                tasks.add(new Event(action,startTime,endTime));
            }catch (StarkException.InvalidIndexException e){
                throw new StarkException.InvalidIndexException(" OOPS!!! \"EVENT\" tasks need to start and ends at a specific date/time " +
                        System.lineSeparator() + "\t\t \"eg: event project meeting /from Mon 2pm /to 4pm\" ");
            }


            totalTasks++;
        }
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
                throw new StarkException.InvalidIndexException("OOPS! Task list is empty!"+
                                                     System.lineSeparator() + " \t\t please add tasks");
            }else {
                throw new StarkException.InvalidIndexException("OOPS! input task number is invalid " +
                                                     System.lineSeparator() +" Valid range is 1 to " + totalTasks);
            }
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }


}
