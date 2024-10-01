import java.util.ArrayList;
import java.util.Scanner;

public class Alice {
    private static ArrayList<Task> Tasks = new ArrayList<>();

    public static void printTasks(){
        for (int i = 0; i < Tasks.size(); i++){
            System.out.println((i+1) + "." + Tasks.get(i).toString());
        }
    }

    public static void main(String[] args) {
        String separator = "____________________________________________________________\n";
        String intro =
                "____________________________________________________________\n" +
                "Hello! I'm Alice, here to make you magically organized!\n" +
                        "How may I help you?\n" +
                "____________________________________________________________";

        String ending =
                "____________________________________________________________\n" +
                "Bye. Back to my wonderland!\n" +
                "____________________________________________________________";

        System.out.println(intro);
        String line;
        Scanner input = new Scanner(System.in);
        line = input.nextLine();
        String[] instruction = line.split(" ");
        while(!instruction[0].equals("bye")){
            String body = "";
            String param = "";
            String param2 = "";
            int i = 2;
            switch (instruction[0]) {
                case "list":
                    System.out.print(separator);
                    System.out.println("Here are the tasks in your list:");
                    printTasks();
                    System.out.print(separator);
                    break;
                case "delete":
                    if (instruction.length == 1){
                        System.out.println("Do specify the task number to delete!");
                        break;
                    }
                    try {
                        String task = Tasks.get(Integer.parseInt(instruction[1]) - 1).toString();
                        System.out.print(separator);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(task);
                        Tasks.remove(Integer.parseInt(instruction[1]) - 1);
                        System.out.print("Now you have " + Tasks.size() + " in the list." + "\n" + separator);

                    }catch (IndexOutOfBoundsException e){
                        System.out.print(separator);
                        System.out.println("Task number not found!");
                        System.out.print(separator);
                        break;
                    }
                    break;
                case "mark":
                    if (instruction.length == 1){
                        System.out.print(separator);
                        System.out.println("Do specify the task number!");
                        System.out.print(separator);
                        break;
                    }

                    System.out.print(separator);
                    Task task_mark = Tasks.get(Integer.parseInt(instruction[1]) - 1);
                    task_mark.setDone(true);
                    System.out.println("Nice! I've marked this task as done:)");
                    System.out.println(task_mark.toString());
                    System.out.print(separator);
                    break;
                case "unmark":
                    if (instruction.length == 1){
                        System.out.print(separator);
                        System.out.println("Do specify the task number!");
                        System.out.print(separator);
                        break;
                    }

                    System.out.print(separator);
                    Task task_unmark = Tasks.get(Integer.parseInt(instruction[1]) - 1);
                    task_unmark.setDone(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task_unmark.toString());
                    System.out.print(separator);
                    break;
                case "todo":
                    if (instruction.length == 1){
                        System.out.print(separator);
                        System.out.println("The description of todo cannot be empty!");
                        System.out.print(separator);
                        break;
                    }
                    body += instruction[1];
                    while(i < instruction.length) {
                        body += " " + instruction[i];
                        i++;
                    }
                    Tasks.add(new Todo(body));
                    System.out.print(separator);
                    System.out.println("Got it, I've added this task: \n" + Tasks.getLast().toString());
                    System.out.print("Now you have " + Tasks.size() + " in the list." + "\n" + separator);

                    break;
                case "deadline":
                    if (instruction.length == 1){
                        System.out.print(separator);
                        System.out.println("The description of deadline cannot be empty!");
                        System.out.print(separator);
                        break;
                    }
                    body += instruction[1];
                    try {
                        while (!instruction[i].equals("/by")) {
                            body += " " + instruction[i];
                            i++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.print(separator);
                        System.out.println("Do include the /by flag!");
                        System.out.print(separator);
                        break;
                    }
                    i++;
                    try {
                        param += instruction[i];
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.print(separator);
                        System.out.println("Do include the day after /by!");
                        System.out.print(separator);
                        break;
                    }

                    i++;
                    while(i < instruction.length) {
                        param += " " + instruction[i];
                        i++;
                    }
                    Tasks.add(new Deadline(body, param));
                    System.out.print(separator);
                    System.out.println("Got it, I've added this task: \n" + Tasks.getLast().toString());
                    System.out.print("Now you have " + Tasks.size() + " in the list." + "\n" + separator);

                    break;
                case "event":
                    body += instruction[1];
                    try {
                        while (!instruction[i].equals("/from")) {
                            body += " " + instruction[i];
                            i++;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.print(separator);
                        System.out.println("Do include the /from flag!");
                        System.out.print(separator);
                        break;
                    }
                    i++;
                    try {
                        param += instruction[i];
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.print(separator);
                        System.out.println("Do include the day after /from!");
                        System.out.print(separator);
                        break;
                    }
                    i++;
                    try {
                        while (!instruction[i].equals("/to")) {
                            param += " " + instruction[i];
                            i++;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.print(separator);
                        System.out.println("Do include the /to flag!");
                        System.out.print(separator);
                        break;
                    }
                    i++;
                    try {
                        param2 += instruction[i];
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.print(separator);
                        System.out.println("Do include the day after /to!");
                        System.out.print(separator);
                        break;
                    }
                    i++;
                    while(i < instruction.length) {
                        param2 += " " + instruction[i];
                        i++;
                    }
                    Tasks.add(new Event(body, param, param2));
                    System.out.print(separator);
                    System.out.println("Got it, I've added this task: \n" + Tasks.getLast().toString());
                    System.out.print("Now you have " + Tasks.size() + " in the list." + "\n" + separator);

                    break;
                default:
                    System.out.print(separator);
                    System.out.println("Please put an instruction I can understand :(");
                    System.out.print(separator);

            }

            input = new Scanner(System.in);
            line = input.nextLine();
            instruction = line.split(" ");
        }
        System.out.println(ending);
    }
}
