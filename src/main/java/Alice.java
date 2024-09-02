import java.util.Scanner;

public class Alice {
    private static Task[] Tasks = new Task[100];
    private static int taskCount = 0;

    public static void addTask(Task t){
        Tasks[taskCount] = t;
        taskCount++;
    }

    public static void printTasks(){
        for (int i = 0; i < taskCount; i++){
            System.out.println((i+1) + "." + Tasks[i].getStatusIcon() + Tasks[i].getDescription());
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
            switch (instruction[0]) {
                case "list":
                    System.out.print(separator);
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
                    System.out.println(task_mark.getStatusIcon() + task_mark.getDescription());
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
                    System.out.println(task_unmark.getStatusIcon() + task_unmark.getDescription());
                    System.out.print(separator);
                    break;
                default:
                    addTask(new Task(line));
                    System.out.print(separator + "added: " + line + "\n" + separator);
            }

            input = new Scanner(System.in);
            line = input.nextLine();
            instruction = line.split(" ");
        }
        System.out.println(ending);
    }
}
