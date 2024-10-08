import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Alice {
    private static TaskList tasks;
    private static Storage storage;

    public static String separator = "____________________________________________________________\n";

    public Alice(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }



    public static void separatorMessage(String message){
        System.out.print(separator);
        System.out.println(message);
        System.out.print(separator);
    }


    public static void run() {

        String intro =
                "____________________________________________________________\nHello! I'm Alice, here to make you magically organized!\nHow may I help you?\n____________________________________________________________";

        String ending =
                """
                        ____________________________________________________________
                        Bye. Back to my wonderland!
                        ____________________________________________________________""";

        System.out.println(intro);
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        String[] instruction = line.split(" ");
        while(!instruction[0].equals("bye")){
            StringBuilder body = new StringBuilder();
            StringBuilder param = new StringBuilder();
            StringBuilder param2 = new StringBuilder();
            int i = 2;
            switch (instruction[0]) {
                case "list":
                    System.out.print(separator);
                    System.out.println("Here are the tasks in your list:");
                    tasks.printTasks();
                    System.out.print(separator);
                    break;
                case "delete":
                    if (instruction.length == 1){
                        separatorMessage("Do specify the task number to delete!");
                        break;
                    }
                    try {
                        String task = tasks.get(Integer.parseInt(instruction[1]) - 1).toString();
                        System.out.print(separator);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(task);
                        tasks.remove(Integer.parseInt(instruction[1]) - 1);
                        System.out.print("Now you have " + tasks.size() + " in the list." + "\n" + separator);

                    }catch (IndexOutOfBoundsException e){
                        separatorMessage("Task number not found!");
                        break;
                    }
                    break;
                case "mark":
                    if (instruction.length == 1){
                        separatorMessage("Do specify the task number!");
                        break;
                    }

                    System.out.print(separator);
                    Task task_mark = tasks.get(Integer.parseInt(instruction[1]) - 1);
                    task_mark.setDone(true);
                    System.out.println("Nice! I've marked this task as done:)");
                    System.out.println(task_mark);
                    System.out.print(separator);
                    break;
                case "unmark":
                    if (instruction.length == 1){
                        separatorMessage("Do specify the task number!");
                        break;
                    }

                    System.out.print(separator);
                    Task task_unmark = tasks.get(Integer.parseInt(instruction[1]) - 1);
                    task_unmark.setDone(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task_unmark);
                    System.out.print(separator);
                    break;
                case "todo":
                    if (instruction.length == 1){
                        separatorMessage("The description of todo cannot be empty!");
                        break;
                    }
                    body.append(instruction[1]);
                    while(i < instruction.length) {
                        body.append(" ").append(instruction[i]);
                        i++;
                    }
                    tasks.add(new Todo(body.toString()));
                    System.out.print(separator);
                    System.out.println("Got it, I've added this task: \n" + tasks.getLast().toString());
                    System.out.print("Now you have " + tasks.size() + " in the list." + "\n" + separator);

                    break;
                case "deadline":
                    if (instruction.length == 1){
                        separatorMessage("The description of deadline cannot be empty!");
                        break;
                    }
                    body.append(instruction[1]);
                    try {
                        while (!instruction[i].equals("/by")) {
                            body.append(" ").append(instruction[i]);
                            i++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e){
                        separatorMessage("Do include the /by flag!");
                        break;
                    }
                    i++;
                    try {
                        param.append(instruction[i]);
                    } catch (ArrayIndexOutOfBoundsException e){
                        separatorMessage("Do include the day after /by!");
                        break;
                    }

                    i++;
                    while(i < instruction.length) {
                        param.append(" ").append(instruction[i]);
                        i++;
                    }
                    tasks.add(new Deadline(body.toString(), param.toString()));
                    System.out.print(separator);
                    System.out.println("Got it, I've added this task: \n" + tasks.getLast().toString());
                    System.out.print("Now you have " + tasks.size() + " in the list." + "\n" + separator);

                    break;
                case "event":
                    body.append(instruction[1]);
                    try {
                        while (!instruction[i].equals("/from")) {
                            body.append(" ").append(instruction[i]);
                            i++;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        separatorMessage("Do include the /from flag!");
                        break;
                    }
                    i++;
                    try {
                        param.append(instruction[i]);
                    }catch (ArrayIndexOutOfBoundsException e){
                        separatorMessage("Do include the day after /from!");
                        break;
                    }
                    i++;
                    try {
                        while (!instruction[i].equals("/to")) {
                            param.append(" ").append(instruction[i]);
                            i++;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        separatorMessage("Do include the /to flag!");
                        break;
                    }
                    i++;
                    try {
                        param2.append(instruction[i]);
                    }catch (ArrayIndexOutOfBoundsException e){
                        separatorMessage("Do include the day after /to!");
                        break;
                    }
                    i++;
                    while(i < instruction.length) {
                        param2.append(" ").append(instruction[i]);
                        i++;
                    }
                    tasks.add(new Event(body.toString(), param.toString(), param2.toString()));
                    System.out.print(separator);
                    System.out.println("Got it, I've added this task: \n" + tasks.getLast().toString());
                    System.out.print("Now you have " + tasks.size() + " in the list." + "\n" + separator);

                    break;
                default:
                    separatorMessage("Please put an instruction I can understand :(");
            }

            input = new Scanner(System.in);
            line = input.nextLine();
            instruction = line.split(" ");
        }
        System.out.println(ending);

    }

    public static void main(String[] args) throws IOException {
        new Alice("data/tasks.txt").run();
        storage.writeToFile("data/tasks.txt", tasks.toString());
    }
}
