public class TaskList {
    private Task[] taskList;//declare task array
    private int listCount;//declare count variable

    //initial TaskList
    public TaskList() {
        this.taskList = new Task[100];  //assume a maximum of 100 tasks for now
        this.listCount = 0;
    }

    //add a new task to the task list
    public void addTask(String description) {
        taskList[listCount] = new Task(description);
        String[] lineOut = new String[100];
        lineOut[0] = "added: " + description;
        print(lineOut);
        listCount++;
    }

    //mark a task as done based on its index
    public void markTask(int index) {
        if (index >= 0 && index < listCount) {
            taskList[index].markDone();
            String[] lineOut = new String[100];
            lineOut[0] = "Nice! I've marked this task as done:";
            lineOut[1] = "  " + taskList[index];
            print(lineOut);
        } 
    }

    //mark a task as not done based on its index
    public void unmarkTask(int index) {
        if (index >= 0 && index < listCount) {
            taskList[index].unmarkDone();
            String[] lineOut = new String[100];
            lineOut[0] = "OK, I've marked this task as not done yet:";
            lineOut[1] = "  " + taskList[index];
            print(lineOut);
        }
    }

    //print the list of tasks
    public void printList() {

        String[] lineOut = new String[100];
        //print list msg
        lineOut[0] = "Here are the tasks in your list:";

        int taskNo = 1;
 
        for (Task task : taskList) {
            if(task == null){
                break;//exit this loop and continue
            }

            //print only when there is value
            lineOut[taskNo] = taskNo+". "+task;
            taskNo++;// increase task number
        }
        print(lineOut);
    }


    public void print(String[] lineOut){
        System.out.println("  -----------------------------------");
        for(String line: lineOut){
            if(line == null){
                break;//exit this loop and continue
            }
            System.out.println("  "+line);
        }
        System.out.println("  -----------------------------------");
    }

 
}
