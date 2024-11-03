import General.DukeException;
import General.Task;
import TaskList.TaskList;
import Ui.Ui;
import Storage.Storage;
import Parser.Parser;

import java.io.FileNotFoundException;
import java.util.*;

public class PistaMint {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    public static int length = 50;
    public static String line = "-".repeat(length);
    public static String input = "";
    public static Task[] taskList = new Task[100];
    public static ArrayList<Task> taskArrayList = new ArrayList<Task>();
    public static int itemCount = 0;
    public static String directoryPath = "data";
    public static String filePath = directoryPath + "/duke.txt";
       public PistaMint(String filePath) throws FileNotFoundException {
        storage=new Storage(directoryPath,filePath);
        tasks=new TaskList(storage);
        storage.load();

    }
    /*public static void greetings() {
        System.out.println("\t" + line + "\n\tNi Hao! I'm PistaMint\n\tWhat can I do for you?\n\t" + line);
    }
    public static void exit() {
        System.out.println("\t" + line + "\n\tXie Xie! Hope to see you again soon~\n\t" + line);
    }
    public static void echo(char symbol) {
        if (input.equalsIgnoreCase("bye")) {
            return;
        }
        System.out.println("\n\tGot it! I've added this task ");
        System.out.println("\t" + "[" + symbol + "]" + "[" + taskArrayList.get(itemCount - 1).getStatusIcon() + "] " + taskArrayList.get(itemCount - 1).getDescription());
        System.out.println("\tNow you have " + itemCount + " task(s) in the list.\n\t" + line);
    }*/
    /*public static void printItems() {
        System.out.println("\t" + line);
        for (int i = 0; i < itemCount; i++) {
            System.out.println("\t" + (i + 1) + ".[" + taskArrayList.get(i).getSymbol() + "][" + taskArrayList.get(i).getStatusIcon() + "] " + taskArrayList.get(i).getDescription());
        }
        System.out.println("\t" + line);
    }*/
    /*public static void markTask(String word, int index) {
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
    }*/
    /*public static void removeTask(int index) {
        try {
            Task t= taskArrayList.get(index-1);
            if(t !=null){
                itemCount--;
                System.out.println("\t" + line + "\n\tNoted! I've removed this task \n\t" + "[" +taskArrayList.get(index-1).getSymbol()+ "]" + "[" + taskArrayList.get(index- 1).getStatusIcon() + "] " + taskArrayList.get(index- 1).getDescription());
                System.out.println("\t" + "Now you have "+itemCount+" task(s) in the list.\n\t" + line);
                taskArrayList.remove(t);
            } else{
                System.out.println("\t" + line + "\n\tItem is out of range. You currently only have " + itemCount + " items.\n\t" + line);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t" + line + "\n\tItem is out of range. You currently only have " + itemCount + " items.\n\t" + line);
        }
    }*/
    /*public static void addTask(Task t, boolean fromFile) throws IOException {
        taskArrayList.add(t);
        itemCount++;
        if (!fromFile) {
            appendToFile(filePath,t);
        }
    }*/
    /*public static void processTask(String input, String task) {
        String action, timeline, from, to = "";
        try {
            if (task.equalsIgnoreCase("todo") ) {
                action = input.substring(input.indexOf(" "));
                Todo todo = new Todo(action.trim(), 'T');
                addTask(todo,false);
                echo('T');
            } else {
                String description;
                if (task.equalsIgnoreCase("deadline")) {
                    description= input.substring(input.indexOf(" "), input.indexOf("/by"));
                    timeline = input.substring(input.indexOf("/by") + 3);
                    Deadline deadline = new Deadline(description.trim(), 'D', timeline);
                    addTask(deadline,false);
                    echo('D');
                } else if (task.equalsIgnoreCase("event")) {
                    description= input.substring(input.indexOf(" "), input.indexOf("/from"));
                    from = input.substring(input.indexOf("/from") + 5, input.indexOf("/to"));
                    to = input.substring(input.indexOf("/to") + 3);
                    Event event = new Event(description.trim(), 'E', from, to);
                    addTask(event,false);
                    echo('E');
                }
            }

        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println("\t" + line + "\n\tOOPS!!! The description of " + task + " is incomplete.\n\t" + line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
    /*public static void appendToFile(String filePath, Task t) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        String task= t.getSymbol()+"|"+(t.getStatusIcon().equals("X") ? "1":"0")+"|"+t.getDescription();
        fw.write(System.lineSeparator()+task);
        fw.close();
    }*/

   /* public static void processFile(char task, String mark,String description) throws IOException {
        if(task=='T'){
            Todo todo=new Todo(description,task);
            addTask(todo,true);
        } else if(task=='D'){
            Deadline deadline=new Deadline(description,task);
            addTask(deadline,true);
        } else{
            Event event=new Event(description,task);
            addTask(event,true);
        }
        if(mark.equals("1")){
            taskArrayList.get(taskArrayList.size()-1).markAsDone();
        }
    }*/
    public void run() {
        Ui.showGreeting();
        Scanner scanner=new Scanner(System.in);
        while(!input.equalsIgnoreCase("bye")){
            input = scanner.nextLine();
            Parser.parseCommand(input);
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        Storage storage=new Storage(directoryPath,filePath);
        TaskList taskList = new TaskList(storage);   // Instantiate TaskList
        Parser parser = new Parser(taskList);
        Ui ui = new Ui(taskList);
        new PistaMint("./data/duke.txt").run();
        /*File directory = new File(directoryPath);
        File file = new File(filePath);
        try {
            if (!directory.exists()) {
                System.out.println("Directory does not exist. Creating directory...");
                boolean createDir = directory.mkdir();
                if (!createDir) {
                    System.out.println("Failed to create directory.");
                    return;
                } else{
                    System.out.println("Directory created successfully.");
                }
            }
            // Check if the file exists, if not create it
            if (!file.exists()) {
                System.out.println("File does not exist. Creating file...");
                boolean createFile = file.createNewFile();
                if (!createFile) {
                    System.out.println("Failed to create file.");
                } else {
                    System.out.println("File created successfully.");
                }
            }
            Scanner s= new Scanner(file);
            while (s.hasNext()) {
                String line=s.nextLine();
                char task = (line.split("\\|")[0]).charAt(0);
                String mark = line.split("\\|")[1];
                String description = line.split("\\|")[2];
                processFile(task, mark, description);
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
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
                    } catch (NumberFormatException | IndexOutOfBoundsException e ) {
                        System.out.println("\t" + line+"\n\tYour input might be incorrect / out of range, please input the command 'mark/unmark/' follow by an Integer\n\teg. mark 1 OR unmark 1\n\t" + line);
                    }
                } else if (task.equalsIgnoreCase("todo") || task.equalsIgnoreCase("deadline") || task.equalsIgnoreCase("event")) {
                    processTask(input, task);
                } else if (task.equalsIgnoreCase("bye")) {
                    exit();
                } else if (task.equalsIgnoreCase("delete")){
                    try {
                        removeTask(Integer.parseInt(input.split(" ")[1]));
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("\t" + line+"\n\tYour input is incorrect, please input the command 'delete' follow by an Integer\n\teg.delete 1\n\t" + line);
                    }
                } else {
                    throw new DukeException("\t" + line + "\n\tOOPS!! I'm sorry, but I don't know what that means :(\n\t" + line);
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }


        }*/
    }
}
