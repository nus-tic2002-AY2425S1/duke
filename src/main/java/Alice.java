import java.util.Scanner;

public class Alice {
    private static Task[] Tasks = new Task[100];
    private static int taskCount = 0;

    public static void printTasks(){
        for (int i = 0; i < taskCount; i++){
            System.out.println((i+1) + "." + Tasks[i].toString());
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
                case "mark":
                    if (instruction.length == 1){
                        System.out.println("Do specify the task number!");
                        break;
                    }

                    System.out.print(separator);
                    Task task_mark = Tasks[Integer.parseInt(instruction[1])-1];
                    task_mark.setDone(true);
                    System.out.println("Nice! I've marked this task as done:)");
                    System.out.println(task_mark.toString());
                    System.out.print(separator);
                    break;
                case "unmark":
                    if (instruction.length == 1){
                        System.out.println("Do specify the task number!");
                        break;
                    }

                    System.out.print(separator);
                    Task task_unmark = Tasks[Integer.parseInt(instruction[1])-1];
                    task_unmark.setDone(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task_unmark.toString());
                    System.out.print(separator);
                    break;
                case "todo":
                    body += instruction[1];
                    while(i < instruction.length) {
                        body += " " + instruction[i];
                        i++;
                    }
                    Tasks[taskCount] = new Todo(body);
                    System.out.print(separator);
                    System.out.println("Got it, I've added this task: \n" + Tasks[taskCount].toString());
                    taskCount++;
                    System.out.print("Now you have " + taskCount + " in the list." + "\n" + separator);

                    break;
                case "deadline":
                    body += instruction[1];
                    while(!instruction[i].equals("/by")){
                        body += " " + instruction[i];
                        i++;
                    }
                    i++;
                    param += instruction[i];
                    i++;
                    while(i < instruction.length) {
                        param += " " + instruction[i];
                        i++;
                    }
                    Tasks[taskCount] = new Deadline(body, param);
                    System.out.print(separator);
                    System.out.println("Got it, I've added this task: \n" + Tasks[taskCount].toString());
                    taskCount++;
                    System.out.print("Now you have " + taskCount + " in the list." + "\n" + separator);

                    break;
                case "event":
                    body += instruction[1];
                    while(!instruction[i].equals("/from")){
                        body += " " + instruction[i];
                        i++;
                    }
                    i++;
                    param += instruction[i];
                    i++;
                    while(!instruction[i].equals("/to")){
                        param += " " + instruction[i];
                        i++;
                    }
                    i++;
                    param2 += instruction[i];
                    i++;
                    while(i < instruction.length) {
                        param2 += " " + instruction[i];
                        i++;
                    }
                    Tasks[taskCount] = new Event(body, param, param2);
                    System.out.print(separator);
                    System.out.println("Got it, I've added this task: \n" + Tasks[taskCount].toString());
                    taskCount++;
                    System.out.print("Now you have " + taskCount + " in the list." + "\n" + separator);

                    break;
                default:


            }

            input = new Scanner(System.in);
            line = input.nextLine();
            instruction = line.split(" ");
        }
        System.out.println(ending);
    }
}
