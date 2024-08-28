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
            System.out.println((i+1) + ". " + Tasks[i].getDescription());
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
        while(!line.equals("bye")){
            switch (line) {
                case "list":
                    System.out.print(separator);
                    printTasks();
                    System.out.print(separator);
                    break;
                default:
                    addTask(new Task(line));
                    System.out.print(separator + "added: " + line + "\n" + separator);
            }

            input = new Scanner(System.in);
            line = input.nextLine();
        }
        System.out.println(ending);
    }
}
