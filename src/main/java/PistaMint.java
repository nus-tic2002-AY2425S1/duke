import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PistaMint {
    public static int length = 50;
    public static String line = "-".repeat(length);
    public static String input = "";
    //public static String[] listItems=new String[100];
    public static Task[] taskList = new Task[100];
    public static ArrayList<Task> taskArrayList = new ArrayList<Task>();
    public static int itemCount = 0;

    public static void greetings() {
        System.out.println("\t" + line + "\n\tNi Hao! I'm PistaMint\n\tWhat can I do for you?\n\t" + line);
    }

    public static void exit() {
        System.out.println("\t" + line + "\n\tXie Xie! Hope to see you again soon~\n\t" + line);
    }

    public static void echo(char symbol) {
        if (input.equalsIgnoreCase("bye")) {
            return;
        }
        System.out.println("\t" + line + "\n\tGot it! I've added this task ");

        System.out.println("\t" + "[" + symbol + "]" + "[" + taskArrayList.get(itemCount - 1).getStatusIcon() + "] " + taskArrayList.get(itemCount - 1).getDescription());

        System.out.println("\tNow you have " + itemCount + " task(s) in the list.\n\t" + line);


    }

    public static void addTask(Task t) {
        taskArrayList.add(t);
        itemCount++;
    }
    /*public static Task[] addList(Task t) {
        taskList[itemCount] = t;
        itemCount++;
        return Arrays.copyOf(taskList, itemCount);
    }*/

    public static void printItems() {
        System.out.println("\t" + line);
        for (int i = 0; i < itemCount; i++) {
            System.out.println("\t" + (i + 1) + ".[" + taskArrayList.get(i).getSymbol() + "][" + taskArrayList.get(i).getStatusIcon() + "] " + taskArrayList.get(i).getDescription());
        }
        System.out.println("\t" + line);
    }
    public static void markTask(String word, int index) {
        try {
            if (word.equalsIgnoreCase("mark")) {
                taskArrayList.get(index - 1).markAsDone();
                System.out.println("\t"+line+"\n\tNice! I've marked this task as done:\n\t[" + taskArrayList.get(index-1).getSymbol() + "][" + taskArrayList.get(index - 1).getStatusIcon() + "] " + taskArrayList.get(index - 1).getDescription()+"\n\t"+line);
            } else {
                taskArrayList.get(index - 1).markAsUnDone();
                System.out.println("\t"+line+"\n\tOK, I've marked this task as not done yet!\n\t[" + taskArrayList.get(index - 1).getStatusIcon() + "] " + taskArrayList.get(index - 1).getDescription()+"\n\t"+line);
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("\t" + line + "\n\tItem is out of range. You currently only have " + itemCount + " items.\n\t" + line);
        }
    }

    public static void processTask(String input, String task) {
        String action, timeline, from, to = "";
        try {
            if (task.equalsIgnoreCase("todo")) {
                action = input.substring(input.indexOf(" ") + 1);
                Todo todo = new Todo(action, 'T');
                addTask(todo);
                //addList(todo);
                echo('T');
            } else {
                String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/"));
                if (task.equalsIgnoreCase("deadline")) {
                    timeline = input.substring(input.indexOf("/by") + 3);
                    Deadline deadline = new Deadline(description, 'D', timeline);
                    addTask(deadline);
                    //addList(deadline);
                    echo('D');
                } else if (task.equalsIgnoreCase("event")) {
                    from = input.substring(input.indexOf("/from") + 5, input.indexOf("/to"));
                    to = input.substring(input.indexOf("/to") + 3);
                    Event event = new Event(description, 'E', from, to);
                    addTask(event);
                    //addList(event);
                    echo('E');
                }
            }
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("\t" + line + "\n\tOOPS!!! The description of " + task + " is incomplete.\n\t" + line);
        }
    }
    public static void removeTask(int index) {
        try {
            Task t= taskArrayList.get(index-1);
            if(t !=null){
                itemCount--;
                System.out.println("\t" + line + "\n\tNoted! I've removed this task \n\t" + "[" +taskArrayList.get(index-1).getSymbol()+ "]" + "[" + taskArrayList.get(index- 1).getStatusIcon() + "] " + taskArrayList.get(index- 1).getDescription());
                System.out.println("\t" + "Now you have "+itemCount+" task(s) in the list.\n\t" + line);
                taskArrayList.remove(t);
            }
            else{
                System.out.println("\t" + line + "\n\tItem is out of range. You currently only have " + itemCount + " items.\n\t" + line);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t" + line + "\n\tItem is out of range. You currently only have " + itemCount + " items.\n\t" + line);
        }
    }

    public static void main(String[] args) throws DukeException {
        greetings();
        while (!input.equalsIgnoreCase("bye")) {
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            String task = input.split(" ")[0];
            int number;
            String action, timeline, from, to = "";
            try {
                if (task.equalsIgnoreCase("list")) {
                    printItems();
                } else if (task.equalsIgnoreCase("mark") || task.equalsIgnoreCase("unmark")) {
                    try{
                        markTask(task, Integer.parseInt(input.split(" ")[1]));
                    }
                    catch (NumberFormatException | IndexOutOfBoundsException e ) {
                        System.out.println("\t" + line+"\n\tYour input might be incorrect / out of range, please input the command 'mark/unmark/' follow by an Integer\n\teg. mark 1 OR unmark 1\n\t" + line);
                    }
                } else if (task.equalsIgnoreCase("todo") || task.equalsIgnoreCase("deadline") || task.equalsIgnoreCase("event")) {
                    processTask(input, task);
                } else if (task.equalsIgnoreCase("bye")) {
                    exit();
                } else if (task.equalsIgnoreCase("delete")){
                    try {
                        removeTask(Integer.parseInt(input.split(" ")[1]));
                    }
                    catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("\t" + line+"\n\tYour input is incorrect, please input the command 'delete' follow by an Integer\n\teg.delete 1\n\t" + line);
                    }
                }
                else {
                    throw new DukeException("\t" + line + "\n\tOOPS!! I'm sorry, but I don't know what that means :(\n\t" + line);
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }


        }
    }
}
